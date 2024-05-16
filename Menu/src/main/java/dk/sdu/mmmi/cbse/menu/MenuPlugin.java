package dk.sdu.mmmi.cbse.menu;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * The type Menu plugin.
 */
public class MenuPlugin implements IGamePluginService {
    /**
     * @param gameData
     * @param world
     */
    @Override
    public void start(GameData gameData, World world) {
        gameData.setGameOver(true);
        Pane menu = gameData.getMenu();
        menu.setTranslateY(gameData.getDisplayHeight()/2);
        menu.setTranslateX(gameData.getDisplayWidth()/2);
        menu.setId("Main");
        TextField userName = new TextField();
        userName.setText("USER");
        userName.setId("username");
        userName.deselect();
        Button Start = new Button();
        Start.setId("StartBTN");
        Start.setText("Start (E)");
        EventHandler<MouseEvent> startEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                gameData.setGameOver(false);
                gameData.setDestroyedEnemies(0);
                gameData.setDestroyedAsteroids(0);
                for (Entity entity : world.getEntities()) {
                    entity.setDead(true);
                }
                gameData.getMenu().setDisable(true);
                gameData.getMenu().setVisible(false);
                TextField UserName = (TextField) menu.lookup("#username");
                gameData.setPlayerName(UserName.getText());
            }
        };

        //Start.setOnAction(startEvent);
        Start.setOnMouseClicked(startEvent);
        Button Exit = new Button();
        Exit.setText("Exit (Q)");
        Exit.setId("ExitBTN");
        EventHandler<MouseEvent> exitEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                javafx.application.Platform.exit();
            }
        };
        Exit.setOnMouseClicked(exitEvent);
        menu.setPrefSize(90,95);
        menu.setStyle("-fx-background-radius: 6; " +
                "-fx-background-color: white; " +
                "-fx-background-insets: 0, 0 1 1 0;");
        userName.setTranslateX(5);
        userName.setTranslateY(5);
        userName.setPrefWidth(80);
        Start.setTranslateX(5);
        Start.setTranslateY(35);
        Start.setPrefWidth(80);
        Exit.setTranslateX(5);
        Exit.setTranslateY(65);
        Exit.setPrefWidth(80);
        if(menu.getChildren().contains(userName)) menu.getChildren().remove(userName);
        if(menu.getChildren().contains(Start)) menu.getChildren().remove(Start);
        if(menu.getChildren().contains(Exit)) menu.getChildren().remove(Exit);
        menu.getChildren().add(userName);
        menu.getChildren().add(Start);
        menu.getChildren().add(Exit);
        /*Pane TotalMenu = gameData.getMenu();
        TotalMenu.getChildren().add(menu);
        gameData.setMenu(TotalMenu);*/
        gameData.setMenu(menu);
    }

    /**
     * @param gameData
     * @param world
     */
    @Override
    public void stop(GameData gameData, World world) {

    }
}
