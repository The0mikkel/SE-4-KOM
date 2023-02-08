package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.Iterator;

public class EnemyBullet extends Bullet {

    public EnemyBullet(float x, float y, float radians) {
        super(x, y, radians);

        this.color = new float[]{1, 0, 0, 1};
    }
}
