package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import character.Merchant;
import character.Police;
import character.Thief;
import place.City;
import place.Station;

public class TestPolice {
	@After
	public void tearDown() throws Exception {
		City.destroyInstance();
	}

	@Test
	public void testConstructor() {
		Police p = new Police("p1");
		assertEquals("p1", p.getName());
	}

	@Test
	public void testArrestReward() {
		assertEquals(200, Police.ARREST_AWARD);
	}

	@Test
	public void testReceiveReport() {
		Station station = City.getInstance().getStation();

		Merchant m = new Merchant("m1");
		Thief t = new Thief("t1", 10);
		Police p = new Police("p1");

		m.setMoney(40);

		// Thief stays in city.
		m.getThieves().add(t);
		p.receiveReport(m, t);
		assertEquals(1, station.getCases().size());

		// Clear case in the station.
		station.getCases().clear();

		// Thief stays in jail.
		station.addToJail(t);
		p.receiveReport(m, t);
		assertEquals(50, m.getMoney());
		
		assertEquals(0, p.getMoney());
	}

	@Test
	public void testAction() {
		City city = City.getInstance();
		Station station = city.getStation();

		Merchant m = new Merchant("m1");
		Thief t = new Thief("t1", 10);
		Police p = new Police("p1");

		city.addPersonToGame(m);
		city.addPersonToGame(t);
		city.addPersonToGame(p);

		p.action(t);
		assertEquals(0, station.getJail().size());
		assertEquals(0, p.getMoney());
		assertTrue(city.getPersonsInGame().contains(t));
	}

	@Test
	public void testActionOnThief() {
		City city = City.getInstance();
		Station station = city.getStation();

		Merchant m = new Merchant("m1");
		Thief t = new Thief("t1", 10);
		Police p = new Police("p1");

		m.setMoney(40);
		t.setMoney(10);
		city.addPersonToGame(m);
		city.addPersonToGame(t);
		city.addPersonToGame(p);

		station.addCase(m, t);
		p.action(t);
		assertEquals(0, station.getCases().size());
		assertEquals(1, station.getJail().size());
		assertEquals(0, t.getMoney());
		assertEquals(50, m.getMoney());
		assertEquals(200, p.getMoney());
		assertTrue(station.getJail().contains(t));
		assertFalse(city.getPersonsInGame().contains(t));
	}

	@Test
	public void testActionOnThiefWithMultipleCases() {
		City city = City.getInstance();
		Station station = city.getStation();

		Merchant m1 = new Merchant("m1");
		Merchant m2 = new Merchant("m2");
		Thief t1 = new Thief("t1", 55);
		Police p = new Police("p1");

		m1.setMoney(40);
		m2.setMoney(60);
		t1.setMoney(500);
		city.addPersonToGame(m1);
		city.addPersonToGame(m2);
		city.addPersonToGame(t1);
		city.addPersonToGame(p);

		station.addCase(m1, t1);
		station.addCase(m2, t1);
		p.action(t1);
		assertEquals(0, station.getCases().size());
		assertEquals(1, station.getJail().size());
		assertEquals(500 - 55 - 55, t1.getMoney());
		assertEquals(40 + 55, m1.getMoney());
		assertEquals(60 + 55, m2.getMoney());
		assertEquals(200, p.getMoney());
		assertTrue(station.getJail().contains(t1));
		assertFalse(city.getPersonsInGame().contains(t1));
	}
}
