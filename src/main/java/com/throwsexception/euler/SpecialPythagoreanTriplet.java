package com.throwsexception.euler;

public class SpecialPythagoreanTriplet {

	/**
	 * A + B + C = 1000
	 * A*A + B*B = C*C
	 * 
	 * @param sumOfABC
	 * @return
	 */
	public static int get(int sumOfABC) {
		return get(sumOfABC/2, sumOfABC/2, sumOfABC);
	}
	
	private static int get(int maxA, int maxB, int sumOfABC) {
		
		for(int i=1; i<maxA; i++) {
			for(int j=i; j<maxB; j++) {
				int c = sumOfABC-i-j;
				if(isValid(i, j, c)) {
					return i*j*c;
				}
			}
		}
		
		throw new IllegalStateException("not found");
		
	}

	private static boolean isValid(int i, int j, int c) {
		
		if( ((i*i)+(j*j)) == c*c ) {
			return true;
		} else {
			return false;
		}
		
	}

	public static void main(String[] args) {
		System.out.println(get(1000));
	}

}
