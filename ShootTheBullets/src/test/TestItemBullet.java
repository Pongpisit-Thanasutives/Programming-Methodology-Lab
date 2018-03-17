package test;
import junit.framework.TestCase;
import logic.ItemBullet;
import logic.PlayerStatus;
import logic.SpecialGun;


public class TestItemBullet extends TestCase {

	

	protected void setUp() throws Exception {
		super.setUp();
	}
	public void test(){
		ItemBullet it = new ItemBullet(20, 5);
		PlayerStatus p = new PlayerStatus();
		SpecialGun gunSP = new SpecialGun(30,30,4);
		p.setCurrentGun(gunSP);
		assertEquals(30, gunSP.getBulletQuantity());
		for(int i = 0 ;i<6;i++)
			if(gunSP.canShoot())
				gunSP.shoot();
		
		assertEquals(30-6, gunSP.getBulletQuantity());
		it.collect(p);
		assertEquals(30, gunSP.getBulletQuantity());
		for(int i = 0 ;i<40;i++)
			if(gunSP.canShoot())
				gunSP.shoot();
		
		assertEquals(0, gunSP.getBulletQuantity());
		it.collect(p);
		assertEquals(30, gunSP.getBulletQuantity());
	
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
