package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ben on 21/01/17.
 */

public class Activist extends Obstacle {

    public Activist(){
        sprite = App.createScaledSprite(new Texture("Activist.png"));
        sprite.setX(Gdx.graphics.getWidth() - sprite.getWidth() / 2.f);
        sprite.setY(5.f * App.getPPU());

    }

    public void kill() {
        //TODO: play distruction animation
    }

    public void attack(){

    }
}
