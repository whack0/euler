package com.throwsexception.euler;

import com.google.common.math.LongMath;

//The prime factors of 13195 are 5, 7, 13 and 29.
//What is the largest prime factor of the number 600851475143 ?
public class LargestPrimeFactor {

	public static boolean isPrime(long l) {
		return LongMath.isPrime(l);
	}
	
	public static long largestPrimeFactor(long pfo) {
		long half = pfo/2;
		System.out.println("Half of : " + pfo + ", is: " + half);
		
		if(half%2==0) {
			half--;
		}
		
		while( true  ) {
			while(!isPrime(half)) {
				half=half-2;
			}
			//System.out.println("  Next prime: " + half);
			if(pfo % half == 0) {
				break;
			}
			half=half-2;
		}
		return half;
		
	}
	
	public static void main(String[] args) {
		System.out.println("Largest prime factor of 13195 is: " + largestPrimeFactor(13195));
		System.out.println("Largest prime factor of 600851475143 is: " + largestPrimeFactor(600851475143L));
	}

}
