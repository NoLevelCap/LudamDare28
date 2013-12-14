package com.nolevelcap.world;

import java.awt.Rectangle;
import java.io.IOException;

import com.nolevelcap.main.ResourceLoader;

import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;
import mdesl.graphics.TextureRegion;

public class Tile {
	
	private int width = 64;
	private int height = 64;
	private int lineno;
	private int vno;
	public Texture TileSet;
	private SpriteBatch draw;
	public int type;
	public Rectangle collisionBox;
	
	public Tile(int lineno, int vno, int type, SpriteBatch draw){
		this.lineno = lineno;
		this.vno = vno;
		this.type = type;
		this.draw = draw;
		try {
			this.TileSet = new Texture(this.draw.getResource("TileSet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(int worldshift){
		switch (type) {
		case 0:
			draw.draw(getType(type), worldshift+lineno*width, vno*height, width, height);
			collisionBox = new Rectangle(worldshift+lineno*width, vno*height, width, height);
		case 1:
			draw.draw(getType(type), worldshift+lineno*width, vno*height, width, height);
			collisionBox = new Rectangle(worldshift+lineno*width, vno*height, width, height);
		case 2:
			draw.draw(getType(type), worldshift+lineno*width, 720-vno*height, width, height);
			collisionBox = new Rectangle(worldshift+lineno*width, 720-vno*height, width, height);
		case 3:
			draw.draw(getType(type), worldshift+lineno*width, 720-vno*height, width, height);
			collisionBox = new Rectangle(worldshift+lineno*width, 720-vno*height, width, height);
		}
	}
	
	public TextureRegion getType(int type){
		TextureRegion Texture;
		switch (type) {
		case 0:
			Texture = new TextureRegion(TileSet, 32, 0, 32, 32);
			return Texture;
		case 1:
			Texture = new TextureRegion(TileSet, 0, 0, 32, 32);
			return Texture;
		case 2:
			Texture = new TextureRegion(TileSet, 0, 0, 32, 32);
			return Texture;
		case 3:
			Texture = new TextureRegion(TileSet, 64, 0, 32, 32);
			return Texture;
		default:
			Texture = new TextureRegion(TileSet, 0, 0, 32, 32);
			return Texture;
		}
	}
	
	public void getTileInfo(int type, int vno){
		switch (type) {
		case 0:
		case 1:
		default:
		}
	}
}
