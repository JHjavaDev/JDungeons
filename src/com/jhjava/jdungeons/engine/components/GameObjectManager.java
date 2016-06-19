package com.jhjava.jdungeons.engine.components;

import com.jhjava.jdungeons.engine.GameContainer;
import com.jhjava.jdungeons.engine.render.Renderer;

import java.util.ArrayList;

public class GameObjectManager {
	private ArrayList<GameObject> objects = new ArrayList<>();

	public void updateObjects(GameContainer gc, float delta) {
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).update(gc, delta);

			if(!objects.get(i).isEnabled()) {
				objects.remove(i);
			}
		}
	}

	public void renderObjects(GameContainer gc, Renderer renderer) {
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).render(gc, renderer);
		}
	}

	public void addObject(GameObject object) {
		objects.add(object);
	}

	public GameObject findObject(String tag) {
		for(GameObject object : objects) {
			if(object.getTag().equals(tag)) {
				return object;
			}
		}

		return null;
	}

	public void disposeObjects() {
		for(GameObject object : objects) {
			object.dispose();
		}
	}
}
