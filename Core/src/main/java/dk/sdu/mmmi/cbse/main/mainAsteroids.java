package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * The type Main asteroids.
 */
public class mainAsteroids {
    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();
    private final List<IGamePluginService> gamePluginServices;
    private final List<IEntityProcessingService> entityProcessingServiceList;
    private final List<IPostEntityProcessingService> postEntityProcessingServices;

    /**
     * Instantiates a new Main asteroids.
     *
     * @param gamePluginServices           the game plugin services
     * @param entityProcessingServiceList  the entity processing service list
     * @param postEntityProcessingServices the post entity processing services
     */
    mainAsteroids(List<IGamePluginService> gamePluginServices,
                  List<IEntityProcessingService> entityProcessingServiceList,
                  List<IPostEntityProcessingService> postEntityProcessingServices) {
        this.gamePluginServices = gamePluginServices;
        this.entityProcessingServiceList = entityProcessingServiceList;
        this.postEntityProcessingServices = postEntityProcessingServices;
    }

    /**
     * Start.
     *
     * @param window the window
     */
    public void start(Stage window) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AsteroidsServiceConfig.class);

        for (String beanName : ctx.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }

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
        menu.autosize();
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
        for (IGamePluginService iGamePlugin : getGamePluginServices()) {
            iGamePlugin.start(gameData, world);
        }
        for (Entity entity : world.getEntities()) {
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }

        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();
        render();
    }

    /**
     * Render.
     */
    public void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                draw();
                gameData.getKeys().update();
            }

        }.start();
    }

    private void update() {
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
            postEntityProcessorService.process(gameData, world);
        }
    }

    private void draw() {
        Text text = new Text(10, 20,
                "Destroyed asteroids: " + gameData.getDestroyedAsteroids() +
                        "\nDestroyed enemies: " + gameData.getDestroyedEnemies() +
                        "\nScore: " + gameData.getScore()
                        + "\namount of elements: " + gameWindow.getChildren().size()
        );
        gameData.setDisplayWidth((int) gameWindow.getWidth());
        /*gameData.setLog("");
        gameData.setLog(gameData.getLog()+"\nData: "+gameData.getDisplayWidth()+", Window: "+gameWindow.getWidth());*/
        gameData.setDisplayHeight((int) gameWindow.getHeight());
        text.setFill(Color.WHITE);
        if (gameData.getLog() != "") text.setText(text.getText() + "\nLog:" + gameData.getLog());
        text.setId("text");
        int count = 0;

        for (Node node : gameWindow.getChildren()) {
            if (Objects.equals(node.getId(), "text")) {
                gameWindow.getChildren().remove(node);
                gameWindow.getChildren().add(text);
                count++;
                break;
            }
            if (Objects.equals(node.getId(), "menu")) {
                gameWindow.getChildren().remove(node);
                gameWindow.getChildren().add(gameData.getMenu());
            }
        }
        if (count == 0) gameWindow.getChildren().add(text);
        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            if (entity.getDead()) {
                world.removeEntity(entity);
                gameWindow.getChildren().remove(polygon);
                if (polygon != null) polygons.remove(polygon);
                continue;
            }
            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoordinates());
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
            polygon.setFill(entity.getPaint());
        }
    }

    /**
     * Gets game plugin services.
     *
     * @return the game plugin services
     */
    public List<IGamePluginService> getGamePluginServices() {
        return gamePluginServices;
    }

    /**
     * Gets entity processing services.
     *
     * @return the entity processing services
     */
    public List<IEntityProcessingService> getEntityProcessingServices() {
        return entityProcessingServiceList;
    }

    /**
     * Gets post entity processing services.
     *
     * @return the post entity processing services
     */
    public List<IPostEntityProcessingService> getPostEntityProcessingServices() {
        return postEntityProcessingServices;
    }

}
