package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import character.Merchant;
import character.Person;
import character.Police;
import character.Thief;
import util.RandomUtils;

public class TestThief {
	@Before
	public void setUp() throws Exception {
		RandomUtils.setSeed(12345);
	}

	@After
	public void tearDown() throws Exception {
		RandomUtils.unsetSeed();
	}

	@Test
	public void testOneParameterConstructor() {
		Thief t = new Thief("t1");

		assertEquals("t1", t.getName());
		assertEquals(7, t.getStolenAmount());
	}

	@Test
	public void testTwoParameterConstructor() {
		Thief t = new Thief("t1", 15);
		assertEquals("t1", t.getName());
		assertEquals(15, t.getStolenAmount());

		t = new Thief("t2", 50);
		assertEquals("t2", t.getName());
		assertEquals(50, t.getStolenAmount());

		t = new Thief("t3", 5);
		assertEquals("t3", t.getName());
		assertEquals(5, t.getStolenAmount());

		t = new Thief("t4", 0);
		assertEquals("t4", t.getName());
		assertEquals(0, t.getStolenAmount());

		t = new Thief("t5", -50);
		assertEquals("t5", t.getName());
		assertEquals(0, t.getStolenAmount());
	}

	@Test
	public void testSetStolenAmount() {
		Thief t = new Thief("t1");

		t.setStolenAmount(10);
		assertEquals(10, t.getStolenAmount());
		t.setStolenAmount(0);
		assertEquals(0, t.getStolenAmount());
		t.setStolenAmount(100);
		assertEquals(100, t.getStolenAmount());
		t.setStolenAmount(-50);
		assertEquals(0, t.getStolenAmount());
	}

	@Test
	public void testAction() {
		Person person = new Person("person");
		Police police = new Police("police");
		Thief thief = new Thief("thief", 20);
		Thief thief2 = new Thief("thief2", 15);
		Merchant merchant = new Merchant("merchant", 50);

		person.setMoney(200);
		merchant.setMoney(500);
		police.setMoney(100);
		thief2.setMoney(150);

		thief.action(person);
		assertEquals(180, person.getMoney());
		assertEquals(20, thief.getMoney());
		thief.action(police);
		assertEquals(100, police.getMoney());
		thief.action(thief2);
		assertEquals(150, thief2.getMoney());
		thief.action(merchant);
		assertEquals(480, merchant.getMoney());
		assertEquals(40, thief.getMoney());

		person.setMoney(10);
		thief.action(person);
		assertEquals(0, person.getMoney());
		assertEquals(50, thief.getMoney());

		merchant.setMoney(5);
		thief.action(merchant);
		assertEquals(0, merchant.getMoney());
		assertEquals(55, thief.getMoney());
	}
}
