package com.nolevelcap.player;

import java.awt.Rectangle;

import com.nolevelcap.main.MainGame;

public class Physics {
	
	private MainGame game;
	
	public Physics(MainGame game){
		this.game = game;
	}
	
	public void CheckForControls(){
		
	}
	
	public int getNewY(int Vel, int ObjY){
		
		ObjY = ObjY + Vel;
		
		return ObjY;
		
	}
	
	public float getNewAcceleration(int Acc){
		return Acc + 0.5;
	}
	
	public int getVelocity(int Vel, int Acc){
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
