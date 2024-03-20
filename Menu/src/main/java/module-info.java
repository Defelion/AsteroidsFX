import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.menu.MenuControlSystem;

module Menu {
    requires Common;
    requires javafx.graphics;
    requires javafx.controls;
    provides IEntityProcessingService with MenuControlSystem;
}