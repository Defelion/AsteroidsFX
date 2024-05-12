package dk.sdu.mmmi.cbse.common.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;


/**
 * The interface Asteroid splitter.
 */
public interface IAsteroidSplitter {
    /**
     * Create split asteroid entity.
     *
     * @param e        the e
     * @param world    the world
     * @param gameData the game data
     * @return the entity
     */
    Entity createSplitAsteroid(Entity e, World world, GameData gameData);
}