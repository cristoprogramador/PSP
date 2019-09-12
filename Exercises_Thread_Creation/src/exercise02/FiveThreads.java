package exercise02;

public class FiveThreads extends Thread {
	private int id;
	
	public FiveThreads (int id){
		this.id = id;
	}

	@Override
	public void run() {
		int time = 500 + (int) (Math.random()*((10000 - 500) + 1));
		System.out.println("Hello, This is the thread " + this.id 
				+ " and i am going to sleep " + (time) + " milliseconds");	
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Hello, this is the thread " + this.id + " an i am awaken");
	}
}
