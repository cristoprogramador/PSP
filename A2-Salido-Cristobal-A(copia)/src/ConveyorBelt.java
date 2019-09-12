import java.util.ArrayList;

public class ConveyorBelt {
	private static ArrayList<Dish> conveyorBelt;

	public ConveyorBelt() {
		conveyorBelt = new ArrayList<Dish>();
	}

	public synchronized void addDish(Dish dish) {
		conveyorBelt.add(dish);
		notify();
	}

	public synchronized Dish getDish() {
		Dish dsConveyor = new Dish();

		if (conveyorBelt.size() == 0) {

			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		
		dsConveyor = conveyorBelt.get(0);
		conveyorBelt.remove(0);

		return dsConveyor;
	}

}
