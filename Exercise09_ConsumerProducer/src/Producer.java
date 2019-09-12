import java.util.Random;

/**
 * Class Producer
 * 
 * This class represents a thread which produces a character
 */
public class Producer extends Thread {
	/**
	 * Class attributes
	 */
	private Vault vault;

	/**
	 * Constructor of the class
	 */
	Producer(Vault c) {
		vault = c;
	}

	/**
	 * Method run
	 * 
	 * Main code for a thread
	 */
	public void run() {
		Random r = new Random();
		for (int i = 0; i < 2000; i++) {
			//Produces a random character
			char c = (char)('a'+r.nextInt(23));
			vault.add(c);
		}
	}
}