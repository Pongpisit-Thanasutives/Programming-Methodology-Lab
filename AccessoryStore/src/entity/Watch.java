package entity;

import character.Person;

public class Watch extends Item {
	
	private boolean isSecondHand;
	private int hour;
	private int minute;

	
	
	public Watch(String name, double price, int hour, int minute) {
		super(name, price);
		this.isSecondHand = false;
		this.setTime(hour, minute);
	}

	public Watch(String name, double price) {
		this(name, price, 0, 0);
	}
	
	@Override
	public String toString() {
		String s = String.format("This watch is %s: %.1f ", this.getName(), this.getPrice());
		if (this.getOwner() == null) {
			s += "(FOR SALE)";
		} else if (this.getIsRented()) {
			s += "(RENTED " + this.getRemainingRentDay() + " day(s) remaining)";
		} else {
			s += "(FOR RENT/SALE)";
		}
		return s;
	}

	@Override
	public void sell(Person buyer) {
		if (this.getIsRented())return;
		double p = 0.0; 
		if (this.getOwner() == null) {
			p = this.getPrice();
		}else {
			if (this.isSecondHand) {
				p = this.getPrice() * (0.8);
			}else {
				p = this.getPrice();
			}
		}
		
		if (buyer.getMoney() < p) {
			return;
		}
		
		if (this.getOwner() != null) this.getOwner().increaseMoney(p);
		buyer.decreaseMoney(p);
		this.setOwner(buyer);
		this.setSecondHand(true);
		
		if(!this.isSecondHand)System.out.println(buyer.getName() + " buys " + this.getName() + ".");
		else {
			System.out.println(buyer.getName() + " buys " + this.getName() + " (Secondhand).");
		}
	}

	@Override
	public String use() {
		return "This is " + this.getName() + ". It's " + String.format("%02d", hour) + ":" + String.format("%02d", minute) + ".";
	}
	
	@Override
	public void rent (Person renter, int day) {
		if (this.getOwner() != null) {
			if (renter.getMoney() < day * this.getPrice() * 0.2) {
				return;
			}
			this.getOwner().increaseMoney(day * this.getPrice() * 0.2 * 0.25);
			this.setRenter(renter);
			this.setIsRented(true);
			this.setRemainingRentDay(day);
			renter.decreaseMoney(day * this.getPrice() * 0.2);
			System.out.println(renter.getName() + " rents " + this.getName() + " for " + day + " day(s).");
		}	
	}
	
	private void setTime(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}

	public boolean getIsSecondHand() {
		return isSecondHand;
	}

	public void setSecondHand(boolean isSecondHand) {
		// Pass in true if this watch is purchased for the first time
		if (this.isSecondHand) isSecondHand = true;
		this.isSecondHand = isSecondHand;
	}
}
