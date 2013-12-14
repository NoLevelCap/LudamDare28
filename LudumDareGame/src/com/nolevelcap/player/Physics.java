package com.nolevelcap.player;

import java.awt.Rectangle;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import com.nolevelcap.main.MainGame;

public class Physics {
	
	private MainGame game;
	
	public Physics(MainGame game){
		this.game = game;
	}
	
	
	public float getNewY(float Vel, float ObjY){
		
		ObjY = ObjY + Vel;
		
		return ObjY;
		
	}
	
	public float getNewAcceleration(float Acc){
		if (Acc < 0){
		return Acc += 0.10f;
		} else {
		return Acc -= 0.10f;
		}
	}
	
	public float getVelocity(float Vel, float Acc){
		Vel = Vel - Acc * game.getDeltaTime();
		return Vel;
	}
	
	public boolean checkForCollision(Rectangle one, Rectangle two){
		if(one.intersects(two)){
			return true;
		} else {
			return false;
		}
	}

}
