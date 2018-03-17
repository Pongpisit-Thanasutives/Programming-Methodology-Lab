package test;

import static org.junit.Assert.*;
import org.junit.Test;

import logic.ItemSpecialGun;
import logic.PlayerStatus;

public class TestItemSpecialGun {
	
	@Test
	public void testConstructor() {
		ItemSpecialGun isg = new ItemSpecialGun(10, 50);
		PlayerStatus p = new PlayerStatus();
		isg.collect(p);
		assertEquals(3, p.getCurrentGun().getAttack());		
		assertEquals(50, isg.getZ());
	}

}
