import java.util.ArrayList;
import java.util.Random;

public class Kitchen extends Thread {
	private static ArrayList<Dish> listKitchen;
	private ConveyorBelt cb;
	private static final String[] dishName ={"makisushi","futomaki","hosomaki","kappamaki"};
	private static final double[] dishCost={2.00, 2.50, 3.75, 1.15};
	
	public Kitchen (ConveyorBelt cb){
		this.cb = cb;
		listKitchen = new ArrayList<Dish>();
	}
	
	@Override
	public void run(){
		Random r = new Random();
		int number;
		
		for (int i = 0; i < 8; i++) {	
			number = r.nextInt(4);
			Dish dsKitchen = new Dish(dishName[number], dishCost[number]);
			
			try {
				Thread.sleep(r.nextInt(1500)+1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			listKitchen.add(dsKitchen);
			cb.addDish(dsKitchen);			
		}			
	}
}
