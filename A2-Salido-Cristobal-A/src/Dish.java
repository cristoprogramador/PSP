/**
 * Class of dishes
 * @author Cristobal Salido
 *
 */
public class Dish {
	private String name;
	private double cost;
	/**
	 * void constructor
	 */
	public Dish() {
	}

	/**
	 * Constructor
	 * @param nm name of dish
	 * @param cs cost of dish
	 */
	public Dish(String nm, double cs) {		
		name = nm;
		cost = cs;
	}

	/**
	 * Get name of dish
	 * @return Name of dish
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get cost of dish
	 * @return Cost of dish
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * Return the name an the cost of the dish in a String.
	 */
	@Override
	public String toString() {
		return "Dish name: " + name + ", cost: " + cost;
	}

}
