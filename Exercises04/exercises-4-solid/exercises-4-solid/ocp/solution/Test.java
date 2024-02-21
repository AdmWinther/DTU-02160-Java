package ocp.violation;

class Test {

	public static void main(String[] args) {
		AreaCalculator calc = new AreaCalculator();
		Circle circle = new Circle(2.0);
		Rectangle rectangle = new Rectangle(3.0,4.0);
		Object [] shapes = new Object []{circle, rectangle};
		double area = calc.calculateArea(shapes);
		System.out.println(12.0 + Math.pow(2.0,2)* Math.PI == area);
	}

}
