package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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
    private boolean soundPlayed = false;
    private float width1, width2;

    public GameOverScreen(App app) {
        this.app = app;
        politicalBS = Gdx.audio.newSound(Gdx.files.internal("bullshit.wav"));

        GlyphLayout layout = new GlyphLayout();
        layout.setText(app.getBigFont(), "You didn't become");
        width1 = layout.width;

        layout.setText(app.getBigFont(), "president.");
        width2 = layout.width;
    }

    @Override
    public void show() {
        stage = new Stage();
        stage.addActor(new Actor());

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
        app.getBigFont().draw(stage.getBatch(), "You didn't become",
                (Gdx.graphics.getWidth() - width1) / 2.f, 60.f * App.getPPU());
        app.getBigFont().draw(stage.getBatch(), "president.",
                (Gdx.graphics.getWidth() - width2) / 2.f, 50.f * App.getPPU());
        stage.getBatch().end();

        while(!soundPlayed)
            soundPlayed = (politicalBS.play(1.f) != -1);
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
