package com.throwsexception.euler;

import com.google.common.math.LongMath;

public class NthPrime {

	public static long nthPrime(int n) {
		
		int pc = 0;
		int counter = 1;
		while(true) {
			if(LongMath.isPrime(counter)) {
				pc++;
			}
			if(pc == n) {
				return counter;
			}
			counter++;
		}
	}
	public static void main(String[] args) {
		for(int i=1; i<=10001; i++) {
			System.out.println(i + " : " + nthPrime(i));
		}
	}

}
