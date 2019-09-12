import java.util.Random;

public class Customer extends Thread {
	private static final int MAXDISHES = 2;

	private ConveyorBelt cb;
	private double account;
	private double[] cost;
	private String[] dishes;

	public Customer(ConveyorBelt cb) {
		this.cb = cb;
		cost = new double[MAXDISHES];
		dishes = new String[MAXDISHES];
	}

	@Override
	public void run() {
		Random r = new Random();

		for (int i = 0; i < MAXDISHES; i++) {
			Dish dsCustomer = new Dish();
			dsCustomer = cb.getDish();
			
			cost[i] = dsCustomer.getCost();
			account += dsCustomer.getCost();
			dishes[i] = dsCustomer.getName();
			
			try {
				Thread.sleep(r.nextInt(5000) + 5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public double getAmount() {
		return account;
	}

	public String getAccount() {
		String dishesString = "Client " + getName() + " Dishes:";
		for (int i = 0; i < dishes.length; i++) {
			dishesString += "\n" + dishes[i] + " cost:" + cost[i];
		}
		return dishesString + "\nAmount: " + getAmount()+"\n";
	}
}
