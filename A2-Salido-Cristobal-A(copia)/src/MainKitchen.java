public class MainKitchen {

	public static void main(String[] args) {
		ConveyorBelt cb = new ConveyorBelt();

		Kitchen k = new Kitchen(cb);
//		Customer c1 = new Customer(cb);
//		Customer c2 = new Customer(cb);
//		Customer c3 = new Customer(cb);
//		Customer c4 = new Customer(cb);
		Customer2 c1 = new Customer2(cb);
		Customer2 c2 = new Customer2(cb);
		Customer2 c3 = new Customer2(cb);
		Customer2 c4 = new Customer2(cb);

		k.start();
		c1.start();
		c2.start();
		c3.start();
		c4.start();

		try {
			k.join();
			c1.join();
			c2.join();
			c3.join();
			c4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(c1.getAccount());
		System.out.println(c2.getAccount());
		System.out.println(c3.getAccount());
		System.out.println(c4.getAccount());

		double amount = c1.getAmount()+c2.getAmount()+c3.getAmount()+c4.getAmount();
		
		System.out.println("Profits: " + amount + "€");

	}

}
