package com.throwsexception.euler;

public class MultipleOf3And5 {

	private MultipleOf3And5() {}
	
	public static int sumOfUnder(int max) {
		int sum = 0;
		for(int i=3; i<max; i++) {
			if(i%3==0 || i%5==0) {
				sum += i;
			}
		}
		return sum;
		
	}
	
	public static void main(String[] args) {
		System.out.println("Sum for 10 is: " + sumOfUnder(1000));
	}

}
