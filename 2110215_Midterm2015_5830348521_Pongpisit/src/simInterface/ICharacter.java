package simInterface;

public interface ICharacter extends Comparable<ICharacter>{
	
	public void start();
	public void run();
	public double getSpeed();
	public double getDistance();
	public void setSpeed(double speed);
	public void setDistance(double distance);

}
