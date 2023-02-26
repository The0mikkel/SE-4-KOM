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
         * Process entity after all ordinary processing.<br />
         * This can be for collision detection or similar elements, that needs to be processed after all entities has been processed.
         * <br />
         * Pre-condition: A game tick has passed since last call and all entities has been processed.<br />
         * Post-condition: The entity has been processed and updated.
         * 
         * @param gameData Data for the game
         * @param world World of the game
         * 
         * @see GameData
         * @see World
         */
        void process(GameData gameData, World world);
}
