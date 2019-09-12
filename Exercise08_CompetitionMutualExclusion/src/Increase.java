/**
 * Class Increase
 * 
 * This class represents a thread which increases a value
 */
public class Increase extends Thread {
	/**
	 * Class attributes
	 */
	private Counter cont;

	/**
	 * Constructor of the class
	 */
	Increase(Counter c) {
		cont = c;
	}

	/**
	 * Method run
	 * 
	 * Main code for a thread
	 */
	public void run() {
		for (int i = 0; i < 2000; i++)
			cont.increase();
	}
}