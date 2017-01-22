package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by KaseiFox on 1/20/17.
 */

public class GameScreen implements Screen {
    private App app;
    private Stage stage;
    private ProjectilePool projectilePool;
    private ProjectileFactory projectileFactory;
    private ObstaclePool obstaclePool;
    private ObstacleFactory obstacleFactory;
    private Spawner spawner;
    private Joystick joystick;
    private ShootButtonActor button;

    private TrumpActor trump;

    public GameScreen(App app) {
        this.app = app;
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        obstaclePool = new ObstaclePool(stage);
        projectilePool = new ProjectilePool(stage, obstaclePool);

        projectileFactory = new ProjectileFactory(projectilePool);
        trump = new TrumpActor(projectileFactory, app);
        obstacleFactory = new ObstacleFactory(obstaclePool, projectileFactory, trump);
        spawner = new Spawner(obstacleFactory);
        joystick = new Joystick(trump);
        button = new ShootButtonActor(projectileFactory, trump);

        stage.addActor(trump);
        stage.addActor(joystick);
        stage.addActor(button);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spawner.updateSpawn(delta);

        obstaclePool.detectCollisions(trump);
        projectilePool.detectCollisions(trump);

        stage.act(delta);
        stage.draw();

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        String moneyStr = "Money: " + (trump.getMoney() < 0? "-$" : "$") + numberFormat.format(Math.abs(trump.getMoney()));
        String votesStr = "Votes: " + numberFormat.format(trump.getVotes() / 1000) + "K";

        stage.getBatch().begin();
        app.getFont().draw(stage.getBatch(), moneyStr,
            1.f * App.getPPU(),
            Gdx.graphics.getHeight() - 1.f * App.getPPU());
        app.getFont().draw(stage.getBatch(), votesStr,
            1.f * App.getPPU(),
            Gdx.graphics.getHeight() - (1.f * App.getPPU() + 18 * Math.round(App.getScaleRatio())));
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
