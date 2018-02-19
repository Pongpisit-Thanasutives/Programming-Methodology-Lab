package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import character.Person;
import entity.Watch;

public class TestWatch {

	@Test
	public void testConstructor() {
		Watch w = new Watch("Watch1", 100);
		assertEquals(100000, w.getPrice(), 0);
		assertEquals("Watch1", w.getName());
		assertEquals(false, w.getIsSecondHand());
		assertEquals(false, w.getIsRented());
	}

	@Test
	public void testRent() {
		Watch w = new Watch("Watch1", 100);
		Person p = new Person("P1", 1000000);
		Person owner = new Person("OWNER", 1000000);
		w.setOwner(owner);
		w.rent(p, 6);
		assertEquals(true, w.getIsRented());
		assertEquals(p, w.getRenter());
		assertEquals(6, w.getRemainingRentDay());
		assertEquals(1000000 - (100000 * 0.2 * 6), p.getMoney(), 0);
		assertEquals(1000000 + (100000 * 0.2 * 6 * 0.25), owner.getMoney(), 0);
	}

	@Test
	public void testSell() {
		Watch w = new Watch("Watch1", 100);
		Person p = new Person("P1", 10000000);
		w.sell(p);
		assertEquals(p, w.getOwner());
		assertEquals(true, w.getIsSecondHand());
		assertEquals(10000000 - w.getPrice(), p.getMoney(), 0);

		Person p2 = new Person("P2", 10000000);
		w.sell(p2);
		assertEquals(p2, w.getOwner());
		assertEquals(10000000 - (w.getPrice() * 0.8), p2.getMoney(), 0);
		assertEquals(10000000 - (w.getPrice() * 0.2), p.getMoney(), 0);
	}

	@Test
	public void testToString() {
		Watch w = new Watch("Watch1", 100);
		String pre = "This watch is " + "Watch1" + ": " + "100000.0" + " ";
		assertEquals(pre + "(FOR SALE)", w.toString());

		Person p = new Person("P1", 10000000);
		w.sell(p);
		assertEquals(pre + "(FOR RENT/SALE)", w.toString());

		Person p2 = new Person("P2", 10000000);
		w.rent(p2, 5);
		assertEquals(pre + "(RENTED " + 5 + " day(s) remaining)", w.toString());
	}

	@Test
	public void testUse() {
		Watch w = new Watch("Watch1", 100);
		assertEquals("This is Watch1. It's 00:00.", w.use());

		Watch w2 = new Watch("Watch2", 100, 3, 9);
		assertEquals("This is Watch2. It's 03:09.", w2.use());
	}
}