import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.commonweb.ScoreSaverProcess;

module CommonScore {
    exports dk.sdu.mmmi.cbse.commonweb;
    requires Common;
    requires static lombok;
    provides IPostEntityProcessingService with ScoreSaverProcess;
}