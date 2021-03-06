package com.nolevelcap.main;

import java.awt.Rectangle;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class MouseHandling {
	
	public int x, y;
	private boolean leftClicked;
	
	public void logic(){
		x = Mouse.getX(); // will return the X coordinate on the Display.
		y = Mouse.getY(); // will return the Y coordinate on the Display.
		y = Display.getHeight() - y;
		leftClicked = Mouse.isButtonDown(0);
	}
	
	public boolean mouseHover(Rectangle comp){
		Rectangle moundRect = new Rectangle(x, y, 5, 5);
		if (moundRect.intersects(comp)){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean leftClicked(){
		if (Mouse.isButtonDown(0)){
			return true;
		} else {
			return false;
		}
	}
}
