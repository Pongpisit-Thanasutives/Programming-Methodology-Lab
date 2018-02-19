package store;

import java.util.ArrayList;
import java.util.List;

import character.Person;
import entity.Item;
import entity.Ring;
// import entity.Ring;
import entity.Watch;
import util.RandomUtils;

public class Store {
	
	private List<Person> customers;
	private List<Item> items;

	private static final String[] WATCH_NAMES = { "Richard Mille RM29", "Richard Mille RM30",
			"Rolex Cosmograph Daytona", "Patek Philippe Annual", "AUDEMARS PIGUET Royal Oak Chronograph",
			"Rolex Yacht-Master", "Patek Philippe Gold Perpetual", "Annual Calendar Chronograph",
			"Rolex Pro Hunter Deepsea Green", "Richard Mille RM010", "Rolex Cosmograph Daytona",
			"Rolex Yacht-Master Rose Gold", "AUDEMARS PIGUET Royal Oak Stainless Steel", "Rolex Datejust Oyster 41",
			"Patek Philippe Aquanaut Brown Dial", "Rolex Day-Date 36", "GMT Master ii pepsi", "Patek Philippe 5135R",
			"ROLEX Daytona 116518", "Patek Philippe 5170R-001", "A. Lange and Sohne 1815", "ROLEX DAYTONA 116523",
			"Patek Philippe Complications", "Rolex Cosmagraph Daytona", "Patek Philippe Complications Annual Calenda" };

	private static final double[] WATCH_PRICES = { 3100000.00, 3600000.00, 1900000.00, 1600000.00, 613000.00, 887000.00,
			3600000.00, 3120000.00, 800000.00, 3260000.00, 550000.00, 460000.00, 580000.00, 410000.00, 1150000.00,
			1200000.00, 1360000.00, 1500000.00, 640000.00, 2600000.00, 1500000.00, 980000.00, 2300000.00, 1000000.00,
			1400000.00 };

	private static final String[] RING_NAMES = { "Bronze Ring", "Silver Ring", "Gold Ring", "Platinum Ring",
			"Amethyst Ring", "Sapphire Ring", "Pearl Ring", "Amber Ring", "Emerald Ring", "Lapis lazuli Ring",
			"Topaz Ring", "Jade Ring", "Ruby Ring", "Spinel Ring", "Diamond Ring" };

	private static final double[] RING_PRICES = { 300000.00, 350000.00, 700000.00, 1000000.00, 350000.00, 500000.00,
			450000.00, 700000.00, 550000.00, 2000000.00, 340000.00, 1200000.00, 1500000.00, 900000.00, 2200000.00 };

	public Store() {
		customers = new ArrayList<Person>();
		items = new ArrayList<Item>();
		this.initializeItem();
	}
	
	private void initializeItem() {
		for(int i = 0; i < Store.WATCH_NAMES.length; i++) {
			this.items.add(new Watch(Store.WATCH_NAMES[i],Store.WATCH_PRICES[i], RandomUtils.randomRange(0, 23), RandomUtils.randomRange(0, 59) ));
		}
		
		for(int i = 0; i < Store.RING_NAMES.length; i++) {
			this.items.add(new Ring(Store.RING_NAMES[i],Store.RING_PRICES[i] ));
		}
	}

	public void runPreDailyEvent() {
		int returnedItemCount = 0;

		// Manage all rented items.
		for (int i = 0; i < this.items.size(); i++) {
			if (items.get(i).getIsRented()) {
				items.get(i).setRemainingRentDay(items.get(i).getRemainingRentDay() - 1);
				if (items.get(i).getRemainingRentDay() == 0) {
					System.out.println(items.get(i).getRenter().getName() + " returns " + items.get(i).getName() + ".");
					items.get(i).returnItem();
					returnedItemCount += 1;
				}
			}
		}
		
		// Print the number of return item.
		System.out.println("---------------------");

		if (returnedItemCount <= 1) {
			System.out.println("There is " + returnedItemCount + " returned item.");
		} else {
			System.out.println("There are " + returnedItemCount + " returned items.");
		}
	}

	public void runStoreDailyEvent() {
		// Every customer takes action.
		for (Person customer : this.customers) {
			
			// Find items that are not rented.
			List<Item> notRentedItems = new ArrayList<Item>();
			for (Item item : this.items) {
				if (!item.getIsRented()) {
					notRentedItems.add(item);
				}
			}

			// If there is no item that is not rented, skip this customer.
			if (notRentedItems.size() == 0) {
				continue;
			}

			// Customer picks one item that is not rented.
			Item interestedItem = RandomUtils.randomElementInList(notRentedItems);
			
			// If there is currently no item's owner.
			if (interestedItem.getOwner() == null) {
				// Buy the item.
				interestedItem.sell(customer);

			} else if (customer != interestedItem.getOwner()) { // If the customer is not the owner himself/herself
				// Determine if the customer wants to buy or rent.
				boolean wantToBuy = (interestedItem instanceof Watch) ? RandomUtils.randomBoolean() : false;
				if (wantToBuy) {
					// Buy an item as a secondhand one.
					interestedItem.sell(customer);
				} else {
					// Decide the number of days for rent.
					int rand = RandomUtils.randomRange(1, 7);
					// Rent an item.
					interestedItem.rent(customer, rand);
				}
//				if (interestedItem instanceof Watch) {
//					boolean wantToBuy = RandomUtils.randomBoolean();
//					if (wantToBuy) {
//						interestedItem.sell(customer);
//					}else {
//						interestedItem.rent(customer, RandomUtils.randomRange(1, 7));
//					}
//				}else {
//					interestedItem.rent(customer, RandomUtils.randomRange(1, 7));
//				}
			}
		}
	}	
	public void showAllItems() {
		for (Item item : items) {
			System.out.println(item.toString());
		}
	}
	
	public void useAllItems() {
		for (Item item : items) {
			System.out.println(item.use());
		}
	}

	public List<Person> getCustomers() {
		return customers;
	}

	public List<Item> getItems() {
		return items;
	}
	
	public void addCustomer(Person customer) {
		this.customers.add(customer);
	}
	
	public void showCustomersMoney() {
		for (Person person : customers) {
			System.out.println(person.getName() + " : " + person.getMoney());
		}
	}
}
