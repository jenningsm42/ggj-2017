package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
    private Sound rigged;

    public ProjectilePool(Stage stage, ObstaclePool obstaclePool){
        this.stage = stage;
        this.obstaclePool = obstaclePool;
        projectiles = new ArrayList<ProjectileActor>();
        screen = new Rectangle(-50.f * App.getPPU(), 0,
            Gdx.graphics.getWidth() + 50.f * App.getPPU(), Gdx.graphics.getHeight());
        rigged = Gdx.audio.newSound(Gdx.files.internal("rigged.wav"));

    }

    public void addProjectile(ProjectileActor projectile) {
        projectiles.add(projectile);
        stage.addActor(projectile);
    }

    public void trumpStrikesAgain(TrumpActor trump){
        Iterator<ProjectileActor> iterator = projectiles.iterator();
        while(iterator.hasNext()){
            ProjectileActor proj = iterator.next();
            if(proj.getType() != ProjectileType.MONEY) continue;

            Iterator<Obstacle> obstacleIterator = obstaclePool.getObstacles().iterator();
            while(obstacleIterator.hasNext()) {
                Obstacle obstacle = obstacleIterator.next();
                if(proj.getSprite().getBoundingRectangle().overlaps(
                        obstacle.getSprite().getBoundingRectangle())) {
                    if(obstacle.damage()) {
                        obstacleIterator.remove();
                        switch(obstacle.getType()) {
                            case ACTIVIST:
                                trump.addVotes(50000);
                                break;
                            case MEDIA:
                                trump.addVotes(400000);
                                break;
                            case AUDITOR:
                                trump.addVotes(100000);
                                break;
                        }
                    }
                    proj.remove();
                    iterator.remove();
                    break;
                }
            }
        }
    }

    public void detectCollisions(TrumpActor trump){
        Iterator<ProjectileActor> iterator = projectiles.iterator();
        while(iterator.hasNext()){
            ProjectileActor proj = iterator.next();
            if(proj.getType() != ProjectileType.MONEY && proj.hasCollision(trump)){
                int voteDamageRate = (trump.getMoney() < 100000? 4 : 1);
                switch(proj.getType()){
                    case AUDIT_NOTICE:
                        auditCollision(trump);
                        break;
                    case NEWSPAPER:
                        newspaperCollision(trump, voteDamageRate);
                        break;
                    case SLUR:
                        slurCollision(trump, voteDamageRate);
                        break;
                }

                iterator.remove();
                proj.remove();
                continue;
            }

            if(!screen.overlaps(proj.getSprite().getBoundingRectangle())) {
                iterator.remove();
                proj.remove();
            }
        }
        trumpStrikesAgain(trump);
    }

    private void auditCollision(TrumpActor trump){
        trump.addMoney(-100000);
        rigged.play(1.0f);
        trump.resetMoneyGainTimer();
    }

    private void newspaperCollision(TrumpActor trump){
        trump.addVotes(-250000);
    }

    private void newspaperCollision(TrumpActor trump, int rate){
        rigged.play(1.0f);
        trump.addVotes(-250000 * rate);
    }

    private void slurCollision(TrumpActor trump, int rate) {
        trump.addVotes(-150000 * rate);
        rigged.play(1.0f);
    }
}
