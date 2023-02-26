package dk.sdu.student.mialb21.enemy;

import com.badlogic.gdx.math.MathUtils;
import dk.sdu.student.mialb21.common.data.Entity;
import dk.sdu.student.mialb21.common.data.GameData;
import dk.sdu.student.mialb21.common.data.GameKeys;
import dk.sdu.student.mialb21.common.data.World;
import dk.sdu.student.mialb21.common.data.entityparts.LifePart;
import dk.sdu.student.mialb21.common.data.entityparts.MovingPart;
import dk.sdu.student.mialb21.common.data.entityparts.PositionPart;
import dk.sdu.student.mialb21.common.data.entityparts.ShootingPart;
import dk.sdu.student.mialb21.common.services.IEntityProcessingService;

public class EnemyControlSystem implements IEntityProcessingService {

    private float totalTime = 0f;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);
            ShootingPart shootingPart = enemy.getPart(ShootingPart.class);
            LifePart lifePart = enemy.getPart(LifePart.class);

            this.totalTime = (this.totalTime + gameData.getDelta()) % 100;

            float controlRotateAmplifier = MathUtils.random(0.1f,2f);
            float controlGeneralAmplifier = MathUtils.random(0.1f,2f);

            movingPart.setLeft(
                    (MathUtils.sin(totalTime * controlRotateAmplifier + MathUtils.random(0f, 2f)) * controlGeneralAmplifier) < MathUtils.random(-0.3f, -controlGeneralAmplifier)
            );
            movingPart.setRight(
                    (MathUtils.sin(totalTime * controlRotateAmplifier + MathUtils.random(0f, 2f)) * controlGeneralAmplifier) > MathUtils.random(0.8f, controlGeneralAmplifier)
            );
            movingPart.setUp(
                    MathUtils.random(0.01f, 1f) > MathUtils.random(0.5f, 1f)
            );

            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);
            shootingPart.process(gameData, enemy);
            lifePart.process(gameData, enemy);

            shootingPart.setShooting(MathUtils.random(0f,1f) > 0.99f);

            if (lifePart.isDead()) {
                world.removeEntity(enemy);
            }

            updateShape(enemy);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = (float) (Math.PI / 2f);

        float[] points = new float[7];
        points[0] = 1;
        points[1] = 2;
        points[2] = 2.2f;
        points[3] = 2.5f;
        points[4] = 2.8f;
        points[5] = 3;
        points[6] = 4;

        float[] distance = new float[7];
        distance[0] = distance[1] = distance[2] = 10;
        distance[3] = 15;
        distance[4] = distance[5] = distance[6] = 10;

        for (int i = 0; i < 7; i++) {
            shapex[i] = (float) (x + Math.cos(radians + Math.PI * (points[i] / 5)) * distance[i]);
            shapey[i] = (float) (y + Math.sin(radians + Math.PI * (points[i] / 5)) * distance[i]);
        }

        for (int i = 0; i < 7; i++) {
            shapex[i + 7] = (float) (x - Math.cos(radians + Math.PI * points[i] / 5) * distance[i]);
            shapey[i + 7] = (float) (y - Math.sin(radians + Math.PI * points[i] / 5) * distance[i]);
        }

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}












