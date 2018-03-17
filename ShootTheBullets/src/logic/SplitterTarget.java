package logic;

import java.awt.Graphics2D;
import java.util.List;
import lib.DrawingUtility;
import lib.RandomUtility;

public class SplitterTarget extends ShootableObject{

	private List<TargetObject> onScreenObject;
	
	public SplitterTarget(int radius, int movingDuration, int z, List<TargetObject> onScreenObject) {
		super(radius, movingDuration, z, 5);
		this.setLife(5);
		this.onScreenObject = onScreenObject;
	}
	
	@Override
	public void hit(PlayerStatus player){
		int numberOfSmallTargets = 0;
		super.hit(player);
		if (this.isDestroyed) {
			numberOfSmallTargets = RandomUtility.random(3, 6);
			for(int i = 0; i < numberOfSmallTargets; i++) {				
				this.onScreenObject.add(new SmallTarget(this.radius / 2, this.movingDuration, this.z, this.x, this.y));
			}
		}
	}

	@Override
	public void render(Graphics2D g2) {
		DrawingUtility.drawShootableObject(g2, x, y, radius, "splitter", isPointerOver);
	}

}
