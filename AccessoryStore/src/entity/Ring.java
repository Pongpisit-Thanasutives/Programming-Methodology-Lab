package entity;

import character.Person;

public class Ring extends Item {

	public Ring(String name, double price) {
		super(name, price);
	}
	
	@Override
	public String toString() {
		String s = String.format("This ring is %s: %.1f ", this.getName(), this.getPrice());

		if (this.getOwner() == null) {
			s += "(FOR SALE)";
		} else if (this.getIsRented()) {
			s += "(RENTED " + this.getRemainingRentDay() + " day(s) remaining)";
		} else {
			s += "(FOR RENT)";
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
			return;
		}
	
		if(buyer.getMoney() >  p) {
			this.setOwner(buyer);
			buyer.decreaseMoney(p);
			System.out.println(buyer.getName() + " buys " + this.getName() + ".");
		}
	}

	@Override
	public void rent(Person renter, int day) {
		
			if (renter.getMoney() < day * this.getPrice() * 0.1) {
				return;
			}
			this.setRenter(renter);
			this.setIsRented(true);
			this.setRemainingRentDay(day);
			this.getOwner().increaseMoney(day * this.getPrice() * 0.1 * 0.25);
			renter.decreaseMoney(day * this.getPrice() * 0.1);
			System.out.println(renter.getName() + " rents " + this.getName() + " for " + day + " day(s).");

	}

	@Override
	public String use() {
		if (this.getPrice() < 500000) {
			return "This is " + this.getName() + ". Nothing happened... It's a poor man's ring.";
		}else {
			return "This is " + this.getName() + ". It's shining... Such a beautiful ring.";
		}
	}

}
