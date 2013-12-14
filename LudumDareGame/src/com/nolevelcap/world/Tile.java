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
		if(vno == 3) {
			if(lineno == 0){
				this.type = 3;
			} else if(lineno == 9){
				this.type = 5;
			} else {
				this.type = 4;
			}
		} else {
			if(lineno == 9){
				this.type = 6;
			} else {
				this.type = 1;
			}
		}
		this.draw = draw;
		try {
			this.TileSet = new Texture(this.draw.getResource("TileSet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(){
		TextureRegion tex = getType(1);
		draw.draw(getType(type), lineno*width, 720-vno*height, width, height);
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
		case 4:
			Texture = new TextureRegion(TileSet, 96, 0, 32, 32);
			return Texture;
		case 5:
			Texture = new TextureRegion(TileSet, 128, 0, 32, 32);
			return Texture;
		case 6:
			Texture = new TextureRegion(TileSet, 160, 0, 32, 32);
			return Texture;
		default:
			Texture = new TextureRegion(TileSet, 0, 0, 32, 32);
			return Texture;
		}
	}
}
