package com.nolevelcap.world;

import java.io.IOException;

import com.nolevelcap.main.ResourceLoader;

import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;
import mdesl.graphics.TextureRegion;

public class Tile {
	
	private int width = 128;
	private int height = 128;
	private int lineno;
	private int vno;
	public Texture TileSet;
	private SpriteBatch draw;
	private int type;
	
	public Tile(int lineno, int vno, int type, SpriteBatch draw){
		this.lineno = lineno;
		this.vno = vno;
		this.type = type;
		this.draw = draw;
		try {
			this.TileSet = new Texture(this.draw.getResource("GameTileSet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(){
		TextureRegion tex = getType(1);
		draw.draw(getType(1), lineno*width, 720-vno*height, width, height);
	}
	
	public TextureRegion getType(int type){
		TextureRegion Texture;
		switch (type) {
		case 0:
			Texture = new TextureRegion(TileSet, 0, 0, 32, 32);
			return Texture;
		case 1:
			Texture = new TextureRegion(TileSet, 0, 32, 32, 32);
			return Texture;
		default:
			Texture = new TextureRegion(TileSet, 0, 0, 32, 32);
			return Texture;
		}
	}
}