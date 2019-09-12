/**
 * This class implements the Runnable interface
 */
public class MyRunnable implements Runnable {
	String id;

	public MyRunnable(String id) {
		this.id = id;
	}

	/**
	 * The method run() contains all the code executed by the thread
	 */
	public void run() {

		// The thread will display its id 5 fimes
		for (int x = 0; x < 5; x++) {
			System.out.println(id + " " + x);
		}

	}
}
