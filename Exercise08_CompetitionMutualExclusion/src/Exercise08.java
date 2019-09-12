/**
 * This is the code for Exercise07
 */
public class Exercise08 {

	/**
	 * Main entry point of the program.
	 */
	static public void main(String[] argv) {
		// A shared resource is created
		Counter c = new Counter();

		// The shared resource is used by two threads
		Increase inc = new Increase(c);
		Decrease dec = new Decrease(c);

		// The two threads are executed
		inc.start();
		dec.start();

		// The main thread waits for the two threads to finish
		try {
			inc.join();
			dec.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// The value of the counter is shown after the execution of the two threads
		System.out.println(c.getValue());

	}

}