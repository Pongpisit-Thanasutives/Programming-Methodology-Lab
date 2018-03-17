package test;
import junit.framework.TestCase;
import logic.SpecialGun;


public class TestSpecialGun extends TestCase {

	
	protected void setUp() throws Exception {
		super.setUp();
	}
	public void test_constructor(){
		SpecialGun gun = new SpecialGun(20, 20, 4);
		assertEquals(4, gun.getAttack());
		assertEquals(20, gun.getBulletQuantity());
		
	}
	public void testbullt(){
		SpecialGun gun = new SpecialGun(20, 20, 4);
		gun.shoot();
		gun.shoot();
		assertEquals(18, gun.getBulletQuantity());
		for(int i = 0;i<20;i++)
			if(gun.canShoot())
				gun.shoot();
		
		assertEquals(0, gun.getBulletQuantity());
		gun.setBulletQuantity(30);
		assertEquals(20, gun.getBulletQuantity());
		gun.setBulletQuantity(-100);
		assertEquals(0, gun.getBulletQuantity());
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
