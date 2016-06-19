package com.jhjava.jdungeons.game;

import com.jhjava.jdungeons.engine.GameContainer;
import com.jhjava.jdungeons.engine.components.Collider;
import com.jhjava.jdungeons.engine.components.GameComponent;
import com.jhjava.jdungeons.engine.components.GameObject;
import com.jhjava.jdungeons.engine.render.Pixel;
import com.jhjava.jdungeons.engine.render.Renderer;
import com.sun.glass.events.KeyEvent;

public class AttackComponent extends Collider {
	private int w, h;
	private int range;

	private boolean attack;

	public AttackComponent(int x, int y, int w, int h, int range) {
		setX(x);
		setY(y);
		this.w = w;
		this.h = h;
		this.range = range;
		setTag("attackCollider");
		setEnabled(false);
	}

	@Override
	public void update(GameContainer gc, GameObject object, float delta) {
		if(getObject() == null) {
			setObject(object);
		}

		setX(object.getX() + getHalfWidth() - range / 2);
		setY(object.getY() + getHalfHeight() - range / 2);
		setHalfWidth(w / 2 + range);
		setHalfHeight(h / 2 + range);

		if(gc.getInput().isKeyPressed(KeyEvent.VK_SPACE)) {
			attack = true;
			setEnabled(true);
		}

		if(isEnabled()) {
			gc.getPhysics().addCollider(this);
		}
	}

	public void collision(Collider other, float delta) {
		other.getObject().componentEvent(tag, getObject());
	}

	@Override
	public void render(GameContainer gc, Renderer renderer) {
		super.render(gc, renderer);
		if(attack) {
			renderer.drawRect((int) ((getX()) - getHalfWidth()), (int) ((getY()) - getHalfHeight()), w + range, h + range, 0xff000000);

			attack = false;
			setEnabled(false);
		}
	}
}
