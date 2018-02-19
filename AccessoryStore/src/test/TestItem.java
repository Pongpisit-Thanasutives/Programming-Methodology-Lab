package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import character.Person;
import entity.Item;

public class TestItem {

	private class ClassForTestItem extends Item {

		public ClassForTestItem(String name, double price) {
			super(name, price);
		}

		@Override
		public void sell(Person buyer) {
			return;
		}

		@Override
		public void rent(Person renter, int day) {
			return;
		}

		@Override
		public String use() {
			return null;
		}

	}

	@Test
	public void testConstructor() {
		ClassForTestItem item = new ClassForTestItem("ItemA", 10);
		assertEquals("ItemA", item.getName());
		assertEquals(100000, item.getPrice(), 0);
		assertEquals(false, item.getIsRented());
	}

	@Test
	public void testSetPrice() {
		ClassForTestItem item1 = new ClassForTestItem("ItemA", 99999);
		assertEquals(100000, item1.getPrice(), 0);
		ClassForTestItem item2 = new ClassForTestItem("ItemB", 10000001);
		assertEquals(10000000, item2.getPrice(), 0);
		ClassForTestItem item3 = new ClassForTestItem("ItemC", 1000000);
		assertEquals(1000000, item3.getPrice(), 0);
	}

	@Test
	public void testSetRemainingDay() {
		ClassForTestItem item = new ClassForTestItem("ItemA", 10);
		item.setRemainingRentDay(-1);
		assertEquals(0, item.getRemainingRentDay());
		item.setRemainingRentDay(1);
		assertEquals(1, item.getRemainingRentDay());
		item.setRemainingRentDay(-3);
		assertEquals(0, item.getRemainingRentDay());
		item.setRemainingRentDay(55);
		assertEquals(55, item.getRemainingRentDay());
	}

	@Test
	public void testReturnItem() {
		ClassForTestItem item = new ClassForTestItem("ItemA", 10);
		Person p = new Person("P1", 1000000);
		item.setRenter(p);
		item.setIsRented(true);
		item.returnItem();
		assertEquals(null, item.getRenter());
		assertEquals(false, item.getIsRented());
	}

}
