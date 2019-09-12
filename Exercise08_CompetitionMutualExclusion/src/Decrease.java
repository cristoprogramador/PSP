/**
 * Class Decrease
 * 
 * This class represents a thread which decreases a value
 */
public class Decrease extends Thread {
	/**
	 * Class attributes
	 */
	private Counter cont;

	/**
	 * Constructor of the class
	 */
	Decrease(Counter c) {
		cont = c;
	}

	/**
	 * Method run
	 * 
	 * Main code for a thread
	 */
	public void run() {
		for (int i = 0; i < 2000; i++)
			cont.decrease();
	}
}