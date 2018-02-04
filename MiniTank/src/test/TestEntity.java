package test;

import static org.junit.Assert.*;

import javafx.scene.paint.*;

import model.Entity;
import model.Field;
import model.Tank;

import org.junit.Test;

public class TestEntity {

	private Field field = new Field();
	
	@Test
	public void testConstructor() {
		Entity e = new Tank(field, 2, 3, Entity.NORTH, 1, 1, 0, 1, Color.BLACK);
		assertEquals(2, e.getX());
		assertEquals(3, e.getY());
		assertEquals(Entity.NORTH, e.getDirection());
		e = new Tank(field, -5, 3, Entity.NORTH, 1, 1, 0, 1, Color.BLACK);
		assertEquals(0, e.getX());
		assertEquals(0, e.getY());
		e = new Tank(field, 3, 12, Entity.NORTH, 1, 1, 0, 1, Color.BLACK);
		assertEquals(0, e.getX());
		assertEquals(0, e.getY());
		e = new Tank(field, 123, -55, -1, 1, 1, 0, 1, Color.BLACK);
		assertEquals(0, e.getX());
		assertEquals(0, e.getY());
		assertEquals(Entity.EAST, e.getDirection());
		e = new Tank(field, 0, 0, 4, 1, 1, 0, 1, Color.BLACK);
		assertEquals(Entity.EAST, e.getDirection());
	}
	
	@Test
	public void testPositionCheck(){
		Entity e = new Tank(field, 2, 3, Entity.NORTH, 1, 1, 0, 1, Color.BLACK);
		Entity e2 = new Tank(field, 2, 2, Entity.NORTH, 1, 1, 0, 1, Color.BLACK);
		assertFalse(e.isSamePosition(e2));
		e2 = new Tank(field, 2, 3, Entity.NORTH, 1, 1, 0, 1, Color.BLACK);
		assertTrue(e.isSamePosition(e2));
		e2 = new Tank(field, 3, 2, Entity.NORTH, 1, 1, 0, 1, Color.BLACK);
		assertFalse(e.isSamePosition(e2));
	}

	@Test
	public void testDestroyFlag(){
		Entity e = new Tank(field, 2, 3, Entity.NORTH, 1, 1, 0, 1, Color.BLACK);
		e.setDestroyed(true);
		assertTrue(e.isDestroyed());
	}
}
