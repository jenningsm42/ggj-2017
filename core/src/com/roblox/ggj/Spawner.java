package com.roblox.ggj;

import com.badlogic.gdx.utils.Timer;

/**
 * Created by ben on 21/01/17.
 */

public class Spawner {
    private ObstacleFactory factory;
    private float spawnAccumulater = 0;

    public Spawner(ObstacleFactory factory) {
        this.factory = factory;
    }

    public void startSpawningEnemies(float delta){
        
        startSpawningEnemies();
    }

    public void startSpawningEnemies(){
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {

            }
        } float delaySeconds, float intervalSeconds);
    }

}