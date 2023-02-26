package dk.sdu.student.mialb21.asteroid;

import com.badlogic.gdx.math.MathUtils;
import dk.sdu.student.mialb21.common.data.Color;
import dk.sdu.student.mialb21.common.data.Entity;
import dk.sdu.student.mialb21.common.data.GameData;
import dk.sdu.student.mialb21.common.data.World;
import dk.sdu.student.mialb21.common.data.entityparts.LifePart;
import dk.sdu.student.mialb21.common.data.entityparts.MovingPart;
import dk.sdu.student.mialb21.common.data.entityparts.PositionPart;
import dk.sdu.student.mialb21.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {

    private Entity asteroid;
    private int life;
    private float deacceleration, acceleration, maxSpeed, rotationSpeed;
    private int shapePointCount;
    private Color color;

    public AsteroidPlugin() {
        this(3);
    }

    public AsteroidPlugin(int life) {
        this.life = life;

        this.deacceleration = 0;
        this.acceleration = 0;
        this.maxSpeed = 400;
        this.rotationSpeed = 0;
        this.color = new Color(1,1,1,1);
        this.shapePointCount = 8;
    }

    @Override
    public void start(GameData gameData, World world) {
        asteroid = createInitialAsteroid(gameData);
        world.addEntity(asteroid);
    }

    public Entity createInitialAsteroid(GameData gameData) {
        float x = MathUtils.random(gameData.getDisplayWidth());
        float y = MathUtils.random(gameData.getDisplayHeight());
        float radians = MathUtils.random(0, (float) (2 * Math.PI));

        float startSpeed = MathUtils.random(25f, 75f);

        Entity asteroid = new Asteroid();
        this.setRadius(asteroid);

        this.buildAsteroid(gameData, asteroid, x, y, radians, startSpeed);

        return asteroid;
    }

    protected void createSplittetAsteroid(GameData gameData, World world, Entity asteroid) {
        world.removeEntity(asteroid);

        PositionPart positionPart = asteroid.getPart(PositionPart.class);
        MovingPart movingPart = asteroid.getPart(MovingPart.class);
        LifePart lifePart = asteroid.getPart(LifePart.class);

        this.life = lifePart.getLife() - 1;

        if (lifePart.getLife() <= 1) {
            return;
        }

        float[] splits = new float[] {(float) ((Math.PI * 1/2)), (float) ((Math.PI * 1/2) * (-1))};

        for (float split : splits) {
            Entity splittetAsteroid = new Asteroid();

            this.setRadius(splittetAsteroid);

            float radians = positionPart.getRadians() + split;

            float bx = (float) MathUtils.cos(radians) * asteroid.getRadius();
            float x = bx + positionPart.getX();
            float by = (float) MathUtils.sin(radians) * asteroid.getRadius();
            float y = by + positionPart.getY();

            float startSpeed = MathUtils.random(movingPart.getSpeed(), 75f);

            this.buildAsteroid(gameData, splittetAsteroid, x, y, radians, startSpeed);

            world.addEntity(splittetAsteroid);
        }
    }

    private void buildAsteroid(GameData gameData, Entity asteroid, float x, float y, float radians, float startSpeed) {
        asteroid.setShapeX(new float[this.shapePointCount]);
        asteroid.setShapeY(new float[this.shapePointCount]);
        asteroid.setColor(this.color);
        asteroid.add(new MovingPart(this.deacceleration, this.acceleration, this.maxSpeed, this.rotationSpeed, startSpeed));
        asteroid.add(new PositionPart(x, y, radians));
        LifePart lifePart = new LifePart(this.life, 0);
        asteroid.add(lifePart);
        this.setRadius(asteroid);
    }

    private void setRadius(Entity asteroid) {
        float radius = 0;
        switch (this.life) {
            case 1:
                radius = 10;
                break;
            case 2:
                radius = 15;
                break;
            case 3:
                radius = 25;
                break;
            default:
                break;
        }
        asteroid.setRadius(radius);
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(asteroid);
    }
}
