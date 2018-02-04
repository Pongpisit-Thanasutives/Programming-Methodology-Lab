package slave;

public class Card {
	
	private int rank;
	private int suit;
	
	public Card(int rank, int suit) {
		if (rank < 1 || rank > 13) {
			rank = 1;
		}
		if (suit < 1 || suit > 4) {
			suit = 1;
		}
		this.rank = rank;
		this.suit = suit;
	}
	
	public boolean isBiggerThan(Card card) {
		int[] valueOfrank = new int[] {0, 12, 13, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		if (valueOfrank[this.rank] > valueOfrank[card.rank]) return true;
		if (valueOfrank[this.rank] == valueOfrank[card.rank]) {
			if (this.suit > card.suit) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String[] nameOfsuits = new String[] {"Clubs", "Diamonds", "Hearts", "Spades"};
		String[] nameOfranks = new String[] {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		return nameOfsuits[this.suit - 1] + nameOfranks[this.rank - 1];
	}
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		if (rank < 1 || rank > 13) {
			rank = 1;
		}
		this.rank = rank;
	}

	public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		if (suit < 1 || suit > 4) {
			suit = 1;
		}
		this.suit = suit;
	}
}
