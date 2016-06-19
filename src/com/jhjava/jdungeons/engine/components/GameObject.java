package com.jhjava.jdungeons.engine.components;

import com.jhjava.jdungeons.engine.GameContainer;
import com.jhjava.jdungeons.engine.render.Renderer;

import java.awt.*;
import java.util.ArrayList;

public abstract class GameObject {
	protected String tag = "null";
	protected boolean enabled = true;
	protected float x, y, w, h;

	protected ArrayList<GameComponent> components = new ArrayList<>();

	public abstract void update(GameContainer gc, float delta);

	public void updateComponents(GameContainer gc, float delta) {
		for(GameComponent component : components) {
			component.update(gc, this, delta);
		}
	}

	public abstract void render(GameContainer gc, Renderer renderer);

	public void renderComponents(GameContainer gc, Renderer renderer) {
		for(GameComponent component : components) {
			component.render(gc, renderer);
		}
	}

	public abstract void componentEvent(String name, GameObject object);

	public abstract void dispose();

	public void addComponent(GameComponent c) {
		components.add(c);
	}

	public void removeComponent(String tag) {
		for(int i = 0; i < components.size(); i++) {
			if(components.get(i).getTag().equals(tag)) {
				components.remove(i);
			}
		}
	}

	public void translate(float x, float y) {
		this.x += x;
		this.y += y;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}
}
