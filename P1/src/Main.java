import java.util.ArrayList;

public class Main {
	private static int MAX = 10000; // 10.000
	private static int MAX_ITER = 100000; // 100.000
	private static int MAX_RANDOM = 1000000; // 1.000.000
	private static int MAX_THREADS = 5;

	public static void main(String[] args) {
		ArrayList<GetNumber> lsTrhead = new ArrayList<GetNumber>();
		int[] v = new int[MAX];

		for (int i = 0; i < MAX_THREADS; i++) {
			int startArray = 0 + (MAX / MAX_THREADS) *i;
			System.out.println("Start Array "+ i + " Trhead: "+startArray);
			
			int endArray = (MAX / MAX_THREADS) + (MAX / MAX_THREADS) * i;
			System.out.println("End Array "+ i + " Trhead: "+ endArray);
			
//			int startItera = 0 + (MAX_ITER / MAX_THREADS) *i;
//			System.out.println("Start Itera "+ i + " Trhead: "+startArray);
//			
//			int endItera = (MAX_ITER / MAX_THREADS) + (MAX_ITER / MAX_THREADS) * i;
//			System.out.println("End Itera "+ i + " Trhead: "+ endArray);
			
			int startRandom = 0 + (MAX_RANDOM / MAX_THREADS) * i;
			System.out.println("Start Random "+ i + " Trhead: "+ startRandom);
			
			int endRandom = (MAX_RANDOM / MAX_THREADS) + (MAX_RANDOM / MAX_THREADS) * i;
			System.out.println("End Random "+ i + " Trhead: "+ endRandom);

			int itera = MAX_ITER / MAX_THREADS;
			
			lsTrhead.add(new GetNumber(itera, startArray, endArray, startRandom, endRandom, v));
			lsTrhead.get(i).start();
		}
		
	}
}
