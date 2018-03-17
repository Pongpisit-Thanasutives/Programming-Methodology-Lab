package logic;

import java.awt.Graphics2D;

import lib.ConfigurableOption;
import lib.DrawingUtility;
import lib.RandomUtility;

public class SmallTarget extends ShootableObject {

	public SmallTarget(int radius, int movingDuration, int z){
		super(radius, movingDuration, z, 5);
		this.setLife(2);
	}
	
	public SmallTarget(int radius, int movingDuration, int z, int startX,int startY) {
		/* fill code */
		super(radius, movingDuration, z, 5);
				
		movingType = 0;
		if(RandomUtility.random(0, 1) == 1){
			movingParameter = new int[]{
					startX,startY,
					RandomUtility.random(0,1) == 0 ? -radius : ConfigurableOption.screenWidth+radius,
					RandomUtility.random(-radius, ConfigurableOption.screenHeight+radius)
			};
		}else{
			movingParameter = new int[]{
					startX,startY,
					RandomUtility.random(-radius, ConfigurableOption.screenWidth+radius),
					RandomUtility.random(0,1) == 0 ? -radius : ConfigurableOption.screenHeight+radius
			};
		}
	}

	@Override
	public void render(Graphics2D g2) {
		DrawingUtility.drawShootableObject(g2, x, y, radius, "small", isPointerOver);		
	}

}
