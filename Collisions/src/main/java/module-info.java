import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.damagecol.damageControlSystem;

module Collisions {
    requires Common;
    requires javafx.graphics;
    provides IPostEntityProcessingService with damageControlSystem;
}