package dk.sdu.student.mialb21.common.services;

import dk.sdu.student.mialb21.common.data.GameData;
import dk.sdu.student.mialb21.common.data.World;

/**
 * Entity processing service for after ordinary processing
 *
 * @author jcs
 */
public interface IPostEntityProcessingService  {
        /**
         * Process entity after all ordinary processing.
         * This can be for collision detection or similar elements, that needs to be processed after all entities has been processed.
         *
         * @param gameData Data for the game
         * @param world World of the game
         */
        void process(GameData gameData, World world);
}
