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
    private float radius;
    private TrumpActor trump;
    private int pointer = -1;


    public Joystick(TrumpActor trump){
        texture = new Texture("Joystick1.png");
        sprite = App.createScaledSprite(texture);
        sprite.setX(1.f * App.getPPU());
        sprite.setY(1.f * App.getPPU());
        radius = sprite.getWidth() / 2.f;

        xCenter = sprite.getX() + sprite.getWidth() / 2.f;
        yCenter = sprite.getY() + sprite.getHeight() / 2.f;

        this.trump = trump;

    }

    @Override
    public void act(float delta){
        Vector2 vector = new Vector2();
        if(pointer != -1 && Gdx.input.isTouched(pointer)) {
            float x = Gdx.input.getX(pointer);
            float y = Gdx.graphics.getHeight() - Gdx.input.getY(pointer);

            float dx = x - xCenter;
            float dy = y - yCenter;

            vector.set(dx, dy);
            if(vector.len2() > radius * radius) vector.setLength2(radius * radius);

            vector.scl(1.f / radius);
            trump.setVelocity(vector);
            return;
        } else if(pointer != -1) pointer = -1;

        for(int i = 0; i < 5; i++) {
            if(Gdx.input.isTouched(i)) {
                float x = Gdx.input.getX(i);
                float y = Gdx.graphics.getHeight() - Gdx.input.getY(i);

                float dx = x - xCenter;
                float dy = y - yCenter;

                vector.set(dx, dy);
                if(vector.len2() > radius * radius) continue;

                pointer = i;
                vector.scl(1.f / radius);
                trump.setVelocity(vector);
                return;
            }
        }

        vector.setZero();
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

        vector.scl(1.f / radius);
        return vector;
    }
}
