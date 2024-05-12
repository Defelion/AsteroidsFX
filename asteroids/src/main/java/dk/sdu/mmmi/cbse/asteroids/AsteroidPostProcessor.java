package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroids;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

/**
 * The type Asteroid post processor.
 */
public class AsteroidPostProcessor implements IPostEntityProcessingService{
    /**
     * @param gameData
     * @param world
     * @author hbp
     */
    @Override
    public void process(GameData gameData, World world) {
        if(world.getEntities(Asteroids.class).size() < 1){
            AsteroidPlugin asteroidPlugin = new AsteroidPlugin();
            asteroidPlugin.start(gameData,world);
        }
        else {
            for(Entity Asteroid : world.getEntities(Asteroids.class)) {
                AsteroidPlugin asteroidPlugin = new AsteroidPlugin();
                if(Asteroid.getHealth() <= 0){
                    for(int i = 0; i < (Asteroid.getSize()/10); i++) {
                        Entity newAst = asteroidPlugin.createAsteroid(Asteroid,gameData);
                        if(newAst != null) world.addEntity(newAst);
                    }
                }
            }
        }
    }
}
