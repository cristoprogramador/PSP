
public class Terminator_Bank {
	public static void main(String[] args) {
		

	Acount acount = new Acount(500);
		
	Connor sarah = new Connor("Sarah", acount);
	Connor john = new Connor("John", acount);
	
	sarah.start();
	john.start();
	

	try {
		sarah.join();
		john.join();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
	
	}
}
