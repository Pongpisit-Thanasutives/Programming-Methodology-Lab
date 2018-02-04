package test;

import static org.junit.Assert.*;

import javafx.scene.paint.*;

import model.Bullet;
import model.ExtraBullet;
import model.Entity;
import model.Field;
import model.Tank;

import org.junit.Before;
import org.junit.Test;

public class TestBullet {

	private Field field = new Field();
	private java.lang.reflect.Field f = null;
	
	@Before
	public void setUp(){
		try {
			f = Entity.class.getDeclaredField("isDestroyedInNextState");
			f.setAccessible(true);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			System.out.println("Security Ex");
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			System.out.println("No field");
		}
	}
	
	@Test
	public void testCalculateNextState(){
		Tank t = new Tank(field,0,0,Entity.EAST,3,3,0,5,Color.BLUE);
		Bullet b = new Bullet(field,0,0,Entity.WEST,1,1,t);
		assertEquals(b.getNextX(),0);
		assertEquals(b.getNextY(),0);
		try {
			assertTrue(f.getBoolean(b));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Exception 1");
		}
		
		b = new Bullet(field,2,0,Entity.WEST,1,1,t);
		assertEquals(b.getNextX(),1);
		assertEquals(b.getNextY(),0);
		
		b = new Bullet(field,1,3,Entity.SOUTH,1,1,t);
		assertEquals(b.getNextX(),1);
		assertEquals(b.getNextY(),4);
		
		b = new Bullet(field,2,2,Entity.NORTH,1,1,t);
		assertEquals(b.getNextX(),2);
		assertEquals(b.getNextY(),2);
		try {
			assertTrue(f.getBoolean(b));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Exception 2");
		}
	}

	@Test
	public void testUpdate(){
		Tank t = new Tank(field,0,0,Entity.EAST,3,3,0,5,Color.BLUE);
		Bullet b = new Bullet(field,1,0,Entity.WEST,1,1,t);
		b.update();
		assertEquals(1, b.getX());
		assertEquals(0, b.getY());
		b.update();
		assertEquals(0, b.getX());
		assertEquals(0, b.getY());
	}
	
	@Test
	public void testHit(){
		Tank t = new Tank(field,1,1,Entity.EAST,3,3,0,5,Color.BLUE);
		Tank t2 = new Tank(field,1,2,Entity.EAST,3,3,0,5,Color.BLUE);
		Bullet b = new Bullet(field,1,0,Entity.SOUTH,0,1,t);
		//Cannot hit shooter
		b.update();
		b.hit();
		assertFalse(b.isDestroyed());
		//Hit tank2
		b.update();
		b.hit();
		assertTrue(b.isDestroyed());
		assertEquals(4,t2.getLife());
		
		field.getEntities().remove(b);
		
		//Bullet can hit bullet
		b = new ExtraBullet(field,0,0,Entity.SOUTH,0,1,t);
		Bullet b2 = new Bullet(field,0,0,Entity.NORTH,0,1,t2);
		b.hit();
		assertTrue(b.isDestroyed());
		assertTrue(b2.isDestroyed());
		
		field.getEntities().remove(b);
		field.getEntities().remove(b2);
		
		//But not the bullet from the same shooter
		b = new ExtraBullet(field,0,0,Entity.SOUTH,0,1,t);
		b2 = new Bullet(field,0,0,Entity.NORTH,0,1,t);
		b.hit();
		assertFalse(b.isDestroyed());
		assertFalse(b2.isDestroyed());
	}
}
