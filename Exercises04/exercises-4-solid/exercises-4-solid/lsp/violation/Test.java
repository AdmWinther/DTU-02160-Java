package lsp.violation;

public class Test {

	public static void main(String[] args) {
		TransportationDevice car = new Car();
		TransportationDevice bike = new Bicycle();
		Object[] devices = new Object [] {car, bike};
		
		for (Object device : devices) {
			((TransportationDevice) device).startEngine();
	    }
	}
}
