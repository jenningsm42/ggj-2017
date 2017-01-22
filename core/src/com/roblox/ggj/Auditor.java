package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ben on 21/01/17.
 */

public class Auditor extends Obstacle {

    public Auditor(float speed, ProjectileFactory projectileFactory, TrumpActor trump){
        super(speed, projectileFactory, trump);
        frames.add(new Texture("Auditor_1.png"));
        frames.add(new Texture("Auditor_2.png"));
        frames.add(new Texture("Auditor_1.png"));
        frames.add(new Texture("Auditor_3.png"));
        sprite = App.createScaledSprite(frames.get(0));
        type = ObstacleType.AUDITOR;
        projectileType = ProjectileType.AUDIT_NOTICE;

        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        sprite.translate(velocity.x * delta, velocity.y * delta);
        setCoordinateFields();
    }

    public void kill() {
        //TODO: play distruction animation
        remove();
    }

    public void attack(){

    }

}
