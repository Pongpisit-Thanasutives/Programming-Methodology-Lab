package logic;

import java.awt.Graphics2D;
import lib.DrawingUtility;

public class ItemSpecialGun extends CollectibleObject {
	
	public ItemSpecialGun(int movingDuration, int z) {
		super(50, movingDuration, z, 50);
	}

	@Override
	public void render(Graphics2D g2) {
		DrawingUtility.drawItemGun(g2, this.x, this.y, this.radius, "gun",this.isPointerOver);
	}

	@Override
	public void collect(PlayerStatus player) {
		player.setCurrentGun(new SpecialGun(20, 20, 3));		
	}
}
