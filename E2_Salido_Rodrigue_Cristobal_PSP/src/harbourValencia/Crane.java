package harbourValencia;

import java.util.Random;

public class Crane extends Thread {
	private Ground ground;
	private int maxPackages;
	private Pequod pequod;

	public Crane(Ground cs, Pequod pq, int mxcl) {
		ground = cs;
		maxPackages = mxcl;
		pequod = pq;
	}

	@Override
	public void run() {
		Random r = new Random();
		Package newPackage = new Package();

		int count = 0;
		while (count < maxPackages) {
			newPackage = pequod.getPackageFromPequod();

			try {
				Thread.sleep(r.nextInt(5000) + 2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ground.putPackage(newPackage);
			count++;
			System.out.println("paquete en suelo");		
		}
	}
}
