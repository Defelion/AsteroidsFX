package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * The interface Bullet processing service.
 */
public interface IBulletProcessingService {

    /**
     * Process.
     *
     * @param gameData the game data
     * @param world    the world
     * @throws
     */
    void process(GameData gameData, World world);
}
