package slave;

import java.util.ArrayList;
import slave.Utility;

public class Player {
	private String name;
	private boolean pass;
	private ArrayList<Card> cardsInHand;
	
	
	public Player(String name) {
		this.name = name;
		this.pass = false;
		this.cardsInHand = new ArrayList<Card>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}

	public ArrayList<Card> getCardsInHand() {
		return cardsInHand;
	}
	
	public void addCardToHand(Card card) {
		this.cardsInHand.add(card);
	}
	
	public void play(Field field) {
		if (this.win()) {
			this.pass = true;
			System.out.println(this.name + " passes with empty hand");
			return;
		}
		this.pass = true;
		ArrayList<CardPile> allPossibleCardPiles = new ArrayList<CardPile>();
		CardPile cardPileToplay;
		allPossibleCardPiles = Utility.generatePossibleCardPile(this.cardsInHand);
		for (int i = allPossibleCardPiles.size() - 1; i >= 0; i--) {
			if (allPossibleCardPiles.get(i).canBePlacedOnTopOf(field.getTopPile())) {
				cardPileToplay = allPossibleCardPiles.get(i);
				System.out.println(this.name + " plays " + cardPileToplay.toString());
				this.pass = false;
				field.setTopPile(cardPileToplay);
				for (int j = 0; j < cardPileToplay.getCardInPile().length; j++) {
					this.cardsInHand.remove(cardPileToplay.getCardInPile()[j]);
				}
				return;
			}
		}
		if (this.pass) {
			System.out.println(this.name + " passes");
		}
	}
	
	public boolean win() {
		return this.cardsInHand.size() == 0;
	}
}
