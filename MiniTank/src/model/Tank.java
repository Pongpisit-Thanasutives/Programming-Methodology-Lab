package model;

import javafx.scene.paint.Color;
import utility.Utility;
import java.util.ArrayList;
import javafx.util.Pair;

public class Tank extends Entity{

	private int life;
	private Color color;
	private int bulletType;
	private 	int firingDelay;
	private int firingDelayCounter;
	
	public Tank(Field field, int x, int y, int direction, int movingDelay, int firingDelay, int firingType, int life, Color color) {
		super(field, x, y, direction, movingDelay);
		if (life <= 0) {
			life = 1;
		}
		this.life = life;
		if (color == null) {
			color = Color.BLACK;
		}
		this.color = color;
		this.bulletType = firingType;
		this.firingDelay = firingDelay;
		this.firingDelayCounter = 0;
	}

	@Override
	public void update() {
		
		if (this.isDestroyed) {
			return;
		}

		if(move()){
			if(this.firingDelayCounter < this.firingDelay) {
				this.firingDelayCounter += 1;
				return;
			}
			this.firingDelayCounter = 0;
			if (this.bulletType == 0) {
				new Bullet(this.field, this.x, this.y, this.direction, 1, 1, this);
			}else {
				new ExtraBullet(this.field, this.x, this.y, this.direction, 1, 2, this);
			}	
		}
	}
	@Override
	protected void calculateNextState() {
		
		boolean canAdvance = true;

		ArrayList<Pair<Integer,Integer>> directionToUsedValue = new ArrayList<Pair<Integer,Integer>>();
		directionToUsedValue.add(new Pair<Integer, Integer>(-1, 0));
		directionToUsedValue.add(new Pair<Integer, Integer>(0, -1));
		directionToUsedValue.add(new Pair<Integer, Integer>(1, 0));
		directionToUsedValue.add(new Pair<Integer, Integer>(0, 1));
		
		int tmpX = this.x + directionToUsedValue.get(this.direction).getKey();
		int tmpY = this.y + directionToUsedValue.get(this.direction).getValue();
		if (this.field.outOfField(tmpX, tmpY) || this.field.getTerrainAt(tmpX, tmpY) < 0){
			canAdvance = false;
		}
		
		if (!canAdvance) {
			int rand = Utility.random(0, 1);
			if(rand == 0) {
				rand = -1;
			}else {
				rand = 1;	
			}
			this.nextDirection = this.direction + rand;
			if (this.nextDirection < 0) {
				this.nextDirection += 4;
			}
			if (this.nextDirection > 3) {
				this.nextDirection -= 4;
			}
			this.nextX = this.x;
			this.nextY = this.y;
		}else {
			this.nextDirection = this.direction;
			this.nextX = this.x + directionToUsedValue.get(this.nextDirection).getKey();
			this.nextY = this.y + directionToUsedValue.get(this.nextDirection).getValue();
		}
		
		// Also,isDestroyedInNextStatestatus is always set to false here 
		// as the other method will set the proper value for this status
		this.isDestroyedInNextState = false;
	}
	
	public Color getTankColor() {
		return color;
	}
	
	public int getLife() {
		return life;
	}
	
	public void decreaseLife(int amount) {
		if (this.life - amount <= 0) {
			this.life = 0;
			this.isDestroyed = true;
			return;
		}
		this.life -= amount;
	}
}
