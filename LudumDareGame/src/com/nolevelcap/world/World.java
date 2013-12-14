package com.nolevelcap.world;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import com.nolevelcap.main.ResourceLoader;

import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;

public class World {
	
	public Tile Tiles[][] = new Tile[50][3];
	private SpriteBatch draw;
	public Texture Background;
	public Texture Opacity;
	
	public World(SpriteBatch draw){
		this.draw = draw;
		try {
			this.Background = new Texture(this.draw.getResource("Background.png"));
			this.Opacity = new Texture(this.draw.getResource("CloudFilter.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		create();
	}
	
	public void render(){
		draw.begin();
		draw.draw(Background, 0, 0, Display.getWidth()*2, Display.getHeight());
		draw.draw(Opacity, 0, 0, Display.getWidth()*2, Display.getHeight());
		for(int vno = 0; vno<= 2; vno++){
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
		for(int vno = 0; vno<= 2; vno++){
			for(int lineno = 0; lineno<= 49; lineno++){
				Tile tile = new Tile(lineno, vno+1, 0, draw);
				Tiles[lineno][vno] = tile;
			}
		}
	}
}
