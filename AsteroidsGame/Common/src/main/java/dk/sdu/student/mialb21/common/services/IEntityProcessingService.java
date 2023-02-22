package dk.sdu.student.mialb21.common.services;

import dk.sdu.student.mialb21.common.data.GameData;
import dk.sdu.student.mialb21.common.data.World;

/**
 * Entity processing service
 */
public interface IEntityProcessingService {
    /**
     * Process the entity
     *
     * @param gameData Data for the game
     * @param world World of the game
     */
    void process(GameData gameData, World world);
}
