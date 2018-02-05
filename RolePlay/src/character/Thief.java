package character;

import util.RandomUtils;

public class Thief extends Person{
	
	private int stolenAmount;
	
	public Thief(String name) {
		this(name, RandomUtils.randomRange(6, 30));
	}
	
	public Thief(String name, int stolenAmount) {
		super(name);
		this.setStolenAmount(stolenAmount);
	}
	@Override
	public String toString() {
		return super.toString() + ", stolenAmount : " + stolenAmount;
	}
	
	public void setStolenAmount(int stolenAmount) {
		if (stolenAmount < 0) stolenAmount = 0;
		this.stolenAmount = stolenAmount;
	}
	@Override
	public void action(Person other) {
		if (!(other instanceof Police) && !(other instanceof Thief)) {
			if (this.stolenAmount > other.getMoney()) {
				System.out.println(this.getNameWithRole() + " steals " + other.getMoney() + " from " + other.getNameWithRole());
				this.money += other.getMoney();
				other.decreaseMoney(other.getMoney());
			}else {
				System.out.println(this.getNameWithRole() + " steals " + this.stolenAmount + " from " + other.getNameWithRole());
				this.money += this.stolenAmount;
				other.decreaseMoney(this.stolenAmount);
			}
		}else {
			System.out.println(this.getNameWithRole() + " meets " + other.getNameWithRole());
		}
	}
	
	public int getStolenAmount() {
		return this.stolenAmount;
	}
}
