package test;

import static org.junit.Assert.*;

import org.junit.Test;

import slave.Card;
import slave.Deck;

public class TestDeck {

	@Test
	public void testDeck() {
		Deck d = new Deck();
		assertTrue(d.getDeckSize() == 52);
		assertTrue(d.deal() != null);
	}

	@Test
	public void testShuffle() {
		for (int i = 1; i <= 10; i++) {
			Deck d1 = new Deck();
			Deck d2 = new Deck();
			d1.shuffle();
			d2.shuffle();
			Card c1 = d1.deal();
			Card c2 = d2.deal();
			assertFalse(c1.getRank() == c2.getRank() && c1.getSuit() == c2.getSuit());
		}
	}

	@Test
	public void testDeal() {
		Deck d = new Deck();
		int size = d.getDeckSize();
		Card c1 = d.deal();
		Card c2;
		assertFalse(size == d.getDeckSize());
		while (d.getDeckSize() > 0) {
			c2 = d.deal();
			assertFalse(c1.getRank() == c2.getRank()
					&& c1.getSuit() == c2.getSuit());
		}
	}

}
