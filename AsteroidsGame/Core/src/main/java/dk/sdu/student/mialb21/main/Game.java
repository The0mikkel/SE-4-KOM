package dk.sdu.student.mialb21.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.student.mialb21.common.data.Color;
import dk.sdu.student.mialb21.common.services.IPostEntityProcessingService;
import dk.sdu.student.mialb21.common.util.SPILocator;
import dk.sdu.student.mialb21.components.IProcessor;
import dk.sdu.student.mialb21.components.PluginInjection;
import dk.sdu.student.mialb21.managers.GameInputProcessor;
import dk.sdu.student.mialb21.common.data.Entity;
import dk.sdu.student.mialb21.common.data.GameData;
import dk.sdu.student.mialb21.common.data.World;
import dk.sdu.student.mialb21.common.services.IEntityProcessingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("game")
public class Game
        implements ApplicationListener {

    private AnnotationConfigApplicationContext components;

    private static OrthographicCamera cam;
    private ShapeRenderer sr;

    private final GameData gameData = new GameData();
    private World world = new World();

    public Game() {
        this.components = new AnnotationConfigApplicationContext();
        this.components.scan("dk.sdu.student.mialb21.components");
        this.components.refresh();
    }

    @Override
    public void create() {
        sr = new ShapeRenderer();

        if (
                gameData.getDisplayWidth() != Gdx.graphics.getWidth()
                || gameData.getDisplayHeight() != Gdx.graphics.getHeight()
        ) {
            this.updateCam(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        Gdx.input.setInputProcessor(
            new GameInputProcessor(gameData)
        );

        ((PluginInjection) components.getBean("pluginInjector")).startPlugins(gameData, world);
    }

    @Override
    public void render() {
        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());

        update();

        draw();

        gameData.getKeys().update();
    }

    private void updateCam(int width, int height) {
        gameData.setDisplayWidth(width);
        gameData.setDisplayHeight(height);

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate((float) gameData.getDisplayWidth() / 2, (float) gameData.getDisplayHeight() / 2);
        cam.update();
    }

    private void update() {
        ((IProcessor) components.getBean("processorInjector")).process(gameData, world);
        ((IProcessor) components.getBean("postProcessorInjector")).process(gameData, world);
    }

    private void draw() {
        for (Entity entity : world.getEntities()) {

            Color color = entity.getColor();
            sr.setColor(color.getR(), color.getG(), color.getB(), color.getA());

            sr.begin(ShapeRenderer.ShapeType.Line);

            float[] shapex = entity.getShapeX();
            float[] shapey = entity.getShapeY();

            for (int i = 0, j = shapex.length - 1;
                    i < shapex.length;
                    j = i++) {

                sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
            }

            sr.end();
        }
    }

    @Override
    public void resize(int width, int height) {
        this.updateCam(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return SPILocator.locateAll(IEntityProcessingService.class);
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return SPILocator.locateAll(IPostEntityProcessingService.class);
    }
}
