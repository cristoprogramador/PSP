import java.util.ArrayList;
import java.util.Random;

/**
 * Class type shared object to save dishes we can add and get dishes
 * 
 * @author Cristobal Salido
 *
 */
public class ConveyorBelt {
	private int CAPACIDAD = 16;
	private static ArrayList<Dish> conveyorBelt;

	/**
	 * Constructor
	 */
	public ConveyorBelt() {
		conveyorBelt = new ArrayList<Dish>();
	}

	/**
	 * method synchronized for the threads to add a dish
	 * 
	 * @param dish
	 *            for the list
	 */
	public synchronized void addDish(Dish dish) {
		// limitamos los platos en la lista a 16
		while (conveyorBelt.size() == CAPACIDAD) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		conveyorBelt.add(dish);
		// whit notify() starts the fist thread that is waiting
		notify();
	}

	/**
	 * method synchronized for the threads to get a dish
	 * 
	 * @param dish
	 *            from the list
	 */
	public synchronized Dish getDish() {
		// if there are no dishes
		while (conveyorBelt.size() == 0) {
			// the thread who call the method goes to waiting
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		// get the dish from the list and remove it
		Dish dsConveyor = conveyorBelt.get(0);
		conveyorBelt.remove(0);
		// despertamos a cocinero si se retira un plato
		// si la cinta estaba llena el cocinero estaba esperando y pondrá otro
		notify();
		return dsConveyor;
	}

}
