package logic;

import java.awt.Graphics2D;
import lib.DrawingUtility;

public class SimpleTarget extends ShootableObject {

	public SimpleTarget(int radius,int movingDuration,int z) {
		super(radius, movingDuration, z, 5);
		this.setLife(3);
	}

	@Override
	public void render(Graphics2D g2) {
		DrawingUtility.drawShootableObject(g2, x, y, radius, "simple", isPointerOver);
	}

}
