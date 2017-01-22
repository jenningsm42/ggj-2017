package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by ben on 21/01/17.
 */

public class ShootButtonActor extends Actor {
    private Sprite sprite;
    private Texture buttonTexture;
    private ProjectileFactory factory;


    public ShootButtonActor(ProjectileFactory factory){
        buttonTexture = new Texture("button.png");
        sprite = App.createScaledSprite(buttonTexture);
        sprite.setX(Gdx.graphics.getWidth() - sprite.getWidth());
        sprite.setY(sprite.getY());
        this.factory = factory;
    }

    @Override
    public void act(float delta) {
        if(Gdx.input.isTouched()){
            factory.createProjectile();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
}
