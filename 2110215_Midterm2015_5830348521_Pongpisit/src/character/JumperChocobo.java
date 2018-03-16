package character;

import main.RacingManager;
import simInterface.IObstructable;

public class JumperChocobo extends Chocobo implements IObstructable{

	private int turnCount;
	private int obstructedDuration;
	
	public JumperChocobo() {
		this.speed = 7.5;
		this.turnCount = 1;
		this.obstructedDuration = 0;
	}
	
	@Override
	public void start() {
		this.distance = 0;
	}

	public int getObstructedDuration() {
		return obstructedDuration;
	}

	public void setObstructedDuration(int obstructedDuration) {
		this.obstructedDuration = obstructedDuration;
	}

	@Override
	public void run() {
		
		if (this.turnCount == 0) {
			this.distance += this.speed;
			this.turnCount += 1;
		}
		else {
			RacingManager.printJumperChocoboPreparingToJump(this.getDistance());
			this.turnCount -= 1;
		}
	}
	
	public void obstructed() {
		if (this.obstructedDuration > 0)return;
		else {
			RacingManager.printJumperChocoboObstructed();
			this.setSpeed(this.speed / 2.0);
			this.setObstructedDuration(2);
		}
	}
}
