package slave;

public class Deck {
	private int deckCount;
	private Card[] cards;
	public Deck() {
		this.deckCount = 0;
		this.cards = new Card[52];
		for (int rank = 1; rank<=13; rank++) {
			for (int suit = 1; suit <= 4; suit++ ) {
				cards[this.deckCount] = new Card(rank, suit);
				this.deckCount +=1 ; 
			}
		}
	}
	
	public void shuffle() {
		int count = 0;
		while(true) {
			if (count == 500) {
				break;
			}
			int index1 = slave.Utility.random(0, this.deckCount - 1);
			int index2 = slave.Utility.random(0, this.deckCount - 1);
			if (index1 != index2) {
				Card tmpCard = this.cards[index1];
				this.cards[index1] = this.cards[index2];
				this.cards[index2] = tmpCard;
				count += 1;
				
			}
		}
	}
	
	public Card deal() {
		Card card = this.cards[this.deckCount - 1];
		this.cards[this.deckCount - 1] = null;
		this.deckCount -= 1;
		return card;
	}
	
	public int getDeckSize() {
		return this.deckCount;
	}
	
}
