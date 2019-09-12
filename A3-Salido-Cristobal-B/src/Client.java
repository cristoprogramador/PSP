import java.util.List;
import java.util.Random;
/**
 * Client class type thread
 * @author Cristobal Salido
 *
 */
public class Client extends Thread{

	private List<Service> servicesList;
	private ServicesQueue servicesQueue;
	private Service service;
	/**
	 * Constructor
	 * @param lsSrv list of services
	 * @param sq ServicesQueue shared object Service Queue
	 */
	public Client(List<Service> lsSrv, ServicesQueue sq){
		servicesList = lsSrv;
		servicesQueue = sq;
	}
	
	/**
	 * Method run() thread
	 */
	public void run(){
		Random r = new Random();
		//get a random service from the list of services
		service = servicesList.get(r.nextInt(servicesList.size()));
		try {
			//waiting to choice
			Thread.sleep (r.nextInt(20000)+5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Service Solicited an add at queue
		servicesQueue.choseService(service);
		System.out.println("Client "+getName()+" ready to be attended");
	}
	/**
	 * Get price of service selected from the list services
	 * @return double price of service selected
	 */
	public double getPrice(){
		return service.getPrice();
	}
	/**
	 * Return the name of client and the name and price and the discount of the service in a String.
	 * @return String name of client and service data
	 */
	public String getAccount(){
		return "Client: "+ getName() +" " + service.toString();
	}
}
