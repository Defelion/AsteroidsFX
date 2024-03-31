package dk.sdu.mmmi.cbse.menu;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MenuControlSystem implements IEntityProcessingService {
    /**
     * @param gameData
     * @param world
     */
    @Override
    public void process(GameData gameData, World world) {
        if(!gameData.getMenu().isDisabled()) {
            if(!gameData.getMenu().isDisabled() && gameData.getKeys().isDown(GameKeys.Q)) {
                Platform.exit();
            }
            Pane menu = gameData.getMenu();
            
            Button Start = new Button();
            Start.setId("StartBTN");
            Start.setText("Start (E)");
            EventHandler<MouseEvent> startEvent = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    gameData.setDestroyedEnemies(0);
                    gameData.setDestroydAsteroids(0);
                    for (Entity entity : world.getEntities()) {
                        entity.setDead(true);
                    }
                    gameData.getMenu().setDisable(true);
                    gameData.getMenu().setVisible(false);
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
            menu.setPrefSize(90,70);
            menu.setBackground(Background.fill(Color.LIGHTGRAY));
            Exit.setTranslateX(5);
            Exit.setTranslateY(35);
            Exit.setPrefWidth(80);
            Start.setTranslateX(5);
            Start.setTranslateY(5);
            Start.setPrefWidth(80);
            if(menu.getChildren().contains(Start)) {
                menu.getChildren().remove(Start);
                menu.getChildren().remove(Exit);
            }
            menu.getChildren().add(Start);
            menu.getChildren().add(Exit);
            gameData.setMenu(menu);
        }
    }
}
