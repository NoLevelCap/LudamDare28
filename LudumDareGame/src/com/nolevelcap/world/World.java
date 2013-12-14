package com.nolevelcap.world;

import java.util.ArrayList;

import com.nolevelcap.main.ResourceLoader;

import mdesl.graphics.SpriteBatch;

public class World {
	
	private Tile Tiles[][] = new Tile[50][2];
	private SpriteBatch draw;
	
	public World(SpriteBatch draw){
		this.draw = draw;
		create();
	}
	
	public void render(){
		draw.begin();
		for(int vno = 0; vno<= 1; vno++){
			for(int lineno = 0; lineno<= 49; lineno++){
				Tiles[lineno][vno].render();
			}
		}
		draw.end();
	}
	
	public void logic(){
		
	}
	
	public void create(){
		//Generation Code
		for(int vno = 0; vno<= 1; vno++){
			for(int lineno = 0; lineno<= 49; lineno++){
				Tile tile = new Tile(lineno, vno+1, 1, draw);
				Tiles[lineno][vno] = tile;
			}
		}
	}
}