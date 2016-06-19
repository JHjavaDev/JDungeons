package com.jhjava.jdungeons.game;

import com.jhjava.jdungeons.engine.GameContainer;
import com.jhjava.jdungeons.engine.audio.SoundClip;
import com.jhjava.jdungeons.engine.components.GameObject;
import com.jhjava.jdungeons.engine.components.GameObjectManager;
import com.jhjava.jdungeons.engine.components.State;
import com.jhjava.jdungeons.engine.render.Image;
import com.jhjava.jdungeons.engine.render.Renderer;

import java.util.ArrayList;

public class PlayState extends State {
	private SoundClip music;

	public PlayState(String name) {
		this.name = name;
		music = new SoundClip("music-" + name + ".wav");
	}

	@Override
	public void update(GameContainer gc, float delta) {
		if(!music.isRunning()) {
			music.loop();
		}
		manager.updateObjects(gc, delta);
	}

	@Override
	public void render(GameContainer gc, Renderer renderer) {
		manager.renderObjects(gc, renderer);
	}

	@Override
	public void dispose() {
		music.close();
		manager.disposeObjects();
	}
}
