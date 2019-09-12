import java.util.Random;

public class GetNumber extends Thread {

	private int itera;
	private int startArray;
	private int endArray;
	private int startRandom;
	private int endRandom;
	private static int MILLIS = 1000000; // 1.000.000
	int[] v;

	public GetNumber(int it, int sa, int ea, int sr, int er, int[] v) {
		itera = it;
		startArray = sa;
		endArray = ea;
		startRandom = sr;
		endRandom = er;
		this.v = v;
	}
	
	public int getRandom (){
		int number = 0;
		Random r = new Random();
		//GENERAMOS UN NUMRO RANDOM ENTRE LA PARTE DEL RANGO QUE LE TOCA
		for (int i = 0; i < itera; i++){
			number += r.nextInt((this.endRandom - this.startRandom) + 1) + this.startRandom;		
		}
		return number;
	}
	
	@Override
	public void run() {
		long startTime = System.nanoTime();

		for (int i = this.startArray; i < this.endArray; i++) {
			this.v[i] = getRandom();
		}	
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration / MILLIS);
	}

}
