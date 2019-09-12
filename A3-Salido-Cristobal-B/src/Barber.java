import java.util.Random;
/**
 * Barber class type thread
 * @author Cristobal Salido
 *
 */
public class Barber extends Thread {
	private ServicesQueue servicesQueue;
	private int maxCustomers;
	/**
	 * Constructor
	 * @param srvQue ServicesQueue shared object Service Queue
	 * @param maxC int number of max clients to attend
	 */
	public Barber(ServicesQueue srvQue, int maxC) {
		servicesQueue = srvQue;
		maxCustomers = maxC;
	}

	/**
	 * Method run() thread
	 */
	public void run() {

		Random r = new Random();
		int count = 0;
		System.out.println("Barber ready");
		// during maxCustomers make in loop
		while (count < maxCustomers) {
			//get from the share object a service required in queue
			servicesQueue.attendService();
			try {
				//attending service
				Thread.sleep(r.nextInt(3000) + 3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Clients attended: " + (count + 1));
			count++;
		}

	}

}
