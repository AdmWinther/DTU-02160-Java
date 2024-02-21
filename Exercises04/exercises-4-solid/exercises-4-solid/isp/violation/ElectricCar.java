package isp.violation;

public class ElectricCar implements ICar {
    private int speed;
 
    public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public void accelerate() {
        this.speed++;
    }
 
    @Override
    public void shiftDown() {
    		throw new UnsupportedOperationException("not supported by electric car"); 
    }
 
    @Override
    public void shiftUp() {
        throw new UnsupportedOperationException("not supported by electric car");
    }

	
}
