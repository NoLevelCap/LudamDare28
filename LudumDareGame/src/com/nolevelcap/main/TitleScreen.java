package com.nolevelcap.main;

import java.io.IOException;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.util.ResourceLoader;

import com.nolevelcap.player.attacks.Bullet;

import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;
import mdesl.graphics.text.BitmapFont;

public class TitleScreen {
	
	SpriteBatch draw;
	BitmapFont font;
	Texture[] Background;
	Texture logo, Mainlogo;
	private boolean newpagetransition = false;
	private boolean introtransition = true;
	private boolean texttransition = false;
	private boolean reversetransition = false;
	private int animationIndex;
	private long sinceLastMove, currentTime, lastTime;
	private float opacity = 1; 
	private float Titleopacity = 1.5f;
	private int selection = 0;
	private int nextselection;
	private float dx, idx;
	private MainGame game;
	
	public TitleScreen(SpriteBatch draw, BitmapFont font, MainGame game){
		this.draw = draw;
		this.font = font;
		this.game = game;
		try {
			System.out.println(draw.getResource("/res/title.png"));
			this.logo = new Texture(draw.getResource("title.png"));
			this.Mainlogo = new Texture(draw.getResource("Maintitle.png"));
			this.Background = new Texture[10];
			this.Background[0] = new Texture(draw.getResource("propperback.png"));
			this.Background[1] = new Texture(draw.getResource("trans0.png"));
			this.Background[2] = new Texture(draw.getResource("trans1.png"));
			this.Background[3] = new Texture(draw.getResource("trans2.png"));
			this.Background[4] = new Texture(draw.getResource("trans3.png"));
			this.Background[5] = new Texture(draw.getResource("trans4.png"));
			this.Background[6] = new Texture(draw.getResource("trans5.png"));
			this.Background[7] = new Texture(draw.getResource("trans6.png"));
			this.Background[8] = new Texture(draw.getResource("trans7.png"));
			this.Background[9] = new Texture(draw.getResource("trans8.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(){
		GL11.glClearColor(0, 0, 0, 0);
		draw.begin();
		draw.setColor(1, 1, 1, opacity);
		draw.draw(Background[animationIndex], 0, 0, Display.getWidth(), Display.getHeight());
		draw.setColor(1, 1, 1, Titleopacity);
		draw.draw(logo, 0, 0, Display.getWidth(), Display.getHeight());
		draw.setColor(1, 1, 1, 1);
		draw.draw(Mainlogo, 0, 0, Display.getWidth(), Display.getHeight());
		displayText();
		draw.end();
		GL11.glClearColor(0, 0, 0, 0);
	}
	
	public void animation(){
		currentTime = getTime();
		sinceLastMove = currentTime - lastTime;
		if(newpagetransition){
			if (sinceLastMove > 75){
				if(animationIndex<9){
					animationIndex+=1;
				} else if(opacity > 0){
					opacity -= 0.1f;
				} else {
					newpagetransition = false;
				}
				sinceLastMove = 0;
				lastTime = currentTime;
			}
		} else if(introtransition) {
			if (sinceLastMove > 100){
				if(Titleopacity > 0){
					Titleopacity -= 0.1f;
				} else {
					introtransition = false;
				}
				sinceLastMove = 0;
				lastTime = currentTime;
			}
		} else {
			animationIndex = 0;
		}
		
		//setTextTransition(1);
	}
	
	public void displayText(){
		draw.setColor(0, 0, 0, 1);
		System.out.println(texttransition);
		if(texttransition){
			font.drawBigText(draw, getSelectionText(selection), (680+(int) idx)-(font.getWidth(getSelectionText(selection))), 360-(font.getLineHeight()/2), 1.5f);
			font.drawBigText(draw, getSelectionText(nextselection),  680-(font.getWidth(getSelectionText(selection)))+ (int) dx, 360-(font.getLineHeight()/2), 1.5f);
			font.drawBigText(draw, "USE RIGHT ARROW KEY TO SWITCH BETWEEN MENU ITEMS", 1200-font.getWidth("USE ARROW KEYS TO SWITCH BETWEEN MENU ITEMS"), 720-font.getLineHeight(), 0.75f);
			if(!(nextselection==selection)){
			if(dx <= 0){
				if(dx==0 || dx<5 && dx>-5){
					selection = nextselection;
					texttransition = false;
				} else {
					dx += 1 * game.getDeltaTime(); 	
				}
				} else {
					dx -= 2 * game.getDeltaTime(); 	
					idx -= 2 * game.getDeltaTime(); 	
			} 
			} else {
				texttransition = false;
			}
		}else if(reversetransition){
			font.drawBigText(draw, getSelectionText(selection), (680-(int) idx)-(font.getWidth(getSelectionText(selection))), 360-(font.getLineHeight()/2), 1.5f);
			font.drawBigText(draw, getSelectionText(nextselection),  680-(font.getWidth(getSelectionText(selection)))- (int) dx, 360-(font.getLineHeight()/2), 1.5f);
			font.drawBigText(draw, "USE RIGHT ARROW KEY TO SWITCH BETWEEN MENU ITEMS", 1200-font.getWidth("USE ARROW KEYS TO SWITCH BETWEEN MENU ITEMS"), 720-font.getLineHeight(), 0.75f);
			if(!(nextselection==selection)){
			if(dx <= 0){
				if(dx==0 || dx<5 && dx>-5){
					selection = nextselection;
					texttransition = false;
				} else {
					dx += 1 * game.getDeltaTime(); 	
				}
				} else {
					dx -= 2 * game.getDeltaTime(); 	
					idx -= 2 * game.getDeltaTime(); 	
			} 
			} else {
				reversetransition = false;
			}
		}else {
			font.drawBigText(draw, getSelectionText(selection), 680-(font.getWidth(getSelectionText(selection))), 360-(font.getLineHeight()/2), 1.5f);
			font.drawBigText(draw, "USE RIGHT ARROW KEY TO SWITCH BETWEEN MENU ITEMS", 1200-font.getWidth("USE ARROW KEYS TO SWITCH BETWEEN MENU ITEMS"), 720-font.getLineHeight(), 0.75f);
		}
		
	}
	
	public String getSelectionText(int type){
		switch (type){
		case 0:
			return "PLAY GAME";
		case 1:
			return "ABOUT";
		case 2:
			return "HELP";
		case 3:
			return "EXIT";
		default:
			return "ERROR";	
		}
	}

	
	
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public void setTextTransition(int nextSelection){
		dx = 1280;
		idx = 0; 
		texttransition = true;
		this.nextselection = nextSelection;
	}
	
	public void setReverseTextTransition(int nextSelection){
		dx = 1280;
		idx = 0; 
		reversetransition = true;
		this.nextselection = nextSelection;
	}
	
	public void checkForInput(){
		//Controls to be placed here
		Display.processMessages();
		
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)||Keyboard.isKeyDown(Keyboard.KEY_D)) {
			if(!texttransition){
			if(selection<=2){
				setTextTransition(selection+1);
			} else {
				setTextTransition(0);
			}
			}
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)||Keyboard.isKeyDown(Keyboard.KEY_A)) {
			if(!reversetransition){
			if(selection>=1){
				setReverseTextTransition(selection-1);
			} else {
				setReverseTextTransition(3);
			}
			}
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_I)) {
			if(!newpagetransition) {
				newpagetransition = true;
			} 
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
			newpagetransition = false;
			opacity = 1;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)){
			switch (selection){
			case 0:
				game.switchGameState(State.POWERS_MENU_STATE);
				break;
			case 1:
				game.switchGameState(State.ABOUT_STATE);
				break;
			case 2:
				game.switchGameState(State.HELP_STATE);
				break;
			case 3:
				game.exit();
				break;	
			default:
				break;
			}
		}
	}
		//For toggle inputs
}
