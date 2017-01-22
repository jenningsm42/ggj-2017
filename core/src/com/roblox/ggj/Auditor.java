package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ben on 21/01/17.
 */

public class Auditor extends Obstacle {
    private float accelerationX;
    private final float maxAccel = 28.f * App.getPPU();
    private final float maxXVel = 10.f * App.getPPU();
    private Sound terribleOrganization;

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
        lives = 2;
        accelerationX = 0.f;

        terribleOrganization = Gdx.audio.newSound(Gdx.files.internal("terrible_organization.wav"));
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        accelerationX = (trump.getX() < getX()? -maxAccel : maxAccel);

        velocity.x += accelerationX * delta;
        if(Math.abs(velocity.x) > maxXVel)
            velocity.x = Math.signum(velocity.x) * maxXVel;
        velocity.setLength(speed);
        sprite.translate(velocity.x * delta, velocity.y * delta);
        setCoordinateFields();
    }

    public void kill() {
        //TODO: play distruction animation
        terribleOrganization.play(1.0f);
        remove();
    }

    public void attack(){

    }

}
