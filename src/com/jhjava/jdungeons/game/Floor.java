package com.jhjava.jdungeons.game;

import com.jhjava.jdungeons.engine.GameContainer;
import com.jhjava.jdungeons.engine.components.Collider;
import com.jhjava.jdungeons.engine.components.GameObject;
import com.jhjava.jdungeons.engine.render.Image;
import com.jhjava.jdungeons.engine.render.Renderer;

public class Floor extends GameObject {
	private Image texture;

	public Floor(int x, int y, String image) {
		this.x = x;
		this.y = y;

		texture = new Image(image);

		w = texture.width;
		h = texture.height;
	}

	@Override
	public void update(GameContainer gc, float delta) {

	}

	@Override
	public void render(GameContainer gc, Renderer renderer) {
		renderer.drawImage(texture, (int) x, (int) y);
	}

	@Override
	public void componentEvent(String name, GameObject object) {

	}

	@Override
	public void dispose() {

	}
}
