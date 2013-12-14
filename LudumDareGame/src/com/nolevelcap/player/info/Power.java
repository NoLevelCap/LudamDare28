package com.nolevelcap.player.info;

import java.awt.Rectangle;

import com.nolevelcap.main.MouseHandling;

import mdesl.graphics.Color;
import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;
import mdesl.graphics.TextureRegion;

public class Power {

	public TextureRegion texture;
	public int xref, yref;
	private static final int width = 230;
	private static final int height = 230;
	private Rectangle boundingBox; 
	private boolean mouseHover;
	public Power(int type, int xref, int yref, Texture source){
		texture = getTextureRegion(type, source);
		this.xref = xref;
		this.yref = yref;
	}
	
	public void logic(MouseHandling mouse){
		mouseHover = mouse.mouseHover(boundingBox);
		//System.out.println("Collision for" + this+ ": "+mouseHover);
	}

	public void render(SpriteBatch draw, int ox, int oy) {
		if(yref > 0){
		draw.setColor(Color.BLACK);
		} else {
			if (mouseHover) {
				System.out.print("WHITE");
				draw.setColor(Color.WHITE);
			} else {
				draw.setColor(Color.GRAY);
			}
		}
		draw.draw(texture, ox+(xref*width), oy+(yref*height), width, height);
		boundingBox = new Rectangle(ox+(xref*width), oy+(yref*height), width, height);
		draw.setColor(Color.WHITE);
	}
	
	public TextureRegion getTextureRegion(int type, Texture source){
		switch (type){
		case 0:
			return new TextureRegion(source, 0, 0, 32, 32);
		case 1:
			return new TextureRegion(source, 32, 0, 32, 32);
		case 2:
			return new TextureRegion(source, 64, 0, 32, 32);
		case 3:
			return new TextureRegion(source, 96, 0, 32, 32);
		case 4:
			return new TextureRegion(source, 128, 0, 32, 32);
		case 5:
			return new TextureRegion(source, 160, 0, 32, 32);
		case 6:
			return new TextureRegion(source, 192, 0, 32, 32);
		case 7:
			return new TextureRegion(source, 224, 0, 32, 32);
		case 8:
			return new TextureRegion(source, 0, 32, 32, 32);
		default:
			return new TextureRegion(source, 0, 0, 32, 32);
		}
	}
	
	public void onClick(){
		
	}
}
