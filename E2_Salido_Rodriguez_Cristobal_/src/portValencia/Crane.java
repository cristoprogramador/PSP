package portValencia;
import java.util.Random;

public class Crane extends Thread{
	private Ground ground;
	private int maxPackages;
	
	public Crane(Ground cs, int mxcl){
		ground = cs;
		maxPackages = mxcl;
	}

	@Override
	public void run() {	
		Package package = new Package();
		Random r = new Random();
		while (true){
			int weight = r.nextInt(200)+500;
			
			try {
				Thread.sleep(r.nextInt(5000)+2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}

}
