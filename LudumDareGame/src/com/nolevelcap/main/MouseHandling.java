package com.nolevelcap.main;

import java.awt.Rectangle;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class MouseHandling {
	
	private int x, y;
	
	public void logic(){
		x = Mouse.getX(); // will return the X coordinate on the Display.
		y = Mouse.getY(); // will return the Y coordinate on the Display.
		y = Display.getHeight() - y;
	}
	
	public boolean mouseHover(Rectangle comp){
		Rectangle moundRect = new Rectangle(x, y, 5, 5);
		if (moundRect.intersects(comp)){
			return true;
		} else {
			return false;
		}
		

	}
}
