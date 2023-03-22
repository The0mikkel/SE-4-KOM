package dk.sdu.student.mialb21.asteroid;

import java.lang.Math;
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
        for (int i = 0; i < Math.floor(Math.random() * 15) + 5; i++) {
            asteroid = createInitialAsteroid(gameData);
            world.addEntity(asteroid);
        }
    }

    /**
     * Create initial asteroid
     * <br />
     * Pre-condition: New asteroid has to be created with default data. Game is running <br />
     * Post-condition: Asteroid entity, that is set with initial default data.
     *
     * @param gameData Data for the
     *
     * @return Asteroid entity, to be added to the world
     */
    public Entity createInitialAsteroid(GameData gameData) {
        float x = (float) (Math.random() * gameData.getDisplayWidth());
        float y = (float) (Math.random() * gameData.getDisplayHeight());
        float radians = (float) (Math.random() * (2 * Math.PI));

        float startSpeed = (float) (Math.random() * 50f) + 25f;

        Entity asteroid = new Asteroid();
        this.setAsteroidRadius(asteroid);

        this.buildAsteroid(gameData, asteroid, x, y, radians, startSpeed);

        return asteroid;
    }

    /**
     * Create splittet asteroid, that bases some data points on previous asteroids
     * <br />
     * Pre-condition: Asteroid that is started and has to be splittet, and game is running <br />
     * Post-condition: Splittet asteroid added to the game world
     *
     * @param gameData Data for the game
     * @param world World of the game
     * @param asteroid Asteroid to be splittet
     */
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

            this.setAsteroidRadius(splittetAsteroid);

            float radians = positionPart.getRadians() + split;

            float bx = (float) Math.cos(radians) * asteroid.getRadius();
            float x = bx + positionPart.getX();
            float by = (float) Math.sin(radians) * asteroid.getRadius();
            float y = by + positionPart.getY();

            float currentSpeed = movingPart.getSpeed();
            float startSpeed = (float) ((Math.random() * (75f - currentSpeed)) + currentSpeed);

            this.buildAsteroid(gameData, splittetAsteroid, x, y, radians, startSpeed);

            world.addEntity(splittetAsteroid);
        }
    }

    /**
     * Build parts for asteroid
     * <br />
     * Pre-condition: Asteroid that has not yet had parts added, or been build, and has to have parts added <br />
     * Post-condition: Build asteroid with needed parts added
     *
     * @param gameData Data for the game
     * @param asteroid World of the game
     * @param x Start x position
     * @param y Start y position
     * @param radians Start radians
     * @param startSpeed Start speed
     */
    private void buildAsteroid(GameData gameData, Entity asteroid, float x, float y, float radians, float startSpeed) {
        asteroid.setShapeX(new float[this.shapePointCount]);
        asteroid.setShapeY(new float[this.shapePointCount]);
        asteroid.setColor(this.color);
        asteroid.add(new MovingPart(this.deacceleration, this.acceleration, this.maxSpeed, this.rotationSpeed, startSpeed));
        asteroid.add(new PositionPart(x, y, radians));
        LifePart lifePart = new LifePart(this.life, 0);
        asteroid.add(lifePart);
        this.setAsteroidRadius(asteroid);
    }

    /**
     * Set radius of asteroid, based on its life
     * <br />
     * Pre-condition: Asteroid entity<br />
     * Post-condition: Asteroid entity with correct radius
     *
     * @param asteroid Asteroid entity to have its radius updated
     */
    private void setAsteroidRadius(Entity asteroid) {
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
