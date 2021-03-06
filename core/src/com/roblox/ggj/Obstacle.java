package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ben on 20/01/17.
 */

public abstract class Obstacle extends Actor {
    protected Sprite sprite;
    protected List<Texture> frames;
    protected float animationDelay = 0.3f;
    protected float animationTime = 0.f;
    protected int animationFrame = 0;
    protected float attackDelay = 1.5f;
    protected float attackTime = 0.75f;
    protected int health;
    protected float speed;
    protected Vector2 velocity;
    protected ObstacleType type;
    protected ProjectileType projectileType;
    protected int lives;

    private ProjectileFactory projectileFactory;
    protected TrumpActor trump;

    public Obstacle(float speed, ProjectileFactory projectileFactory, TrumpActor trump) {
        this.speed = speed;
        this.projectileFactory = projectileFactory;
        this.trump = trump;
        this.health = 2;
        velocity = new Vector2(0, -speed);
        frames = new ArrayList<Texture>();
    }

    @Override
    public void act(float delta) {
        animationTime += delta;
        if(animationTime >= animationDelay) {
            animationTime = 0.f;
            if(++animationFrame >= frames.size()) animationFrame = 0;
            sprite.setTexture(frames.get(animationFrame));
        }

        attackTime += delta;
        if(attackTime >= attackDelay) {
            attackTime = 0.f;
            projectileFactory.createProjectile(this, trump, projectileType);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    public abstract void kill();

    public boolean damage() {
        if(--lives < 1) {
            kill();
            return true;
        }
        return false;
    }

    public boolean hasCollision(TrumpActor trump){
        return sprite.getBoundingRectangle().overlaps(trump.getHitBox());
    }

    public Sprite getSprite(){
        return sprite;
    }

    public ObstacleType getType(){
        return this.type;
    }

    protected void setCoordinateFields() {
        setX(sprite.getX() + sprite.getWidth() / 2.f);
        setY(sprite.getY() + sprite.getHeight() / 2.f);
    }

}
