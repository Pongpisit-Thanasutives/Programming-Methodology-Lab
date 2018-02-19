package entity;

import character.Person;

public abstract class Item implements ISalable, IRentable {

	private String name;
	private double price;
	private boolean isRented;
	private int remainingRentDay;
	private Person owner;
	private Person renter;
	
	
	
	public Item(String name, double price) {
		this.setName(name);
		this.setPrice(price);
		this.isRented = false;
	}

	private void setPrice(double money) {
		if (money > 10000000) money = 10000000;
		if (money < 100000) money = 100000;
		this.price = money;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public String getName() {
		return name;
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	public boolean getIsRented() {
		return isRented;
	}

	public Person getOwner() {
		return owner;
	}

	public Person getRenter() {
		return renter;
	}
	
	public int getRemainingRentDay() {
		return remainingRentDay;
	}

	public void setIsRented(boolean isRented) {
		this.isRented = isRented;
	}

	public void setRemainingRentDay(int remainingRentDay) {
		if (remainingRentDay < 0) {
			remainingRentDay = 0;
		}
		this.remainingRentDay = remainingRentDay;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public void setRenter(Person renter) {
		this.renter = renter;
	}

	public void returnItem() {
		this.setIsRented(false);
		this.setRenter(null);
	}
	
	public abstract String use();
}
