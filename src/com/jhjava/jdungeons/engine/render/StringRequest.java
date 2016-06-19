package com.jhjava.jdungeons.engine.render;

public class StringRequest {
	private String string;
	private int color;
	private int x, y;
	private boolean translate;

	public StringRequest(String string, int color, int x, int y, boolean translate) {
		this.string = string;
		this.color = color;
		this.x = x;
		this.y = y;
		this.translate = translate;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isTranslate() {
		return translate;
	}

	public void setTranslate(boolean translate) {
		this.translate = translate;
	}
}
