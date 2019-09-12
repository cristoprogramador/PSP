
public class MyRunnableClass implements Runnable{
	
	@Override
	public void run(){
		System.out.println("Soy una interface que me convertiré en un hilo");

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
