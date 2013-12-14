package com.nolevelcap.main;

import static org.lwjgl.opengl.GL11.glClearColor;

import java.io.IOException;
import java.net.URL;

import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;
import mdesl.test.Game;
import mdesl.test.SimpleGame;

import org.lwjgl.LWJGLException;

import com.nolevelcap.player.Physics;
import com.nolevelcap.player.Player;
import com.nolevelcap.world.World;


public class MainGame extends SimpleGame {
	
	private static final int SWIDTH = 1280;
	private static final int SHEIGHT = 720;
	private SpriteBatch draw;
	public Texture tex;
	private ResourceLoader resourceloader;
	private ClassLoader classLoader;
	private World world;
	private Player player;
	private Physics physics;
	public long timestart;
	
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
		
		world.render();
		player.render();
		
		logic();
		
	}

	protected void create() throws LWJGLException {
		super.create();
		
		glClearColor(0.5f, .5f, .5f, 1f);
		
		draw = new SpriteBatch();
		
		classLoader = Thread.currentThread().getContextClassLoader();
		resourceloader = new ResourceLoader(classLoader);
		world = new World(draw);
		physics = new Physics(this);
		player = new Player(draw, world, physics);
		
		try {
			resourceloader.initTextures();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		timestart = this.getTime();
	}

	protected void dispose() throws LWJGLException {
		super.dispose();
	}
	
	
	protected void logic() {
		player.logic();
	}
	
	public ResourceLoader getResourceLoader(){
		return resourceloader;
	}
	
}
