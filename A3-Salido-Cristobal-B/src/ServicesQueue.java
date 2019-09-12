import java.util.ArrayList;
import java.util.List;
/**
 * Class type shared object to save dishes
 * we can add and get dishes
 * @author Cristobal Salido
 */
public class ServicesQueue {
	private List<Service> services;
	/**
	 * Constructor
	 */
	public ServicesQueue() {
		services = new ArrayList<>();
	}
	
	/**
	 * method synchronized for the threads to add a service to the list 
	 * @param service for the list
	 */
	public synchronized void choseService(Service service) {
		//add a service
		services.add(service);
		//notify who a service is add to the list
		notify();
	}
	
	/**
	 * method synchronized for the threads to get a service 
	 * @return service from the list
	 */
	public synchronized Service attendService() {	
		//the thread wait to attend a service if not are one at list
		while (services.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
		//get a service to attend
		return services.remove(0);	
		}	
}
