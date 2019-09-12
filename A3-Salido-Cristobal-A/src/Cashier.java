import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cashier extends Thread{
	private Cash cash;
	private int maxClients;
	private List<Purchase> salesList;
	
	public Cashier (Cash cs, int mxcl){
		cash = cs;
		maxClients = mxcl;
		salesList = new ArrayList<>();
	}

	@Override
	public void run() {	
		Random r = new Random();
		Purchase purchase;
		
		int count = 0;		
		while (count < maxClients){
			purchase = cash.getPurchase();
			try {
				Thread.sleep(r.nextInt(5000)+2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			salesList.add(purchase);
			System.out.println("Cliente atendido");
			count++;
		}
	}

	public List<Purchase> getSalesList() {
		return salesList;
	}
}
