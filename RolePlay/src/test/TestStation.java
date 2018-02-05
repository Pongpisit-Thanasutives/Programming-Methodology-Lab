package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import character.Person;
import character.Thief;
import place.Station;

public class TestStation {
	@Test
	public void testConstructor() {
		Station s = new Station();
		assertNotEquals(null, s.getCases());
		assertNotEquals(null, s.getJail());
	}

	@Test
	public void testAddCase() {
		Station s = new Station();

		Person victim1 = new Person("p1");
		Thief thief1 = new Thief("t1");
		s.addCase(victim1, thief1);
		assertEquals(1, s.getCases().size());

		Person victim2 = new Person("p2");
		Thief thief2 = new Thief("t2");
		s.addCase(victim2, thief2);
		assertEquals(2, s.getCases().size());

		assertEquals(victim1, s.getCases().get(0).getVictim());
		assertEquals(thief1, s.getCases().get(0).getThief());
		assertEquals(victim2, s.getCases().get(1).getVictim());
		assertEquals(thief2, s.getCases().get(1).getThief());
	}

	@Test
	public void testAddToJail() {
		Station s = new Station();

		Thief thief1 = new Thief("t1");
		Thief thief2 = new Thief("t2");

		s.addToJail(thief1);
		assertEquals(1, s.getJail().size());

		s.addToJail(thief2);
		assertEquals(2, s.getJail().size());

		assertTrue(s.getJail().contains(thief1));
		assertTrue(s.getJail().contains(thief2));
	}
}
