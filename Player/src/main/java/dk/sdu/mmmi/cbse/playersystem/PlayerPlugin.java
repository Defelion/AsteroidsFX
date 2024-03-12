package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class PlayerPlugin implements IGamePluginService {

    private Entity player;

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        player = createPlayerShip(gameData);
        Pane menu = gameData.getMenu();
        menu.setVisible(false);
        menu.setDisable(true);
        gameData.setMenu(menu);
        //gameData.setLog(gameData.getLog()+"\nPlayer Created");
        world.addEntity(player);
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

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }

}
