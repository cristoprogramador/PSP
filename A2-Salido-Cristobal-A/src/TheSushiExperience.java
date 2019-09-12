
import java.util.ArrayList;
import java.util.List;

/**
 * How is working "The Sushi Experience" restaurant
 * 
 * @author Cristobal Salido
 */
public class TheSushiExperience {
	private static int MAXCUSTOMERS = 4;
	private static double amount;
	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// creating the restaurant parts
		ConveyorBelt cb = new ConveyorBelt();

		List<Customer> customerList = new ArrayList<>();

		// System.out.println("Dishes on the conveyor belt:");
		// System.out.println("--------------------------------");
		
		Kitchen k = new Kitchen(cb);
		k.start();
		
		// Customer c1 = new Customer(cb);
		// Customer c2 = new Customer(cb);
		// Customer c3 = new Customer(cb);
		// Customer c4 = new Customer(cb);

		// c1.start();
		// c2.start();
		// c3.start();
		// c4.start();
		
		for (int i = 0; i < MAXCUSTOMERS; i++) {
			customerList.add(new Customer(cb));
			customerList.get(i).start();
		}

		// waiting for all to end
		for (int i = 0; i < MAXCUSTOMERS; i++) {
			try {
				customerList.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
//		try {
//			k.join();
//			// c1.join();
//			// c2.join();
//			// c3.join();
//			// c4.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		// writing accounts
//		System.out.println("\nAccounts");
//		System.out.println("---------");
//		for (int i = 0; i < MAXCUSTOMERS; i++) {
//			System.out.println(customerList.get(i).getAccount());
//		}
		
//		System.out.println(c1.getAccount());
//		System.out.println(c2.getAccount());
//		System.out.println(c3.getAccount());
//		System.out.println(c4.getAccount());
		
		// writing the profits
		System.out.println("--------------------------------");
		for (int i = 0; i < MAXCUSTOMERS; i++) {
			amount += customerList.get(i).getAmount();
		}
//		double amount = c1.getAmount() + c2.getAmount() + c3.getAmount() + c4.getAmount();
		System.out.println("Total profits: " + (double) Math.round(amount * 100d) / 100d + "€");
		
		//para dar el ejercicio por acabado correctamente
		System.exit(0);
	}

}
