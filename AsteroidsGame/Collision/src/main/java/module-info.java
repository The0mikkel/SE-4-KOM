import dk.sdu.student.mialb21.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;

    provides IPostEntityProcessingService with dk.sdu.student.mialb21.collision.CollisionDetector;
}