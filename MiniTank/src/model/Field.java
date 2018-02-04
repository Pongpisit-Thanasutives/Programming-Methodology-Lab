package model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Field {

	private int[][] map;
	private List<Entity> entities;
	
	public Field() {
		this.map = new int[][] {{0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0}};
		map[4][1] = -1;
		map[1][2] = -2;
		map[2][4] = -1;
		entities = new CopyOnWriteArrayList<Entity>();
	}

	public void addEntity(Entity e) {
		this.entities.add(e);
	}

	public List<Entity> getEntities() {
		return entities;
	}
	
	public int getTerrainAt(int x,int y) {
		if(outOfField(x, y)) return -3;
		else return map[y][x];
	}

	public boolean outOfField(int x, int y) {
		return (y < 0 || y > 5) || (x < 0 || x > 5);
	}
	
	public void updateFieldState() {
		for (Entity e : entities) {
			if (e.isDestroyed) entities.remove(e);
		}
		for (Entity e : entities) {
			e.update();
			if (e instanceof Bullet) ((Bullet) e).hit();
		}
	}
}
