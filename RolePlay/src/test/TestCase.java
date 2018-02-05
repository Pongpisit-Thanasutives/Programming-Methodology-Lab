package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import character.Person;
import character.Thief;
import other.Case;

public class TestCase {
	@Test
	public void testConstructor() {
		Person p = new Person("p");
		Thief t = new Thief("t");
		Case c = new Case(p, t);

		assertEquals(p, c.getVictim());
		assertEquals(t, c.getThief());
	}

	@Test
	public void testGetVictim() {
		Person p = new Person("p");
		Thief t = new Thief("t");
		Case c = new Case(p, t);

		assertEquals(p, c.getVictim());
	}

	@Test
	public void testGetThieft() {
		Person p = new Person("p");
		Thief t = new Thief("t");
		Case c = new Case(p, t);

		assertEquals(t, c.getThief());
	}
}
