package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ben on 21/01/17.
 */

public class Activist extends Obstacle {

    public Activist(float speed, ProjectileFactory projectileFactory, TrumpActor trump) {
        super(speed, projectileFactory, trump);

        frames.add(new Texture("Fem1.png"));
        frames.add(new Texture("Fem2.png"));
        frames.add(new Texture("Fem1.png"));
        frames.add(new Texture("Fem3.png"));
        sprite = App.createScaledSprite(frames.get(0));
        type = ObstacleType.ACTIVIST;
        projectileType = ProjectileType.SLUR;

        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());
        this.lives = 1;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        sprite.translate(velocity.x * delta, velocity.y * delta);
        setCoordinateFields();
    }

    public void damage(){
        kill();
    }

    public void kill() {
        //TODO: play distruction animation
        remove();
    }

    public void attack(){

    }


}
