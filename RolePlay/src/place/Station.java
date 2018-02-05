package place;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import character.Person;
import character.Thief;
import other.Case;

public class Station {
	private ArrayList<Case> cases;
	private LinkedHashSet<Thief> jail;
	
	public Station() {
		cases = new ArrayList<Case>();
		jail = new LinkedHashSet<Thief>();
	}
	
	public void addCase(Person victim, Thief thief) {
		this.cases.add(new Case(victim, thief));
	}

	public ArrayList<Case> getCases() {
		return cases;
	}

	public LinkedHashSet<Thief> getJail() {
		return jail;
	}
	
	public void addToJail(Thief thief) {
		jail.add(thief);
	}
}
