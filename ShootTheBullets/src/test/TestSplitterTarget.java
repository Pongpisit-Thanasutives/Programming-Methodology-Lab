package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import logic.PlayerStatus;
import logic.SpecialGun;
import logic.SplitterTarget;
import logic.TargetObject;

import org.junit.Test;

public class TestSplitterTarget {

	@Test
	public void testConstructor() {
		List<TargetObject> onScreenObject = new ArrayList<TargetObject>();
		SplitterTarget st = new SplitterTarget(40,10,5,onScreenObject);
		assertTrue(st.isVisible());
		//System.out.println(onScreenObject.size());
		assertEquals(0, onScreenObject.size());	
	}
	
	@Test
	public void testSplit() {
		List<TargetObject> onScreenObject = new ArrayList<TargetObject>();
		SplitterTarget st = new SplitterTarget(40,10,5,onScreenObject);
		PlayerStatus p = new PlayerStatus();
		SpecialGun gunSP = new SpecialGun(30,30,3);
		p.setCurrentGun(gunSP);
		st.hit(p);
		assertEquals(0, onScreenObject.size());	
		st.hit(p);
		//System.out.println(onScreenObject.size());
		assertTrue(onScreenObject.size() >= 3 && onScreenObject.size() <= 6);
	}

}
