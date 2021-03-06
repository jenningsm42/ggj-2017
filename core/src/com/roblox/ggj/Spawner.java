package com.roblox.ggj;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

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
    private Queue<Integer> history;
    private final int historySize = 10;

    public Spawner(ObstacleFactory factory) {
        this.factory = factory;
        rand = new Random();
        history = new ArrayBlockingQueue<Integer>(historySize);
    }

    private void spawn(){
        int randomNum = rand.nextInt((max - min) + 1) + min;
        if(history.size() >= 2 && randomNum == history.peek()) {
            Integer historyArray[] = history.toArray(new Integer[history.size()]);
            if(historyArray[history.size() - 2] == randomNum) {
                if(++randomNum > max) randomNum = min;
            }
        }

        if(history.size() == historySize)
            history.poll();
        history.add(randomNum);

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
