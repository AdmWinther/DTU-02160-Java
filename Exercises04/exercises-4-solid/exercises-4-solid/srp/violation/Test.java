package srp.violation;

public class Test {

	public static void main(String[] args) {
		Book book = new Book();
		book.setTitle("1984");
		book.printCurrentPageAsPlain();
		book.printCurrentPageAsHTML();
	}
}
