package com.nolevelcap.entity;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import org.lwjgl.util.vector.Vector2f;

import com.nolevelcap.player.Physics;
import com.nolevelcap.player.Player;
import com.nolevelcap.world.World;

import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;
import mdesl.graphics.TextureRegion;

public class Enemy {
		
		private Texture tileset;
		public TextureRegion[] texture;
		SpriteBatch draw;
		private long sinceLastMove, currentTime, lastTime;
		private float YVelocity;
		private float YAcceleration;
		private float XAcceleration;
		public Vector2f location;
		Physics physics;
		World world;
		private float ly, dy;
		private boolean inAir, colliding, playercollided;
		public int width = 64;
		public int height = 128;
		private Random ran;
		public Rectangle boundingBox;
		public int health = 2;
		Player player;
		Enemies enemy;
		
		public Enemy(SpriteBatch draw, Physics physics, World world,int drawx,int drawy, Player player, Enemies enemy){
			this.draw = draw;
			this.physics = physics;
			this.world = world;
			this.ran = new Random();
			this.player = player;
			this.enemy = enemy;
			try {
				tileset = new Texture(draw.getResource("Enemy/GreenSlime.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			initTexture();
			location = new Vector2f(drawx, drawy);
			ly = location.y;
			dy = location.y;
			boundingBox = new Rectangle((int) location.x,(int) location.y, width, height);
			
		}
		
		public void Render(){
			draw.draw(texture[0], location.x, location.y, width, height);
			boundingBox.setRect(location.x, location.y, width, height);
		}
		
		public void Logic(){
			
			
			YAcceleration  = physics.getNewAcceleration(YAcceleration);
			
			YVelocity = physics.getVelocity(YVelocity, YAcceleration);
			
			dy = physics.getNewY(YVelocity, dy);
			
			Rectangle desiredRect = new Rectangle((int) location.x,(int) dy, width, height);
			
			for(int vno = 0; vno<= world.TileHeight-1; vno++){
				for(int lineno = 0; lineno<= world.WorldLength-1; lineno++){
					if(world.shift+lineno*world.Tiles[lineno][vno].width < 2000 && world.shift+lineno*world.Tiles[lineno][vno].width > -200){
						if(physics.checkForCollision(desiredRect, world.Tiles[lineno][vno].collisionBox)){
						//System.out.println("collision");
							colliding = true;
							YVelocity = 0;
							dy = ly;
						} 
					}
				}
			}
			
			ly = dy;
			location.y = dy;
			
			if (location.y>295){
				inAir = false;
			} else {
				inAir = true;
			}
			
			AI();
			
			location.x += XAcceleration;
			if (XAcceleration > 0){
				XAcceleration -= 0.5;
			} else if (XAcceleration < 0){
				XAcceleration += 0.5;
			}
		}
		
		public void AI(){
			int chance = ran.nextInt(20);
			chance += 1;
			switch(chance){
			case 1: 
				int fdist = 5;
				if(location.x + width + fdist < 1000){	
					//System.out.println("OKF"+(location.x + fdist));
					walkForwards(fdist);
				}
				//System.out.println("Walk Forwards: "+this);
				break;
			case 2:
				int bdist = 5;
				if(location.x - bdist > 0){
					//System.out.println("OKB"+(location.x - bdist));
					walkBackwards(bdist);
				}
				//System.out.println("Walk Backwards: "+this);
				break;
			case 3:
				if(colliding && !inAir){
					int Jheight  = ran.nextInt(10);
					Jump(Jheight);
					//System.out.println("Jump: "+this);
				}
				break;
			default:
				//System.out.println("Do Nothing: "+this);
				break;
			}
		}
		
		public boolean canCollide(){
			if(playercollided){
				int AnimationSpeed = 1000;
				currentTime = physics.getTime();
				sinceLastMove = currentTime - lastTime;
				if (sinceLastMove > AnimationSpeed){
					sinceLastMove = 0;
					lastTime = currentTime;
					return false;
				} else {
					return false;
				}
			} else {
				return true;
			}
		}
		
		public void collided(){
			playercollided = true;
		}
		
		private void Jump(float Height){
		    YVelocity -= Height;
		}
		
		private void walkForwards(float distance){
		    XAcceleration += distance;
		}
		
		private void walkBackwards(float distance){
		    XAcceleration -= distance;
		}
		
		public void Animation(){
			
		}
		
		public void initTexture(){
			this.texture = new TextureRegion[4];
			this.texture[0] = new TextureRegion(tileset, 0, 0, 16, 32);
			this.texture[1] = new TextureRegion(tileset, 32, 0, 16, 32);
			this.texture[2] = new TextureRegion(tileset, 64, 0, 16, 32);
			this.texture[3] = new TextureRegion(tileset, 96, 0, 16, 23);
		}
		
		public void onDeath(){
			System.out.print("TEST");
			for (int i = 0; i <= player.goldSpawnRate; i++){
				enemy.spawnGoldDrop((int) location.x,(int) location.y);
			}
			
			for (int i = 0; i <= player.intellectSpawnRate; i++){
				enemy.spawnIntellectDrop((int) location.x,(int) location.y);
			}
		}
}
