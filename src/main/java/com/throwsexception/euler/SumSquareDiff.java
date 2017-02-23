package com.throwsexception.euler;

public class SumSquareDiff {

	public static int getSquareDiff(int ...nums) {
		return squareOfSum(nums) - sumOfSquare(nums);
	}
	
	private static int sumOfSquare(int[] nums) {
		int sum = 0;
		for(int i : nums) {
			sum += i*i;
		}
		return sum;
	}

	private static int squareOfSum(int[] nums) {
		int sum = 0;
		for(int i : nums) {
			sum += i;
		}
		return sum*sum;
	}

	public static void main(String[] args) {
		int[] nums = new int[100];
		for(int i=0; i<nums.length; i++) {
			nums[i] = i+1;
		}
		
		System.out.println("diff 1 - 10: " + getSquareDiff(nums));
	}
	
}
