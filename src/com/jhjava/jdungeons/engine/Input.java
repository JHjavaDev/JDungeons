package com.jhjava.jdungeons.engine;

import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	private GameContainer gc;

	private boolean[] keys = new boolean[256];
	private boolean[] keysLast = new boolean[256];

	private boolean[] buttons = new boolean[5];
	private boolean[] buttonsLast = new boolean[5];

	private int mouseX, mouseY;

	private float mouseWheelPos;

	public Input(GameContainer gc) {
		this.gc = gc;
		gc.getWindow().getCanvas().addKeyListener(this);
		gc.getWindow().getCanvas().addMouseListener(this);
		gc.getWindow().getCanvas().addMouseMotionListener(this);
		gc.getWindow().getCanvas().addMouseWheelListener(this);
	}

	public void update() {
		keysLast = keys.clone();
		buttonsLast = buttons.clone();
	}

	public boolean isKeyDown(int keyCode) {
		return keys[keyCode];
	}

	public boolean isKeyPressed(int keyCode) {
		return keys[keyCode] && !keysLast[keyCode];
	}

	public boolean isKeyReleased(int keyCode) {
		return !keys[keyCode] && keysLast[keyCode];
	}

	public boolean isButtonDown(int buttonCode) {
		return buttons[buttonCode];
	}

	public boolean isButtonPressed(int buttonCode) {
		return buttons[buttonCode] && !buttonsLast[buttonCode];
	}

	public boolean isButtonReleased(int buttonCode) {
		return !buttons[buttonCode] && buttonsLast[buttonCode];
	}

	public int getMouseX() {
		return mouseX + gc.getRenderer().getTransX();
	}

	public int getMouseY() {
		return mouseY + gc.getRenderer().getTransY();
	}

	public float getMouseWheelPos() {
		return mouseWheelPos;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton()] = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = (int) (e.getX() / gc.getScale());
		mouseY = (int) (e.getY() / gc.getScale());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = (int) (e.getX() / gc.getScale());
		mouseY = (int) (e.getY() / gc.getScale());
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		mouseWheelPos = e.getWheelRotation();
	}
}
