package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by KaseiFox on 1/20/17.
 */

public class SplashScreen implements Screen {
    private App app;
    private Stage stage;
    private Sound millionDollars;
    private boolean soundPlayed = false;

    private class SplashActor extends Actor {
        private Sprite splash;


        public SplashActor() {
            splash = App.createScaledSprite(new Texture("splash.png"));
            splash.setPosition((Gdx.graphics.getWidth() - splash.getWidth()) / 2.f, (Gdx.graphics.getHeight() - splash.getHeight()) / 2.f);

        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            splash.draw(batch);
        }
    }

    public SplashScreen(App app) {
        this.app = app;
        millionDollars = Gdx.audio.newSound(Gdx.files.internal("million_dollars.wav"));

    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        stage.addActor(new SplashActor());

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

        while(!soundPlayed)
            soundPlayed = (millionDollars.play(1.f) != -1);
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
