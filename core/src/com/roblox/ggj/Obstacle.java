package com.roblox.ggj;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by ben on 20/01/17.
 */

public abstract class Obstacle extends Actor {
    protected Sprite sprite;
    protected int health;
    protected float speed;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    public abstract void kill();

    public abstract void interactWithTrump();

    public boolean hasCollision(Obstacle Trump){
        return true;
    }



}
