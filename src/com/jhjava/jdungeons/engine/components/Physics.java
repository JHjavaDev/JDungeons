package com.jhjava.jdungeons.engine.components;

import java.util.ArrayList;

public class Physics {
	private ArrayList<Collider> objects = new ArrayList<>();

	public void update(float delta) {
		for(int i = 0; i < objects.size(); i++) {
			for(int j = i + 1; j < objects.size(); j++) {
				Collider c0 = objects.get(i);
				Collider c1 = objects.get(j);

				if(Math.abs(c0.getX() - c1.getX()) < c0.getHalfWidth() + c1.getHalfWidth()) {
					if(Math.abs(c0.getY() - c1.getY()) < c0.getHalfHeight() + c1.getHalfHeight()) {
						c0.collision(c1, delta);
						c1.collision(c0, delta);
					}
				}
			}
		}

		objects.clear();
	}

	public void addCollider(Collider c) {
		objects.add(c);
	}
}
