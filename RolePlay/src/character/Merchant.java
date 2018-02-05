package character;
import util.RandomUtils;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Merchant extends Person{
	
	private ArrayList<Thief> thieves;
	private int price;
	
	public Merchant(String name) {
		this(name, RandomUtils.randomRange(11, 50));
	}
	
	public Merchant(String name, int price) {
		super(name);
		this.setprice(price);
		this.thieves = new ArrayList<Thief>();
	}

	@Override
	public String toString() {
		StringJoiner thiefNameList = new StringJoiner(", ", "[", "]");

		for (Thief thief : thieves) {
			thiefNameList.add(thief.getName());
		}

		return super.toString() + ", price : " + price + "\n\tThief list : " + thiefNameList;
	}
	
	public void setprice(int amount) {
		if(amount >=0) this.price = amount;
		else this.price = 0;
	}

	@Override
	public void action(Person other) {
		if (other instanceof Thief) {
//			if (!this.thieves.contains((Thief)(other))) 
			this.thieves.add((Thief)(other));
			System.out.println(this.getNameWithRole() + " remembers " + other.getNameWithRole());
		}
		if (other instanceof Police) {
			if (!this.thieves.isEmpty()) {
				System.out.println(this.getNameWithRole() + " reports " + other.getNameWithRole());
				for (int i = 0; i < this.thieves.size(); i++) {
					((Police)(other)).receiveReport(this, thieves.get(i));
				}
				// It should also clear the set of remembered thieves after reporting
				this.thieves.clear();
			}else {
				System.out.println(this.getNameWithRole() + " meets " + other.getNameWithRole());
			}
		}
		if (other instanceof Merchant) {
			System.out.println(this.getNameWithRole() + " meets " + other.getNameWithRole());
		}
		if (!(other instanceof Thief) && !(other instanceof Police) && !(other instanceof Merchant)) {
			System.out.println(this.getNameWithRole() + " sells somthing to " + other.getNameWithRole());
		}
	}
	
	public void sell(Person buyer) {
		if (buyer.getMoney() < this.price) {
			return;
		}
		this.money += this.price;
		buyer.decreaseMoney(this.price);
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public void setPrice(int price) {
		if (price < 0) price = 0;
		this.price = price;
	}
	
	public ArrayList<Thief> getThieves(){
		return this.thieves;
	}
}
