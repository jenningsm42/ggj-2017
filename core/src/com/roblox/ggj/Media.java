package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ben on 21/01/17.
 */

public class Media extends Obstacle {

    public Media(){
        sprite = App.createScaledSprite(new Texture("media.png"));
        this.type = ObstacleType.MEDIA;
        super.spawn();

        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());
    }

    @Override
    public void update(float delta) {
        setCoordinateFields();
    }

    public void kill() {
        //TODO: play distruction animation
    }

    public void attack(){

    }
}
