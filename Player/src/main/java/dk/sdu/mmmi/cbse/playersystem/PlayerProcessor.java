package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.awt.*;

/**
 * The type Player processor.
 */
public class PlayerProcessor implements IPostEntityProcessingService {
    /**
     * @param gameData
     * @param world
     */
    @Override
    public void process(GameData gameData, World world) {
        if(gameData.getMenu().isDisabled() && world.getEntities(Player.class).size() <= 0) {
            gameData.setDestroyedEnemies(0);
            gameData.setDestroyedAsteroids(0);
            for (Entity entity : world.getEntities()) {
                entity.setDead(true);
            }
            PlayerPlugin playerPlugin = new PlayerPlugin();
            playerPlugin.start(gameData, world);
        }
        if (!gameData.getMenu().isDisabled() && gameData.getKeys().isDown(GameKeys.E)) {
            PlayerPlugin playerPlugin = new PlayerPlugin();
            playerPlugin.start(gameData, world);
            Pane menu = gameData.getMenu();
            menu.setVisible(false);
            menu.setDisable(true);
            gameData.setMenu(menu);
        }
    }
}
