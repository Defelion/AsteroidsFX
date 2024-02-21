import dk.sdu.mmmi.cbse.asteroids.AsteroidPlugin;
import dk.sdu.mmmi.cbse.asteroids.AsteroidProcessor;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module asteroids {
    requires Common;
    requires CommonAsteroids;
    provides IGamePluginService with AsteroidPlugin;
    provides IEntityProcessingService with AsteroidProcessor;
}