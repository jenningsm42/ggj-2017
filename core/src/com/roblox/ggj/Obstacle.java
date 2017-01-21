package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

/**
 * Created by ben on 20/01/17.
 */

public abstract class Obstacle extends Actor {
    protected Sprite sprite;
    protected int health;
    protected float velocity;
    protected float screenTop = Gdx.graphics.getHeight();
    protected Random rand;


    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    public abstract void kill();

    public boolean hasCollision(TrumpActor Trump){
        if(this.sprite.getBoundingRectangle().contains(Trump.getSprite().getBoundingRectangle()))
            return true;
        else
            return false;
    }

    public Sprite getSprite(){
        return sprite;
    }

    protected void spawn(){
        float randWidth = rand.nextFloat() * Gdx.graphics.getWidth() - sprite.getWidth();
        sprite.setX(randWidth);
        sprite.setY(screenTop);
    }

    protected void setCoordinateFields() {
        setX(sprite.getX() + sprite.getWidth() / 2.f);
        setY(sprite.getY() + sprite.getHeight() / 2.f);
    }

}
