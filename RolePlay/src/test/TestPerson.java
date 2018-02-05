package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import character.Merchant;
import character.Person;
import character.Police;
import character.Thief;

public class TestPerson {
	@Test
	public void testConstructor() {
		Person p = new Person("Person1");

		assertEquals("Person1", p.getName());
		assertEquals(0, p.getMoney());
	}

	@Test
	public void testSetMoney() {
		Person p = new Person("p1");

		p.setMoney(100);
		assertEquals(100, p.getMoney());
		p.setMoney(0);
		assertEquals(0, p.getMoney());
		p.setMoney(1000);
		assertEquals(1000, p.getMoney());
		p.setMoney(-50);
		assertEquals(0, p.getMoney());
	}

	@Test
	public void testIncreaseMoney() {
		Person p = new Person("p1");

		p.increaseMoney(10);
		assertEquals(10, p.getMoney());
		p.increaseMoney(50);
		assertEquals(60, p.getMoney());
		p.increaseMoney(100);
		assertEquals(160, p.getMoney());
	}

	@Test
	public void testDecreaseMoney() {
		Person p = new Person("p1");

		p.setMoney(100);
		assertEquals(100, p.getMoney());
		p.decreaseMoney(50);
		assertEquals(50, p.getMoney());
		p.decreaseMoney(30);
		assertEquals(20, p.getMoney());
		p.decreaseMoney(20);
		assertEquals(0, p.getMoney());
		p.decreaseMoney(100);
		assertEquals(0, p.getMoney());

		p.setMoney(50);
		p.decreaseMoney(80);
		assertEquals(0, p.getMoney());
	}

	@Test
	public void testAction() {
		Person person = new Person("person");
		Person person2 = new Person("person2");
		Police police = new Police("police");
		Thief thief = new Thief("thief", 20);
		Merchant merchant = new Merchant("merchant", 50);

		person.setMoney(200);

		person.action(person2);
		assertEquals(200, person.getMoney());
		person.action(police);
		assertEquals(200, person.getMoney());
		person.action(thief);
		assertEquals(200, person.getMoney());
		person.action(merchant);
		assertEquals(150, person.getMoney());
	}
}
