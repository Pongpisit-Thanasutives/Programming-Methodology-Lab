package entity;

import character.Person;

public interface IRentable {
	public void rent(Person renter, int day);
	public void returnItem();
}
