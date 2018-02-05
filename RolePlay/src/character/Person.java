package character;

public class Person {
	
	protected String name;
	protected int money;
	
	
	protected Person() {
		this.money = 0;
	}
	
	public Person(String name) {
		this();
		this.name = name;
	}

	public void action(Person other) {
		if (other instanceof Merchant) {
			Merchant merchant = (Merchant)(other);
			
			// this person must buy something from the merchant
			merchant.sell(this);
			System.out.println(this.getNameWithRole() + " buys something from " + other.getNameWithRole());
		}else {
			System.out.println(this.getNameWithRole() + " meets " + other.getNameWithRole());
		}
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "| name : " + name + ", money : " + money;
	}

	public String getNameWithRole() {
		return this.name + "(" + this.getClass().getSimpleName() + ")";
	}

	public String getName() {
		return name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		if (money < 0) money = 0;
		this.money = money;
	}
	
	public void increaseMoney(int amount) {
		this.setMoney(this.money + amount);
	}
	
	public void decreaseMoney(int amount) {
		this.setMoney(this.money - amount);
	}
}
