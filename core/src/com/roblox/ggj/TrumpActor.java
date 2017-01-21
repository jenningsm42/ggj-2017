package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
    private final float speed = 40.f * App.getPPU();
    private ProjectileFactory projectileFactory;
    private float moneyTime = 0.f;

    private int money = 1000000;
    private int votes = 100000;

    TrumpActor(ProjectileFactory projectileFactory) {
        sprite = App.createScaledSprite(new Texture("trump.png"));
        sprite.setX((Gdx.graphics.getWidth() - sprite.getWidth()) / 2.f);
        sprite.setY(5.f * App.getPPU());

        velocity = new Vector2();

        setX(sprite.getX() + sprite.getWidth() / 2.f);
        setY(sprite.getY() + sprite.getHeight() / 2.f);
        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());

        this.projectileFactory = projectileFactory;
    }

    public Sprite getSprite(){
        return sprite;
    }

    @Override
    public void act(float delta) {
        if(Gdx.input.isTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.graphics.getHeight() - Gdx.input.getY();

            if(y / App.getPPU() < 20.f) {
                // Move trump
                float length = Math.abs(sprite.getX() + sprite.getWidth() / 2.f - x) / App.getPPU();

                if (length > 5.f) {
                    float dx = x - (sprite.getX() + sprite.getWidth() / 2.f);
                    float dy = y - (sprite.getY() + sprite.getHeight() / 2.f);

                    velocity.set(dx, 0);
                    velocity.setLength(speed * delta);
                } else if (length < 1.f) {
                    velocity.setLength2(0);
                }
            } else {
                // Throw money
                if(money  >= 50000 && moneyTime > 1.f) {
                    money -= 50000;
                    moneyTime = 0.f;
                    projectileFactory.createProjectile(null, this, ProjectileType.MONEY);
                }
            }
        } else velocity.setLength2(0);

        sprite.translate(velocity.x, velocity.y);

        setX(sprite.getX() + sprite.getWidth() / 2.f);
        setY(sprite.getY() + sprite.getHeight() / 2.f);

        moneyTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
}
