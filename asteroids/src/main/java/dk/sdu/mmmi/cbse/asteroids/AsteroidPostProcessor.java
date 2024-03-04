package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroids;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class AsteroidPostProcessor implements IPostEntityProcessingService {
    /**
     * @param gameData
     * @param world
     */
    @Override
    public void process(GameData gameData, World world) {
        for(Entity asteroid : world.getEntities(Asteroids.class)) {
            if(asteroid.getHealth() <= 0) {
                gameData.setDestroydAsteroids(gameData.getDestroydAsteroids()+1);
                gameData.setScore(gameData.getScore()+asteroid.getSize());
                asteroid.setDead(true);
            }
        }
    }
}
