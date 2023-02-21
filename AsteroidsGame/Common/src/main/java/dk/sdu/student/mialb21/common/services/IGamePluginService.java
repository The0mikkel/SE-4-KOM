package dk.sdu.student.mialb21.common.services;

import dk.sdu.student.mialb21.common.data.GameData;
import dk.sdu.student.mialb21.common.data.World;

/**
 * Game plugin service
 */
public interface IGamePluginService {
    /**
     * Start the plugin
     *
     * @param gameData Data for the game
     * @param world World of the game
     */
    void start(GameData gameData, World world);

    /**
     * Stop the plugin
     *
     * @param gameData Data for the game
     * @param world World of the game
     */
    void stop(GameData gameData, World world);
}
