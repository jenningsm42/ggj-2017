package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by KaseiFox on 1/20/17.
 */

public class TrumpActor extends Actor {
    private Sprite sprite;
    private Vector2 velocity;
    private final float speed = 20.f * App.ppu;

    TrumpActor() {
        sprite = App.createScaledSprite(new Texture("trump.png"));
        sprite.setX((Gdx.graphics.getWidth() - sprite.getWidth()) / 2.f);
        sprite.setY(5.f * App.ppu);

        velocity = new Vector2();
    }

    public Sprite getSprite(){
        return sprite;
    }

    @Override
    public void act(float delta) {
        if(Gdx.input.isTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();

            if(!sprite.getBoundingRectangle().contains(x, y)) {
                float dx = x - (sprite.getX() + sprite.getWidth() / 2.f);
                float dy = y - (sprite.getY() + sprite.getHeight() / 2.f);

                velocity.set(dx, dy);
                velocity.setLength(speed);
            }
        } else velocity.setLength2(0);

        sprite.translate(velocity.x, velocity.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
}
