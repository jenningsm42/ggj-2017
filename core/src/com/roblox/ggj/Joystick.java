package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by ben on 21/01/17.
 */

public class Joystick extends Actor {
    private Sprite sprite;
    private Texture texture;
    private float xCenter;
    private float yCenter;
    private double radius;
    private TrumpActor trump;


    public Joystick(TrumpActor trump){
        texture = new Texture("Joystick1.png");
        sprite = App.createScaledSprite(texture);
        sprite.setX(1.f * App.getPPU());
        sprite.setY(1.f * App.getPPU());
        double magnitudeSqrd = sprite.getWidth() * sprite.getWidth() +
                sprite.getHeight() * sprite.getHeight();
        radius = Math.sqrt(magnitudeSqrd);

        xCenter = sprite.getX() + sprite.getWidth() / 2.f;
        yCenter = sprite.getY() + sprite.getHeight() / 2.f;

        this.trump = trump;

    }

    @Override
    public void act(float delta){
        Vector2 vector;
        if(Gdx.input.isTouched()){
            float xInput = Gdx.input.getX();
            float yInput = Gdx.graphics.getHeight() - Gdx.input.getY();
            vector = getVector(xInput, yInput);
        }
        else vector = new Vector2();

        trump.setVelocity(vector);
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        sprite.draw(batch);
    }

    private Vector2 getVector(float xInput, float yInput){
        float dx = xInput - xCenter;
        float dy = yInput - yCenter;

        Vector2 vector = new Vector2(dx, dy);
        if(vector.len() > radius) return new Vector2();

        vector.setLength2(1.f);
        return vector;
    }
}
