package dk.sdu.student.mialb21.defaultplayer;

import dk.sdu.student.mialb21.common.data.GameData;
import dk.sdu.student.mialb21.common.data.World;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PlayerPluginTest {
    private static GameData mockedGameData;
    private static World mockedWorld;
    private static PlayerPlugin playerPlugin;

    @BeforeAll
    static void setUp() {
        mockedGameData = mock(GameData.class);
        mockedWorld = mock(World.class);
        playerPlugin = new PlayerPlugin();
    }

    @Test
    @Order(1)
    void start() {
        playerPlugin.start(mockedGameData, mockedWorld);

        verify(mockedWorld).addEntity(any(Player.class));
    }

    @Test
    @Order(2)
    void stop() {
        playerPlugin.stop(mockedGameData, mockedWorld);

        verify(mockedWorld).removeEntity(any(Player.class));
    }
}