package logic;

public abstract class ShootableObject extends TargetObject{

	protected int reward;
	private int life;
	
	public ShootableObject(int radius, int movingDuration,int z,int reward) {
		super(radius, movingDuration, z);
		this.reward = reward;
	}
	
	public void setLife(int life){
		if (life > 0) this.life = life;
		else {
			this.life = 0;
			this.isDestroyed = true;
		}
	}
	
	public void hit(PlayerStatus player){
		if(isDestroyed || player.getCurrentGun() == null) return;
		setLife(life-player.getCurrentGun().getAttack());
		if(isDestroyed)
			player.increaseScore(reward);
	}
}
