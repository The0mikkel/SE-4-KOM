import dk.sdu.student.mialb21.common.services.IBulletCreator;
import dk.sdu.student.mialb21.common.services.IEntityProcessingService;
import dk.sdu.student.mialb21.common.services.IGamePluginService;
import dk.sdu.student.mialb21.common.util.SPILocator;

module DefaultEnemy {
    requires Common;

    uses SPILocator;
    uses IBulletCreator;

    provides IGamePluginService with dk.sdu.student.mialb21.enemy.EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.student.mialb21.enemy.EnemyControlSystem;
}