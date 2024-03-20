package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class PlayerProcessor implements IPostEntityProcessingService {
    /**
     * @param gameData
     * @param world
     */
    @Override
    public void process(GameData gameData, World world) {
        if(gameData.getMenu().isDisabled() && world.getEntities(Player.class).size() <= 0) {
            gameData.setDestroyedEnemies(0);
            gameData.setDestroydAsteroids(0);
            for (Entity entity : world.getEntities()) {
                entity.setDead(true);
            }
            PlayerPlugin playerPlugin = new PlayerPlugin();
            playerPlugin.start(gameData, world);
            //world.addEntity(createPlayerShip(gameData));
        }
        if (!gameData.getMenu().isDisabled() && gameData.getKeys().isDown(GameKeys.UP)) {
            PlayerPlugin playerPlugin = new PlayerPlugin();
            playerPlugin.start(gameData, world);
            Pane menu = gameData.getMenu();
            menu.setVisible(false);
            menu.setDisable(true);
        }
    }

    private Entity createPlayerShip(GameData gameData) {
        Entity playerShip = new Player();
        //playerShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        playerShip.setPolygonCoordinates(
                -4,-12,
                12,0,
                -4,12,
                -24,12,
                -12,0,
                -24,-12);
        playerShip.setSize(6);
        playerShip.setDamage(2);
        playerShip.setX(gameData.getDisplayHeight()/2);
        playerShip.setY(gameData.getDisplayWidth()/2);
        playerShip.setSpeed(1);
        playerShip.setShotTimer(0);
        playerShip.setMaxShotTimer(20);
        playerShip.setHealth(100);
        playerShip.setCurrenthealth(playerShip.getHealth());
        Paint paint = Color.BLUE;
        playerShip.setPaint(paint);
        playerShip.setRotationSpeed(2);
        return playerShip;
    }
}
