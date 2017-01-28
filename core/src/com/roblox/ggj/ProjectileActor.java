package com.roblox.ggj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KaseiFox on 1/21/17.
 */

public class ProjectileActor extends Actor {
    private List<Texture> frames;
    private Sprite sprite;
    private float animationDelay = 0.1f;
    private float animationTime = 0.f;
    private int animationFrame = 0;
    private Vector2 velocity;
    private float accelX = 0.f; // For slurs only
    private float time = 0.f; // ^^^^
    private ProjectileType type;


    public ProjectileActor(Vector2 origin, Vector2 velocity, ProjectileType type) {
        this.velocity = velocity;
        this.type = type;
        frames = new ArrayList<Texture>();

        switch(type) {
            case NEWSPAPER:
                frames.add(new Texture("News1.png"));
                frames.add(new Texture("News2.png"));
                frames.add(new Texture("News3.png"));
                break;
            case AUDIT_NOTICE:
                frames.add(new Texture("Audit1.png"));
                frames.add(new Texture("Audit2.png"));
                frames.add(new Texture("Audit3.png"));
                break;
            case SLUR:
                frames.add(new Texture("Sign1.png"));
                frames.add(new Texture("Sign2.png"));
                frames.add(new Texture("Sign3.png"));
                frames.add(new Texture("Sign4.png"));
                break;
            case MONEY:
            default:
                frames.add(new Texture("Money_Shot1.png"));
                frames.add(new Texture("Money_Shot2.png"));
                frames.add(new Texture("Money_Shot3.png"));
                break;
        }
        sprite = App.createScaledSprite(frames.get(0));

        sprite.setPosition(
                origin.x - sprite.getWidth() / 2.f,
                origin.y - sprite.getHeight() / 2.f
        );
    }

    public boolean hasCollision(TrumpActor Trump){
        return sprite.getBoundingRectangle().overlaps(Trump.getHitBox());
    }

    public Sprite getSprite(){
        return sprite;
    }

    public ProjectileType getType(){ return type; }

    @Override
    public void act(float delta) {
        animationTime += delta;
        if(animationTime >= animationDelay) {
            animationTime = 0.f;
            if(++animationFrame >= frames.size()) animationFrame = 0;
            sprite.setTexture(frames.get(animationFrame));
        }

        if(type == ProjectileType.AUDIT_NOTICE)
            waveBehavior(delta);

        sprite.translate(velocity.x * delta, velocity.y * delta);
    }

    public void waveBehavior(float delta) {
        time += delta;
        accelX = -360.f * App.getPPU() * (float)Math.cos(4.f * time);
        velocity.x += accelX * delta;
        velocity.setLength(ProjectileFactory.projectileSpeed);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
}
