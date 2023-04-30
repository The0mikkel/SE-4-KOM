package dk.sdu.student.mialb21.collision;

import dk.sdu.student.mialb21.common.data.Entity;
import dk.sdu.student.mialb21.common.data.GameData;
import dk.sdu.student.mialb21.common.data.World;
import dk.sdu.student.mialb21.common.data.entityparts.LifePart;
import dk.sdu.student.mialb21.common.data.entityparts.PositionPart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CollisionDetectorTest {
    private CollisionDetector collisionDetector;

    @BeforeEach
    void setUp() {
        collisionDetector = new CollisionDetector();
    }

    private LifePart setupMocking(
            World mockedWorld,
            String entity1ID,
            int entity1Life,
            float entity1X,
            float entity1Y,
            float entity1R,
            String entity2ID,
            int entity2Life,
            float entity2X,
            float entity2Y,
            float entity2R
    ) {


        // Entity 1
        Entity entity1 = mock(Entity.class);
        when(entity1.getID()).thenReturn(entity1ID); // ID of entity
        LifePart lifePart1 = mock(LifePart.class);
        PositionPart positionPart1 = mock(PositionPart.class);
        when(entity1.getPart(LifePart.class)).thenReturn(lifePart1);
        when(entity1.getPart(PositionPart.class)).thenReturn(positionPart1);

        // Test specific elements
        when(lifePart1.getLife()).thenReturn(entity1Life); // Life of part
        when(positionPart1.getX()).thenReturn(entity1X); // X position
        when(positionPart1.getY()).thenReturn(entity1Y); // Y position
        when(entity1.getRadius()).thenReturn(entity1R); // Radius

        // Entity 2
        Entity entity2 = mock(Entity.class);
        when(entity2.getID()).thenReturn(entity2ID); // ID of entity
        LifePart lifePart2 = mock(LifePart.class);
        PositionPart positionPart2 = mock(PositionPart.class);
        when(entity2.getPart(LifePart.class)).thenReturn(lifePart2);
        when(entity2.getPart(PositionPart.class)).thenReturn(positionPart2);

        // Test specific elements
        when(lifePart2.getLife()).thenReturn(entity2Life); // Life of part
        when(positionPart2.getX()).thenReturn(entity2X); // X position
        when(positionPart2.getY()).thenReturn(entity2Y); // Y position
        when(entity2.getRadius()).thenReturn(entity2R); // Radius

        List<Entity> entityList = new LinkedList<>();
        entityList.add(entity1);
        entityList.add(entity2);
        when(mockedWorld.getEntities()).thenReturn(entityList);

        return lifePart1;
    }

    @Test
    void processFalseWithLife() {
        GameData mockedGameData = mock(GameData.class);
        World mockedWorld = mock(World.class);

        LifePart lifepart = setupMocking(
                mockedWorld,
                "1",
                1,
                0,
                0,
                1,
                "2",
                1,
                2,
                1,
                1
        );

        collisionDetector.process(mockedGameData, mockedWorld);

        verify(lifepart, never()).setIsHit(anyBoolean());
    }

    @Test
    void processTrueWithLife() {
        GameData mockedGameData = mock(GameData.class);
        World mockedWorld = mock(World.class);

        LifePart lifepart = setupMocking(
                mockedWorld,
                "1",
                1,
                0,
                0,
                2,
                "2",
                1,
                2,
                1,
                2
        );

        collisionDetector.process(mockedGameData, mockedWorld);

        verify(lifepart).setIsHit(true);
    }

    @Test
    void processFalseWithNoLife() {
        GameData mockedGameData = mock(GameData.class);
        World mockedWorld = mock(World.class);

        LifePart lifepart = setupMocking(
                mockedWorld,
                "1",
                0,
                0,
                0,
                1,
                "2",
                0,
                2,
                1,
                1
        );

        collisionDetector.process(mockedGameData, mockedWorld);

        verify(lifepart, never()).setIsHit(anyBoolean());
    }

    @Test
    void processTrueWithNoLife() {
        GameData mockedGameData = mock(GameData.class);
        World mockedWorld = mock(World.class);

        LifePart lifepart = setupMocking(
                mockedWorld,
                "1",
                0,
                0,
                0,
                2,
                "2",
                0,
                2,
                1,
                2
        );

        collisionDetector.process(mockedGameData, mockedWorld);

        verify(lifepart, never()).setIsHit(anyBoolean());
    }

    @Test
    void processSameID() {
        GameData mockedGameData = mock(GameData.class);
        World mockedWorld = mock(World.class);

        LifePart lifepart = setupMocking(
                mockedWorld,
                "1",
                0,
                0,
                0,
                10,
                "1",
                0,
                2,
                1,
                10
        );

        collisionDetector.process(mockedGameData, mockedWorld);

        verify(lifepart, never()).setIsHit(anyBoolean());
    }

    @Test
    void collides() {
        assertTrue(collisionDetector.collides(0, 0, 6, 10, 0, 6));
        assertTrue(collisionDetector.collides(0, 0, 5.000001f, 10, 0, 5.000001f));
        assertTrue(collisionDetector.collides(0, 0, 10, 10, 10, 10));
        assertFalse(collisionDetector.collides(0, 0, 5, 10, 0, 5));
        assertFalse(collisionDetector.collides(0, 0, 5, 10, 10, 5));
    }
}