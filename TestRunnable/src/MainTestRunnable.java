
public class MainTestRunnable {
	
	public static void main(String[]args){
		MyThreadClass t1 = new MyThreadClass();
		
		t1.start();
		
		MyRunnableClass mrc = new MyRunnableClass();
		Thread t2 = new Thread(mrc);
		t2.start();
		
		System.out.println("Programa pincipal ha acabado");
	}
}
