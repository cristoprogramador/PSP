/**
 * How is working  "The Sushi Experience" restaurant 
 * @author Cristobal Salido
 */
public class TheSushiExperience {
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		//creating the restaurant parts
		ConveyorBelt cb = new ConveyorBelt();
		Kitchen k = new Kitchen(cb);
		Customer c1 = new Customer(cb);
		Customer c2 = new Customer(cb);
		Customer c3 = new Customer(cb);
		Customer c4 = new Customer(cb);

	//	System.out.println("Dishes on the conveyor belt:");
	//	System.out.println("--------------------------------");

		//starting the components
		k.start();
		c1.start();
		c2.start();
		c3.start();
		c4.start();

		//waiting for all to end 
		try {
			k.join();
			c1.join();
			c2.join();
			c3.join();
			c4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//writing accounts 
		System.out.println("\nAccounts");
		System.out.println("---------");
		System.out.println(c1.getAccount());
		System.out.println(c2.getAccount());
		System.out.println(c3.getAccount());
		System.out.println(c4.getAccount());
		//writing the profits
		System.out.println("--------------------------------");
		double amount = c1.getAmount()+c2.getAmount()+c3.getAmount()+c4.getAmount();
		System.out.println("Total profits: " + (double)Math.round(amount * 100d) / 100d + "€");

	}

}
