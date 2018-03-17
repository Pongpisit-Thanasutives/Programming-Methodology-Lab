package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.PlayerStatus;
import logic.SimpleTarget;
import logic.SpecialGun;

public class TestSimpleTarget {

	@Test
	public void testConstructor() {
		SimpleTarget st = new SimpleTarget(5, 5, 4);
		assertEquals(4, st.getZ());	
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
