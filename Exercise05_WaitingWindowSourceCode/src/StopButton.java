import java.awt.Button;

public class StopButton extends Thread{
	Button bt;
	
	public StopButton (Button bt){
		this.bt = bt;
	}

	
	public void run(){
		try {
			System.out.println("We are going to sleep");
			bt.setEnabled(false);
			Thread.sleep(5000);
			bt.setEnabled(true);
			System.out.println("We now are wake up");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
