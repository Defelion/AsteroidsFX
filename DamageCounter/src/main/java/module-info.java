import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module DamageCounter {
    requires Common;
    requires javafx.graphics;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.damage.damageControlSystem;
}