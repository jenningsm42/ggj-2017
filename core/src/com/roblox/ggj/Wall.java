package com.roblox.ggj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by ben on 20/01/17.
 */

public class Wall extends Obstacle {

    public Wall () {
        sprite = App.createScaledSprite(new Texture("wall.png"));
    }

    public void kill(){
        //TODO: play destruction animation

    }

}

