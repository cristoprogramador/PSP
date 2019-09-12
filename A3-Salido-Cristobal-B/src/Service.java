/**
 * Class of Services
 * @author Cristobal Salido
 */
public class Service {
	private String name;
	private double price;
	private boolean offer;
	private int discount;

	/**
	 * Void Constructor
	 */
	public Service() {
	}

	/**
	 * Constructor
	 * @param nm String name of Service
	 * @param prc double price of Service
	 * @param dsct int discount of Service
	 */
	public Service(String nm, double prc, int dsct) {
		name = nm;
		discount = dsct;
		price = prc - ((prc / 100) * dsct);
		if (dsct > 0)
			offer = true;
		else
			offer = false;
	}

	/**
	 * Get the name of service
	 * @return String name of service
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the price of service
	 * @return double price of service
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Get the discount of service
	 * @return int discount of service
	 */
	public int getDiscount() {
		return discount;
	}

	/**
	 * Return the name the price and the discount of the service in a String.
	 */
	@Override
	public String toString() {
		
		if (offer)
			return getName() + " Price: " + getPrice() + "€ Offer " + getDiscount()+"% Off";
		else
			return getName() + " Price: " + getPrice() + "€";
	}
}
