package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * The interface Game plugin service.
 */
public interface IGamePluginService {

    /**
     * Start.
     *
     * @param gameData the game data
     * @param world    the world
     */
    void start(GameData gameData, World world);

    /**
     * Stop.
     *
     * @param gameData the game data
     * @param world    the world
     */
    void stop(GameData gameData, World world);
}
