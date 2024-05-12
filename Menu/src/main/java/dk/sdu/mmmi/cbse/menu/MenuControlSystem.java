package dk.sdu.mmmi.cbse.menu;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import javafx.application.Platform;

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
