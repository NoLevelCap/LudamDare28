package com.nolevelcap.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;
import mdesl.graphics.text.BitmapFont;

public class ResourceLoad {
	
	private ClassLoader classloader;
	public HashMap<String, Texture> Textures;
	public HashMap<String, Texture> TextureRegions;
	private SpriteBatch draw;
	
	public ResourceLoad(SpriteBatch draw, ClassLoader cl){
		this.draw = draw;
		this.classloader = cl;
	}
	
	public BitmapFont LoadFont(String fntFile, String imgFile) {
		BitmapFont font;
		try {
			font = new BitmapFont(draw.getResource(fntFile), draw.getResource(imgFile));
			return font;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public File LoadFromFile(String loc){
		System.out.println(loc);
		File file = new File(loc);
		return file;
	}
	
	public void ReadTextures(File file) throws IOException{
		Scanner FileReader = new Scanner(file);
		String TextureReference = getTextureReference(FileReader);
		FileReader.close();
	}
	
	public String getTextureReference(Scanner FileReader){
		if(FileReader.hasNext()){
			String next = FileReader.next();
			String TextureReference = "";
			if(FileReader.hasNext()){
			do {
				System.out.println("Has Next");
				TextureReference += next;
				next = FileReader.next();
			} while (!(next == ":") && FileReader.hasNext());
			System.out.println(TextureReference);
			} 
			return TextureReference;
		} else {
				System.out.println("EMERGENCY FILE EMPTY");
				return null;
		}
	}
	
	public String getTexURL(Scanner FileReader){
			return null;
	}
	
	public void initTextures() {
		Textures = new HashMap<String, Texture>();
		try {
			Textures.put("blue", new Texture(classloader.getResource("blue.png")));
			Textures.put("test", new Texture(classloader.getResource("testImage.png")));
			Textures.put("icon-i", new Texture(classloader.getResource("intellect.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
