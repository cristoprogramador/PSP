package exercise02;

import java.util.ArrayList;

public class TestThread {
	
	public static void main(String[] args) {
		ArrayList<FiveThreads> names = new ArrayList<FiveThreads>();
		
		for (int i = 0; i < 5; i++) {		
			names.add(new FiveThreads(i));
		}
		
		System.out.println("The main thread has finished");
		
		for (int i = 0; i < 5; i++) {		
			names.get(i).start();
		}
	}

}
