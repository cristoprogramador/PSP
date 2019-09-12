import java.util.Arrays;

public class Purchase {	
	private int cost[];
	
	public Purchase (int[] cs){
		cost =cs;
	}

	public int[] getCost() {
		return cost;
	}
	
	public int getTotal() {
		int total=0;
		for (int i = 0; i < cost.length; i++) {
			total += cost[i];
		}
		return total;
	}

	@Override
	public String toString() {
		return "Purchase [cost=" + Arrays.toString(cost) + "]";
	}

}
