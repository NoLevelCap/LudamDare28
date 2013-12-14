package com.nolevelcap.main;

import static org.lwjgl.opengl.GL11.glClearColor;

import java.io.IOException;
import java.net.URL;

import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;
import mdesl.test.Game;
import mdesl.test.SimpleGame;

import org.lwjgl.LWJGLException;


public class MainGame extends SimpleGame {
	
	private static final int SWIDTH = 1280;
	private static final int SHEIGHT = 720;
	private SpriteBatch draw;
	public Texture tex;
	private ResourceLoader resourceloader;
	private ClassLoader classLoader;
	
	public static void main(String[] args) throws LWJGLException{
		Game game = new MainGame();
		game.setDisplayMode(SWIDTH, SHEIGHT, false);
		game.start();
		
	}

	protected void resize() throws LWJGLException {
		super.resize();
	}

	protected void render() throws LWJGLException {
		super.render();
		
		logic();
		
		draw.begin();
		draw.draw(resourceloader.Textures.get("blue"), 0, 0, 500, 500);
		draw.end();
	}

	protected void create() throws LWJGLException {
		super.create();
		
		glClearColor(0.5f, .5f, .5f, 1f);
		
		draw = new SpriteBatch();
		
		classLoader = Thread.currentThread().getContextClassLoader();
		resourceloader = new ResourceLoader(classLoader);
		
		try {
			resourceloader.initTextures();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void dispose() throws LWJGLException {
		super.dispose();
	}
	
	
	protected void logic() {
		
	}
	
}
