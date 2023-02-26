package dk.sdu.student.mialb21.defaultplayer;

import dk.sdu.student.mialb21.common.data.Entity;
import dk.sdu.student.mialb21.common.data.GameData;
import dk.sdu.student.mialb21.common.data.World;
import dk.sdu.student.mialb21.common.data.entityparts.LifePart;
import dk.sdu.student.mialb21.common.data.entityparts.MovingPart;
import dk.sdu.student.mialb21.common.data.entityparts.PositionPart;
import dk.sdu.student.mialb21.common.data.entityparts.ShootingPart;
import dk.sdu.student.mialb21.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

    private Entity player;

    public PlayerPlugin() {

    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        player = createPlayerShip(gameData);
        world.addEntity(player);
    }

    /**
     * Create player ship entity with default data and parts
     * <br />
     * Pre-condition: New player entity has to be created for the game <br />
     * Post-condition: Player entity, that has default parameters and parts
     *
     * @param gameData Data for the game
     * @return Player entity with default parameters and parts
     */
    private Entity createPlayerShip(GameData gameData) {

        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 300;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
        float radians = 3.1415f / 2;

        Entity playerShip = new Player();

        playerShip.setRadius(8);

        playerShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed, 0));
        playerShip.add(new PositionPart(x, y, radians));
        playerShip.add(new LifePart(1, 0));
        playerShip.add(new ShootingPart(0.2f));

        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }
}
