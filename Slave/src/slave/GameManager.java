package slave;

import java.util.ArrayList;

public class GameManager {

	public static void main(String[] args) {
		
		String s = "";
		
		String[] order = new String[] {"Alice", "Bill", "Cain", "Doug"};
		ArrayList<String> leftPlayers = new ArrayList<String>();
		leftPlayers.add("Alice");
		leftPlayers.add("Bill");
		leftPlayers.add("Cain");
		leftPlayers.add("Doug");
		Player alice = new Player(order[0]);
		Player bill = new Player(order[1]);
		Player cain = new Player(order[2]);
		Player doug = new Player(order[3]);
		
		Deck deck = new Deck();
		deck.shuffle();
		
		for (int i = 0; i < 13; i++) {
			alice.addCardToHand(deck.deal());
			bill.addCardToHand(deck.deal());
			cain.addCardToHand(deck.deal());
			doug.addCardToHand(deck.deal());
		}
		Field field = new Field();
		while(true) {
			
			alice.setPass(false);
			bill.setPass(false);
			cain.setPass(false);
			doug.setPass(false);
			
			if (leftPlayers.size() == 1) {
				System.out.println(s + "  is a SLAVE!");
				break;
			}
			
			while(true) {
				if (alice.isPass() && bill.isPass() && cain.isPass() && doug.isPass()) {
					System.out.println("All declare \"PASS\"");
					field.clearField();
					break;
				}
				if (!alice.isPass()) {
					alice.play(field);
				}
				if (alice.win()) {
					leftPlayers.remove("Alice");
					if (leftPlayers.size() == 1) {
						s = leftPlayers.get(0);
						break;
					}
				}
				if (!bill.isPass()) {
					bill.play(field);
				}
				if (bill.win()) {
					leftPlayers.remove("Bill");
					if (leftPlayers.size() == 1) {
						s = leftPlayers.get(0);
						break;
					}
				}
				if (!cain.isPass()) {
					cain.play(field);
				}
				if (cain.win()) {
					leftPlayers.remove("Cain");
					if (leftPlayers.size() == 1) {
						s = leftPlayers.get(0);
						break;
					}
				}
				if (!doug.isPass()) {
					doug.play(field);
				}
				if (doug.win()) {
					leftPlayers.remove("Doug");
					if (leftPlayers.size() == 1) {
						s = leftPlayers.get(0);
						break;
					}
				}
			}
		}
	}
}
