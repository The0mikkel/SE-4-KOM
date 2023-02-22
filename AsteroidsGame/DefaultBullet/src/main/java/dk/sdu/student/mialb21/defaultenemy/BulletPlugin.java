package dk.sdu.student.mialb21.defaultenemy;

import dk.sdu.student.mialb21.common.data.Color;
import dk.sdu.student.mialb21.common.data.Entity;
import dk.sdu.student.mialb21.common.data.GameData;
import dk.sdu.student.mialb21.common.data.World;
import dk.sdu.student.mialb21.common.data.entityparts.LifePart;
import dk.sdu.student.mialb21.common.data.entityparts.MovingPart;
import dk.sdu.student.mialb21.common.data.entityparts.PositionPart;
import dk.sdu.student.mialb21.common.services.IEntityProcessingService;
import dk.sdu.student.mialb21.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {

    private Entity bullet;

    private float x, y, radians, velocity;

    public BulletPlugin(float x, float y, float radians, float velocity) {
        this.x = x;
        this.y = y;
        this.radians = radians;
        this.velocity = velocity;
    }

    @Override
    public void start(GameData gameData, World world) {
        bullet = createBullet(gameData);
        world.addEntity(bullet);
    }

    private Entity createBullet(GameData gameData) {
        float deacceleration = 0;
        float acceleration = 0;
        float maxSpeed = 400;
        float rotationSpeed = 0;
        float x = this.x / 2;
        float y = this.y / 2;
        float radians = this.radians;

        float shootSpeed = 400;

        Entity bullet = new Bullet();

        bullet.setShapeX(new float[2]);
        bullet.setShapeY(new float[2]);
        bullet.setColor(new Color(1,1,1,1));
        bullet.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed, shootSpeed));
        bullet.add(new PositionPart(x, y, radians));
        bullet.add(new LifePart(100, 8));

        return bullet;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(bullet);
    }
}
