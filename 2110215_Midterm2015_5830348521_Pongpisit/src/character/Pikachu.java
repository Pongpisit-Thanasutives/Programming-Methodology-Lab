package character;

import main.RacingManager;
import simInterface.ICharacter;
import simInterface.IObstructable;

public class Pikachu implements ICharacter, IObstructable{
	
	private double speed;
	private double distance;
	private int obstructedDuration;
	
	public Pikachu() {
		this.speed = 4;
		this.setObstructedDuration(0);
	}

	@Override
	public int compareTo(ICharacter o) {
		
		int priority = 0;
		int othPriority = -1;
		
		if (Double.compare(this.distance, o.getDistance()) != 0) {
			if (Double.compare(this.distance, o.getDistance()) < 0) {
				return -1;
			}else {
				return 1;
			}
		}else {
			 if (o instanceof RunnerChocobo) othPriority = 2;
			 else if (o instanceof JumperChocobo) othPriority = 1;
			 else if (o instanceof Pikachu) othPriority = 0;
			 
			 if (priority > othPriority) return -1;
			 else return 1;
		}
	}

	@Override
	public void start() {
		this.distance = 0;
	}

	@Override
	public void run() {
		this.distance += this.speed;
	}

	@Override
	public double getSpeed() {
		return this.speed;
	}

	@Override
	public double getDistance() {
		return this.distance;
	}

	@Override
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	@Override
	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public void obstructed() {
		if (this.obstructedDuration > 0)return;
		RacingManager.printPikachuObstructed();
		this.speed = 0;
		this.setObstructedDuration(1);
	}

	@Override
	public void setObstructedDuration(int obstructedDuration) {
		this.obstructedDuration = obstructedDuration;
	}

	@Override
	public int getObstructedDuration() {
		return this.obstructedDuration;
	}

}
