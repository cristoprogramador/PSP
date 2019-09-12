package dataAnalysis;

public class SecuentialFibonacci {
	private static final int[] numbers ={10,15,25,30,35,40,45,47};
	
	public static void main(String[] args) {
		long totalFibonacci = 0;
		
		for (int i = 0; i < numbers.length; i++) {		
			totalFibonacci += fibonacci(numbers[i]);
		}
		System.out.println("Total: "+ totalFibonacci);
		
	}

	public static long fibonacci(long n) {
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
}
