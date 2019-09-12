/**
 * Class Vector
 * 
 */
public class Vector extends Thread {
	/**
	 * Class attributes
	 */
	private int[] data;
	private String name;
	private int size;
	private int sum;
	private long sumSqr;
	private double mean;

	/**
	 * Constructor of the class
	 */
	Vector(int t, String n) {
		this.name = n;
		this.size = t;
		this.sum = 0;
		this.sumSqr = 0;
		this.mean = 0.0;
		this.data = new int[this.size];
		for (int i = 0; i < this.size; i++) {
			data[i] = (int) (Math.random() * 100.0);
		}
	}

	/**
	 * Method sum
	 * 
	 * @return: the value of the attribute sum
	 */
	public int sum() {
		return (this.sum);
	}

	/**
	 * Method sumSqr
	 * 
	 * @return: the value of the attribute sumSqr
	 */
	public long sumSqr() {
		return (this.sumSqr);
	}

	/**
	 * Method mean
	 * 
	 * @return: the value of the attribute mean
	 */
	public double mean() {
		return (this.mean);
	}

	/**
	 * Method run
	 * 
	 * Main code for a thread
	 */
	public void run() {
		for (int i = 0; i < this.size; i++) {
			System.out.println(this.name + ": " + i);
			this.sum += data[i];
			this.sumSqr += data[i] * data[i];
		}
		this.mean = (double) this.sum / (double) this.size;
	}
}