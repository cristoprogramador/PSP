
public class MainProgram {
	
	public static void main (String[] args){
		MyThread mt1 = new MyThread();
		MyThread mt2 = new MyThread();
		
		mt1.start();
		mt2.start();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("End of the main thread");
	}

}
