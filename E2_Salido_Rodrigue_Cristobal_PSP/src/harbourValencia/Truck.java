package harbourValencia;

import java.util.Random;

public class Truck extends Thread{
	private Ground ground;
	private int maxPackage;
	private int totalWeight;
	private int[] weight;
	
	public Truck (Ground gr, int mxpkg){
		ground = gr;
		maxPackage = mxpkg;
		totalWeight = 0;
	}
	
	@Override
	public void run() {
		Random r = new Random();
		//packages to get in the truck
		int packagesToGet = r.nextInt(maxPackage-4)+4;
		weight = new int[packagesToGet];
		Package newPackage = new Package();
		
		for (int i = 0; i < packagesToGet ; i++) {
			//Geta a package
			newPackage = ground.getPackage();
			//wait for get the nest package
			try {
				Thread.sleep(r.nextInt(3000)+5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			weight[i]= newPackage.getWeight();
			System.out.println("Package geted");
		}	
	
	}
	
	public String getAmountOfCharge(){				
		return "Truck "+getName()+ " "+weight.length+" Package/s " + getTotalWeight() +"Kg";
	}
	
	public int getTotalWeight(){		
		for (int i = 0; i < weight.length; i++) {
			totalWeight += weight[1];
		}
		return totalWeight;
	}
}
