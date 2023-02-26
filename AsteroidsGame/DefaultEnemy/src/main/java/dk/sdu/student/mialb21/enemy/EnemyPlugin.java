package dk.sdu.student.mialb21.enemy;

import com.badlogic.gdx.math.MathUtils;
import dk.sdu.student.mialb21.common.data.Color;
import dk.sdu.student.mialb21.common.data.Entity;
import dk.sdu.student.mialb21.common.data.GameData;
import dk.sdu.student.mialb21.common.data.World;
import dk.sdu.student.mialb21.common.data.entityparts.LifePart;
import dk.sdu.student.mialb21.common.data.entityparts.MovingPart;
import dk.sdu.student.mialb21.common.data.entityparts.PositionPart;
import dk.sdu.student.mialb21.common.data.entityparts.ShootingPart;
import dk.sdu.student.mialb21.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;

    public EnemyPlugin() {

    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        enemy = createPlayerShip(gameData);
        world.addEntity(enemy);
    }

    private Entity createPlayerShip(GameData gameData) {

        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 300;
        float rotationSpeed = 5;
        float x = MathUtils.random(0, gameData.getDisplayWidth());
        float y = MathUtils.random(0, gameData.getDisplayHeight());
        float radians = MathUtils.random(0f, (float) (2 * Math.PI));

        Entity enemyShip = new Enemy();

        enemyShip.setRadius(10);

        enemyShip.setShapeX(new float[14]);
        enemyShip.setShapeY(new float[14]);
        enemyShip.setColor(new Color(1,0,0,1));
        enemyShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed, 50));
        enemyShip.add(new PositionPart(x, y, radians));
        enemyShip.add(new LifePart(1, 0));
        enemyShip.add(new ShootingPart(0.2f));

        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy);
    }
}
