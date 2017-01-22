package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by ben on 20/01/17.
 */

public class ObstaclePool {
    private List<Obstacle> obstacles;
    private Stage stage;
    private Rectangle screen;

    public ObstaclePool(Stage stage){
        this.stage = stage;
        obstacles = new ArrayList<Obstacle>();
        screen = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() + 1);
    }

    public void addObstacle(Obstacle obstacle){
        obstacles.add(obstacle);
        stage.addActor(obstacle);
    }

    public void detectCollisions(TrumpActor trump){
        Iterator<Obstacle> iterator = obstacles.iterator();
        while(iterator.hasNext()) {
            Obstacle obstacle = iterator.next();
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
                obstacle.kill();
                iterator.remove();
                continue;
            }

            if(!screen.overlaps(obstacle.getSprite().getBoundingRectangle())) {
                obstacle.remove();
                iterator.remove();
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
