package com.roblox.ggj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by KaseiFox on 1/21/17.
 */

public class ProjectileActor extends Actor {
    private Sprite sprite;
    private Vector2 velocity;
    private ProjectileType type;

    public ProjectileActor(Vector2 origin, Vector2 velocity, ProjectileType type) {
        this.velocity = velocity;
        this.type = type;

        Texture texture;
        switch(type) {
            case NEWSPAPER:
                texture = new Texture("proj_newspaper.png");
                break;
            case AUDIT_NOTICE:
                texture = new Texture("proj_audit_notice.png");
                break;
            case MONEY:
            default:
                texture = new Texture("proj_money.png");
                break;
        }
        sprite = App.createScaledSprite(texture);

        sprite.setPosition(
                origin.x - sprite.getWidth() / 2.f,
                origin.y - sprite.getHeight() / 2.f
        );
    }

    public boolean hasCollision(TrumpActor Trump){
        if(this.sprite.getBoundingRectangle().contains(Trump.getSprite().getBoundingRectangle()))
            return true;
        else
            return false;
    }

    public Sprite getSprite(){
        return sprite;
    }

    public ProjectileType getType(){ return type; }

    public void update(float delta) { }

    @Override
    public void act(float delta) {
        sprite.translate(velocity.x * delta, velocity.y * delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
}
