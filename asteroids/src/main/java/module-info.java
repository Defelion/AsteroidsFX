import dk.sdu.mmmi.cbse.asteroids.AsteroidPlugin;
import dk.sdu.mmmi.cbse.asteroids.AsteroidProcessor;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module asteroids {
    requires Common;
    requires CommonAsteroids;
    requires javafx.graphics;
    provides IGamePluginService with AsteroidPlugin;
    provides IEntityProcessingService with AsteroidProcessor;
    //provides IPostEntityProcessingService with AsteroidProcessor;
}