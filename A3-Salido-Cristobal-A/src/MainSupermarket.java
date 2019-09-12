import java.util.ArrayList;
import java.util.List;

public class MainSupermarket {
	private static final int MAXITEMS = 5;
	private static final int MAXCLIENTS = 5;
	private static Cash cash;
	private static List<Client> clientList;
	private static List<Purchase> salesList;
	
	public static void main(String[] args) {
		cash = new Cash();
		clientList = new ArrayList<>();
		
		Cashier cashier = new Cashier(cash, MAXCLIENTS);
		cashier.start();

		for (int i = 0; i < MAXCLIENTS; i++) {
			clientList.add(new Client(cash, MAXITEMS));
			clientList.get(i).start();
		}
		
		for (int i = 0; i < clientList.size(); i++) {
			try {
				clientList.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			cashier.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		salesList = cashier.getSalesList();
		int totalBenefits = 0;
		for (Purchase purchase : salesList) {
			System.out.println(purchase.toString());
			totalBenefits += purchase.getTotal();
		}
		System.out.println("-------------------");
		System.out.println("Total benefits: " + totalBenefits + "€");
			
		
	}

}
