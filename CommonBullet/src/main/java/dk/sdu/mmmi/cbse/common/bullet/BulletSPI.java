package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 * The interface Bullet spi.
 *
 * @author corfixen
 */
public interface BulletSPI {
    /**
     * Create bullet entity.
     *
     * @param e        the e
     * @param gameData the game data
     * @return the entity
     */
    Entity createBullet(Entity e, GameData gameData);


}
