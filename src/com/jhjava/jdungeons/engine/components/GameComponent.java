package com.jhjava.jdungeons.engine.components;

import com.jhjava.jdungeons.engine.GameContainer;
import com.jhjava.jdungeons.engine.render.Renderer;

public abstract class GameComponent {
	protected String tag = null;
	public abstract void update(GameContainer gc, GameObject object, float delta);
	public abstract void render(GameContainer gc, Renderer renderer);

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
