package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import character.Person;
import entity.Ring;

public class TestRing {

	@Test
	public void testConstructor() {
		Ring r = new Ring("Ring1", 10);
		assertEquals(100000, r.getPrice(), 0);
		assertEquals("Ring1", r.getName());
		assertEquals(false, r.getIsRented());
	}

	@Test
	public void testRent() {
		Ring r = new Ring("Ring1", 10);
		Person owner = new Person("OWNER", 1000000);
		r.setOwner(owner);
		Person p = new Person("P1", 1000000);
		r.rent(p, 6);
		assertEquals(true, r.getIsRented());
		assertEquals(p, r.getRenter());
		assertEquals(6, r.getRemainingRentDay());
		assertEquals(1000000 - (100000 * 0.1 * 6), p.getMoney(), 0);
		assertEquals(1000000 + (100000 * 0.1 * 6 * 0.25), owner.getMoney(), 0);
	}

	@Test
	public void testSell() {
		Ring r = new Ring("Ring1", 10);
		Person p = new Person("P1", 1000000);
		r.sell(p);
		assertEquals(p, r.getOwner());
		assertEquals(1000000 - r.getPrice(), p.getMoney(), 0);
	}

	@Test
	public void testToString() {
		Ring r = new Ring("Ring1", 10);
		String pre = "This ring is " + "Ring1" + ": " + "100000.0" + " ";
		assertEquals(pre + "(FOR SALE)", r.toString());

		Person p = new Person("P1", 1000000);
		r.sell(p);
		assertEquals(pre + "(FOR RENT)", r.toString());

		Person p2 = new Person("P2", 1000000);
		r.rent(p2, 3);
		assertEquals(pre + "(RENTED " + 3 + " day(s) remaining)", r.toString());
	}

	@Test
	public void testUse() {
		Ring r = new Ring("Ring1", 499999);
		assertEquals("This is Ring1. Nothing happened... It's a poor man's ring.", r.use());
		Ring r2 = new Ring("Ring2", 500000);
		assertEquals("This is Ring2. It's shining... Such a beautiful ring.", r2.use());
	}

}
