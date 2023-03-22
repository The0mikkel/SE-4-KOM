import dk.sdu.student.mialb21.common.services.IEntityProcessingService;
import dk.sdu.student.mialb21.common.services.IGamePluginService;

module DefaultBullet {
    requires Common;
    provides IGamePluginService with dk.sdu.student.mialb21.bullet.BulletPlugin;
    provides IEntityProcessingService with dk.sdu.student.mialb21.bullet.BulletControlSystem;
}