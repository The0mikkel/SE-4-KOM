package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class Bullet extends SpaceObject {

    private float lifetime;
    private float lifetimer;

    private boolean remove;

    private float deceleration;

    public Bullet(float x, float y, float radians) {
        this.x = x;
        this.y = y;
        this.radians = radians;

        float speed = 350;
        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;

        width = height = 2;

        this.lifetimer = 0;
        this.lifetime = 3;

        this.deceleration = 50;

        this.color = new float[]{1, 1, 1, 1};
    }

    public boolean shouldRemove() {
        return remove;
    }

    public void update(float dt) {

        float vec = (float) Math.sqrt(dx * dx + dy * dy);
        dx -= (dx / vec) * deceleration * dt;
        dy -= (dy / vec) * deceleration * dt;

        this.x += dx * dt;
        this.y += dy * dt;

        wrap();

        this.lifetimer += dt;

        if (this.lifetimer > this.lifetime) {
            this.remove = true;
        }
    }

    public void draw(ShapeRenderer sr) {
        if (this.color.length >= 4) {
            sr.setColor(this.color[0], this.color[1], this.color[2], this.color[3]);
        } else {
            sr.setColor(1, 1, 1, 1);
        }

        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.circle(this.x - this.width / 2, this.y - this.height / 2, this.width / 2);
        sr.end();
    }

    public static void bulletsUpdater(ArrayList<Bullet> bullets, float dt) {
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();

            bullet.update(dt);

            if (bullet.shouldRemove()) {
                bulletIterator.remove();
            }
        }
    }
}
