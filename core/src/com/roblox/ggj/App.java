package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class App extends com.badlogic.gdx.Game {
	private BitmapFont font;
	private BitmapFont bigFont;

	@Override
	public void create () {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("munro.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

		parameter.size = 18 * Math.round(getScaleRatio());
		font = generator.generateFont(parameter);
		font.setColor(Color.BLACK);

		parameter.size = 28 * Math.round(getScaleRatio());
		bigFont = generator.generateFont(parameter);
		bigFont.setColor(Color.BLACK);

		setScreen(new SplashScreen(this));
	}

	public BitmapFont getFont() {
		return font;
	}

	public BitmapFont getBigFont() {
		return bigFont;
	}

	public static Sprite createScaledSprite(Texture texture) {
		Sprite sprite = new Sprite(texture);
		sprite.setSize(sprite.getWidth() * getScaleRatio(),
				sprite.getHeight() * getScaleRatio());
		return sprite;
	}

	public static float getPPU() {
		return Gdx.graphics.getHeight() / 100.f;
	}

	public static float getScaleRatio() {
		return Gdx.graphics.getHeight() / 480.f; // Scale by 4x on 1080p screen
	}
}
