import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.menu.MenuControlSystem;
import dk.sdu.mmmi.cbse.menu.MenuPlugin;

module Menu {
    requires Common;
    requires javafx.graphics;
    requires javafx.controls;
    provides IEntityProcessingService with MenuControlSystem;
    provides IGamePluginService with MenuPlugin;
}