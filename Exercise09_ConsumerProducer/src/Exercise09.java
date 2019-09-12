/**
 * This is the code for Exercise07
 */
public class Exercise09 {

	/**
	 * Main entry point of the program.
	 */
	static public void main(String[] argv) {
		// A shared resource is created
		Vault c = new Vault();

		// The shared resource is used by two threads
		Producer prod = new Producer(c);
		Consumer cons = new Consumer(c);

		// The two threads are executed
		prod.start();
		cons.start();

		// The main thread waits for the two threads to finish
		try {
			prod.join();
			//dec.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}

}