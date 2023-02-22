package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;

import java.util.ArrayList;

public class Player extends SpaceObject {
    protected float[] flamex;
    protected float[] flamey;

    protected boolean left;
    protected boolean right;
    protected boolean up;

    protected final int MAX_BULLETS = 4;
    protected boolean shoot;
    protected boolean shooting;
    protected ArrayList<Bullet> bullets;

    protected float maxSpeed;
    protected float acceleration;
    protected float deceleration;
    protected float acceleratingTimer;

    public Player(ArrayList<Bullet> bullets) {

        x = Game.WIDTH / 2;
        y = Game.HEIGHT / 2;

        maxSpeed = 300;
        acceleration = 200;
        deceleration = 10;

        shapex = new float[4];
        shapey = new float[4];

        flamex = new float[3];
        flamey = new float[3];

        radians = MathUtils.PI / 2;
        rotationSpeed = 3;

        this.bullets = bullets;
        this.shoot = false;
        this.shooting = false;

        this.color = new float[]{1, 1, 1, 1};
    }

    protected void setShape() {
        float pi = MathUtils.PI;

        shapex[0] = x + MathUtils.cos(radians) * 8;
        shapey[0] = y + MathUtils.sin(radians) * 8;

        shapex[1] = x + MathUtils.cos(radians - 4 * pi / 5) * 8;
        shapey[1] = y + MathUtils.sin(radians - 4 * pi / 5) * 8;

        shapex[2] = x + MathUtils.cos(radians + pi) * 5;
        shapey[2] = y + MathUtils.sin(radians + pi) * 5;

        shapex[3] = x + MathUtils.cos(radians + 4 * pi / 5) * 8;
        shapey[3] = y + MathUtils.sin(radians + 4 * pi / 5) * 8;
    }

    protected void setFlame() {
        float pi = MathUtils.PI;

        flamex[0] = x + MathUtils.cos(radians - 5 * pi / 6) * 5;
        flamey[0] = y + MathUtils.sin(radians - 5 * pi / 6) * 5;

        flamex[1] = x + MathUtils.cos(radians - pi) * (6 + acceleratingTimer * 50);
        flamey[1] = y + MathUtils.sin(radians - pi) * (6 + acceleratingTimer * 50);

        flamex[2] = x + MathUtils.cos(radians + 5 * pi / 6) * 5;
        flamey[2] = y + MathUtils.sin(radians + 5 * pi / 6) * 5;
    }

    public void setLeft(boolean b) {
        left = b;
    }

    public void setRight(boolean b) {
        right = b;
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void setShoot(boolean b) {
        shoot = b;

        if (!this.shoot) {
            this.shooting = false;
        }
    }

    public void shoot() {
        if (this.bullets.size() >= this.MAX_BULLETS) {
            return;
        }
        if (!this.shoot || this.shooting) {
            return;
        }

        this.shoot = false;
        this.addShot();
    }

    protected void addShot() {
        this.bullets.add(new Bullet(this.speed, x, y, radians));
    }

    public void update(float dt) {
        // turning
        if (left) {
            radians += rotationSpeed * dt;
        } else if (right) {
            radians -= rotationSpeed * dt;
        }

        // accelerating
        if (up) {
            dy += MathUtils.sin(radians) * acceleration * dt;
            dx += MathUtils.cos(radians) * acceleration * dt;
            acceleratingTimer += dt;
            if (acceleratingTimer > 0.1f) {
                acceleratingTimer = 0;
            }
        } else {
            acceleratingTimer = 0;
        }

        // deceleration
        float vec = (float) Math.sqrt(dx * dx + dy * dy);
        if (vec > 0) {
            dx -= (dx / vec) * deceleration * dt;
            dy -= (dy / vec) * deceleration * dt;
        }
        if (vec > maxSpeed) {
            dx = (dx / vec) * maxSpeed;
            dy = (dy / vec) * maxSpeed;
        }

        // set position
        x += dx * dt;
        y += dy * dt;

        // set shape
        setShape();

        // Set flame
        if (up) {
            setFlame();
        }

        // Shoot
        this.shoot();

        // screen wrap
        wrap();
    }

    public void draw(ShapeRenderer sr) {

        if (this.color.length >= 4) {
            sr.setColor(this.color[0], this.color[1], this.color[2], this.color[3]);
        } else {
            sr.setColor(1, 1, 1, 1);
        }

        sr.begin(ShapeType.Line);

        // Draw ship
        for (int i = 0, j = shapex.length - 1;
                i < shapex.length;
                j = i++) {

            sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
        }

        // draw flames
        if (up) {
            for (int i = 0, j = flamex.length - 1;
                 i < flamex.length;
                 j = i++) {

                sr.line(flamex[i], flamey[i], flamex[j], flamey[j]);
            }
        }

        sr.end();

        bullets.forEach((bullet -> bullet.draw(sr)));
    }

}
