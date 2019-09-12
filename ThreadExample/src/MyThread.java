
public class MyThread extends Thread {

	//This method contains the code to be executed by the thread
	public void run(){
		System.out.println(this.getName()+": This code is executed by the thread");
	}
}
