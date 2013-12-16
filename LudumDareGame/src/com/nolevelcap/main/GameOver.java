package com.nolevelcap.main;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import mdesl.graphics.Color;
import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;
import mdesl.graphics.text.BitmapFont;

public class GameOver {
	
	SpriteBatch draw;
	BitmapFont MainMenuFont;
	BitmapFont MainFont;
	MainGame game;
	Texture Background;
	
	public GameOver(SpriteBatch draw, BitmapFont MainMenuFont, BitmapFont MainFont, MainGame game){
		this.draw = draw;
		this.MainFont = MainFont;
		this.MainMenuFont = MainMenuFont;
		this.game = game;
		try {
			this.Background = new Texture(draw.getResource("propperback.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(){
		draw.begin();
		draw.setColor(Color.WHITE);
		draw.draw(Background, 0, 0, Display.getWidth(), Display.getHeight());
		displayText();
		draw.end();
	}

	public void displayText() {
		draw.setColor(Color.BLACK);
		MainMenuFont.drawBigText(draw, "Game Over", 135, 10, 3);
		MainFont.drawBigText(draw, "Thank-You for playing, I know most of it does not work.", 200, 150, 1);
		MainFont.drawBigText(draw, "You got "+game.player.gold+" gold, and "+game.player.intellect+" intellect!", 200, 150+(MainFont.getLineHeight()*1), 1);
		MainFont.drawBigText(draw, "If I had more time I would have added more powers", 200, 150+(MainFont.getLineHeight()*2), 1);
		MainFont.drawBigText(draw, "and fixed more of the game.", 200, 150+(MainFont.getLineHeight()*3), 1);
		
		MainFont.drawBigText(draw, "Press \"escape\" to go back to the powers menu.", 300, 720-(MainFont.getLineHeight()), 1);
	}
	
	public void checkForInput(){
		//Controls to be placed here
		Display.processMessages();
		
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			game.switchGameState(State.MAIN_MENU_STATE);
		}

	}
}
