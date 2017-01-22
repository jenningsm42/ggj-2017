package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

/**
 * Created by KaseiFox on 1/21/17.
 */

public class ProjectileFactory {
    private ProjectilePool projectilePool;
    private Sound paperThrow;
    private Sound whiteMale;
    private Sound malePrivelage;
    private Sound moneyThrow;
    private Random rand;
    private int minSound = 0;
    private int maxSound = 1;

    public final static float projectileSpeed = 50.f * App.getPPU();


    public ProjectileFactory(ProjectilePool projectilePool) {
        this.projectilePool = projectilePool;
        rand = new Random();

        paperThrow = Gdx.audio.newSound(Gdx.files.internal("newspaper.wav"));
        whiteMale = Gdx.audio.newSound(Gdx.files.internal("whitemale.wav"));
        malePrivelage = Gdx.audio.newSound(Gdx.files.internal("male_privelage.wav"));
        moneyThrow = Gdx.audio.newSound(Gdx.files.internal("kaching.wav"));

    }

    public void createProjectile(Obstacle obstacle, TrumpActor trump, ProjectileType type) {
        switch(type) {
            case MONEY: {
                // From Trump, velocity is straight up
                assert (obstacle == null);

                Vector2 velocity = new Vector2(0, projectileSpeed);
                Vector2 origin = new Vector2(trump.getX(), trump.getY());

                ProjectileActor projectile = new ProjectileActor(origin, velocity, type);
                moneyThrow.play(1.0f);
                projectilePool.addProjectile(projectile);
            } break;
            case SLUR: {
                int randomNum = rand.nextInt((maxSound - minSound) + 1) + minSound;
                if (randomNum == 0)
                    malePrivelage.play(1.0f);
                else whiteMale.play(0.7f);
                // From activist, throw towards trump
                Vector2 velocity = new Vector2(
                        trump.getX() - obstacle.getX(),
                        trump.getY() - obstacle.getY()
                );
                velocity.setLength(projectileSpeed);
                Vector2 origin = new Vector2(obstacle.getX(), obstacle.getY());

                ProjectileActor projectile = new ProjectileActor(origin, velocity, type);
                projectilePool.addProjectile(projectile);
            } break;
            case NEWSPAPER: {
                // From media, split up into 3 projectiles in a cone
                Vector2 origin = new Vector2(obstacle.getX(), obstacle.getY());

                Vector2 velocities[] = new Vector2[3];
                velocities[0] = new Vector2(0, -projectileSpeed);
                velocities[1] = new Vector2(-3, -4); // From 3-4-5 triangle -- 30 degrees
                velocities[1].setLength(projectileSpeed);
                velocities[2] = new Vector2(3, -4);
                velocities[2].setLength(projectileSpeed);

                paperThrow.play(1.0f);
                for(int i = 0; i < 3; i++)
                    projectilePool.addProjectile(new ProjectileActor(origin, velocities[i], type));

            } break;
            case AUDIT_NOTICE: {
                // From auditors, audit notice will move in a wave (velocity doesn't matter)
                Vector2 origin = new Vector2(obstacle.getX(), obstacle.getY());
                Vector2 velocity = new Vector2(0, -projectileSpeed); // Temporary!
                projectilePool.addProjectile(new ProjectileActor(origin, velocity, type));
            } break;
        }
    }
}
