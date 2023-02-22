package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;

import java.util.ArrayList;

public class EnemyPlayer extends Player {
    protected float controlSpeedAmplifier;
    protected float controlRotateAmplifier;
    protected float controlGeneralAmplifier;

    protected float totalTime;

    public EnemyPlayer(ArrayList<Bullet> bullets) {
        super(bullets);

        this.x = MathUtils.random(0, Game.WIDTH);
        this.y = MathUtils.random(0, Game.HEIGHT);
        this.radians = MathUtils.random();

        this.color = new float[]{1, 0, 0, 1};

        this.controlSpeedAmplifier = MathUtils.random(0.8f,1.2f);
        this.controlRotateAmplifier = MathUtils.random(0.9f,2f);
        this.controlGeneralAmplifier = MathUtils.random(1f,2f);

        this.totalTime = 0;
    }

    protected void addShot() {
        this.bullets.add(new EnemyBullet(this.speed, x, y, radians));
    }

    public void update(float dt) {
        this.totalTime = (totalTime + dt) % 100;

        // random controls
        this.setUp(
                (MathUtils.sin(totalTime * this.controlSpeedAmplifier + MathUtils.random(0f, 2f)) * this.controlGeneralAmplifier) > MathUtils.random(0.5f, this.controlGeneralAmplifier)
                );
        this.setLeft(
                (MathUtils.sin(totalTime * this.controlRotateAmplifier + MathUtils.random(0f, 2f)) * this.controlGeneralAmplifier) > MathUtils.random(0.8f, this.controlGeneralAmplifier)
        );
        this.setRight(
                (MathUtils.sin(totalTime * this.controlRotateAmplifier + MathUtils.random(0f, 2f)) * this.controlGeneralAmplifier) < MathUtils.random(-0.3f, -this.controlGeneralAmplifier)
        );
        this.setShoot(
                (MathUtils.random(0f,100f) > 99)
        );

        // Run normal update
        super.update(dt);
    }
}
