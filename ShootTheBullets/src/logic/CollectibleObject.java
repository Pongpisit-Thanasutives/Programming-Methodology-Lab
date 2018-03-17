package logic;

import lib.AudioUtility;

public abstract class CollectibleObject extends TargetObject{
	
	private int requiredGrabbingTime;
	private int grabbingTimeCount;
	
	public CollectibleObject(int radius, int movingDuration, int z, int requiredGrabbingTime) {
		super(radius, movingDuration, z);
		this.requiredGrabbingTime = requiredGrabbingTime;
		this.grabbingTimeCount = 0;
	}
	
	public void ungrab(){
		this.grabbingTimeCount = 0;
	}

	public void grab(PlayerStatus player){
		if(isDestroyed) return;
		if(this.requiredGrabbingTime == this.grabbingTimeCount){
			AudioUtility.playSound("collect");
			collect(player);
			isDestroyed = true;
			return;
		}
		this.grabbingTimeCount += 1;
	}
	public abstract void collect(PlayerStatus player);
}
