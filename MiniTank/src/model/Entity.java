package model;

public abstract class Entity {
	
	public static final int WEST = 0;
	public static final int NORTH = 1;
	public static final int EAST = 2;
	public static final int SOUTH = 3;
	
	protected int x, y, direction;
	protected boolean isDestroyed;
	
	protected int nextX, nextY, nextDirection;
	protected boolean isDestroyedInNextState;
	
	private int movingDelay;
	private int movingDelayCounter;
	
	protected Field field;
	
	public Entity(Field field,int x,int y,int direction,int movingDelay) {
		if (x > 5 || x < 0 || y > 5 || y < 0) {
			x = 0;
			y = 0;
		}
		if (direction < 0 || direction > 3) {
			direction = 2;
		}
		
		this.x = x;
		this.y = y;
		this.direction = direction;
		
		this.movingDelay = movingDelay;
		this.movingDelayCounter = this.movingDelay;
		
		this.field = field;
		field.addEntity(this);
		this.isDestroyed = false;
		
		this.calculateNextState();
	}
	
	public abstract void update();
	protected abstract void calculateNextState();
	
	// There is delay before you can actually move
	protected boolean move() {
		if (!this.isDestroyed) {
			if (this.movingDelayCounter > 0) {
				this.movingDelayCounter -= 1;
				return false;
			}
			this.movingDelayCounter = this.movingDelay;
			this.x = this.nextX;
			this.y = this.nextY;
			this.direction = this.nextDirection;
			this.isDestroyed = this.isDestroyedInNextState;
			this.calculateNextState();
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isSamePosition(Entity other) {
		return (this.x == other.x) && (this.y == other.y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDirection() {
		return direction;
	}

	public int getNextX() {
		if (nextX < 0) {
			return 0;
		}else if(nextX > 5) {
			return 5;
		}
		return nextX;
	}

	public int getNextY() {
		return nextY;
	}
	
	public int getNextDirection() {
		return nextDirection;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}

	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}
}
