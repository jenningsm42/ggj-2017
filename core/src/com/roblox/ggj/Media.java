package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by ben on 21/01/17.
 */

public class Media extends Obstacle {
    private Vector2 target;
    private Random random;

    public Media(float speed, ProjectileFactory projectileFactory, TrumpActor trump){
        super(speed, projectileFactory, trump);
        frames.add(new Texture("media.png"));
        sprite = App.createScaledSprite(frames.get(0));
        type = ObstacleType.MEDIA;
        projectileType = ProjectileType.NEWSPAPER;

        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());

        target = new Vector2(Gdx.graphics.getWidth() / 2.f, 75.f * App.getPPU());
        random = new Random();
        lives = 1;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        float dx = target.x - getX();
        float dy = target.y - getY();
        if(dx*dx + dy*dy < 5.f * App.getPPU()) {
            target.x = random.nextFloat() * 20.f * App.getPPU() + Gdx.graphics.getWidth() / 2.f - 10.f * App.getPPU();
            target.y = random.nextFloat() * 15.f * App.getPPU() + 65.f * App.getPPU();

            dx = target.x - getX();
            dy = target.y - getY();
        }

        velocity.set(dx, dy);
        velocity.scl(speed / 200.f);

        sprite.translate(velocity.x * delta, velocity.y * delta);
        setCoordinateFields();
    }

    public void damage(){
        System.out.println("did damage");
        if(lives == 1)
            kill();
        else
            lives--;
    }

    public void kill() {
        //TODO: play distruction animation
        remove();
    }

    public void attack(){

    }
}
