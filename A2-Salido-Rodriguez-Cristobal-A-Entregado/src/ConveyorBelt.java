import java.util.ArrayList;
/**
 * Class type shared object to save dishes
 * we can add and get dishes
 * @author Cristobal Salido
 *
 */
public class ConveyorBelt {
	private static ArrayList<Dish> conveyorBelt;
	/**
	 * Constructor
	 */
	public ConveyorBelt() {
		conveyorBelt = new ArrayList<Dish>();
	}

	/**
	 * method synchronized for the threads to add a dish 
	 * @param dish for the list
	 */
	public synchronized void addDish(Dish dish) {
		conveyorBelt.add(dish);
		//whit notify() starts the fist thread that is waiting
		notify();
	}

	/**
	 * method synchronized for the threads to get a dish 
	 * @param dish from the list
	 */
	public synchronized Dish getDish() {
		Dish dsConveyor = new Dish();
		//if there are no dishes
		if (conveyorBelt.size() == 0) {
			//the thread who call the method goes to waiting
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		//get the dish from the list and remove it
		dsConveyor = conveyorBelt.get(0);
		conveyorBelt.remove(0);

		return dsConveyor;
	}

}
