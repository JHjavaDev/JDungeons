package com.jhjava.jdungeons.engine.components;

import com.jhjava.jdungeons.engine.GameContainer;
import com.jhjava.jdungeons.engine.render.Renderer;

public abstract class State {
	protected String name;

	protected GameObjectManager manager = new GameObjectManager();

	public abstract void update(GameContainer gc, float delta);
	public abstract void render(GameContainer gc, Renderer renderer);
	public abstract void dispose();

	public GameObjectManager getManager() {
		return manager;
	}

	public void setManager(GameObjectManager manager) {
		this.manager = manager;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
