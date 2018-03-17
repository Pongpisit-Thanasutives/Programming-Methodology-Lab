package test;

import static org.junit.Assert.*;
import logic.PlayerStatus;
import logic.SimpleTarget;
import logic.SmallTarget;
import logic.SpecialGun;

import org.junit.Test;

public class TestSmallTarget {

	@Test
	public void testConstructor() {
		SmallTarget st = new SmallTarget(1, 5, 2, 0, 1);
		assertEquals(2, st.getZ());	
		assertTrue(st.isVisible());
		st.setLife(0);
		assertFalse(st.isVisible());
	}
	
	@Test
	public void testDestroyFlag() {
		SimpleTarget st = new SimpleTarget(5, 5, 4);
		st.setLife(1);
		assertTrue(st.isVisible());
		st.setLife(0);
		assertFalse(st.isVisible());
	}
	
	@Test
	public void testLife() {
		SimpleTarget st = new SimpleTarget(5, 5, 4);
		st.setLife(10);
		PlayerStatus p = new PlayerStatus();
		SpecialGun gunSP = new SpecialGun(30,30,8);
		p.setCurrentGun(gunSP);
		st.hit(p);
		assertTrue(st.isVisible());
		st.hit(p);
		assertFalse(st.isVisible());
	}

}
