package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ben on 21/01/17.
 */

public class Activist extends Obstacle {

    public Activist(float speed){
        super(speed);

        frames.add(new Texture("activist.png"));
        sprite = App.createScaledSprite(frames.get(0));
        type = ObstacleType.ACTIVIST;

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
