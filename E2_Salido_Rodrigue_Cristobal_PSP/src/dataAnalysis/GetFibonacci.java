package dataAnalysis;

public class GetFibonacci extends Thread {
	private long number;
	private long result;
	
	public GetFibonacci(long n){
		number = n;
	}
	
	@Override
	public void run() {
		result = fibonacci(number);
	}
	
	public long fibonacci(long n) {
		if (n > 1) {
			return fibonacci(n - 1) + fibonacci(n - 2);
		} else if (n == 1) {
			return 1;
		} else if (n == 0) {
			return 0;
		} else {
			System.out.println("You must enter a value > 0");
			return -1;
		}
	}

	public long getFibonazi() {
		return result;
	}

}
