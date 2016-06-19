package com.jhjava.jdungeons.game;

import com.jhjava.jdungeons.engine.AbstractGame;
import com.jhjava.jdungeons.engine.GameContainer;
import com.jhjava.jdungeons.engine.render.Renderer;
import com.sun.glass.events.KeyEvent;

public class GameManager extends AbstractGame {
	private Loader loader = new Loader();

	public GameManager() {

	}

	@Override
	public void init(GameContainer gc) {
		StartState startState = new StartState(new String[]{"floor2", "floor1"}, loader, gc);
		push(startState);
	}

	@Override
	public void update(GameContainer gc, float delta) {
		peek().update(gc, delta);
	}

	@Override
	public void render(GameContainer gc, Renderer renderer) {
		peek().render(gc, renderer);
	}

	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.setTitle("JDungeons V2.2");
		gc.setScale(3.0f);
		gc.setWidth(320);
		gc.setHeight(224);
		gc.setLockFrameRate(true);
		gc.setClearScreen(true);
		gc.setLightingEnabled(false);
		gc.setDynamicLights(false);
		gc.start();
	}

	public Loader getLoader() {
		return loader;
	}
}
