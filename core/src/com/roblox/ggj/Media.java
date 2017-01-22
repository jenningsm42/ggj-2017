package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ben on 21/01/17.
 */

public class Media extends Obstacle {

    public Media(float speed, ProjectileFactory projectileFactory, TrumpActor trump){
        super(speed, projectileFactory, trump);
        frames.add(new Texture("media.png"));
        sprite = App.createScaledSprite(frames.get(0));
        type = ObstacleType.MEDIA;
        projectileType = ProjectileType.NEWSPAPER;

        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());
        lives = 5;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        sprite.translate(velocity.x * delta, velocity.y * delta);
        setCoordinateFields();
    }

    public void damage(){
        if(lives == 1)
            kill();
        else
            lives--;
    }

    public void kill() {
        //TODO: play distruction animation
        remove();
    }

    public void attack(){

    }
}
