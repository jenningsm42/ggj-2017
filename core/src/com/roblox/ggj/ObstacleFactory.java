package com.roblox.ggj;

import com.badlogic.gdx.Gdx;

import java.util.Random;

/**
 * Created by ben on 21/01/17.
 */

public class ObstacleFactory {
    private ObstaclePool pool;
    private ProjectileFactory projectileFactory;
    private TrumpActor trump;
    private Random rand;
    private float speed = 30.f;

    public ObstacleFactory(ObstaclePool pool, ProjectileFactory projectileFactory, TrumpActor trump) {
        this.pool = pool;
        this.projectileFactory = projectileFactory;
        this.trump = trump;
        rand = new Random();
    }

    public void createObstacle(ObstacleType type) {
        Obstacle obstacle;
        switch (type) {
            case AUDITOR:
                obstacle = new Auditor(speed * App.getPPU(), projectileFactory, trump);
                break;
            case MEDIA:
                obstacle = new Media(speed * App.getPPU(), projectileFactory, trump);
                break;
            case ACTIVIST:
                obstacle = new Activist(speed * App.getPPU(), projectileFactory, trump);
                break;
            default:
                return;
        }

        float randX = rand.nextFloat() * Gdx.graphics.getWidth() - obstacle.getSprite().getWidth();
        obstacle.getSprite().setPosition(randX, Gdx.graphics.getHeight());
        obstacle.setCoordinateFields();
        pool.addObstacle(obstacle);
    }
}
