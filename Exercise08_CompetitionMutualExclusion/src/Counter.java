/**
 * Class Counter
 * 
 * This class contains a value which might be increased or decreased
 */
public class Counter {
	/**
	 * Class attributes
	 */
	private int value = 0;

	/**
	 * Method increase
	 * 
	 * Increases the value of the attribute value
	 */
	public synchronized void increase() {
		
		value++;
	}

	/**
	 * Method decrease
	 * 
	 * Decreases the value of the attribute value
	 */
	public synchronized void decrease() {
		value--;
	}

	/**
	 * Method increase
	 * 
	 * @return value of the attribute value
	 */
	public int getValue() {
		return value;
	}
}