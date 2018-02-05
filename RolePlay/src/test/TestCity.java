package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;

import character.Merchant;
import character.Person;
import character.Police;
import character.Thief;
import place.City;

public class TestCity {
	@After
	public void tearDown() throws Exception {
		City.destroyInstance();
	}

	@Test
	public void testConstructor() {
		assertEquals(0, City.getInstance().getPersonsInGame().size());
		assertEquals(0, City.getInstance().getDayCount());
		assertNotNull(City.getInstance().getStation());
	}

	@Test
	public void testPassDay() {
		City city = City.getInstance();

		Person p1 = new Person("p1");
		Person p2 = new Person("p2");
		Thief t1 = new Thief("t1");
		Police po1 = new Police("po1");
		Merchant m1 = new Merchant("m1");

		city.addPersonToGame(p1);
		city.addPersonToGame(p2);
		city.addPersonToGame(t1);
		city.addPersonToGame(po1);
		city.addPersonToGame(m1);

		city.passDay();
		assertEquals(1, city.getDayCount());
		assertEquals(100, p1.getMoney());
		assertEquals(100, p2.getMoney());
		assertEquals(0, t1.getMoney());
		assertEquals(0, po1.getMoney());
		assertEquals(0, m1.getMoney());

		city.passDay();
		assertEquals(2, city.getDayCount());
		assertEquals(200, p1.getMoney());
		assertEquals(200, p2.getMoney());
		assertEquals(0, t1.getMoney());
		assertEquals(0, po1.getMoney());
		assertEquals(0, m1.getMoney());
	}

	@Test
	public void testDailyPassiveIncome() {
		assertEquals(100, City.DAILY_PASSIVE_INCOME);
	}

	@Test
	public void testAddPersonToGame() {
		City city = City.getInstance();
		ArrayList<Person> persons = city.getPersonsInGame();

		Person p1 = new Person("p1");
		Person p2 = new Person("p2");

		city.addPersonToGame(p1);
		assertEquals(1, persons.size());
		city.addPersonToGame(p2);
		assertEquals(2, persons.size());
		city.addPersonToGame(p1);
		assertEquals(2, persons.size());
		city.addPersonToGame(p2);
		assertEquals(2, persons.size());

		assertEquals(0, persons.indexOf(p1));
		assertEquals(1, persons.indexOf(p2));
	}

	@Test
	public void testRemovePersonFromGame() {
		City city = City.getInstance();
		ArrayList<Person> persons = city.getPersonsInGame();

		Person p1 = new Person("p1");
		Person p2 = new Person("p2");

		city.addPersonToGame(p1);
		city.addPersonToGame(p2);

		city.removePersonFromGame(p1);
		assertEquals(1, persons.size());
		assertFalse(persons.contains(p1));
		assertTrue(persons.contains(p2));

		city.removePersonFromGame(p2);
		assertEquals(0, persons.size());
		assertFalse(persons.contains(p2));
	}
}
