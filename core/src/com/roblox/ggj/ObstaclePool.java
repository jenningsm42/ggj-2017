package com.roblox.ggj;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ben on 20/01/17.
 */

public class ObstaclePool {
    List<Obstacle> obstacles;

    public ObstaclePool(){
        obstacles = new ArrayList<Obstacle>();
    }

    public void addObstacle(Obstacle obstacle){
        obstacles.add(obstacle);
    }

    private void removeObstacle(Obstacle obstacle){
        obstacles.remove(obstacle);
    }


    public void detectCollisions(TrumpActor trump){
        for(Obstacle obstacle : obstacles){
            if(obstacle.hasCollision(trump)){
                switch(obstacle.getType()){
                    case ACTIVIST:
                        activistCollision(trump);
                        break;
                    case AUDITER:
                        auditerCollision(trump);
                        break;
                    case MEDIA:
                        mediaCollision(trump);
                        break;
                }
            }
        }
    }

    private void activistCollision(TrumpActor Trump){
        //do collision
    }

    private void auditerCollision(TrumpActor Trump){
        //do collision
    }

    private void mediaCollision(TrumpActor Trump){
        //do collision
    }
}
