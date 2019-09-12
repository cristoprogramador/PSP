package harbourValencia;

import java.util.ArrayList;
import java.util.List;

public class MainHarbourValencia {
	private static final int MAXPACKAGECHARGE = 5;
	private static final int CHARGE = 25;
	private static final int TRUKS = 4;

	private static Ground ground;
	private static Pequod pequod;
	private static List<Truck> trukList;
	
	public static void main(String[] args) {
		ground = new Ground();
		pequod = new Pequod(CHARGE);
		trukList = new ArrayList<>();
		
		Crane crane = new Crane(ground, pequod, CHARGE);
		crane.start();

		for (int i = 0; i < TRUKS; i++) {
			trukList.add(new Truck(ground, MAXPACKAGECHARGE));
			trukList.get(i).start();
		}
		
		for (int i = 0; i < trukList.size(); i++) {
			try {
				trukList.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			crane.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int totalWeight = 0;
		for (int i = 0; i < trukList.size(); i++) {
			System.out.println(trukList.get(i).getAmountOfCharge());
			totalWeight += (trukList.get(i).getTotalWeight());
		}
		
		System.out.println("-------------------");
		System.out.println("Total weight: " + totalWeight + "Kg");
		System.out.println("Packages in the Pequod " + pequod.getChargeFromPequod());

	}

}
