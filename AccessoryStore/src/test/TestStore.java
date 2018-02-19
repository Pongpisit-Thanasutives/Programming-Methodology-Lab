package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import character.Person;
import store.Store;
import util.RandomUtils;

public class TestStore {

	@Test
	public void testConstructor() {
		Store store = new Store();
		assertEquals(0, store.getCustomers().size());
		assertEquals(40, store.getItems().size());
	}

	@Test
	public void testRunPreDailyEvent() {
		Store store = new Store();
		Person p1 = new Person("P1", 5000000);
		Person p2 = new Person("P2", 5000000);
		store.getItems().get(0).sell(p1);
		store.getItems().get(0).rent(p2, 1);
		store.runPreDailyEvent();
		assertEquals(0, store.getItems().get(0).getRemainingRentDay());
	}

	@Test
	public void testRunStoreDailyEvent() {
		Store store = new Store();
		RandomUtils.setSeed(64);
		Person p = new Person("P1", 5000000);
		Person p2 = new Person("P2", 5000000);
		store.addCustomer(p);
		store.addCustomer(p2);
		store.runStoreDailyEvent();
		assertEquals(4702500, store.getCustomers().get(0).getMoney(), 0);
		assertEquals(4830000, store.getCustomers().get(1).getMoney(), 0);
		assertEquals(p, store.getItems().get(35).getOwner());
		assertEquals(p2, store.getItems().get(35).getRenter());
	}
}
