package com.roblox.ggj;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class App extends com.badlogic.gdx.Game {
	public static final float scaleRatio = 8.f;

	@Override
	public void create () {
		setScreen(new SplashScreen(this));
	}

	public static Sprite createScaledSprite(Texture texture) {
		Sprite sprite = new Sprite(texture);
		sprite.setSize(sprite.getWidth() * scaleRatio,
				sprite.getHeight() * scaleRatio);
		return sprite;
	}

	public static float getPPU() {
		return Gdx.graphics.getHeight() / 100.f;
	}
}
