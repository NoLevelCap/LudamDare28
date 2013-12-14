package com.nolevelcap.player.info;

import java.io.IOException;
import java.util.ArrayList;

import com.nolevelcap.main.MouseHandling;

import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;

public class Powers {
	
	public Power currentPower;
	public ArrayList<Power> allPowers;
	public SpriteBatch draw;
	public Texture powersTileset;
	MouseHandling mouse;
	
	public Powers(SpriteBatch draw, MouseHandling mouse){
		this.draw = draw;
		this.mouse = mouse;
		try {
			this.powersTileset = new Texture(draw.getResource("player/Powers.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		initPowers();
	}
	
	public void initPowers(){
		allPowers = new ArrayList<Power>();
		
		Power HolySlimes = new HolySlimes(0, 0, powersTileset);
		allPowers.add(HolySlimes);
		
		Power ResearchContraption = new ResearchContraption(1, 0, powersTileset);
		allPowers.add(ResearchContraption);
		
		Power Gore2Gold = new Gore2Gold(2, 0, powersTileset);
		allPowers.add(Gore2Gold);
		
		Power FountainOfYouth = new FountainOfYouth(0, 1, powersTileset);
		allPowers.add(FountainOfYouth);
		
		Power SuperStar = new SuperStar(1, 1, powersTileset);
		allPowers.add(SuperStar);
		
		Power HolySlimes3 = new HolySlimes(2, 1, powersTileset);
		allPowers.add(HolySlimes3);
		
		Power HolySlimes4 = new HolySlimes(0, 2, powersTileset);
		allPowers.add(HolySlimes4);
		
		Power HolySlimes5 = new HolySlimes(1, 2, powersTileset);
		allPowers.add(HolySlimes5);
		
		Power MysteryBox = new MysteryBox(2, 2, powersTileset);
		allPowers.add(MysteryBox);
	}
	
	public void render(){
		draw.begin();
		for (Power power: allPowers){
			power.render(draw, 10, 15);
		}
		draw.end();
	}
	
public void logic(MouseHandling mouse){
	for (Power power: allPowers){
		power.logic(mouse);
	}
	}
	
	
}
