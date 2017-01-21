package com.roblox.ggj;

/**
 * Created by ben on 21/01/17.
 */

public class ObstacleFactory {
    private ObstaclePool pool;

    public ObstacleFactory(ObstaclePool pool){
        this.pool = pool;
    }

    public void createObstacle(ObstacleType type){
        Obstacle obstacle = null;
        switch (type){
            case AUDITER:
                obstacle = createAuditer();
                break;
            case MEDIA:
                obstacle = createMedia();
                break;
            case ACTIVIST:
                obstacle = createActivist();
                break;
        }

        if (obstacle == null)
            return;
        else {
            pool.addObstacle(obstacle);
            //might want to add some other functionality in here after
            //adding it to the pool
        }
    }

    private Obstacle createAuditer(){
        Obstacle obstacle = new Auditor();
        return obstacle;
    }

    private Obstacle createMedia(){
        Obstacle obstacle = new Media();
        return obstacle;
    }

    private Obstacle createActivist(){
        Obstacle obstacle = new Activist();
        return obstacle;
    }

}
