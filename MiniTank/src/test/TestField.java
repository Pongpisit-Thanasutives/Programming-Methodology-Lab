package test;

import static org.junit.Assert.*;

import javafx.scene.paint.*;

import org.junit.Test;

import model.*;

public class TestField {

	@Test
	public void testOutOfField() {
		Field f = new Field();
		assertTrue(f.outOfField(-1, -1));
		assertTrue(f.outOfField(-1, 2));
		assertTrue(f.outOfField(2, -1));
		assertTrue(f.outOfField(8, 2));
		assertTrue(f.outOfField(2, 8));
		assertTrue(f.outOfField(12, 10));
		assertFalse(f.outOfField(3, 3));
	}

	@Test
	public void testGetTerrainAt() {
		Field f = new Field();
		assertEquals(f.getTerrainAt(1, 0), 0);
		assertEquals(f.getTerrainAt(2, 1), -2);
		assertEquals(f.getTerrainAt(-2, 1), -3);
		assertEquals(f.getTerrainAt(1, 4), -1);
	}

	@Test
	public void testUpdateFieldState() {
		Field f = new Field();
		Tank t = new Tank(f, 2, 3, Entity.EAST, 3, 3, 0, 5, Color.BLUE);
		Tank t2 = new Tank(f, 3, 4, Entity.WEST, 4, 2, 1, 5, Color.RED);
		Bullet b = new Bullet(f, 2, 3, Entity.NORTH, 3, 5, t2);
		f.updateFieldState(); // Bullet hit tank t, both destroyed but don't
								// clear from the field yet
		assertTrue((f.getEntities()).contains(t));
		assertTrue((f.getEntities()).contains(b));
		f.updateFieldState();
		assertFalse((f.getEntities()).contains(t));
		assertFalse((f.getEntities()).contains(b));
	}
}
