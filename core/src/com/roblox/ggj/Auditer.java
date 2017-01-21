package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ben on 21/01/17.
 */

public class Auditer extends Obstacle {

    public Auditer(){
        sprite = App.createScaledSprite(new Texture("Auditer.png"));
        super.spawn();

    }

    public void kill() {
        //TODO: play distruction animation
    }

    public void attack(){

    }

}
