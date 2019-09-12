package portValencia;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Truck extends Thread {
	private Ground ground;
	private int maxPackage;
	private List<Package> packageList;
	
	public Truck (Ground gr, int mxwgh){
		ground = gr;
		maxPackage = mxwgh;
		packageList = new ArrayList<>();
	}

	@Override
	public void run() {

		Random r = new Random();
		//packages to get in the truck
		int packagesToGet = r.nextInt(maxPackage-1)+1;
		
		
		for (int i = 0; i < packagesToGet ; i++) {
			//Geta a package
			ground.getPackage();
			//wait for get the nest package
			try {
				Thread.sleep(r.nextInt(3000)+5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Package geted");
		}	
		
		
		
	}
}
