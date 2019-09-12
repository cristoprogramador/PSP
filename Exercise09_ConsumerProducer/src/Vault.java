import java.util.ArrayList;
import java.util.List;

/**
 * Class Vault
 * 
 * This class contains a list of characters
 */
public class Vault {
	/**
	 * Class attributes
	 */
	private List<Character> list;

	/**
	 * Constructor of the class
	 */
	public Vault() {
		list = new ArrayList<>();
	}

	/**
	 * Method add
	 * 
	 * Adds a character to the list
	 * 
	 * @param Character
	 *            to be added to the list
	 */
	public synchronized void add(Character c) {
		list.add(c);
		notify();
	}

	/**
	 * Method remove
	 * 
	 * Removes a character to the list and prints the character 
	 * 
	 */
	public synchronized void remove() {
		if (list.size() == 0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Character c = list.remove(0);
		System.out.print(c);
	}

}