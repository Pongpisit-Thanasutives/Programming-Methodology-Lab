package other;

import character.Person;
import character.Thief;

public class Case {
	
	private Person victim;
	private Thief thief;
	
	public Case(Person victim, Thief thief) {
		this.victim = victim;
		this.thief = thief;
	}	
	
	@Override
	public String toString() {
		return "Victim : " + victim.getName() + ", Thief : " + thief.getName();
	}
	public Person getVictim() {
		return victim;
	}

	public Thief getThief() {
		return thief;
	}
}
