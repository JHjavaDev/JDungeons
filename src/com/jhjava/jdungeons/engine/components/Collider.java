package com.jhjava.jdungeons.engine.components;

import com.jhjava.jdungeons.engine.GameContainer;
import com.jhjava.jdungeons.engine.render.Renderer;

public class Collider extends GameComponent {
	private GameObject object;

	private float x, y, halfWidth, halfHeight;

	private boolean isTrigger = true;
	private boolean isDynamic = false;

	private boolean enabled = true;

	public Collider() {
		setTag("collider");
	}

	@Override
	public void update(GameContainer gc, GameObject object, float delta) {
		if(this.object == null) {
			this.object = object;
		}
		halfWidth = object.getW() / 2;
		halfHeight = object.getH() / 2;
		x = object.getX() + halfWidth;
		y = object.getY() + halfHeight;
		if(enabled) {
			gc.getPhysics().addCollider(this);
		}
	}

	@Override
	public void render(GameContainer gc, Renderer renderer) {
//		renderer.drawRect((int) (x - halfWidth), (int) (y - halfHeight), (int) halfWidth * 2, (int) halfHeight * 2, 0xffffffff);
	}

	public void collision(Collider other, float delta) {
		if(isTrigger || other.isTrigger) {
			object.componentEvent(tag, other.getObject());
		} else if (isDynamic) {
			int moveX = 0;
			int moveY = 0;

			int top = (int) ((other.getObject().y + other.getObject().h) - object.y + 0.5f);
			int bottom = (int) ((object.y + object.h) - other.getObject().y + 0.5f);
			int left = (int) ((other.getObject().x + other.getObject().w) - object.x + 0.5f);
			int right = (int) ((object.x + object.w) - other.getObject().x + 0.5f);

			int small = 999999999;

			if(top < small) {
				small = top;
				moveX = 0;
				moveY = top;
			}
			if(bottom < small) {
				small = bottom;
				moveX = 0;
				moveY = bottom * -1;
			}
			if(left < small) {
				small = left;
				moveX = left;
				moveY = 0;
			}
			if(right < small) {
				small = right;
				moveX = right * -1;
				moveY = 0;
			}

			object.translate((int) (moveX * delta * 40), (int) (moveY * delta * 40));
		}
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

	public float getHalfWidth() {
		return halfWidth;
	}

	public void setHalfWidth(float halfWidth) {
		this.halfWidth = halfWidth;
	}

	public float getHalfHeight() {
		return halfHeight;
	}

	public void setHalfHeight(float halfHeight) {
		this.halfHeight = halfHeight;
	}

	public GameObject getObject() {
		return object;
	}

	public boolean isTrigger() {
		return isTrigger;
	}

	public void setTrigger(boolean trigger) {
		isTrigger = trigger;
	}

	public boolean isDynamic() {
		return isDynamic;
	}

	public void setDynamic(boolean dynamic) {
		isDynamic = dynamic;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setObject(GameObject object) {
		this.object = object;
	}
}
