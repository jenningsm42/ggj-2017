package com.roblox.ggj;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ben on 20/01/17.
 */

public class ObstaclePool {
    private List<Obstacle> obstacles;
    private Stage stage;

    public ObstaclePool(Stage stage){
        this.stage = stage;
        obstacles = new ArrayList<Obstacle>();
    }

    public void addObstacle(Obstacle obstacle){
        obstacles.add(obstacle);
        stage.addActor(obstacle);
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
                    case AUDITOR:
                        auditorCollision(trump);
                        break;
                    case MEDIA:
                        mediaCollision(trump);
                        break;
                }
                removeObstacle(obstacle);
                obstacle.kill();
            }
        }
    }

    private void activistCollision(TrumpActor trump){
        // Lose votes
        trump.addVotes(-50000);
    }

    private void auditorCollision(TrumpActor trump){
        // Lose money
        // Temporarily can't gain money
        trump.addMoney(-100000);
    }

    private void mediaCollision(TrumpActor trump){
        // Lose votes
        trump.addVotes(-100000);
    }
}
