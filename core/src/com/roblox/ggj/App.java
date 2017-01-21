package com.roblox.ggj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class App extends com.badlogic.gdx.Game {
	public static final float scaleRatio = 4.f;
	private BitmapFont font;

	@Override
	public void create () {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("munro.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

		parameter.size = 18 * Math.round(scaleRatio);
		font = generator.generateFont(parameter);
		font.setColor(Color.BLACK);

		setScreen(new SplashScreen(this));
	}

	public BitmapFont getFont() {
		return font;
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
