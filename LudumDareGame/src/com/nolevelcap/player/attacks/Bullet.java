package com.nolevelcap.player.attacks;

import java.awt.Rectangle;
import java.io.IOException;

import mdesl.graphics.SpriteBatch;
import mdesl.graphics.Texture;

import com.nolevelcap.player.Physics;

public class Bullet {
	
	private int wandx, wandy, mousex, mousey, gradient, yintercept;
	public int ownx, owny;
	private Physics phys;
	private Texture bullet;
	private SpriteBatch draw;
	public Rectangle boundingBox;
	private float dx, dy;
	
	public Bullet(int wandx, int wandy, int mousex, int mousey, Physics phys, SpriteBatch draw){
		this.wandx = wandx;
		this.wandy = wandy;
		this.mousex = mousex;
		this.mousey = mousey;
		this.yintercept = wandy - gradient*wandx;
		this.ownx = wandx;
		this.owny = wandy;
		this.phys = phys;
		this.draw = draw;
		this.dx = wandx-mousex;
		this.dy = wandy-mousey;
		this.boundingBox = new Rectangle(ownx, owny, 64, 64);
		try {
			this.bullet = new Texture(draw.getResource("Player/bullet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Logic(){
		if(!(owny-mousey < 10 && owny-mousey > - 10) ){
			if(wandy-mousey>=0){
				owny-=0.5*phys.getDelta();
			} else {
				owny+=0.5*phys.getDelta();
			}
		}
		
		if(wandx-mousex>=0){
			ownx-=1*phys.getDelta();
		} else {
			ownx+=0.5*phys.getDelta();
		}
	}
	
	public void render(){
		draw.draw(bullet, ownx, owny, 64, 64);
		boundingBox.setBounds(ownx, owny, 64, 64);
	}
}
