package dk.sdu.student.mialb21.common.services;

import dk.sdu.student.mialb21.common.data.Entity;
import dk.sdu.student.mialb21.common.data.GameData;
import dk.sdu.student.mialb21.common.data.World;

public interface IBulletCreator {
    /**
     * Create and spawn a bullet, based on an entity as the shooter.
     * <br />
     * Pre-condition: Game running and shooter wants bullet to appear.<br />
     * Post-condition: Bullet entity that is ready to be added to the game world.
     *
     * @param shooter World of the game
     * @param gameData Data for the game
     *
     * @return Bullet Entity
     *
     * @see Entity
     * @see GameData
     */
    Entity create(Entity shooter, GameData gameData);
}
