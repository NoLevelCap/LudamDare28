package com.nolevelcap.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import mdesl.graphics.Texture;

public class ResourceLoader {
	
	private ClassLoader classloader;
	public HashMap<String, Texture> Textures;
	public HashMap<String, Texture> TextureRegions;
	
	public ResourceLoader(ClassLoader classloader){
		this.classloader = classloader;
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
	
	public void initTextures() throws IOException{
		Textures = new HashMap<String, Texture>();
		Textures.put("blue", new Texture(classloader.getResource("blue.png")));
	}
}
