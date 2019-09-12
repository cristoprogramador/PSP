public class Dish {
	private String name;
	private double cost;
	
	public Dish() {
	}

	public Dish(String nm, double cs) {		
		name = nm;
		cost = cs;
	}

	public String getName() {
		return name;
	}

	public double getCost() {
		return cost;
	}

	@Override
	public String toString() {
		return "Dish name=" + name + ", cost=" + cost;
	}
	
	
	

}
