package mdesl.graphics;

import java.awt.Rectangle;
import java.io.IOException;

import mdesl.graphics.text.BitmapFont;

import static com.nolevelcap.main.State.*;
import com.nolevelcap.main.MouseHandling;
import com.nolevelcap.player.info.Power;
import com.nolevelcap.player.info.Powers;

public class Button {
	
	private String buttontext;
	private SpriteBatch draw;
	private Rectangle boundingBox; 
	private boolean mouseHover, mouseClicked, valid;
	private Texture buttonBackground;
	private BitmapFont font;
	private int buttonType;
	
	
	public Button(String text, SpriteBatch draw, BitmapFont font, int buttonType){
		this.buttontext = text;
		this.draw = draw;
		this.font = font;
		this.buttonType = buttonType;
		try {
			this.buttonBackground = new Texture(draw.getResource("Player/submitButton.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void logic(MouseHandling mouse, Powers parent){
		mouseHover = mouse.mouseHover(boundingBox);
		
		mouseClicked = mouse.leftClicked();
		
		if(mouseHover){
			if(mouseClicked){
				System.out.println("ButtonClucked");
				switch(buttonType){
				case 0:
					parent.changeGameState(PLAY_GAME_STATE);
					break;
				case 1:
					for (int o = 0; o < parent.allPowers.size(); o++){
						if(parent.hoverPower.equals(parent.allPowers.get(o))){
							System.out.println("MATCH");
							if(!parent.allPowers.get(o).purchased){
							boolean success = parent.purchase(parent.allPowers.get(o).costAmount);
							if(success){
							parent.allPowers.get(o).purchased = true;
							}
							}
						}
					}
					break;
				}
			}
		}
		
		
		switch(buttonType){
		case 0:
			for (int o = 0; o < parent.allPowers.size(); o++){
				if(parent.currentPower.equals(parent.allPowers.get(o))){
					valid = true;
				}
			}
			break;
		case 1:
			if(parent.player.intellect - parent.hoverPower.costAmount > 0){
					valid = true;
			} else {
					valid = false;
			}
			break;
		}
	
		
	}
	
	public void render(int x, int y){
		if(!mouseHover){
			if(valid){
				draw.setColor(Color.GRAY);
			} else {
				draw.setColor(Color.DARK_GRAY);
			}
		} else {
			if(valid){
				draw.setColor(Color.WHITE);
			} else {
				draw.setColor(Color.GRAY);
			}
		}
		draw.draw(buttonBackground, x, y, 168, 64);
		boundingBox = new Rectangle(x, y, 168, 64);
		draw.setColor(Color.BLACK);
		font.drawText(draw, buttontext, x+20, y+17);
		draw.setColor(Color.WHITE);
	}
}
