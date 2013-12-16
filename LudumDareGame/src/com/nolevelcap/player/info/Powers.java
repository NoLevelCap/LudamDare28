package com.nolevelcap.player.info;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import com.nolevelcap.main.MainGame;
import com.nolevelcap.main.MouseHandling;
import com.nolevelcap.main.State;
import com.nolevelcap.player.Player;

import mdesl.graphics.Button;
import mdesl.graphics.Color;
import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;
import mdesl.graphics.text.BitmapFont;

public class Powers {
	
	public Power currentPower;
	public Power hoverPower;
	public ArrayList<Power> allPowers;
	public SpriteBatch draw;
	public Texture powersTileset, Background, submitButton, titleBackground, descBackground, icon;
	MouseHandling mouse;
	private BitmapFont font;
	private Button SubmitButton;
	private Button BuyButton;
	public Player player;
	private MainGame game;
	
	public Powers(SpriteBatch draw, MouseHandling mouse, BitmapFont font, Player player, MainGame game){
		this.draw = draw;
		this.mouse = mouse;
		this.font = font;
		this.player = player;
		this.game = game;
		try {
			this.powersTileset = new Texture(draw.getResource("Powers.png"));
			this.Background = new Texture(draw.getResource("Desktop.png"));
			this.descBackground = new Texture(draw.getResource("powers-backgroundLarge.png"));
			this.titleBackground = new Texture(draw.getResource("powers-background.png"));
			this.submitButton = new Texture(draw.getResource("submitButton.png"));
			this.icon = new Texture(draw.getResource("intellect.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SubmitButton = new Button("Submit", draw, font, 0);
		BuyButton = new Button("Buy", draw, font, 1);
		
		initPowers();
	}
	
	public void initPowers(){
		allPowers = new ArrayList<Power>();
		
		Power HolySlimes = new HolySlimes(0, 0, powersTileset, draw);
		allPowers.add(HolySlimes);
		
		Power ResearchContraption = new ResearchContraption(1, 0, powersTileset, draw);
		allPowers.add(ResearchContraption);
		
		Power Gore2Gold = new Gore2Gold(2, 0, powersTileset, draw);
		allPowers.add(Gore2Gold);
		
		Power FountainOfYouth = new FountainOfYouth(0, 1, powersTileset, draw);
		allPowers.add(FountainOfYouth);
		
		Power SuperStar = new SuperStar(1, 1, powersTileset, draw);
		allPowers.add(SuperStar);
		
		Power HolySlimes3 = new HolySlimes(2, 1, powersTileset, draw);
		allPowers.add(HolySlimes3);
		
		Power HolySlimes4 = new HolySlimes(0, 2, powersTileset, draw);
		allPowers.add(HolySlimes4);
		
		Power HolySlimes5 = new HolySlimes(1, 2, powersTileset, draw);
		allPowers.add(HolySlimes5);
		
		Power MysteryBox = new MysteryBox(2, 2, powersTileset, draw);
		allPowers.add(MysteryBox);
		
		currentPower = new Power(0, 0, 0, powersTileset, draw, 0, new String[] {"Select a tome/power to see", "information about it."}, "The Tome of Help");
		hoverPower = new Power(0, 0, 0, powersTileset, draw, 0, new String[] {"Select a tome/power to see", "information about it."}, "The Tome of Help");
	}
	
	public void render(BitmapFont font){
		draw.begin();
		draw.draw(Background, 0, 0, Display.getWidth(), Display.getHeight());
		
		renderInfo(750,25);
		
		SubmitButton.render(1080, 653);
		
		if(!hoverPower.purchased){
		BuyButton.render(900, 653);
		}
		
		draw.draw(submitButton, 750, 653, 128, 64);
		
		draw.draw(icon, 775, 678, 15, 15);
		draw.setColor(Color.BLACK);
		font.drawBigText(draw, player.intellect+"", 795, 678, 0.5f);
		draw.setColor(Color.WHITE);
		
		for (Power power: allPowers){
			power.render(10, 15, font);
		}
		draw.end();
	}
	
	public void logic(MouseHandling mouse){
	for (Power power: allPowers){
		power.logic(mouse, this);
	}
	SubmitButton.logic(mouse, this);
	BuyButton.logic(mouse, this);
	}
	
	public void renderInfo(int x, int y){
	draw.draw(titleBackground, x, y, 460, 128);
	draw.setColor(Color.BLACK);
	font.drawBigText(draw, hoverPower.name, x+40, y+45, 1.2f);
	draw.setColor(Color.WHITE);
	
	draw.draw(descBackground, x, y+118, 460, 500);
	hoverPower.renderNoShift(x+30, y+150, font, 120, 120);
	draw.setColor(Color.BLACK);
	if(currentPower.desc.length>1){
		int lineno = 1;
		for (String s: hoverPower.desc){
		font.drawText(draw, s, x+170, y+140+((font.getLineHeight()/2)*lineno), 2);
		lineno++;
		}
	}
	draw.setColor(Color.WHITE);
	draw.draw(icon, x+250, y+410, 40, 40);
	draw.setColor(Color.BLACK);
	font.drawBigText(draw, hoverPower.costAmount+"", x+300, y+410, 1.2f);
	draw.setColor(Color.WHITE);
	
	currentPower.renderNoShift(x+30, y+472, font, 120, 120);
	draw.setColor(Color.BLACK);
	int lineno = 4;
	font.drawText(draw, "Selected Power", x+170, y+485, 2);
	font.drawText(draw, currentPower.name, x+170, y+475+((font.getLineHeight()/2)*2), 2);
	font.drawText(draw, currentPower.desc[0], x+170, y+485+((font.getLineHeight()/2)*3), 2);
	if(currentPower.desc.length>1){
	font.drawText(draw, currentPower.desc[1], x+170, y+485+((font.getLineHeight()/2)*4), 2);
	}
	draw.setColor(Color.WHITE);
	draw.draw(icon, x+170, y+485+((font.getLineHeight()/2)*5), 15, 15);
	draw.setColor(Color.BLACK);
	font.drawText(draw, currentPower.costAmount+"", x+190, y+485+((font.getLineHeight()/2)*5), 2);
	draw.setColor(Color.WHITE);
}
	public boolean purchase(int cost){
		if(player.intellect - cost > 0){
		player.intellect -= cost;
		return true;
		} else {
		return false;
		}
	}
	
	public void changeGameState(State playGameState){
		game.switchGameState(playGameState);
	}
}
