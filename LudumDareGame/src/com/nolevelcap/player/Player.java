package com.nolevelcap.player;

import java.awt.Rectangle;
import java.io.IOException;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import com.nolevelcap.main.MainGame;
import com.nolevelcap.player.effects.JumpEffect;
import com.nolevelcap.world.World;

import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;
import mdesl.graphics.TextureRegion;

public class Player {

	private TextureRegion Currentplayer;
	private TextureRegion player[];
	private Texture playerTileSet;
	private SpriteBatch draw;
	private int width = 64;
	private int height = 128;
	private Vector2f location;
	private float YVelocity;
	private float YAcceleration;
	private float XAcceleration;
	private Rectangle collisionBox;
	private World world;
	private Physics physics;
	private float ly, dy;
	private boolean inAir;
	private long sinceLastMove, currentTime, lastTime;
	private int AnimationIndex;
	private boolean flipped, moving, jumped, colliding, scrolling;
	private JumpEffect jump;
	
	public Player(SpriteBatch draw, World world, Physics physics){
		this.world = world;
		this.physics = physics;
		this.draw = draw;
		jump = new JumpEffect(draw);
		try {
			this.playerTileSet = new Texture(this.draw.getResource("player//playerTileset.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		location = new Vector2f(50, 128);
		collisionBox = new Rectangle((int) location.x, (int) location.y, width, height);
		ly = location.y;
		dy = location.y;
		initFrames();
	}
	
	public void render(){
		draw.begin();
		draw.draw(Currentplayer, location.x, location.y, width, height);
		draw.end();
		if(inAir ){
		jump.render(location.x, location.y);
		} 
	}
	
	public void logic(){
		
		if (world.shift == 0){
			scrolling = false;
		} else {
			scrolling = true;
		}
		
		if (YVelocity > 0 || YVelocity < 0){
			System.out.println("Velocity"+YVelocity+" inAir"+inAir+" location.y"+location.y);
		System.out.println(location.y);
		}
		YAcceleration  = physics.getNewAcceleration(YAcceleration);
		
		YVelocity = physics.getVelocity(YVelocity, YAcceleration);
		
		dy = physics.getNewY(YVelocity, dy);
		
		Rectangle desiredRect = new Rectangle((int) location.x,(int) dy, width, height);
		
		for(int vno = 0; vno<= world.TileHeight-1; vno++){
			for(int lineno = 0; lineno<= world.WorldLength-1; lineno++){
				if(physics.checkForCollision(desiredRect, world.Tiles[lineno][vno].collisionBox)){
				//System.out.println("collision");
					colliding = true;
					YVelocity = 0;
					dy = ly;
				} 
			}
		}
		
		ly = dy;
		location.y = dy;
		
		if (location.y>295){
			inAir = false;
			jumped = false;
		} else {
			inAir = true;
		}

	}
	
	public void animation(){
		int AnimationSpeed = 100;
		if(moving){
			AnimationSpeed = 40;
		}
		currentTime = getTime();
		sinceLastMove = currentTime - lastTime;
		//System.out.println(sinceLastMove);
		if(scrolling && !moving || moving && !inAir || !flipped && !inAir && scrolling){
		if (sinceLastMove > AnimationSpeed){
			System.out.println("Activate+"+sinceLastMove);
			if(AnimationIndex<3){
				System.out.println("Activate ++");
				AnimationIndex+=1;
			} else {
				System.out.println("Activate 0");
				AnimationIndex = 0;
			}
			sinceLastMove = 0;
			lastTime = currentTime;
			Currentplayer = player[AnimationIndex];
		}}
		moving = false;
	}
	
	public void initFrames(){
		player = new TextureRegion[4];
		player[0] = new TextureRegion(playerTileSet, 0, 0, width/4, height/4);
		player[1] = new TextureRegion(playerTileSet, width/4, 0, width/4, height/4);
		player[2] = new TextureRegion(playerTileSet, width/2, 0, width/4, height/4);
		player[3] = new TextureRegion(playerTileSet, width-16, 0, width/4, height/4);
		Currentplayer = player[0];
		sinceLastMove = 0;
		AnimationIndex = 0;
		lastTime = getTime();
		System.out.println(lastTime);
	}
	
	public void flipFrames(){
		if(!flipped){
			try {
				playerTileSet = new Texture(this.draw.getResource("player//rplayerTileset.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			player = new TextureRegion[4];
			player[0] = new TextureRegion(playerTileSet, 0, 0, width/4, height/4);
			player[1] = new TextureRegion(playerTileSet, width/4, 0, width/4, height/4);
			player[2] = new TextureRegion(playerTileSet, width/2, 0, width/4, height/4);
			player[3] = new TextureRegion(playerTileSet, width-16, 0, width/4, height/4);
			Currentplayer = player[AnimationIndex];
			flipped = true;
		} else {
			try {
				playerTileSet = new Texture(this.draw.getResource("player//playerTileset.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			player = new TextureRegion[4];
			player[0] = new TextureRegion(playerTileSet, 0, 0, width/4, height/4);
			player[1] = new TextureRegion(playerTileSet, width/4, 0, width/4, height/4);
			player[2] = new TextureRegion(playerTileSet, width/2, 0, width/4, height/4);
			player[3] = new TextureRegion(playerTileSet, width-16, 0, width/4, height/4);
			Currentplayer = player[0];
			flipped = false;
		}
	}
	
	public void checkForInput(){
		//Controls to be placed here
		Display.processMessages();
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			if(YVelocity < 1 && YVelocity > -1 && !inAir){
			    YVelocity -= 20;
				System.out.println("Velocity"+YVelocity+" inAir"+inAir);
				jumped = true;
			    }
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			if(YVelocity < 1 && YVelocity > -1 && !inAir){
			    YVelocity -= 20;
				System.out.println("Velocity"+YVelocity+" inAir"+inAir);
				jumped = true;
			}
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			if(flipped){
				flipFrames();
			}
			if(XAcceleration < 10){
			XAcceleration += 0.5;
			}
			moving = true;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			if(!flipped){
				flipFrames();
			}
			if(XAcceleration > -10){
				XAcceleration -= 0.5;
			}
			moving = true;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
			if(flipped){
			flipFrames();
			}
		}
		
		location.x += XAcceleration;
		if (XAcceleration > 0 && !moving){
			XAcceleration -= 1;
		} else if (XAcceleration < 0 && !moving){
			XAcceleration += 1;
		}
		//For toggle inputs
	}
	
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
}
