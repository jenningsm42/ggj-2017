package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by KaseiFox on 1/22/17.
 */

public class BackgroundActor extends Actor {
    private Sprite backgrounds[];
    private float scrollSpeed = 15.f * App.getPPU();

    public BackgroundActor() {
        backgrounds = new Sprite[2];
        for(int i = 0; i < 2; i++) {
            backgrounds[i] = App.createScaledSprite(new Texture("Background.png"));
            backgrounds[i].setX((Gdx.graphics.getWidth() - backgrounds[i].getWidth()) / 2.f);
        }

        backgrounds[0].setY(0);
        backgrounds[1].setY(backgrounds[1].getHeight() - (int)(App.getScaleRatio()) - 1);
    }

    @Override
    public void act(float delta) {
        for(int i = 0; i < 2; i++) {
            if(backgrounds[i].getY() <= -backgrounds[i].getHeight() + scrollSpeed * (1.2f * delta)) {
                int idx = (i == 0? 1 : 0);
                backgrounds[i].setY(backgrounds[idx].getY() + backgrounds[idx].getHeight() - 1);
            }
            backgrounds[i].translateY(-(int)(scrollSpeed * delta));
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for(Sprite background : backgrounds) {
            background.draw(batch);
        }
    }
}
