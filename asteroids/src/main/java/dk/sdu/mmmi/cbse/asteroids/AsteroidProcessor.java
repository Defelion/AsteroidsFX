package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.asteroids.AsteroidPlugin;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;


public class AsteroidProcessor implements IEntityProcessingService {
    AsteroidPlugin AP = new AsteroidPlugin();
    @Override
    public void process(GameData gameData, World world) {
        if (world.getEntities(Asteroid.class).isEmpty()) AP.start(gameData, world);
        for(Entity astroid : world.getEntities(Asteroid.class)) {
            astroid.setX(astroid.getX() + Math.cos(Math.toRadians(astroid.getRotation())) * astroid.getSpeed());
            astroid.setY(astroid.getY() + Math.sin(Math.toRadians(astroid.getRotation())) * astroid.getSpeed());

            if(astroid.getHealth() < 0) world.removeEntity(astroid);

            if (astroid.getX() < 0) { astroid.setX(gameData.getDisplayWidth()); }
            if (astroid.getX() > gameData.getDisplayWidth()) { astroid.setX(0); }
            if (astroid.getY() < 0) { astroid.setY(gameData.getDisplayHeight()); }
            if (astroid.getY() > gameData.getDisplayHeight()) { astroid.setY(0); }
        }
    }
}