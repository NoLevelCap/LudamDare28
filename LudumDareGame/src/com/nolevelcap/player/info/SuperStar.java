package com.nolevelcap.player.info;

import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;

public class SuperStar extends Power {
	
	public static final int type = 4;
	public static final int costAmount = 150;
	public static final String desc[] = {"Get a intellect boost from a mystical 'PC' from the future"};
	public static final String name = "The tome of";
	
	public SuperStar(int id, int lvl, Texture source, SpriteBatch draw) {
		super(type, id, lvl, source, draw, costAmount, desc, name);
	}
	
	public void init(){}
	

}
