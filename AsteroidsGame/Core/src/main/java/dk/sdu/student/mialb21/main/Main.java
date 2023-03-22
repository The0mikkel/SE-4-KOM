package dk.sdu.student.mialb21.main;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
	
	public static void main(String[] args) {
		
		Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
		cfg.setTitle("Asteroids");
		int size = 1000;
		cfg.setWindowSizeLimits(size,size,size * 2,size * 2);
		cfg.setResizable(true);

		new Lwjgl3Application(new Game(), cfg);
		
	}
	
}
