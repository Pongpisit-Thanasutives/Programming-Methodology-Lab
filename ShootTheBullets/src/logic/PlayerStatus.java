package logic;

import java.awt.Graphics2D;

import lib.ConfigurableOption;
import lib.DrawingUtility;
import lib.GameManager;
import lib.IRenderableObject;

public class PlayerStatus implements IRenderableObject {

	private int remainingTime = 0;
	private int score = 0;
	private Gun currentGun = null;
	private boolean pause = false;
	
	public PlayerStatus(){
		remainingTime = ConfigurableOption.timelimit * GameManager.TICK_PER_SECONDS;
	}
	
	public int getRemainingTime() {
		return remainingTime;
	}

	public void decreaseRemainingTime(int amount) {
		/* fill code */ 
		this.remainingTime -= amount;
		if(this.remainingTime < 0){
			this.remainingTime = 0;
		}
	}

	//----- getter methods -----
	public Gun getCurrentGun() {
		return this.currentGun ;
	}

	public boolean isPause() {
		return this.pause ;
	}
	
	public int getScore() {
		return this.score ;
	}
	
	public void setCurrentGun(Gun currentGun) {
		this.currentGun = currentGun;
	}
	
	//----- methods -----
	
	public void increaseScore(int score) {
		this.score += score;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public boolean isDisplayingArea(int x,int y){
		return y < 40;
	}

	@Override
	public int getZ() {
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public void render(Graphics2D arg0) {
		DrawingUtility.drawStatusBar(arg0, this.remainingTime / GameManager.TICK_PER_SECONDS, this.score, this.currentGun, this.pause);
	}
}
