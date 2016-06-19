package com.jhjava.jdungeons.game;

import com.jhjava.jdungeons.engine.GameContainer;
import com.jhjava.jdungeons.engine.EngineMath;
import com.jhjava.jdungeons.engine.components.Collider;
import com.jhjava.jdungeons.engine.components.GameObject;
import com.jhjava.jdungeons.engine.render.Image;
import com.jhjava.jdungeons.engine.render.Renderer;

public class Enemy extends GameObject {
	private GameContainer gc;

	private Image texture = new Image("slime");

	private Player target;
	private float speed;
	private float range;

	public Enemy(int x, int y) {
		speed = (float) Math.random();
		range = 150;
		this.x = x;
		this.y = y;
		this.w = texture.width;
		this.h = texture.height;
		Collider collider = new Collider();
		collider.setTrigger(false);
		collider.setDynamic(true);
		addComponent(collider);
		Collider trigger = new Collider();
		trigger.setTrigger(true);
		addComponent(trigger);
	}

	@Override
	public void update(GameContainer gc, float delta) {
		if(this.gc == null) {
			this.gc = gc;
		}

		if(Math.abs(target.getX() - x) < range && Math.abs(target.getY() - y) < range) {
			translate(((EngineMath.normalize((int) (target.getX() - x))) * delta * speed * 80), ((EngineMath.normalize((int) (target.getY() - y))) * delta * speed * 80));
		}

		updateComponents(gc, delta);
	}

	@Override
	public void render(GameContainer gc, Renderer renderer) {
		renderer.drawImage(texture, (int) x, (int) y);
	}

	@Override
	public void componentEvent(String name, GameObject object) {
		if(name.equals("attackCollider")) {
			System.out.println("ATTACK!");
			setEnabled(false);
		}
		if(name.equals("collider") && object instanceof Player) {
			if(enabled) {
				((Player) object).death();
			}
		}
	}

	@Override
	public void dispose() {

	}

	public Player getTarget() {
		return target;
	}

	public void setTarget(Player target) {
		this.target = target;
	}
}
