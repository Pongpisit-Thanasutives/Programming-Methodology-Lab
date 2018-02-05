import java.util.Scanner;

import character.Merchant;
import character.Person;
import character.Police;
import character.Thief;
import place.City;
import util.RandomUtils;

public class RolePlay {
	public static void main(String[] args) {
		RandomUtils.setSeed(123456789);

		RolePlay.generatePersons();
		Scanner in = new Scanner(System.in);

		for (int i = 0; i <= 20; i++) {
			System.out.println("Day : " + City.getInstance().getDayCount());
			System.out.println("Enter to pass a day...");
			in.nextLine();
			City.getInstance().passDay();
			City.getInstance().runActionEvent();
		}

		in.close();
	}

	private static void generatePersons() {
		City world = City.getInstance();

		// Add Thief
		for (int i = 0; i < 3; i++) {
			world.addPersonToGame(new Thief("thief" + (i + 1)));
		}

		// Add Police
		for (int i = 0; i < 2; i++) {
			world.addPersonToGame(new Police("police" + (i + 1)));
		}

		// Add Merchant
		for (int i = 0; i < 2; i++) {
			world.addPersonToGame(new Merchant("merchant" + (i + 1)));
		}

		// Add Person
		for (int i = 0; i < 3; i++) {
			world.addPersonToGame(new Person("person" + (i + 1)));
		}
	}
}
