package com.roblox.ggj;

import java.util.HashSet;

/**
 * Created by ben on 20/01/17.
 */

public class ObstaclePool {
    HashSet<Obstacle> obstacles;

    public ObstaclePool(){
        obstacles = new HashSet<Obstacle>();
    }

    public void addObstacle(Obstacle obstacle){
        obstacles.add(obstacle);
    }

    private void removeObstacle(Obstacle obstacle){
        obstacles.remove(obstacle);
    }

    public Obstacle detectCollisions(Obstacle trump){
        for(Obstacle obstacle : obstacles){
            if(obstacle.hasCollision(trump)){
                return obstacle;
            }
        }
        return null;
    }
}
