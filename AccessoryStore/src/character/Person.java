package character;

public class Person {
	private String name;
	private double money;
	
	public Person(String name, double money) {
		this.name = name;
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		if (money < 0) {
			money = 0;
		}
		this.money = money;
	}
	
	public void increaseMoney(double amount) {
		if (amount < 0) {
			amount = 0;
		}
		this.setMoney(money + amount);
	}
	
	public void decreaseMoney(double amount) {
		if (amount < 0) {
			amount = 0;
		}
		this.setMoney(money - amount);
	}
	
	
	
}
