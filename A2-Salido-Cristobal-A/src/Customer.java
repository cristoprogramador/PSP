import java.util.Random;
/**
 * Customer class type thread
 * @author Cristobal Salido
 *
 */
public class Customer extends Thread {
//	private static final int MAXDISHES = 4;
	private ConveyorBelt cb;
	private double[] costs;
	private String[] dishes;

	/**
	 * Constructor
	 * @param cb shared object ConveyorBelt
	 */
	public Customer(ConveyorBelt cb) {
		this.cb = cb;

	}

	@Override
	public void run() {
		Random r = new Random();
		int numPlatos = r.nextInt(3)+1;
		
		costs = new double[numPlatos];
		dishes = new String[numPlatos];
		
		//getting dishes
		for (int i = 0; i < numPlatos; i++) {
			//get a dish
			Dish dsCustomer =  cb.getDish();

			//saving names an costs from the dish
			costs[i] = dsCustomer.getCost();
			dishes[i] = dsCustomer.getName();

			//if we have more than one dish, compare to get a offer
			if (i > 0) {
				for (int j = 0; j <= i - 1; j++) {
					//if we have already taken an equal dish the value is half
					if (dishes[i].equals(dishes[j])) {
						costs[i] = dsCustomer.getCost() / 2;
					}					
				}
			}
			//eating the dish for a time between 5 and 10 seconds
			try {
				Thread.sleep(r.nextInt(5000) + 5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//printing the account
		System.out.println(getAccount());
	}

	/**
	 * @return the total of account rounded with two decimals
	 */
	public double getAmount() {
		double account = 0;
		for (int i = 0; i < costs.length; i++) {
			account += costs[i];
		}
		return (double)Math.round(account * 100d) / 100d;
	}

	/**
	 * @return the list of dishes and his cost
	 */
	public String getAccount() {
		String dishesString = "Client " + getName() + " Dishes:";
		for (int i = 0; i < dishes.length; i++) {
			dishesString += "\n" + dishes[i] + " " + costs[i]+"€";
		}		System.out.println();

		return dishesString + "\n-------------------\nAmount: " + getAmount() + "€\n";
	}
}
