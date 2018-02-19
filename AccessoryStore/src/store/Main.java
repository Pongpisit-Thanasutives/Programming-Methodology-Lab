package store;

import java.util.Scanner;

import character.Person;
import util.RandomUtils;

public class Main {
	public static void main(String[] args) {
		RandomUtils.setSeed(987654321);

		Store store = new Store();
		generateCustomers(store);
		Scanner in = new Scanner(System.in);
		int day = 0;

		System.out.println("--- Welcome to Accessory Store ---");
		showCommandList();

		while (true) {
			String command = in.nextLine();
			switch (command.toLowerCase()) {
			case "e":
				System.out.println("Program terminated");
				in.close();
				return;
			case "i":
				System.out.println("---------------------");
				System.out.println("Item status (Day " + day + "):");
				store.showAllItems();
				System.out.println("---------------------");
				System.out.println("Please press the command (Press h to show command list.) . . . ");
				System.out.println("=====================");
				break;
			case "s":
				System.out.println("---------------------");
				System.out.println("Remaining money (Day " + day + "):");
				store.showCustomersMoney();
				System.out.println("---------------------");
				System.out.println("Please press the command (Press h to show command list.) . . . ");
				System.out.println("=====================");
				break;
			case "h":
				System.out.println("---------------------");
				showCommandList();
				break;
			case "u":
				System.out.println("---------------------");
				store.useAllItems();
				System.out.println("---------------------");
				System.out.println("Please press the command (Press h to show command list.) . . . ");
				System.out.println("=====================");
				break;
			case "":
				day++;
				System.out.println("Day " + day);
				System.out.println("---------------------");
				System.out.println("Pre-event : ");
				store.runPreDailyEvent();
				System.out.println("---------------------");
				System.out.println("Today event : ");
				store.runStoreDailyEvent();
				System.out.println("---------------------");
				System.out.println("Please press the command (Press h to show command list.) . . . ");
				System.out.println("=====================");
				break;
			default:
				System.out.println("Invalid Command");
				System.out.println("Please press the valid command (Press h to show command list.) . . . ");
				System.out.println("=====================");
				break;
			}
		}
	}

	public static void generateCustomers(Store store) {
		String customerNames[] = { "Tutu", "Poom", "Mark", "Tukky", "Peach", "James", "John", "Robert", "Michael",
				"William", "David", "Richard", "Charles", "Joseph", "Thomas", "Sebastian", "Noah", "Dio", "Jotaro",
				"Jonathan" };
		int numberOfCustomers = RandomUtils.randomRange(10, customerNames.length);
		for (int i = 0; i < numberOfCustomers; i++) {
			int money = RandomUtils.randomRange(3000000, 6000000);
			store.addCustomer(new Person(customerNames[i], money));
		}
	}

	public static void showCommandList() {
		System.out.println("Press enter to proceed to next day . . .");
		System.out.println("Press s to show the remaining money of customer . . .");
		System.out.println("Press i to show items' status.");
		System.out.println("Press u to use all items.");
		System.out.println("Press h to show command list.");
		System.out.println("Press e to exit the program . . .");
	}
}
