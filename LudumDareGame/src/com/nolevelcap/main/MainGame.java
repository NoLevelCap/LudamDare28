package com.nolevelcap.main;

import static org.lwjgl.opengl.GL11.glClearColor;
import static com.nolevelcap.main.State.*;

import java.io.File;

import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;
import mdesl.graphics.text.BitmapFont;
import mdesl.test.Game;
import mdesl.test.SimpleGame;

import org.lwjgl.LWJGLException;
import org.newdawn.slick.util.ResourceLoader;

import com.nolevelcap.entity.Enemies;
import com.nolevelcap.player.Physics;
import com.nolevelcap.player.Player;
import com.nolevelcap.player.info.Powers;
import com.nolevelcap.world.World;


public class MainGame extends SimpleGame {
	
	private static final int SWIDTH = 1280;
	private static final int SHEIGHT = 720;
	private SpriteBatch draw;
	public Texture tex;
	private ResourceLoad resourceloader;
	private ClassLoader classLoader;
	private World world;
	public Player player;
	private Physics physics;
	private Enemies enemies;
	private UI ui;
	private Powers powers;
	private About about;
	private GameOver gameover;
	private TitleScreen mainmenu;
	private MouseHandling mouse;
	public long timestart;
	private BitmapFont MainFont, MenuFont, AltFont;
	
	private State GAME_STATE;
	
	public static void main(String[] args) throws LWJGLException{
		//System.setProperty("org.lwjgl.librarypath", new  File("nat").getAbsolutePath());
		Game game = new MainGame();
		game.setDisplayMode(SWIDTH, SHEIGHT, false);
		game.start();
		
	}

	protected void resize() throws LWJGLException {
		super.resize();
	}

	protected void render() throws LWJGLException {
		super.render();
		
		switch (GAME_STATE){
			case PLAY_GAME_STATE:
				player.animation();
				
				world.render();
				enemies.render();
				ui.render();
				player.render();
				break;
			case POWERS_MENU_STATE:
				powers.render(MainFont);
				break;
			case ABOUT_STATE:
				about.render();
				break;
			case GAME_OVER_STATE:
				gameover.render();
				break;
			case MAIN_MENU_STATE:
				mainmenu.animation();
				mainmenu.render();
				break;
		}
		
		logic();
		
	}

	protected void create() throws LWJGLException {
		super.create();
		
		glClearColor(0.5f, .5f, .5f, 1f);
		
		draw = new SpriteBatch();
		
		classLoader = Thread.currentThread().getContextClassLoader();
		resourceloader = new ResourceLoad(draw, classLoader);
		
		MainFont = resourceloader.LoadFont("MainFont.fnt", "MainFont_0.png");
		MenuFont = resourceloader.LoadFont("TitleFont.fnt", "TitleFont_0.png");
		AltFont = resourceloader.LoadFont("Alt.fnt", "Alt_0.png");
		
		mainmenu = new TitleScreen(draw, MainFont, this);
		world = new World(draw);
		physics = new Physics(this);
		mouse = new MouseHandling();
		enemies = new Enemies(draw, this, physics, world);
		player = new Player(draw, world, physics, mouse, enemies);
		powers = new Powers(draw, mouse, MainFont, player, this);
		about = new About(draw, MenuFont, AltFont, this);
		gameover = new GameOver(draw, MenuFont, AltFont, this);
		
		resourceloader.initTextures();
		
		timestart = this.getTime();
		
		GAME_STATE = MAIN_MENU_STATE;
		
	}

	protected void dispose() throws LWJGLException {
		super.dispose();
	}
	
	
	protected void logic() {
		mouse.logic();
		switch (GAME_STATE){
			case PLAY_GAME_STATE:
				player.checkForInput();
				enemies.checkForInput();
				world.logic();
				player.logic();
				enemies.logic();
				ui.logic();
				break;
			case MAIN_MENU_STATE:
				mainmenu.checkForInput();
				break;
			case POWERS_MENU_STATE:
				powers.logic(mouse);
				break;
			case ABOUT_STATE:
				about.checkForInput();
				break;
			case GAME_OVER_STATE:
				gameover.checkForInput();
				break;
			case HELP_STATE:
				break;
		default:
				
			break;
		}
	}
	
	public ResourceLoad getResourceLoader(){
		return resourceloader;
	}
	
	public void switchGameState(State playGameState){
		GAME_STATE = playGameState;
		stateChange(GAME_STATE);
	}
	
	public void stateChange(State STATE){
		switch (STATE){
		case PLAY_GAME_STATE:
			player.setCurrentPower(powers.currentPower);
			ui = new UI(draw, player, mouse, MainFont);
			break;
		case POWERS_MENU_STATE:
			break;
		case MAIN_MENU_STATE:
			break;
		default:
			break;
	}
	}
	
	
}
