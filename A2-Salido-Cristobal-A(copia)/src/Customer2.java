import java.util.ArrayList;
import java.util.Random;

public class Customer2 extends Thread {
	private static final int MAXDISHES = 2;

	private ConveyorBelt cb;
	private double account;
	private static ArrayList<Dish> dishesCustomer;
	//private double[] cost;
	//private String[] dishes;

	public Customer2(ConveyorBelt cb) {
		this.cb = cb;
		//cost = new double[MAXDISHES];
		//dishes = new String[MAXDISHES];
	}

	@Override
	public void run() {
		Random r = new Random();
		dishesCustomer = new ArrayList<>();

		for (int i = 0; i < MAXDISHES; i++) {
			Dish dsCustomer = new Dish();
			
			dsCustomer = cb.getDish();
			
			//cost[i] = dsCustomer.getCost();
			//dishes[i] = dsCustomer.getName();
			dishesCustomer.add(dsCustomer);
			account += dsCustomer.getCost();
			
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

//	public String getAccount() {
//		String dishesString = "Client " + getName() + " Dishes:";
//		for (int i = 0; i < dishes.length; i++) {
//			dishesString += "\n" + dishes[i] + " cost:" + cost[i];
//		}
//		return dishesString + "\nAmount: " + getAmount()+"\n";
//	}
	
	public String getAccount(){
		String dishesString = "Client " + getName() + " Dishes:";
		
		for (Dish dish : dishesCustomer) {
			dishesString += "\n" + dish.getName() + " Cost: " + dish.getCost() + "€";
		}
		
		return dishesString + "\nAmount: " + getAmount()+"\n";
	}
}
