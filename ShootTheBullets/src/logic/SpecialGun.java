package logic;

import java.awt.Graphics2D;

import lib.DrawingUtility;

public class SpecialGun extends Gun {

	protected int bulletQuantity;
	protected int maxBullet;
	
	public SpecialGun(int bulletQuantity, int maxBullet, int attack) {
		super(attack);
		this.maxBullet = maxBullet;
		this.setBulletQuantity(bulletQuantity);
	}
	
	public int getBulletQuantity() {
		return this.bulletQuantity;
	}

	public void setBulletQuantity(int bulletQuantity) {
		if (bulletQuantity > this.maxBullet) bulletQuantity = this.maxBullet;
		if (bulletQuantity <= 0) bulletQuantity = 0;
		this.bulletQuantity = bulletQuantity;
	}

	@Override
	public boolean canShoot(){
		if (this.bulletQuantity > 0)return true;
		return false;
	}
	
	@Override
	public void shoot(){
		if (this.canShoot()) {
			this.bulletQuantity -= 1;
			super.shoot();
		}
	}
	
	@Override
	public void render(Graphics2D g2) {
		DrawingUtility.drawIconGun(g2, bulletQuantity, maxBullet, false);
	}
}
