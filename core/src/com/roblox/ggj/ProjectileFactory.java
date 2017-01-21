package com.roblox.ggj;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by KaseiFox on 1/21/17.
 */

public class ProjectileFactory {
    private ProjectilePool projectilePool;
    private final float projectileSpeed = 100.f * App.getPPU();

    public ProjectileFactory(ProjectilePool projectilePool) {
        this.projectilePool = projectilePool;
    }

    public void createProjectile(Obstacle obstacle, TrumpActor trump, ProjectileType type) {
        Vector2 velocity, origin;
        if(type == ProjectileType.MONEY) {
            // From Trump, velocity is straight up
            assert(obstacle == null);

            velocity = new Vector2(0, projectileSpeed);
            origin = new Vector2(trump.getX(), trump.getY());
        } else {
            // From obstacle, velocity is towards trump
            velocity = new Vector2(
                    trump.getX() - obstacle.getX(),
                    trump.getY() - obstacle.getY()
            );
            velocity.setLength(projectileSpeed);
            origin = new Vector2(obstacle.getX(), obstacle.getY());
        }

        ProjectileActor projectile = new ProjectileActor(origin, velocity, type);
        projectilePool.addProjectile(projectile);
    }
}
