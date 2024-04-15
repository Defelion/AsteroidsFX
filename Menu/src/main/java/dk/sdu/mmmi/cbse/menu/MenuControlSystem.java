package dk.sdu.mmmi.cbse.menu;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
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

import java.util.Objects;

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
            /*for(Node node : gameData.getMenu().getChildren()) {
                if(Objects.equals(node, "Main")){
                    node.setTranslateY((gameData.getDisplayHeight()/2)+(node./2));
                    node.setTranslateX(gameData.getDisplayWidth()/2);
                }
            }*/
        }
    }
}
