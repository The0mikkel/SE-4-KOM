import dk.sdu.student.mialb21.common.services.IEntityProcessingService;
import dk.sdu.student.mialb21.common.services.IGamePluginService;

module DefaultPlayer {
    requires Common;
    provides IGamePluginService with dk.sdu.student.mialb21.defaultplayer.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.student.mialb21.defaultplayer.PlayerControlSystem;
}