package dk.sdu.student.mialb21.defaultenemy;

import dk.sdu.student.mialb21.common.data.Entity;
import dk.sdu.student.mialb21.common.data.GameData;
import dk.sdu.student.mialb21.common.data.World;
import dk.sdu.student.mialb21.common.data.entityparts.MovingPart;
import dk.sdu.student.mialb21.common.data.entityparts.PositionPart;
import dk.sdu.student.mialb21.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {

    private Entity bullet;

    public BulletPlugin() {

    }

    @Override
    public void start(GameData gameData, World world) {
    }
    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy);
    }
}
