package com.nolevelcap.entity;

import java.awt.Rectangle;
import java.io.IOException;

import org.lwjgl.util.vector.Vector2f;

import com.nolevelcap.player.Physics;
import com.nolevelcap.player.Player;
import com.nolevelcap.world.World;

import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;

public class Drop {
	
	public Rectangle boundingBox;
	Texture tex;
	Vector2f location;
	SpriteBatch draw;
	int type;
	private float YVelocity;
	private float YAcceleration;
	private float XAcceleration;
	Physics physics;
	World world;
	private float ly, dy;
	private boolean inAir, colliding, playercollided;
	
	public Drop(int x, int y, int type, SpriteBatch draw, Physics physic, World world){
		this.draw = draw;
		this.type = type;
		this.physics = physic;
		this.world = world;
		location = new Vector2f(x, y);
		getInfo(type);
	}
	
	public void render(){
		draw.draw(tex, location.x, location.y, tex.getWidth(), tex.getHeight());
		setBoundingBox(new Rectangle((int) location.x, (int) location.y, tex.getWidth(), tex.getHeight()));
	}
	
	public void logic(){
		YAcceleration  = physics.getNewAcceleration(YAcceleration);
		
		YVelocity = physics.getVelocity(YVelocity, YAcceleration);
		
		dy = physics.getNewY(YVelocity, dy);
		
		Rectangle desiredRect = new Rectangle((int) location.x,(int) dy, tex.getWidth(), tex.getHeight());
		
		for(int vno = 0; vno<= world.TileHeight-1; vno++){
			for(int lineno = 0; lineno<= world.WorldLength-1; lineno++){
				if(world.shift+lineno*world.Tiles[lineno][vno].width < 2000 && world.shift+lineno*world.Tiles[lineno][vno].width > -200){
					if(physics.checkForCollision(desiredRect, world.Tiles[lineno][vno].collisionBox)){
						System.out.println("collision");
						colliding = true;
						YVelocity = 0;
						dy = ly;
					} 
				}
			}
		}
		
		ly = dy;
		location.y = dy;
		
		if (location.y>350){
			inAir = false;
		} else {
			inAir = true;
		}
	}
	
	public void setBoundingBox(Rectangle Rect){
		boundingBox = Rect;
	}
	
	public void setInformation(Texture tex){
		this.tex = tex;
		setBoundingBox(new Rectangle((int) location.x, (int) location.y, tex.getWidth(), tex.getHeight()));
	}
	
	public void getInfo(int type){
		switch(type){
		case 1:
			try {
				setInformation(new Texture(draw.getResource("Player/coin.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 0:
			try {
				setInformation(new Texture(draw.getResource("Player/intellect.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	
	public void collide(Player player){
		switch(type){
		case 1:
			player.gold += 1;
			break;
		case 0:
			player.intellect += 1;
			break;
		}
	}
}
