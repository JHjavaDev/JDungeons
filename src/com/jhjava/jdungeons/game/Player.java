package com.jhjava.jdungeons.game;

import com.jhjava.jdungeons.engine.GameContainer;
import com.jhjava.jdungeons.engine.audio.SoundClip;
import com.jhjava.jdungeons.engine.components.Camera;
import com.jhjava.jdungeons.engine.components.Collider;
import com.jhjava.jdungeons.engine.components.GameObject;
import com.jhjava.jdungeons.engine.render.*;
import com.sun.glass.events.KeyEvent;

public class Player extends GameObject {
	private Loader loader = new Loader();

	private Image texture = new Image("player");

	private SoundClip deathClip = new SoundClip("death.wav");

	private boolean died = false;
	private int countDown = 10;

	public Player(int x, int y) {
		setTag("player");
		this.x = x;
		this.y = y;
		w = texture.width;
		h = texture.height;
		Collider collider = new Collider();
		collider.setTrigger(false);
		collider.setDynamic(true);
		addComponent(collider);
		addComponent(new Camera());
		addComponent(new AttackComponent(x, y, (int) w, (int) h, 30));
	}

	@Override
	public void update(GameContainer gc, float delta) {
		if(gc.getInput().isKeyDown(KeyEvent.VK_UP)) {
			y -= 80 * delta;
			if(gc.getInput().isKeyDown(KeyEvent.VK_SHIFT)) {
				y -= 80 * delta;
			}
		}
		if(gc.getInput().isKeyDown(KeyEvent.VK_DOWN)) {
			y += 80 * delta;
			if(gc.getInput().isKeyDown(KeyEvent.VK_SHIFT)) {
				y += 80 * delta;
			}
		}
		if(gc.getInput().isKeyDown(KeyEvent.VK_RIGHT)) {
			x += 80 * delta;
			if(gc.getInput().isKeyDown(KeyEvent.VK_SHIFT)) {
				x += 80 * delta;
			}
		}
		if(gc.getInput().isKeyDown(KeyEvent.VK_LEFT)) {
			x -= 80 * delta;
			if(gc.getInput().isKeyDown(KeyEvent.VK_SHIFT)) {
				x -= 80 * delta;
			}
		}

		if(died) {
			countDown--;
			if(countDown <= 0) {
				gc.getGame().setState(loader.loadMap(gc.getGame().peek().getName()));
			}
		}

		updateComponents(gc, delta);
	}

	@Override
	public void render(GameContainer gc, Renderer renderer) {
		renderer.drawImage(texture, (int) (x), (int) (y));

		if(died) {
			renderer.setTranslate(false);
			renderer.drawString("You Died", 0xffff0000, gc.getWidth() / 2 - 14, gc.getHeight() / 2);
			renderer.setTranslate(true);
		}

		renderComponents(gc, renderer);
	}

	@Override
	public void componentEvent(String name, GameObject object) {

	}

	@Override
	public void dispose() {
		deathClip.close();
	}

	public void death() {
		if(!died) {
			deathClip.play();
		}
		died = true;
	}
}
