package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by KaseiFox on 1/20/17.
 */

public class GameScreen implements Screen {
    private App app;
    private Stage stage;
    private ProjectilePool projectilePool;
    private ProjectileFactory projectileFactory;

    public GameScreen(App app) {
        this.app = app;
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        projectilePool = new ProjectilePool(stage);
        projectileFactory = new ProjectileFactory(projectilePool);

        stage.addActor(new TrumpActor(projectileFactory));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
