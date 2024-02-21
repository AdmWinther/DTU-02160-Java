package ocp.violation;

public class AreaCalculator {
	
	public AreaCalculator() {
		super();
	}

	public double calculateArea(Object[] shapes) {
		double area = 0;
		for (Object shape : shapes) {
			if (shape instanceof Rectangle) {
				Rectangle rectangle = (Rectangle) shape;
				area += rectangle.getWidth() * rectangle.getHeight();
			} else {
				Circle circle = (Circle) shape;
				area += Math.pow(circle.getRadius(), 2) * Math.PI;
			}
		}
		return area;
	}
}