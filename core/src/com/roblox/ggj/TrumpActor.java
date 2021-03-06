package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KaseiFox on 1/20/17.
 */

public class TrumpActor extends Actor {
    private Sprite sprite;
    private List<Texture> frames;
    private Vector2 velocity;
    private final float speed = 40.f * App.getPPU();
    private float moneyTime = 0.f;
    private float moneyDelay = .5f;
    private float animationTime = 0.f;
    private int animationFrame = 0;
    private final float animationDelay = 0.2f;
    private ProjectileFactory projectileFactory;
    private App app;
    private Rectangle hitBox;
    private float leftBound;
    private float rightBound;

    private int money = 1000000;
    private int votes = 1000000;
    private float moneyRate = 0.005f;
    private float moneyGainDelay = 3.f; // When hit by an audit notice
    private float moneyGainTime = moneyGainDelay;
    private final int moneyThrown = 30000;

    TrumpActor(ProjectileFactory projectileFactory, App app) {
        frames = new ArrayList<Texture>();
        frames.add(new Texture("Trump_up1.png"));
        frames.add(new Texture("Trump_up2.png"));
        frames.add(new Texture("Trump_up1.png"));
        frames.add(new Texture("Trump_up3.png"));

        sprite = App.createScaledSprite(frames.get(0));
        sprite.setX((Gdx.graphics.getWidth() - sprite.getWidth()) / 2.f);
        sprite.setY(15.f * App.getPPU());

        hitBox = new Rectangle(0.f, 0.f, sprite.getWidth() / 2.f, sprite.getHeight() / 2.f);
        hitBox.setPosition(
                sprite.getX() + sprite.getWidth() / 4.f,
                sprite.getY() + sprite.getHeight() / 4.f
        );

        velocity = new Vector2();

        setX(sprite.getX() + sprite.getWidth() / 2.f);
        setY(sprite.getY() + sprite.getHeight() / 2.f);
        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());

        this.projectileFactory = projectileFactory;
        this.app = app;

        float horizontalBounds = 5.f;
        leftBound = horizontalBounds * App.getPPU();
        rightBound = (Gdx.graphics.getWidth() / App.getPPU() - horizontalBounds) * App.getPPU();
    }

    public Sprite getSprite(){
        return sprite;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void addMoney(int amount) {
        // Allow negative amounts (debt)
        money += amount;
    }

    public void addVotes(int amount) {
        votes += amount;
        if(votes <= 0) {
            app.setScreen(new GameOverScreen(app));
        }
    }

    public int getMoney() {
        return money;
    }

    public int getVotes() {
        return votes;
    }

    public void setVelocity(Vector2 vector){
        this.velocity = vector.scl(speed);
    }

    public void resetMoneyGainTimer() {
        moneyGainTime = 0.f;
    }

    public void requestMoneyThrow() {
        if(money >= moneyThrown && moneyTime >= moneyDelay) {
            moneyTime = 0.f;
            money -= moneyThrown;
            projectileFactory.createProjectile(null, this, ProjectileType.MONEY);
        }
    }

    @Override
    public void act(float delta) {
        if(animationTime >= animationDelay) {
            animationTime = 0.f;
            animationFrame++;
            if(animationFrame >= frames.size()) animationFrame = 0;

            sprite.setTexture(frames.get(animationFrame));
        }

        sprite.translate(velocity.x * delta, velocity.y * delta);

        // Ensure Trump doesn't go out of bounds
        if(sprite.getX() < leftBound)
            sprite.setX(leftBound);
        if(sprite.getX() + sprite.getWidth() > rightBound)
            sprite.setX(rightBound - sprite.getWidth());
        if(sprite.getY() < 0.f)
            sprite.setY(0.f);
        if(sprite.getY() + sprite.getHeight() > Gdx.graphics.getHeight())
            sprite.setY(Gdx.graphics.getHeight() - sprite.getHeight());
        setX(sprite.getX() + sprite.getWidth() / 2.f);
        setY(sprite.getY() + sprite.getHeight() / 2.f);

        hitBox.setPosition(
                sprite.getX() + sprite.getWidth() / 4.f,
                sprite.getY() + sprite.getHeight() / 4.f
        );

        moneyTime += delta;
        moneyGainTime += delta;
        animationTime += delta;

        if(moneyGainTime >= moneyGainDelay)
            this.addMoney(Math.round(votes * moneyRate * delta));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
}
