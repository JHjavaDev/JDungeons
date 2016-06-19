package com.jhjava.jdungeons.game;

import com.jhjava.jdungeons.engine.GameContainer;
import com.jhjava.jdungeons.engine.components.State;
import com.jhjava.jdungeons.engine.render.Renderer;
import com.sun.glass.events.KeyEvent;

public class StartState extends State {
	private Loader loader;

	public StartState(String[] levels, Loader loader, GameContainer gc) {
		name = "start";
		this.loader = loader;

		for(int i = 0; i < levels.length; i++) {
			gc.getGame().push(loader.loadMap(levels[i]));
		}
	}

	@Override
	public void update(GameContainer gc, float delta) {
		if(gc.getInput().isKeyDown(KeyEvent.VK_SPACE)) {
			gc.getGame().pop();
		}
	}

	@Override
	public void render(GameContainer gc, Renderer renderer) {
		renderer.drawString("Press Space to Start", 0xffffffff, gc.getWidth() / 2 - 40, gc.getHeight() / 2);
	}

	@Override
	public void dispose() {

	}
}
