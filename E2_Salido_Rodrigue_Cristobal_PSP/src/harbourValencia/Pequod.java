package harbourValencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pequod {
	private List<Package> boatCharge;
	private int charge;
	private Random r;
	
	public Pequod(){
		
	}
	
	public Pequod(int ch){
		charge = ch;
		boatCharge = new ArrayList<Package>();
		r = new Random();
		
		for (int i = 0; i < charge; i++) {
			int weight = r.nextInt(200)+500;
			boatCharge.add(new Package(i, weight));		}
	}
	
	public Package getPackageFromPequod(){
		return boatCharge.remove(0);
	}
	
	public int getChargeFromPequod(){
		return boatCharge.size();
	}
}
