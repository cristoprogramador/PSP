/**
 * This is the code for Exercise07
 */
public class Exercise07 {

	/**
	 * Main entry point of the program.
	 */
	static public void main(String[] argv) {
		Vector a, b;

		a = new Vector(50, "a");
		b = new Vector(100, "b");

		a.start();
		b.start();

		// What does the method join do?
		try {
			a.join();
			b.join();
		} catch (InterruptedException e) {
		}

		// Print the results
		System.out.println("Sum (x) a: " + a.sum());
		System.out.println("Sum (x) b: " + b.sum());
		System.out.println("Sum (x^2) a: " + a.sumSqr());
		System.out.println("Sum (x^2) b: " + b.sumSqr());
		System.out.println("Mean a: " + a.mean());
		System.out.println("Mean b: " + b.mean());

	}

}