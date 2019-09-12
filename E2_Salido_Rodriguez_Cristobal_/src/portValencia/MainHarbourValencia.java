package portValencia;
import java.util.ArrayList;
import java.util.List;

public class MainHarbourValencia {
	private static final int MAXITEMS = 5;
	private static final int MAXCLIENTS = 5;
	private static Ground cash;
	private static List<Truck> clientList;
	private static List<Package> salesList;
	
	public static void main(String[] args) {
		cash = new Ground();
		clientList = new ArrayList<>();
		
		Crane cashier = new Crane(cash, MAXCLIENTS);
		cashier.start();

		for (int i = 0; i < MAXCLIENTS; i++) {
			clientList.add(new Truck(cash, MAXITEMS));
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
		for (Package purchase : salesList) {
			System.out.println(purchase.toString());
			totalBenefits += purchase.getTotal();
		}
		System.out.println("-------------------");
		System.out.println("Total benefits: " + totalBenefits + "€");
			
		
	}

}
