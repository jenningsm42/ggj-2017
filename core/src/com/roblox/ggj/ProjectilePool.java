package com.roblox.ggj;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.List;

/**
 * Created by KaseiFox on 1/21/17.
 */

public class ProjectilePool {
    private List<ProjectileActor> projectiles;
    private Stage stage;

    public ProjectilePool(Stage stage) {
        this.stage = stage;
    }

    public void addProjectile(ProjectileActor projectile) {
        projectiles.add(projectile);
        stage.addActor(projectile);
    }
}
