package test;
import java.awt.Graphics2D;

import junit.framework.TestCase;
import lib.DrawingUtility;
import logic.Gun;
import logic.PlayerStatus;
import logic.ShootableObject;


public class TestShootableObj extends TestCase {

	class SimpleTarget extends ShootableObject{

		public SimpleTarget(int radius,int movingDuration,int z,int reward) {
			super(radius, movingDuration, z, reward);
			setLife(3);
		}
		public int getReward(){
			return super.reward;
		}

		@Override
		public void render(Graphics2D g2) {
			DrawingUtility.drawShootableObject(g2, x, y, radius, "simple", isPointerOver);
		}

	}
	protected void setUp() throws Exception {
	
		super.setUp();
	}
 
	public void test(){
		SimpleTarget t = new SimpleTarget(50, 20, 3,4);
		assertEquals(4,t.getReward());
		PlayerStatus p = new PlayerStatus();
		t.hit(p);
		assertEquals(0,p.getScore());
		Gun g = new Gun(1);
		p.setCurrentGun(g);
		t.hit(p);
		assertEquals(0,p.getScore());
		t.hit(p);
		t.hit(p);
		assertEquals(t.getReward(),p.getScore());
		t.hit(p);
		assertEquals(t.getReward(),p.getScore());
		

		
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
}
