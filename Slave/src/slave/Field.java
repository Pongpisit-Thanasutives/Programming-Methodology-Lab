package slave;

public class Field {
	private CardPile topPile;

	public Field() {
		this.topPile = null;
	}

	public Field(CardPile topPile) {
		this.topPile = topPile;
	}

	public CardPile getTopPile() {
		return topPile;
	}

	public void setTopPile(CardPile topPile) {
		if (topPile.canBePlacedOnTopOf(this.topPile)) {
			this.topPile = topPile;
		}
	}
	
	public void clearField() {
		this.topPile = null;
	}
}
