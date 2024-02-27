package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroids;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.asteroids.AsteroidPlugin;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;


public class AsteroidProcessor implements IEntityProcessingService {
    //AsteroidPlugin AP = new AsteroidPlugin();
    @Override
    public void process(GameData gameData, World world) {
        //if (world.getEntities(Asteroids.class).isEmpty()) AP.start(gameData, world);
        for(Entity asteroid : world.getEntities(Asteroids.class)) {
            asteroid.setX(asteroid.getX() + Math.cos(Math.toRadians(asteroid.getRotation())) * asteroid.getSpeed());
            asteroid.setY(asteroid.getY() + Math.sin(Math.toRadians(asteroid.getRotation())) * asteroid.getSpeed());

            if(asteroid.getHealth() < 0) world.removeEntity(asteroid);

            if (asteroid.getX() < 0) { asteroid.setX(gameData.getDisplayWidth()); }
            if (asteroid.getX() > gameData.getDisplayWidth()) { asteroid.setX(0); }
            if (asteroid.getY() < 0) { asteroid.setY(gameData.getDisplayHeight()); }
            if (asteroid.getY() > gameData.getDisplayHeight()) { asteroid.setY(0); }
        }
    }
}