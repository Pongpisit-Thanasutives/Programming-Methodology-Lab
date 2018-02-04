package test;

import static org.junit.Assert.*;

import org.junit.Test;

import slave.Card;
import slave.Field;
import slave.Player;
import slave.Utility;

public class TestPlayerAndField {

	@Test
	public void test() {
		Field field = new Field();
		assertTrue(field.getTopPile() == null);

		Player p = new Player("Test");
		p.addCardToHand(new Card(3, 1));
		p.addCardToHand(new Card(3, 3));
		p.addCardToHand(new Card(5, 2));
		p.addCardToHand(new Card(5, 3));
		assertTrue(p.getCardsInHand().size() == 4);
		System.out.println(Utility.generatePossibleCardPile(p.getCardsInHand()));
		p.play(field);
		assertTrue(p.getCardsInHand().size() == 3);
		assertTrue(field.getTopPile() != null);
	}

}
