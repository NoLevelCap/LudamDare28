package com.nolevelcap.player.info;

import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;

public class Gore2Gold extends Power {

	public static final int type = 2;
	public static final int costAmount = 150;
	public static final String desc[] = {"A company is created: 'you","bring gore and you get gold!'"};
	public static final String name = "The Tome Of The Start-Up";
	
	public Gore2Gold(int id, int lvl, Texture source, SpriteBatch draw) {
		super(type, id, lvl, source, draw, costAmount, desc, name);
	}
	
	public void init(){}
	

}
