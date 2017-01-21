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

    public Spawner(ObstacleFactory factory) {
        this.factory = factory;
        rand = new Random();
    }

    private void spawn(){
        int randomNum = rand.nextInt((max - min) + 1) + min;
        switch(randomNum){
            case 0:
                factory.createObstacle(ObstacleType.AUDITER);
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
        if (spawnAux >= 5.f) {
            spawnAux = 0;
            spawn();
        } else {
            spawnAux += delta;
        }
    }
}
