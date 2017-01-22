package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by KaseiFox on 1/21/17.
 */

public class ProjectilePool {
    private ObstaclePool obstaclePool;
    private List<ProjectileActor> projectiles;
    private Stage stage;
    private Rectangle screen;

    public ProjectilePool(Stage stage, ObstaclePool obstaclePool){
        this.stage = stage;
        this.obstaclePool = obstaclePool;
        projectiles = new ArrayList<ProjectileActor>();
        screen = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void addProjectile(ProjectileActor projectile) {
        projectiles.add(projectile);
        stage.addActor(projectile);
    }

    public void removeProjectile(ProjectileActor projectile){
        projectiles.remove(projectile);
        projectile.remove();
    }

    public void detectCollisions(TrumpActor trump){
        Iterator<ProjectileActor> iterator = projectiles.iterator();
        while(iterator.hasNext()){
            ProjectileActor proj = iterator.next();
            if(proj.hasCollision(trump)){
                switch(proj.getType()){
                    case AUDIT_NOTICE:
                        auditCollision(trump);
                        break;
                    case NEWSPAPER:
                        newspaperCollision(trump);
                        break;
                    case SLUR:
                        slurCollision(trump);
                        break;
                    case MONEY:

                }

                if(proj.getType() != ProjectileType.MONEY) {
                    iterator.remove();
                    proj.remove();
                    continue;
                }
            }

            if(!screen.overlaps(proj.getSprite().getBoundingRectangle())) {
                iterator.remove();
                proj.remove();
            }
        }
    }

    private void auditCollision(TrumpActor trump){
        trump.addMoney(-100000);
    }

    private void newspaperCollision(TrumpActor trump){
        trump.addVotes(-250000);
    }

    private void slurCollision(TrumpActor trump) {
        trump.addVotes(-150000);
    }
}
