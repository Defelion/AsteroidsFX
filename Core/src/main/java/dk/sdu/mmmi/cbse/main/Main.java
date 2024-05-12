package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.io.IOException;
import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();


    private static ModuleLayer layer;

    private static ModuleLayer createLayer(String from, String module) {
        var finder = ModuleFinder.of(Paths.get(from));
        var parent = ModuleLayer.boot();
        var cf = parent.configuration().resolve(finder, ModuleFinder.of(), Set.of(module));
        return parent.defineModulesWithOneLoader(cf, ClassLoader.getSystemClassLoader());
    }

    public static void main(String[] args) {
        layer = createLayer(System.getProperty("user.dir") + "/mods-mvn/", "DamageCounter");
        layer = createLayer(System.getProperty("user.dir") + "/mods-mvn/", "Collisions");
        while(layer.modules().iterator().hasNext()){
            System.out.println(layer.modules().iterator().next());
        }
        var services = ServiceLoader.load(layer, IPostEntityProcessingService.class);
        services.stream()
                .map(ServiceLoader.Provider::get)
                .forEach(SplitPackage ->
                        System.out.println(SplitPackage.toString())
                );
        services.stream().close();

        //launch(Main.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        gameData.setDisplayHeight(800);
        gameData.setDisplayWidth(800);
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        BackgroundFill bgFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background bg = new Background(bgFill);
        gameWindow.setBackground(bg);
        gameWindow.autosize();
        Pane menu = gameData.getMenu();
        menu.setId("Menu");
        menu.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        menu.setTranslateX(0);
        menu.setTranslateY(gameWindow.getHeight());
        menu.setVisible(true);
        menu.setDisable(false);
        gameData.setMenu(menu);
        gameWindow.getChildren().add(menu);
        Scene scene = new Scene(gameWindow);
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.E)) {
                gameData.getKeys().setKey(GameKeys.E, true);
            }
            if (event.getCode().equals(KeyCode.A)) {
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.D)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.W)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.Q)) {
                gameData.getKeys().setKey(GameKeys.Q, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, true);
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.E)) {
                gameData.getKeys().setKey(GameKeys.E, false);
            }
            if (event.getCode().equals(KeyCode.Q)) {
                gameData.getKeys().setKey(GameKeys.Q, false);
            }
            if (event.getCode().equals(KeyCode.A)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.D)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.W)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }
        });

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : getPluginServices()) {
            iGamePlugin.start(gameData, world);
        }
        for (Entity entity : world.getEntities()) {
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }

        render();

        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();

    }

    private void render() {
        new AnimationTimer() {
            private long then = 0;

            @Override
            public void handle(long now) {
                update();
                draw();
                gameData.getKeys().update();
            }

        }.start();
    }

    private void update() {

        // Update
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
         for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
            postEntityProcessorService.process(gameData, world);
        }
    }

    private void draw() {
        Text text = new Text(10, 20,
                "Destroyed asteroids: "+gameData.getDestroydAsteroids()+
                        "\nDestroyed enemies: "+gameData.getDestroyedEnemies()+
                        "\nScore: "+gameData.getScore()
                        +"\namount of elements: "+gameWindow.getChildren().size()
        );
        gameData.setDisplayWidth((int) gameWindow.getWidth());
        /*gameData.setLog("");
        gameData.setLog(gameData.getLog()+"\nData: "+gameData.getDisplayWidth()+", Window: "+gameWindow.getWidth());*/
        gameData.setDisplayHeight((int) gameWindow.getHeight());
        text.setFill(Color.WHITE);
        if(gameData.getLog() != "") text.setText(text.getText()+"\nLog:"+gameData.getLog());
        text.setId("text");
        int count = 0;
        for(Node node : gameWindow.getChildren()) {
            if(Objects.equals(node.getId(), "text")) {
                gameWindow.getChildren().remove(node);
                gameWindow.getChildren().add(text);
                count++;
                break;
            }
            if(Objects.equals(node.getId(), "menu")) {
                gameWindow.getChildren().remove(node);
                gameWindow.getChildren().add(gameData.getMenu());
            }
        }
        if(count == 0) gameWindow.getChildren().add(text);
        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            if(entity.getDead()) {
                world.removeEntity(entity);
                gameWindow.getChildren().remove(polygon);
                if(polygon != null) polygons.remove(polygon);
                continue;
            }
            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoordinates());
                polygons.put(entity,polygon);
                gameWindow.getChildren().add(polygon);
            }
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
            polygon.setFill(entity.getPaint());
            //polygon.accessibleTextProperty().setValue(String.valueOf(entity.getHealth()));
        }
    }

    private Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    /*private Collection<? extends IGamePluginService> getPluginServices() {
        // Load services from the default classpath
        Collection<? extends IGamePluginService> defaultServices = ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());

        // Load services from the module layer (assuming Java 9+)
        Collection<? extends IGamePluginService> moduleServices = ModuleLayer.boot().findServices(IGamePluginService.class).stream().toList();

        // Combine services from both sources (consider using Sets for better handling of duplicates)
        List<IGamePluginService> combinedServices = new ArrayList<>(defaultServices);
        combinedServices.addAll(moduleServices);
        return combinedServices;
    }*/

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private static String moduleNameFromJar(Path path) {
        try (FileSystem jarFile = FileSystems.newFileSystem(path, (ClassLoader)null)) {
            String jarfileName = path.getFileName().toString().substring(0, path.getFileName().toString().indexOf("-"));
            //System.out.println(jarfileName);
            return jarfileName;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }
}
