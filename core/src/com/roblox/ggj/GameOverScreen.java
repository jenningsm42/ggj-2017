package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by KaseiFox on 1/22/17.
 */

public class GameOverScreen implements Screen {
    private App app;
    private Stage stage;
    private Sound politicalBS;

    public GameOverScreen(App app) {
        this.app = app;
        politicalBS = Gdx.audio.newSound(Gdx.files.internal("bullshit.wav"));
    }

    @Override
    public void show() {
        stage = new Stage();
        stage.addActor(new Actor());

        politicalBS.play(1.f);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                app.setScreen(new GameScreen(app));
            }
        }, 2.f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        stage.getBatch().begin();
        app.getFont().draw(stage.getBatch(), "You didn't become\npresident", 0, Gdx.graphics.getHeight());
        stage.getBatch().end();
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
