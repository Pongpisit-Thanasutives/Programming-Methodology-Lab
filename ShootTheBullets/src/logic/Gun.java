package logic;

import java.awt.Graphics2D;

import lib.AudioUtility;
import lib.DrawingUtility;
import lib.IRenderableObject;

public class Gun implements IRenderableObject {

	protected int attack;
	
	public Gun(int attack) {
		this.attack = attack;
	}

	public int getAttack(){
		return this.attack;
	}
	
	public boolean canShoot(){
		return true;
	}
	
	public void shoot(){
		AudioUtility.playSound("shoot");
	}

	@Override
	public int getZ() {
		return 0;
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	@Override
	public void render(Graphics2D g2) {
		DrawingUtility.drawIconGun(g2, 0, Integer.MAX_VALUE, true);
	}
}
