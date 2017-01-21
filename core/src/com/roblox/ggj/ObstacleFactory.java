package com.roblox.ggj;

import com.badlogic.gdx.Gdx;

import java.util.Random;

/**
 * Created by ben on 21/01/17.
 */

public class ObstacleFactory {
    private ObstaclePool pool;
    private Random rand;
    private float speed = 30.f;

    public ObstacleFactory(ObstaclePool pool) {
        this.pool = pool;
        rand = new Random();
    }

    public void createObstacle(ObstacleType type) {
        Obstacle obstacle;
        switch (type) {
            case AUDITOR:
                obstacle = new Auditor(speed * App.getPPU());
                break;
            case MEDIA:
                obstacle = new Media(speed * App.getPPU());
                break;
            case ACTIVIST:
                obstacle = new Activist(speed * App.getPPU());
                break;
            default:
                return;
        }

        float randX = rand.nextFloat() * Gdx.graphics.getWidth() - obstacle.getSprite().getWidth();
        obstacle.getSprite().setPosition(randX, Gdx.graphics.getHeight());
        pool.addObstacle(obstacle);
    }
}
