package lsp.violation;

public class Bicycle extends TransportationDevice {
	@Override
	public void startEngine() {
		throw new UnsupportedOperationException("Bikes do not have an engine");  
	}
}
