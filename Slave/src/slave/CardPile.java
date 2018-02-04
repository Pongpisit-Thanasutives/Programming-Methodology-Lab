package slave;

public class CardPile {
	private Card[] cards;

	public CardPile(Card[] cards) {
		this.cards = cards;
	}
	
	public boolean canBePlacedOnTopOf(CardPile othCardPile) {
		if (othCardPile == null)return true;
		if (this.cards.length - 2 == othCardPile.getCardInPile().length) {
			return true;
		}
		if (this.cards.length == othCardPile.getCardInPile().length) {
			if (this.getBiggestCardInThisPile().isBiggerThan(othCardPile.getBiggestCardInThisPile())) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
	
	public Card getBiggestCardInThisPile() {
		Card biggestCard;
		if (this.cards.length == 0) {
			return null;
		}else {
			biggestCard = this.cards[0];
			for (int i = 0; i < this.cards.length; i++) {
				if (this.cards[i].isBiggerThan(biggestCard)) {
					biggestCard = this.cards[i];
				}
			}
			return biggestCard;
		}
	}

	public Card[] getCardInPile() {
		return cards;
	}
	
	@Override
	public String toString() {
		String o = "";
		for (int i = 0; i < this.cards.length; i++) {
			if (i == this.cards.length - 1) {
				o += this.cards[i].toString();
			}else {
				o += this.cards[i].toString() + ", ";	
			}
		}
		return "[" + o + "]";
	}
}
