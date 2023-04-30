package dk.sdu.student.mialb21.defaultplayer;

import dk.sdu.student.mialb21.common.data.GameData;
import dk.sdu.student.mialb21.common.data.World;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerPluginTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void start() {
        GameData mockedGameData = mock(GameData.class);
        World mockedWorld = mock(World.class);

        PlayerPlugin plugin = new PlayerPlugin();

        System.out.println(verify(mockedWorld).addEntity(new Player()));

        fail();
    }

    @Test
    void stop() {
    }
}