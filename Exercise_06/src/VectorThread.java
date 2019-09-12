import java.util.Random;

public class VectorThread extends Thread {

	private int valorSuperior;
	private int valorInferior;
	private int[] v;
	private int MAX_ITER = 100000;		//100.000
	private int MAX_RANDOM = 1000000; 	//1.000.000
	private static int MILLIS = 1000000; 

	public int getNumber() {   
		//no necesitamos que sea statico, q solo se aplique en esta clase
		
		int number = 0;
		Random r = new Random();

		for (int i = 0; i < MAX_ITER; i++) {
			number += r.nextInt(MAX_RANDOM);
		}

		return number;
	}

	
	public VectorThread(int valorInferior, int valorSuperior, int[] v) {

		this.valorSuperior = valorSuperior;
		this.valorInferior = valorInferior;
		this.v = v;
		//todos los hilops trabajan sobre el mismo vector

	}

	@Override
	public void run() {
		long startTime = System.nanoTime();

		for (int i = valorInferior; i < valorSuperior; i++) {
			v[i] = getNumber();
		}

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);

		System.out.println(duration/MILLIS);
	}

}
