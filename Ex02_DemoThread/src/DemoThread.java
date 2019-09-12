/**
 * This example shows how to create a Thread using the class Thread
 */
public class DemoThread {

	/**
	 * Main entry point of the program.
	 */
	static public void main(String[] argv) {
		// Two Threads have been declared, but they do not start running
		MyThread firstThread = new MyThread("Thread 1");
		MyThread secondThread = new MyThread("Thread 2");

		// The two threads start running when the start() method is called
		firstThread.start();
		secondThread.start();

		System.out.println("End of Main Thread");
	}

}
