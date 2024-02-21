package isp.violation;

public class Car implements ICar {
    private int speed; 
    private int gear; 
 
    public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getGear() {
		return gear;
	}

	public void setGear(int gear) {
		this.gear = gear;
	}

	@Override
	public void accelerate() {
        this.speed++;
    }
 
	@Override
    public void shiftDown() {
        this.gear--;
    }
 
	@Override
    public void shiftUp() {
        this.gear++;
    }
}