package dk.sdu.student.mialb21.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
	
	public static void main(String[] args) {
		
		Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
		cfg.setTitle("Asteroids");
		cfg.setWindowSizeLimits(1500, 1000, 4000, 4000);
		cfg.setResizable(true);

		new Lwjgl3Application(new Game(), cfg);
		
	}
	
}
