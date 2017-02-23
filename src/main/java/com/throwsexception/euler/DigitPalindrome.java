package com.throwsexception.euler;



//A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
//Find the largest palindrome made from the product of two 3-digit numbers.
public class DigitPalindrome {

	public static boolean isPalindrome(String s) {
		int len = s.length();
		for(int i=0; i<=len/2; i++) {
			if(s.charAt(i) != s.charAt(len-i-1)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isPalindrome(long l) {
		return isPalindrome(l+"");
	}
	
	public static long findPalindrome(int maxF) {
		int maxP = 0;
		for(int i=maxF; i>1; i--) {
			for(int j=i; j>1; j--) {
				int p = i*j;
				if(p<maxP) {
					break;
				}
				if(isPalindrome(p)) {
					maxP = p;
				}
			}
		}
		return maxP;
	}
	
	public static void main(String[] args) {
		System.out.println("largest palindrom under 999*999: " + findPalindrome(999));
	}

}
