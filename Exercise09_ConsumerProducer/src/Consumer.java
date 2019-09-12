/**
 * Class Consumer
 * 
 * This class represents a thread which consumes a character
 */
public class Consumer extends Thread {
	/**
	 * Class attributes
	 */
	private Vault vault;

	/**
	 * Constructor of the class
	 */
	Consumer(Vault c) {
		vault = c;
	}

	/**
	 * Method run
	 * 
	 * Main code for a thread
	 */
	public void run() {
		for (int i = 0; i < 2000; i++)
			vault.remove();
	}
}