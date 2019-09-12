package dataAnalysis;

import java.util.ArrayList;
import java.util.List;

public class ParallelFibonacci {
	private static final int[] numbers = { 10, 15, 25, 30, 35, 40, 45, 47 };
	private static List<GetFibonacci> fibonacciThreads;
	
	public static void main(String[] args) {
		fibonacciThreads = new ArrayList<>();
		
		long totalFibonacci = 0;

		for (int i = 0; i < numbers.length; i++) {
			fibonacciThreads.add(new GetFibonacci(numbers[i]));
			fibonacciThreads.get(i).start();
		}
		
		for (int i = 0; i < numbers.length; i++) {
			try {
				fibonacciThreads.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < numbers.length; i++) {
			totalFibonacci += fibonacciThreads.get(i).getFibonazi();
		}
		
		System.out.println("Total: " + totalFibonacci);

	}

}
