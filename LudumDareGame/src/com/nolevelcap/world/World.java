package com.nolevelcap.world;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.Display;

import com.nolevelcap.main.ResourceLoader;

import de.matthiasmann.twl.utils.PNGDecoder;
import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;
import mdesl.graphics.TextureRegion;

public class World {
	
	public int TileHeight = 4;
	public int WorldLength = 800;
	public Tile Tiles[][] = new Tile[WorldLength][TileHeight];
	private SpriteBatch draw;
	public Texture Background;
	public TextureRegion BackgroundR;
	public float shift;
	
	public World(SpriteBatch draw){
		this.draw = draw;
		try {
			this.Background = new Texture(draw.getResource("Background.png"));
			this.BackgroundR = new TextureRegion(Background, 0,0, 64, 464);
		} catch (IOException e) {
			e.printStackTrace();
		}
		create();
	}
	
	public void render(){
		draw.begin();
		for(int x = 0; x<= WorldLength/2 -1; x++){
			draw.draw(Background, shift+x*128, 128, 1020, 464);
		}
		for(int vno = 0; vno<= TileHeight-1; vno++){
			for(int lineno = 0; lineno<= WorldLength -1; lineno++){
				Tiles[lineno][vno].render((int) shift);
			}
		}
		draw.end();
	}
	
	public void logic(){
		shift -= 0;
	}
	
	public void create(){
		//Generation Code
		for(int vno = 0; vno<= TileHeight-1; vno++){
			for(int lineno = 0; lineno<= WorldLength-1 ; lineno++){
				Tile tile;
				
				switch (vno){
				case 0:
					tile = new Tile(lineno, vno+1, 0, draw);
					Tiles[lineno][vno] = tile;
					break;
				case 1:
					tile = new Tile(lineno, vno-1, 1, draw);
					Tiles[lineno][vno] = tile;
					break;
				case 2:
					tile = new Tile(lineno, vno-1, 2, draw);
					Tiles[lineno][vno] = tile;	
					break;
				case 3:
					tile = new Tile(lineno, vno-1, 3, draw);
					Tiles[lineno][vno] = tile;	
					break;
				}
			}
		}
		
	}
}
