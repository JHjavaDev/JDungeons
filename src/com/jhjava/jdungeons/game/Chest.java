package com.jhjava.jdungeons.game;

import com.jhjava.jdungeons.engine.GameContainer;
import com.jhjava.jdungeons.engine.components.Collider;
import com.jhjava.jdungeons.engine.components.GameObject;
import com.jhjava.jdungeons.engine.render.Image;
import com.jhjava.jdungeons.engine.render.Renderer;

public class Chest extends GameObject {
	private Image texture = new Image("chest");

	public Chest(int x, int y) {
		this.x = x;
		this.y = y;
		w = texture.width;
		h = texture.height;
		Collider collider = new Collider();
		collider.setTrigger(false);
		addComponent(collider);
	}

	@Override
	public void update(GameContainer gc, float delta) {
		updateComponents(gc, delta);
	}

	@Override
	public void render(GameContainer gc, Renderer renderer) {
		renderer.drawImage(texture, (int) (x), (int) (y));
	}

	@Override
	public void componentEvent(String name, GameObject object) {

	}

	@Override
	public void dispose() {

	}
}
