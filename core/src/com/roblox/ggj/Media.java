package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ben on 21/01/17.
 */

public class Media extends Obstacle {

    public Media(float speed){
        super(speed);
        fireRate = 1.5f;
        frames.add(new Texture("media.png"));
        sprite = App.createScaledSprite(frames.get(0));
        type = ObstacleType.MEDIA;

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
        clear();
    }

    public void attack(){

    }
}
