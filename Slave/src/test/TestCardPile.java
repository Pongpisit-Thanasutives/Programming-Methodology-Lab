package test;

import static org.junit.Assert.*;

import org.junit.Test;

import slave.Card;
import slave.CardPile;

public class TestCardPile {

	@Test
	public void testCanBePlacedOnTopOf() {
		Card[] c1 = { new Card(1, 1), new Card(1, 2), new Card(1, 3) };
		Card[] c2 = { new Card(2, 2) };
		CardPile pile1 = new CardPile(c1);
		CardPile pile2 = new CardPile(c2);
		assertTrue(pile1.canBePlacedOnTopOf(pile2)); // 3 VS 1

		c1 = new Card[] { new Card(11, 2) };
		pile1 = new CardPile(c1);
		assertTrue(pile2.canBePlacedOnTopOf(pile1)); // 11,2 VS 2,2

		c1 = new Card[] { new Card(1, 1), new Card(1, 2), new Card(1, 3) };
		pile1 = new CardPile(c1);
		c2 = new Card[] { new Card(10, 2), new Card(10, 3), new Card(10, 4) };
		pile2 = new CardPile(c2);
		assertTrue(pile1.canBePlacedOnTopOf(pile2)); // 1,1 1,2 1,3 VS 10,2 10,3
														// 10,4

		c1 = new Card[] { new Card(5, 1), new Card(5, 4) };
		c2 = new Card[] { new Card(5, 2), new Card(5, 3) };
		pile1 = new CardPile(c1);
		pile2 = new CardPile(c2);
		assertTrue(pile1.canBePlacedOnTopOf(pile2)); // 5,1 5,4 VS 5,2 5,3

		c1 = new Card[] { new Card(5, 1), new Card(5, 4) };
		c2 = new Card[] { new Card(3, 2) };
		pile1 = new CardPile(c1);
		pile2 = new CardPile(c2);
		assertFalse(pile1.canBePlacedOnTopOf(pile2)); // 2 VS 1

		c1 = new Card[] { new Card(5, 1), new Card(5, 4), new Card(5, 2),
				new Card(5, 3) };
		c2 = new Card[] { new Card(3, 2) };
		pile1 = new CardPile(c1);
		pile2 = new CardPile(c2);
		assertFalse(pile1.canBePlacedOnTopOf(pile2)); // 4 VS 1

		c1 = new Card[] { new Card(1, 1), new Card(1, 2), new Card(1, 3) };
		c2 = new Card[] { new Card(3, 2), new Card(3, 3) };
		pile1 = new CardPile(c1);
		pile2 = new CardPile(c2);
		assertFalse(pile1.canBePlacedOnTopOf(pile2)); // 3 VS 2
	}

}
