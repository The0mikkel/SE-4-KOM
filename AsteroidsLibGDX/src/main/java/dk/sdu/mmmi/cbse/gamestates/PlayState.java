package dk.sdu.mmmi.cbse.gamestates;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.entities.Bullet;
import dk.sdu.mmmi.cbse.entities.EnemyPlayer;
import dk.sdu.mmmi.cbse.entities.Player;
import dk.sdu.mmmi.cbse.managers.GameKeys;
import dk.sdu.mmmi.cbse.managers.GameStateManager;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayState extends GameState {
	
	private ShapeRenderer sr;
	
	private Player player;
	private EnemyPlayer enemy;

	private ArrayList<Bullet> bullets;
	private ArrayList<Bullet> enemyBullets;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		
		this.sr = new ShapeRenderer();

		this.bullets = new ArrayList<Bullet>();
		this.enemyBullets = new ArrayList<Bullet>();

		this.player = new Player(this.bullets);
		this.enemy = new EnemyPlayer(this.enemyBullets);
	}
	
	public void update(float dt) {
		handleInput();
		
		this.player.update(dt);
		this.enemy.update(dt);

		Bullet.bulletsUpdater(bullets, dt);
		Bullet.bulletsUpdater(enemyBullets, dt);
	}
	
	public void draw() {
		this.player.draw(sr);
		this.enemy.draw(sr);

		this.bullets.forEach(bullet -> bullet.draw(sr));
		this.enemyBullets.forEach(bullet -> bullet.draw(sr));
	}
	
	public void handleInput() {
		player.setLeft(GameKeys.isDown(GameKeys.LEFT));
		player.setRight(GameKeys.isDown(GameKeys.RIGHT));
		player.setUp(GameKeys.isDown(GameKeys.UP));
		player.setShoot(GameKeys.isPressed(GameKeys.SPACE));
	}
	
	public void dispose() {}
	
}









