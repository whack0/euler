package com.throwsexception.euler;

public class EvenFibNums {

	private EvenFibNums() {}
	
	public static long sumOfEvenTermsUnder(int max) {
		int fib1 = 1;
		int fib2 = 2;
		int sum = 2;
		while(fib2<max) {
			int tmp = fib1 + fib2;
			fib1 = fib2;
			fib2 = tmp;
			if(fib2 % 2 == 0) {
				sum += fib2;
			}
		}
		return sum;
	}
	
	public static void main(String[] args) {
		System.out.println("Sum of even fibs under 4M: " + sumOfEvenTermsUnder(4*1000*1000));
		
	}
	
}
