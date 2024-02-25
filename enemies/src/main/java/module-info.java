import dk.sdu.mmmi.cbse.common.enemies.EnemiesControlSystem;
import dk.sdu.mmmi.cbse.common.enemies.EnemiesPlugin;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module enemies {
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;
    requires Player;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IEntityProcessingService with EnemiesControlSystem;
    provides IGamePluginService with EnemiesPlugin;
}