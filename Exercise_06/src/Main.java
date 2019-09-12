//import java.util.Random;
//
//public class Main {
//	private static int MAX = 10000;				//10.000
//	private static int MAX_ITER = 100000;		//100.000
//	private static int MAX_RANDOM = 1000000; 	//1.000.000
//	private static int MILLIS = 1000000; 		//1.000.000
//	
//
//	public static int getNumber() {
//		int number = 0;
//		Random r = new Random();
//
//		for (int i = 0; i < MAX_ITER; i++) {
//			number+=r.nextInt(MAX_RANDOM);
//		}
//
//		return number;
//	}
//
//	public static void main(String[] args) {
//		
//		int[] v = new int[MAX];
//
//		long startTime = System.nanoTime();
//
//		for (int i = 0; i < MAX; i++) {
//			v[i] = getNumber();
//		}
//
//		long endTime = System.nanoTime();
//
//		long duration = (endTime - startTime);
//
//		System.out.println(duration/MILLIS);
//
//	}
//
//}
