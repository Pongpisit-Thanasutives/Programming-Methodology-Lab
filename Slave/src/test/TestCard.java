package test;

import static org.junit.Assert.*;

import org.junit.Test;

import slave.Card;

public class TestCard {
	private int minRank = 1;
	private int minSuit = 1;

	@Test
	public void testCard() {
		// correct rank & suit
		Card c = new Card(1, 4);
		assertEquals(1, c.getRank());
		assertEquals(4, c.getSuit());

		// incorrect rank & suit
		c = new Card(-3, -5);
		assertEquals(minRank, c.getRank());
		assertEquals(minSuit, c.getSuit());

		// incorrect rank & suit
		c = new Card(19, 21);
		assertEquals(minRank, c.getRank());
		assertEquals(minSuit, c.getSuit());

		// incorrect rank, but correct suit
		c = new Card(-3, 3);
		assertEquals(minRank, c.getRank());
		assertEquals(3, c.getSuit());

		// correct rank, but incorrect suit
		c = new Card(10, -5);
		assertEquals(10, c.getRank());
		assertEquals(minSuit, c.getSuit());

		// incorrect rank, but correct suit
		c = new Card(15, 3);
		assertEquals(minRank, c.getRank());
		assertEquals(3, c.getSuit());

		// correct rank, but incorrect suit
		c = new Card(10, 9);
		assertEquals(10, c.getRank());
		assertEquals(minSuit, c.getSuit());
	}

	@Test
	public void testSetRank() {
		Card c = new Card(1, 4);
		c.setRank(7);
		assertEquals(7, c.getRank());
		c.setRank(-5);
		assertEquals(minRank, c.getRank());
		c.setRank(11);
		assertEquals(11, c.getRank());
		c.setRank(30);
		assertEquals(minRank, c.getRank());
	}

	@Test
	public void testSetSuit() {
		Card c = new Card(1, 4);
		c.setSuit(3);
		assertEquals(3, c.getSuit());

		c.setSuit(-5);
		assertEquals(minSuit, c.getSuit());

		c.setSuit(2);
		assertEquals(2, c.getSuit());

		c.setSuit(30);
		assertEquals(minSuit, c.getSuit());
	}

	@Test
	public void testIsBiggerThan() {
		// same rank (2 = the highest rank), different suit
		Card c1 = new Card(2, 4);
		Card c2 = new Card(2, 3);
		assertTrue(c1.isBiggerThan(c2));
		
		c1 = new Card(3, 4);
		assertTrue(c2.isBiggerThan(c1));

		c2 = new Card(10, 1);
		assertTrue(c2.isBiggerThan(c1));
	}

}
