package dip.violation;

public class Test {

	public static void main(String[] args) {
		Lamp lamp = new Lamp();
		Button button = new Button(lamp);
		button.push();
		button.push();
	}
}
