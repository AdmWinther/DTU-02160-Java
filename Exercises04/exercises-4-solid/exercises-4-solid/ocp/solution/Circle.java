
public class Circle implements Geometry{
	
	private double radius;

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public Circle(double radius) {
		super();
		setRadius(radius);
	}

	public double Area() {
		return 0;
	}
}
