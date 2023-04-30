package dk.sdu.student.mialb21.collision;

import dk.sdu.student.mialb21.common.data.Entity;
import dk.sdu.student.mialb21.common.data.GameData;
import dk.sdu.student.mialb21.common.data.World;
import dk.sdu.student.mialb21.common.data.entityparts.LifePart;
import dk.sdu.student.mialb21.common.data.entityparts.PositionPart;
import dk.sdu.student.mialb21.common.services.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity hitterEntity : world.getEntities()) {
            for (Entity collidedEntity : world.getEntities()) {
                if (hitterEntity.getID().equals(collidedEntity.getID())) {
                    continue;
                }

                LifePart hitterEntityLifePart = hitterEntity.getPart(LifePart.class);

                if (
                        hitterEntityLifePart.getLife() > 0
                                && this.collides(hitterEntity, collidedEntity
                        )) {
                    hitterEntityLifePart.setIsHit(true);
                }
            }
        }
    }

    private boolean collides(Entity hitterEntity, Entity collidedEntity) {
        // Get data for collision detection
        PositionPart hitterPositionPart = hitterEntity.getPart(PositionPart.class);
        PositionPart collidedPositionPart = collidedEntity.getPart(PositionPart.class);

        return collides(
                hitterPositionPart.getX(),
                hitterPositionPart.getY(),
                hitterEntity.getRadius(),
                collidedPositionPart.getX(),
                collidedPositionPart.getY(),
                collidedEntity.getRadius()
        );
    }

    public boolean collides(float hitterX, float hitterY, float hitterR, float collidedX, float collidedY, float collidedR) {
        float dx = hitterX - collidedX;
        float dy = hitterY - collidedY;
        float distanceBetween = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        // Check if distance is less than the two radiuses, meaning that they are hitting each other
        float collisionDistance = hitterR + collidedR;
        return distanceBetween < collisionDistance;
    }
}
