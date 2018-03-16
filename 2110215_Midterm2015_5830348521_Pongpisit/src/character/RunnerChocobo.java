package character;

public class RunnerChocobo extends Chocobo{

	public RunnerChocobo() {
		this.speed = 3;
	}
	
	@Override
	public void start() {
		this.distance = 0;
	}

	@Override
	public void run() {
		this.distance += this.speed;
	}
}
