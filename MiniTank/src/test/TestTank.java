package test;

import static org.junit.Assert.*;

import javafx.scene.paint.*;

import model.Entity;
import model.Field;
import model.Tank;

import org.junit.Test;

public class TestTank {

	private Field field = new Field();

	@Test
	public void testConstructor() {
		Tank t = new Tank(field, 2, 3, Entity.EAST, 3, 3, 0, 5, Color.BLUE);
		assertTrue(t.getTankColor() == Color.BLUE);
		assertTrue(t.getLife() == 5);
		assertEquals(2, t.getX());
		assertEquals(3, t.getY());
		t = new Tank(field, 2, 3, Entity.EAST, 3, 3, 0, -3, null);
		assertEquals(1, t.getLife());
		assertTrue(t.getTankColor() == Color.BLACK);
	}

	@Test
	public void testCalculateNextState() {
		Tank t = new Tank(field, 0, 0, Entity.EAST, 3, 3, 0, 5, Color.BLUE);
		assertEquals(t.getNextX(), 1);
		assertEquals(t.getNextY(), 0);
		assertEquals(t.getNextDirection(), Entity.EAST);
		t = new Tank(field, 0, 0, Entity.WEST, 3, 3, 0, 5, Color.BLUE);
		assertEquals(t.getNextX(), 0);
		assertEquals(t.getNextY(), 0);
		assertTrue(t.getNextDirection() == Entity.NORTH
				|| t.getNextDirection() == Entity.SOUTH);
		t = new Tank(field, 1, 1, Entity.EAST, 3, 3, 0, 5, Color.BLUE);
		assertEquals(t.getNextX(), 1);
		assertEquals(t.getNextY(), 1);
		assertTrue(t.getNextDirection() == Entity.NORTH
				|| t.getNextDirection() == Entity.SOUTH);
		t = new Tank(field, 1, 3, Entity.SOUTH, 3, 3, 0, 5, Color.BLUE);
		assertEquals(t.getNextX(), 1);
		assertEquals(t.getNextY(), 3);
		assertTrue(t.getNextDirection() == Entity.WEST
				|| t.getNextDirection() == Entity.EAST);
	}

	@Test
	public void testUpdate() { // Test moving delay, firing delay, fire()
		Tank t = new Tank(field, 0, 0, Entity.EAST, 2, 1, 0, 5, Color.BLUE);

		t.update();
		assertEquals(0, t.getX());
		assertEquals(0, t.getY());
		
		t.update();
		assertEquals(0, t.getX());
		assertEquals(0, t.getY());

		t.update(); // tank move to 1,0
		assertEquals(1, t.getX());
		assertEquals(0, t.getY());
		assertEquals(1, field.getEntities().size()); // tank t
		
		t.update();
		assertEquals(1, t.getX());
		assertEquals(0, t.getY());
		
		t.update();
		assertEquals(1, t.getX());
		assertEquals(0, t.getY());
		
		t.update(); // tank move to 2,0 and fire the bullet
		assertEquals(2, t.getX());
		assertEquals(0, t.getY());
		assertEquals(2, field.getEntities().size()); // tank t and a bullet

		Entity b = field.getEntities().get(0);
		assertEquals(2, b.getX());
		assertEquals(0, b.getY());
		assertEquals(Entity.EAST, b.getDirection());
	}

	@Test
	public void testDecreaseLife() {
		Tank t = new Tank(field, 0, 0, Entity.EAST, 3, 3, 0, 5, Color.BLUE);
		assertFalse(t.isDestroyed());
		t.decreaseLife(3);
		assertEquals(2, t.getLife());
		t.decreaseLife(3);
		assertEquals(0, t.getLife());
		assertTrue(t.isDestroyed());
	}
}
