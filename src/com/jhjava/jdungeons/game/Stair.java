package com.jhjava.jdungeons.game;

import com.jhjava.jdungeons.engine.GameContainer;
import com.jhjava.jdungeons.engine.components.Collider;
import com.jhjava.jdungeons.engine.components.GameObject;
import com.jhjava.jdungeons.engine.render.Image;
import com.jhjava.jdungeons.engine.render.Renderer;

public class Stair extends GameObject {
	private GameContainer gc;

	private Image texture;

	public Stair(int x, int y, String image) {
		this.tag = "stair";

		this.x = x;
		this.y = y;

		texture = new Image(image);

		this.w = texture.width;
		this.h = texture.height;

		Collider trigger = new Collider();
		trigger.setTrigger(true);
		addComponent(trigger);
	}

	@Override
	public void update(GameContainer gc, float delta) {
		if(this.gc == null) {
			this.gc = gc;
		}
		updateComponents(gc, delta);
	}

	@Override
	public void render(GameContainer gc, Renderer renderer) {
		renderer.drawImage(texture, (int) x, (int) y);
	}

	@Override
	public void componentEvent(String name, GameObject object) {
		if(name.equals("collider") && object instanceof Player) {
			gc.getGame().pop();
		}
	}

	@Override
	public void dispose() {

	}
}
