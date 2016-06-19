package com.jhjava.jdungeons.game;

import com.jhjava.jdungeons.engine.GameContainer;
import com.jhjava.jdungeons.engine.components.State;
import com.jhjava.jdungeons.engine.render.Renderer;
import com.sun.glass.events.KeyEvent;

public class StartState extends State {
	private Loader loader;
	private String firstLevel;

	public StartState(String firstLevel, Loader loader) {
		name = "start";
		this.loader = loader;
		this.firstLevel = firstLevel;
	}

	@Override
	public void update(GameContainer gc, float delta) {
		if(gc.getInput().isKeyDown(KeyEvent.VK_SPACE)) {
			gc.getGame().setState(loader.loadMap(firstLevel));
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
