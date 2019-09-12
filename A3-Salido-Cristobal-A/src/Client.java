import java.util.Random;

public class Client extends Thread {
	private Cash cash;
	private int maxItems;
	private int[] itemCost;
	
	public Client (Cash cs, int mxit){
		cash = cs;
		maxItems = mxit;
	}

	@Override
	public void run() {
		Random r = new Random();
		int items = r.nextInt(maxItems-1)+1;
		itemCost = new int[items];
		for (int i = 0; i < items ; i++) {
			try {
				Thread.sleep(r.nextInt(3000)+2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int cost = r.nextInt(20)+1;
			itemCost[i] = cost;
		}	
		
		cash.putPurchase(new Purchase(itemCost));
		System.out.println("Cliente en caja");
	}
}
