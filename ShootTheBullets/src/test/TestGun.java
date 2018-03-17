package test;
import junit.framework.TestCase;
import logic.Gun;


public class TestGun extends TestCase {

	

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void test_constructor(){
		Gun g = new Gun(3);
		assertEquals(3, g.getAttack());
		Gun g2 = new Gun(0);
		assertEquals(0, g2.getAttack());
	}
	public void test_shoot(){
		Gun g = new Gun(3);
		assertTrue(g.canShoot());
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
