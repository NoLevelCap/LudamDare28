package com.nolevelcap.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import com.nolevelcap.main.MainGame;
import com.nolevelcap.player.Physics;
import com.nolevelcap.player.attacks.Bullet;
import com.nolevelcap.world.World;

import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;

public class Enemies {
	
	public Texture texture, but;
	SpriteBatch draw;
	public MainGame game;
	public ArrayList<Enemy> enemies;
	public ArrayList<Enemy> enemiesRemove;
	public ArrayList<Drop> drops;
	public ArrayList<Drop> dropsRemove;
	Physics physics;
	World world;
	Random ran;
	long lastSpawn, currentTime, lastTime;
	
	public Enemies(SpriteBatch draw, MainGame game, Physics physics, World world){
		this.draw = draw;
		this.game = game;
		this.physics = physics;
		this.world = world;
		this.enemies = new ArrayList<>();
		this.enemiesRemove = new ArrayList<>();
		this.drops = new ArrayList<>();
		this.dropsRemove = new ArrayList<>();
		this.ran = new Random();
		try {
			this.texture = new Texture(draw.getResource("GreenSlime.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void logic(){
		
		int spawnGap = 1000;
		currentTime = game.getTime();
		lastSpawn = currentTime - lastTime;
		if (lastSpawn > spawnGap){
			int px = ran.nextInt(1280-96);
			spawnEnemy(px, 64);
			lastTime = currentTime;
		}
		
		for (Enemy e: enemies){
			if(e.health<=0){
				e.onDeath();
				enemiesRemove.add(e);
			} else {
				e.Logic();
			}
		}
		
		for (Drop e: drops){
			e.logic();
		}
		
		enemies.removeAll(enemiesRemove);
	}
	
	public void render(){
		draw.begin();
		for (Enemy e: enemies){
			e.Render();
		}
		
		for (Drop e: drops){
			e.render();
		}
		draw.end();
	}
	
	public void checkForInput(){
		//Controls to be placed here
		Display.processMessages();
		
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			int px = ran.nextInt(1280-64);
			spawnEnemy(px, 64);
		}
		
	}
	
	public void spawnEnemy(int x, int y){
		Enemy e = new Enemy(draw, physics, world, x, y, game.player, this);
		enemies.add(e);
	}
	
	public void spawnGoldDrop(int x, int y){
		Drop e = new Drop(x, y, 1, draw, physics, world);
		drops.add(e);
	}
	
	public void spawnIntellectDrop(int x, int y){
		Drop e = new Drop(x, y, 0, draw, physics, world);
		drops.add(e);
	}
	
}
