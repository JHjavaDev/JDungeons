package com.jhjava.jdungeons.engine.components;

import com.jhjava.jdungeons.engine.GameContainer;
import com.jhjava.jdungeons.engine.render.Renderer;

public class Camera extends GameComponent {
	private float x, y;

	public Camera() {
		x = 0;
		y = 0;
	}

	@Override
	public void update(GameContainer gc, GameObject object, float delta) {
		x = (object.x + object.w / 2) - gc.getWidth() / 2;
		y = (object.y + object.h / 2) - gc.getHeight() / 2;
		gc.getRenderer().setTransX((int) x);
		gc.getRenderer().setTransY((int) y);
	}

	@Override
	public void render(GameContainer gc, Renderer renderer) {

	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
