import dk.sdu.student.mialb21.common.services.IEntityProcessingService;
import dk.sdu.student.mialb21.common.services.IGamePluginService;

module Asteroid {
    requires Common;

    provides IGamePluginService with dk.sdu.student.mialb21.asteroid.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.student.mialb21.asteroid.AsteroidControlSystem;
}