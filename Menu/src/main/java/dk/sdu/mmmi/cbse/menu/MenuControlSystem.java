package dk.sdu.mmmi.cbse.menu;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

/**
 * The type Menu control system.
 */
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
            menu.toFront();
            menu.setTranslateX((gameData.getDisplayWidth()/2)-(menu.getPrefWidth()/2));
            menu.setTranslateY((gameData.getDisplayHeight()/2)-(menu.getPrefWidth()/2));
            gameData.setLog("");
            gameData.setLog(gameData.getLog()+"\nresolution:"+gameData.getDisplayWidth()+"x"+gameData.getDisplayHeight());
            gameData.setMenu(menu);
        }
    }
}
