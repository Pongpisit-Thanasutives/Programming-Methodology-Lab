package model;

public class ExtraBullet extends Bullet{
	
	public ExtraBullet(Field field, int x, int y, int direction, int movingDelay, int power, Tank shooter) {
		super(field, x, y, direction, movingDelay, power, shooter);
	}

	@Override
	public void hit() {
		for (Entity entity : this.field.getEntities()) {
			if(this.isSamePosition(entity)) {
				if (entity instanceof Tank && this.getShooter() != (Tank)(entity)) {
					((Tank)(entity)).decreaseLife(this.power);
					this.setDestroyed(true);
					return;
				}else {
					if (entity instanceof Bullet && this.getShooter() != ((Bullet)(entity)).getShooter()) {
						this.setDestroyed(true);
						((Bullet)(entity)).setDestroyed(true);
						return;
					}
				}
			}
		}
	}
}
