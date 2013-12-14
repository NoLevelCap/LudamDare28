package com.nolevelcap.player;

import java.awt.Rectangle;
import java.io.IOException;

import org.lwjgl.util.vector.Vector2f;

import com.nolevelcap.main.MainGame;
import com.nolevelcap.world.World;

import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;

public class Player {

	private Texture player;
	private SpriteBatch draw;
	private int width = 64;
	private int height = 128;
	private Vector2f location;
	private int YVelocity;
	private int YAcceleration;
	private Rectangle collisionBox;
	private World world;
	private Physics physics;
	
	
	public Player(SpriteBatch draw, World world, Physics physics){
		this.world = world;
		this.physics = physics;
		this.draw = draw;
		try {
			this.player = new Texture(this.draw.getResource("player//player_Animation 1_0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		location = new Vector2f(50, 300);
		collisionBox = new Rectangle((int) location.x, (int) location.y, width, height);
		
	}
	
	public void render(){
		draw.begin();
		draw.draw(player, location.x, location.y, width, height);
		draw.end();
	}
	
	public void logic(){
		
		
		
		for(int vno = 0; vno<= 2; vno++){
			for(int lineno = 0; lineno<= 49; lineno++){
				if(physics.checkForCollision(collisionBox, world.Tiles[lineno][vno].collisionBox)){
				System.out.println("collision");
				}
			}
		}
	}
	
	public void SpacebarPressed(){
		
	}
	
}
