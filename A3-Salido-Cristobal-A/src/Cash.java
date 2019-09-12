import java.util.ArrayList;
import java.util.List;

public class Cash {
	private static List<Purchase> purchaseList;
	
	public Cash(){
		purchaseList = new ArrayList<Purchase>();
	}
	
	public synchronized void putPurchase(Purchase prch) {
		purchaseList.add(prch);
		notify();
	}

	public synchronized Purchase getPurchase() {
		if (purchaseList.size() == 0){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return purchaseList.remove(0);
	}

}
