import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * 
 * @author Cristobal Salido
 *
 */
public class BarberShop {
	private static final String[] serviceName = {"Service 1", "Service 2", "Service 3", "Service 4", "Service 5", "Service 6", "Service 7"};
	private static final int[] cost = {5,7,15,10,12,9,6};
	private static final int DISCOUNT = 15;
	private static final int MAXCUSTOMERS = 5;

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		ServicesQueue servicesQueue = new ServicesQueue();
		List<Service> servicesList = new ArrayList<>();
		List<Client> clientsList = new ArrayList<>();
		
		Random r = new Random();
		
		//We generate a list with the 7 services and randomly one on offer.
		int randomOffer = r.nextInt(6);
		for (int i = 0; i < 6; i++) {
			if (i == randomOffer){
				servicesList.add(new Service(serviceName[i], cost[i], DISCOUNT));
			} else{
				servicesList.add(new Service(serviceName[i], cost[i], 0));
			}
		}
		
		//We alert the barber to attend the queue of customers
		Barber barber = new Barber(servicesQueue, MAXCUSTOMERS);
		barber.start();
		
		//We generate a list of clients with the list of services and the queue to position themselves
		for (int i = 0; i < MAXCUSTOMERS; i++) {
			clientsList.add(new Client(servicesList, servicesQueue));
			clientsList.get(i).start();
		}
		
		//We wait for all customers to choose and put themselves in the queue
		for (int i = 0; i < MAXCUSTOMERS; i++) {
			try {
				clientsList.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("All clients queued");
		
		try {
			barber.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("All clients attended\n");
		
		//We collect invoices and report benefits
		double benefits = 0;	
		for (int i = 0; i < MAXCUSTOMERS; i++) {
			System.out.println(clientsList.get(i).getAccount());
			benefits += clientsList.get(i).getPrice();
		}
		System.out.println("-----------------------------------");
		System.out.println("Total benefits: " + benefits + "€");
		//System.exit(0);
	}
}
