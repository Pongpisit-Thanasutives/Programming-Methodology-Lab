package model;

import java.util.ArrayList;

import javafx.util.Pair;

public class Bullet extends Entity{
	
	protected Tank shooter;
	protected int power;
	
	public Bullet(Field field, int x, int y, int direction, int movingDelay, int power, Tank shooter) {
		super(field, x, y, direction, movingDelay);
		this.shooter = shooter;
		this.power = power;
	}

	@Override
	public void update() {
		if (this.isDestroyed) {
			return;
		}
		move();
	}

	@Override
	protected void calculateNextState() {
		
		if (this.isDestroyed) {
			return;
		}
		
		boolean canAdvance = true;

		ArrayList<Pair<Integer,Integer>> directionToUsedValue = new ArrayList<Pair<Integer,Integer>>();
		directionToUsedValue.add(new Pair<Integer, Integer>(-1, 0));
		directionToUsedValue.add(new Pair<Integer, Integer>(0, -1));
		directionToUsedValue.add(new Pair<Integer, Integer>(1, 0));
		directionToUsedValue.add(new Pair<Integer, Integer>(0, 1));
		
		int tmpNextX = this.x + directionToUsedValue.get(this.direction).getKey();
		int tmpNextY = this.y + directionToUsedValue.get(this.direction).getValue();
		
		if(this.field.outOfField(tmpNextX, tmpNextY) || this.field.getTerrainAt(tmpNextX, tmpNextY) == -2) {
			canAdvance = false;
		}
		
		this.nextDirection = this.direction;
		if(!canAdvance) {
			this.isDestroyedInNextState = true;
			if (this.field.getTerrainAt(tmpNextX, tmpNextY) == -2) {
				this.nextX = this.x;
				this.nextY = this.y;
			}else {
				this.nextX = tmpNextX;
				this.nextY = tmpNextY;
			}
		}else {
				this.isDestroyedInNextState = false;
				this.nextX = tmpNextX;
				this.nextY = tmpNextY;
		}
	}
	
	public void hit() {
		for (Entity entity : this.field.getEntities()) {
			if (entity instanceof Tank && this.isSamePosition(entity)) {
				Tank tank = (Tank)entity;
				if (this.getShooter() != tank) {
					tank.decreaseLife(this.power);
					this.setDestroyed(true);
				}
			}
		}
	}
	
	public Tank getShooter() {
		return shooter;
	}
	
}
