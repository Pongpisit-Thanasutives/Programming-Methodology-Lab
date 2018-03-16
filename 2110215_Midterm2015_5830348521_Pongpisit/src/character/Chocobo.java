package character;

import simInterface.ICharacter;

public abstract class Chocobo implements ICharacter{
	
	protected double speed;
	protected double distance;
	
	public double getSpeed() {
		return this.speed;
	}
	
	public double getDistance() {
		return this.distance;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	@Override
	public int compareTo(ICharacter o) {
		// RunnerChocobo > Jumper Chocobo > Pikachu
		
		int priority = -1;
		int othPriority = -1;
		
		if (Double.compare(this.distance, o.getDistance()) != 0) {
			if (Double.compare(this.distance, o.getDistance()) < 0) {
				return -1;
			}else {
				return 1;
			}
		}else {
			 if (this instanceof RunnerChocobo)priority = 2;
			 else if (this instanceof JumperChocobo)priority = 1;
			 
			 if (o instanceof RunnerChocobo) othPriority = 2;
			 else if (o instanceof JumperChocobo) othPriority = 1;
			 else if (o instanceof Pikachu) othPriority = 0;
			 
			 if (priority > othPriority) return -1;
			 else return 1;
		}
	}
}
