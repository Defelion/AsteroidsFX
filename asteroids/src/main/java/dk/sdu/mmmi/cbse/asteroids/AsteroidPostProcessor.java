package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroids;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class AsteroidPostProcessor implements IPostEntityProcessingService{
    /**
     * @param gameData
     * @param world
     */
    @Override
    public void process(GameData gameData, World world) {
        AsteroidPlugin asteroidPlugin = new AsteroidPlugin();
        if(world.getEntities(Asteroids.class).size() < 1){
            asteroidPlugin.start(gameData,world);
        }
        else {
            for(Entity Asteroid : world.getEntities(Asteroids.class)) {
                if(Asteroid.getHealth() <= 0){
                    //asteroidPlugin.start(gameData, world, Asteroid);
                }
            }
        }
    }
}
