package character;

import java.util.ArrayList;

import other.Case;
import place.City;

public class Police extends Person{
	
	public static final int ARREST_AWARD = 200;
	
	public Police(String name) {
		super(name);
	}

	@Override
	public void action(Person other) {
		
		ArrayList<Case> involvedCases = new ArrayList<Case>();
		if (!(other instanceof Thief)) {
			System.out.println(this.getNameWithRole() + " meets " + other.getNameWithRole());
		}else {
			Thief thief = (Thief)(other);
			ArrayList<Case> cases;
			cases = City.getInstance().getStation().getCases();
			for (int i = 0; i < cases.size(); i++) {
				if (cases.get(i).getThief() == thief) {
					cases.get(i).getVictim().increaseMoney(thief.getStolenAmount());
					thief.decreaseMoney(thief.getStolenAmount());
					City.getInstance().getStation().getJail().add(thief);
					City.getInstance().getPersonsInGame().remove(thief);
					involvedCases.add(cases.get(i));
				}
			}
			for (int j = 0; j < involvedCases.size(); j++) {
				System.out.println(this.getNameWithRole() + " catches " + other.getNameWithRole());
				cases.remove(involvedCases.get(j));
			}
			if (City.getInstance().getStation().getJail().size() >0) this.increaseMoney(Police.ARREST_AWARD);
		}
	}

	public void receiveReport(Person victim, Thief thief) {
		if(!City.getInstance().getStation().getJail().contains(thief)) {
			City.getInstance().getStation().addCase(victim, thief);
		}else {
			thief.decreaseMoney(thief.getStolenAmount());
			victim.increaseMoney(thief.getStolenAmount());
		}
	}
}
