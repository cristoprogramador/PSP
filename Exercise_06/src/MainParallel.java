
public class MainParallel {

	
	public static void main(String[] args) {
		int[] v = new int[10000];
		VectorThread m1 = new VectorThread(0, 5000, v);
		m1.start();
		VectorThread m2 = new VectorThread(5000, 10000, v);
		m2.start();
	
	}
}
