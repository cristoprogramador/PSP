/**
 * This example shows how to create a Thread using the interface Runnable
 */
public class DemoRunnable {

	/**
	 * Main entry point of the program.
	 */
	static public void main(String[] argv) {
		// Two objects of a class which implements the interface Runnable
		MyRunnable myRunnable1 = new MyRunnable("Thread 1");
		MyRunnable myRunnable2 = new MyRunnable("Thread 2");

		// Two Threads have been declared, but they do not start running
		Thread firstThread = new Thread(myRunnable1);
		Thread secondThread = new Thread(myRunnable2);

		// The two threads start running when the start() method is called
		firstThread.start();
		secondThread.start();

		System.out.println("End of Main Thread");
	}

}
