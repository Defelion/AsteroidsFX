import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.score.ScoreSaverProcess;

module CommonScore {
    exports dk.sdu.mmmi.cbse.score;
    requires Common;
    requires static lombok;
    provides IPostEntityProcessingService with ScoreSaverProcess;
}