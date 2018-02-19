package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import character.Person;

public class TestPerson {

	@Test
	public void testConstructor() {
		Person p = new Person("EggCatX@France", 500);
		assertEquals("EggCatX@France", p.getName());
		assertEquals(500, p.getMoney(), 0);
	}

	@Test
	public void testSetMoney() {
		Person p = new Person("EggCatX@France", 500);
		p.setMoney(-1);
		assertEquals(0, p.getMoney(), 0);
		p.setMoney(1);
		assertEquals(1, p.getMoney(), 0);
	}

	@Test
	public void testIncreaseMoney() {
		Person p = new Person("EggCatX@France", 500);
		p.increaseMoney(-1);
		assertEquals(500, p.getMoney(), 0);
		p.increaseMoney(1);
		assertEquals(501, p.getMoney(), 0);
	}

	@Test
	public void testDecreaseMoney() {
		Person p = new Person("EggCatX@France", 500);
		p.decreaseMoney(50);
		assertEquals(450, p.getMoney(), 0);
		p.decreaseMoney(999);
		assertEquals(0, p.getMoney(), 0);
	}
}
