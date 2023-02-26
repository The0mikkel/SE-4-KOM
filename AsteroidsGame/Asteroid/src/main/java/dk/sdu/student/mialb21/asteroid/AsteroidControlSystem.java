package dk.sdu.student.mialb21.asteroid;

import dk.sdu.student.mialb21.common.data.Entity;
import dk.sdu.student.mialb21.common.data.GameData;
import dk.sdu.student.mialb21.common.data.World;
import dk.sdu.student.mialb21.common.data.entityparts.LifePart;
import dk.sdu.student.mialb21.common.data.entityparts.MovingPart;
import dk.sdu.student.mialb21.common.data.entityparts.PositionPart;
import dk.sdu.student.mialb21.common.services.IEntityProcessingService;

public class AsteroidControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);
            LifePart lifePart = asteroid.getPart(LifePart.class);

            this.handleAsteroidSplitting(gameData, world, asteroid);

            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);
            lifePart.process(gameData, asteroid);

            if (lifePart.isDead()) {
                world.removeEntity(asteroid);
            }

            this.updateShape(asteroid);
        }
    }

    private void handleAsteroidSplitting(GameData gameData, World world, Entity asteroid) {
        // Get parts
        LifePart lifePart = asteroid.getPart(LifePart.class);

        // Do not continue if not hit or already dead
        if (!lifePart.isIsHit() || lifePart.isDead()) {
            return;
        }

        // Create new asteroids through plugin
        AsteroidPlugin asteroidPlugin = new AsteroidPlugin();
        asteroidPlugin.createSplittetAsteroid(gameData, world, asteroid);

        return;
    }

    private void updateShape(Entity entity) {
        float[] shapeX = entity.getShapeX();
        float[] shapeY = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);

        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        LifePart lifePart = entity.getPart(LifePart.class);

        float[] distances = new float[8];
        switch (lifePart.getLife()) {
            default:
            case 1:
                distances = new float[] {10, 8, 10, 6, 2, 10, 9, 10};
                break;
            case 2:
                distances = new float[] {18, 5, 15, 10, 18, 18, 15, 18};
                break;
            case 3:
                distances = new float[] {25, 20, 23, 21, 26, 18, 25, 25};
                break;
        }

        for (int i = 0; i < 8; i++) {
            shapeX[i] = (float) (x + Math.cos(radians + Math.PI * (i / 4f)) * distances[i]);
            shapeY[i] = (float) (y + Math.sin(radians + Math.PI * (i / 4f)) * distances[i]);
        }

        entity.setShapeX(shapeX);
        entity.setShapeY(shapeY);
    }
}












