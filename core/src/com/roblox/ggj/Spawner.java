package com.roblox.ggj;

import com.badlogic.gdx.utils.Timer;

import java.util.Random;

/**
 * Created by ben on 21/01/17.
 */

public class Spawner {
    private ObstacleFactory factory;
    private float spawnAux = 0;
    private Random rand;
    private int min = 0;
    private int max = 2;
    private float spawnDelay = 3.f;
    private float time = 0.f;
    private final double rate = 0.0017f;

    public Spawner(ObstacleFactory factory) {
        this.factory = factory;
        rand = new Random();
    }

    private void spawn(){
        int randomNum = rand.nextInt((max - min) + 1) + min;
        switch(randomNum){
            case 0:
                factory.createObstacle(ObstacleType.AUDITOR);
                break;
            case 1:
                factory.createObstacle(ObstacleType.MEDIA);
                break;
            case 2:
                factory.createObstacle(ObstacleType.ACTIVIST);
                break;
        }
    }

    public void updateSpawn(float delta) {
        if (spawnAux >= spawnDelay) {
            spawnAux = 0;
            spawn();
        } else {
            spawnAux += delta;
        }

        time += delta;
        spawnDelay = (float)(5.f * Math.exp(-Math.pow(rate * time, 2.f / 5.f)));
    }
}
