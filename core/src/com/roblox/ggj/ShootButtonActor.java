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

public class ShootButtonActor extends Actor {
    private Sprite sprite;
    private Texture buttonTexture;
    private ProjectileFactory factory;
    private TrumpActor trump;
    private float xCenter;
    private float yCenter;
    private float radius;

    public ShootButtonActor(ProjectileFactory factory, TrumpActor trump){
        buttonTexture = new Texture("Joystick_Fire.png");
        sprite = App.createScaledSprite(buttonTexture);
        sprite.setX(Gdx.graphics.getWidth() - sprite.getWidth());
        sprite.setY(sprite.getY());
        this.factory = factory;
        this.trump = trump;

        radius = sprite.getWidth() / 2.f;
        xCenter = sprite.getX() + sprite.getWidth() / 2.f;
        yCenter = sprite.getY() + sprite.getHeight() / 2.f;
    }

    @Override
    public void act(float delta) {
        if(Gdx.input.isTouched()){
            float xInput = Gdx.input.getX();
            float yInput = Gdx.graphics.getHeight() - Gdx.input.getY();
            float dx = xInput - xCenter;
            float dy = yInput - yCenter;

            if(Math.sqrt((double)(dx*dx + dy*dy)) < radius)
                trump.requestMoneyThrow();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
}
