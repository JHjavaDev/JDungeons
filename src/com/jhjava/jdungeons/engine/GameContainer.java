package com.jhjava.jdungeons.engine;

import com.jhjava.jdungeons.engine.components.Physics;
import com.jhjava.jdungeons.engine.render.Renderer;
import com.sun.glass.events.KeyEvent;

public class GameContainer implements Runnable {
	private Thread thread;
	private AbstractGame game;
	private Window window;
	private Renderer renderer;
	private Input input;
	private Physics physics;

	private boolean isRunning = false;

	private float frameCap = 1.0f / 60.0f;

	private int width = 320, height = 240;
	private float scale = 2.0f;
	private String title = "game";

	private boolean lockFrameRate = false;
	private boolean lightingEnabled = false;
	private boolean dynamicLights = false;
	private boolean clearScreen = false;
	private boolean debug = false;

	public GameContainer(AbstractGame game) {
		this.game = game;

	}

	public void start() {
		if(isRunning) {
			return;
		}

		window = new Window(this);
		renderer = new Renderer(this);
		input = new Input(this);
		physics = new Physics();

		thread = new Thread(this);
		thread.run();
	}

	public void stop() {
		if(!isRunning) {
			return;
		}

		isRunning = false;
	}

	public void run() {
		isRunning = true;

		float firstTime = 0f;
		float lastTime = System.nanoTime() / 1000000000.0f;
		float passedTime = 0;
		float unprocessedTime = 0;
		float frameTime = 0;
		int frames = 0;
		int fps = 0;

		game.init(this);

		while(isRunning) {
			boolean render = !lockFrameRate;

			firstTime = System.nanoTime() / 1000000000.0f;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;

			unprocessedTime += passedTime;
			frameTime += passedTime;

			while(unprocessedTime >= frameCap) {
				if(input.isKeyPressed(KeyEvent.VK_F3)) {
					debug = !debug;
				}

				game.update(this, unprocessedTime);
				physics.update(unprocessedTime);
				input.update();

				unprocessedTime -= frameCap;

				render = true;

				if(frameTime >= 1) {
					frameTime = 0;
					fps = frames;
					frames = 0;
				}
			}

			if(render) {
				if(clearScreen) {
					renderer.clear();
				}

				game.render(this, renderer);

				if(lightingEnabled || dynamicLights) {
					renderer.drawLightArray();
					renderer.flushMaps();
				}

				if(debug) {
					renderer.setTranslate(false);
					renderer.drawString(title + " by JHjavaDev", 0xffffffff, 1, 1);
					renderer.drawString("FPS: " + Integer.toString(fps), 0xffffffff, 1, 7);
					renderer.drawString("Width: " + width + " Height: " + height + " Scale: " + scale, 0xffffffff, 1, 13);
					renderer.setTranslate(true);
				}

				renderer.drawStrings();

				window.update();

				frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		cleanUp();
	}

	private void cleanUp() {
		window.cleanUp();
	}

	public AbstractGame getGame() {
		return game;
	}

	public void setGame(AbstractGame game) {
		this.game = game;
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public Physics getPhysics() {
		return physics;
	}

	public void setPhysics(Physics physics) {
		this.physics = physics;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean running) {
		isRunning = running;
	}

	public float getFrameCap() {
		return frameCap;
	}

	public void setFrameCap(int frameCap) {
		this.frameCap = 1.0f / frameCap;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isLightingEnabled() {
		return lightingEnabled;
	}

	public void setLightingEnabled(boolean lightingEnabled) {
		this.lightingEnabled = lightingEnabled;
	}

	public boolean isDynamicLights() {
		return dynamicLights;
	}

	public void setDynamicLights(boolean dynamicLights) {
		this.dynamicLights = dynamicLights;
	}

	public boolean isClearScreen() {
		return clearScreen;
	}

	public void setClearScreen(boolean clearScreen) {
		this.clearScreen = clearScreen;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public boolean isLockFrameRate() {
		return lockFrameRate;
	}

	public void setLockFrameRate(boolean lockFrameRate) {
		this.lockFrameRate = lockFrameRate;
	}
}
