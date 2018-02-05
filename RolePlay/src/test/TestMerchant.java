package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import character.Merchant;
import character.Person;
import character.Police;
import character.Thief;
import place.City;
import util.RandomUtils;

public class TestMerchant {
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
		Merchant t = new Merchant("t1");

		assertEquals("t1", t.getName());
		assertEquals(22, t.getPrice());
	}

	@Test
	public void testTwoParameterConstructor() {
		Merchant t = new Merchant("m1", 15);
		assertEquals("m1", t.getName());
		assertEquals(15, t.getPrice());

		t = new Merchant("m2", 50);
		assertEquals("m2", t.getName());
		assertEquals(50, t.getPrice());

		t = new Merchant("m3", 5);
		assertEquals("m3", t.getName());
		assertEquals(5, t.getPrice());

		t = new Merchant("m4", 0);
		assertEquals("m4", t.getName());
		assertEquals(0, t.getPrice());

		t = new Merchant("m5", -50);
		assertEquals("m5", t.getName());
		assertEquals(0, t.getPrice());
	}

	@Test
	public void testGetPrice() {
		Merchant m = new Merchant("m1", 20);
		assertEquals(20, m.getPrice());

		Merchant m2 = new Merchant("m2", 10);
		assertEquals(10, m2.getPrice());
	}

	@Test
	public void testSetPrice() {
		Merchant m = new Merchant("m1");

		m.setPrice(10);
		assertEquals(10, m.getPrice());
		m.setPrice(0);
		assertEquals(0, m.getPrice());
		m.setPrice(100);
		assertEquals(100, m.getPrice());
		m.setPrice(-50);
		assertEquals(0, m.getPrice());
	}

	@Test
	public void testSell() {
		Person p = new Person("p1");
		Merchant m = new Merchant("m1", 50);

		p.setMoney(20);
		m.sell(p);
		assertEquals(20, p.getMoney());
		assertEquals(0, m.getMoney());

		p.setMoney(100);
		m.sell(p);
		assertEquals(50, p.getMoney());
		assertEquals(50, m.getMoney());

		p.setMoney(50);
		m.sell(p);
		assertEquals(0, p.getMoney());
		assertEquals(100, m.getMoney());
	}

	@Test
	public void testAction() {
		Police police = new Police("police");
		Thief thief = new Thief("thief", 20);
		Merchant merchant = new Merchant("merchant");

		merchant.action(thief);
		assertArrayEquals(new Thief[] { thief }, merchant.getThieves().toArray());
		merchant.action(thief);
		assertArrayEquals(new Thief[] { thief, thief }, merchant.getThieves().toArray());

		merchant.action(police);
		assertEquals(0, merchant.getThieves().size());
		merchant.action(police);
		assertEquals(0, merchant.getThieves().size());

		City.getInstance().getStation().getCases().clear();
		City.getInstance().getStation().addToJail(thief);

		merchant.getThieves().add(thief);
		police.receiveReport(merchant, thief);
		assertEquals(20, merchant.getMoney());
	}
}
