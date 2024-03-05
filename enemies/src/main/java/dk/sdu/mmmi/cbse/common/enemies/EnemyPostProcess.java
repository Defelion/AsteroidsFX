package dk.sdu.mmmi.cbse.common.enemies;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class EnemyPostProcess implements IPostEntityProcessingService {
    /**
     * @param gameData
     * @param world
     */
    @Override
    public void process(GameData gameData, World world) {
        if(world.getEntities(Enemy.class).size() < 1) {
            EnemiesPlugin enemiesPlugin = new EnemiesPlugin();
            enemiesPlugin.start(gameData, world);
        }
    }
}
