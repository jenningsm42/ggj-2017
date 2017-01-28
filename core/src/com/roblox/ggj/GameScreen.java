package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
    private Stage stageUI;
    private Stage stageForeground;
    private Stage stageObstacles;
    private Stage stageProjectiles;
    private Stage stageBackground;
    private ProjectilePool projectilePool;
    private ProjectileFactory projectileFactory;
    private ObstaclePool obstaclePool;
    private ObstacleFactory obstacleFactory;
    private Spawner spawner;
    private Joystick joystick;
    private ShootButtonActor button;

    private TrumpActor trump;
    private BackgroundActor bg;

    public GameScreen(App app) {
        this.app = app;
    }

    @Override
    public void show() {
        stageUI = new Stage();
        stageForeground = new Stage();
        stageObstacles = new Stage();
        stageProjectiles = new Stage();
        stageBackground = new Stage();
        Gdx.input.setInputProcessor(stageUI);

        obstaclePool = new ObstaclePool(stageObstacles);
        projectilePool = new ProjectilePool(stageProjectiles, obstaclePool);

        projectileFactory = new ProjectileFactory(projectilePool);
        trump = new TrumpActor(projectileFactory, app);
        obstacleFactory = new ObstacleFactory(obstaclePool, projectileFactory, trump);
        spawner = new Spawner(obstacleFactory);

        joystick = new Joystick(trump);
        button = new ShootButtonActor(projectileFactory, trump);

        bg = new BackgroundActor();

        stageBackground.addActor(bg);
        stageForeground.addActor(trump);
        stageUI.addActor(joystick);
        stageUI.addActor(button);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spawner.updateSpawn(delta);

        obstaclePool.detectCollisions(trump);
        projectilePool.detectCollisions(trump);

        stageBackground.act(delta);
        stageProjectiles.act(delta);
        stageObstacles.act(delta);
        stageForeground.act(delta);
        stageUI.act(delta);

        stageBackground.draw();
        stageProjectiles.draw();
        stageObstacles.draw();
        stageForeground.draw();
        stageUI.draw();

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        String moneyStr = "Money: " + (trump.getMoney() < 0? "-$" : "$") + numberFormat.format(Math.abs(trump.getMoney()));
        String votesStr = "Votes: " + numberFormat.format(trump.getVotes() / 1000) + "K";

        stageUI.getBatch().begin();

        if(trump.getMoney() < 100000)
            app.getFont().setColor(Color.RED);
        app.getFont().draw(stageUI.getBatch(), moneyStr,
            1.f * App.getPPU(),
            Gdx.graphics.getHeight() - 1.f * App.getPPU());
        if(trump.getMoney() < 100000)
            app.getFont().setColor(Color.WHITE);

        app.getFont().draw(stageUI.getBatch(), votesStr,
            1.f * App.getPPU(),
            Gdx.graphics.getHeight() - (1.f * App.getPPU() + 18 * Math.round(App.getScaleRatio())));
        stageUI.getBatch().end();
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
