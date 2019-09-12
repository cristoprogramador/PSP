import java.util.Random;
/**
 * Class kitchen type thread
 * Make dishes and put in the conveyor belt
 * @author Cristobal Salido
 *
 */
public class Kitchen extends Thread {
//	private static final int MAXDISHES = 16;
	private ConveyorBelt cb;
	private static final String[] dishName ={"Makisushi","Futomaki","Hosomaki","Kappamaki"};
	private static final double[] dishCost={2.00, 2.50, 3.75, 1.15};
	Random r = new Random();

	/**
	 * Constructor
	 * @param cb shared object ConveyorBelt
	 */
	public Kitchen (ConveyorBelt cb){
		this.cb = cb;
	}
	
	@Override
	public void run(){
//		Random r = new Random();
//		int number;
		
		//making dishes
	//	for (int i = 0; i < MAXDISHES; i++) {	
		while (true){
			//number = r.nextInt(4);			
			//making the dish for a time between 5 and 10 seconds
			try {
				Thread.sleep(r.nextInt(1500)+1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
//			Dish dsKitchen = new Dish(dishName[number], dishCost[number]);
			Dish dsKitchen = crearPlato();
			//System.out.println(dsKitchen.getName()+" Dish");			
			
			//add the dish to the conveyor belt
			cb.addDish(dsKitchen);			
		}			
	}
	
	public Dish crearPlato(){
		int number = r.nextInt(4);	
		Dish dsKitchen = new Dish(dishName[number], dishCost[number]);
		return dsKitchen;
	}
}
