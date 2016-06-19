package com.jhjava.jdungeons.engine;

import com.jhjava.jdungeons.engine.components.State;
import com.jhjava.jdungeons.engine.render.Renderer;

import java.util.Stack;

public abstract class AbstractGame {
	private Stack<State> states = new Stack<>();

	public abstract void init(GameContainer gc);
	public abstract void update(GameContainer gc, float delta);
	public abstract void render(GameContainer gc, Renderer renderer);

	public void push(State s) {
		states.push(s);
	}

	public void pop() {
		states.peek().dispose();
		states.pop();
	}

	public void setState(State s) {
		states.peek().dispose();
		states.pop();
		states.push(s);
	}

	public State peek() {
		return states.peek();
	}
}
