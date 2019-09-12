package exercise01;

public class TestClass {

	public static void main(String[] args) {
		ThreadClass tc1 = new ThreadClass(1);
		Thread t1 = new Thread(tc1);
		ThreadClass tc2 = new ThreadClass(2);
		Thread t2 = new Thread(tc2);
		ThreadClass tc3 = new ThreadClass(3);
		Thread t3 = new Thread(tc3);

		t1.start();t2.start();t3.start();

	}

}
