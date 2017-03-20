package com.throwsexception.euler;

import java.util.HashMap;
import java.util.Map;

/*
 * n → n/2 (n is even)
 * n → 3n + 1 (n is odd)
 */
public class LongestCollatzSequence {

	
//	Using the rule above and starting with 13, we generate the following sequence:
//
//		13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
//		It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
//
//		Which starting number, under one million, produces the longest chain?
//
//		NOTE: Once the chain starts the terms are allowed to go above one million.
	
	private static final Map<Integer, Long> COLLATZ_COUNT = new HashMap<Integer, Long>();
	
	public static class CollatzSequence {
	
		protected int start;
		private long len;
		
		public CollatzSequence(int start) {
			this.start = start;
		}
		
		public final long generate() {
			System.out.println("Generating collatz sequence for: " + start + " ...");
			len = doGenerate();
			COLLATZ_COUNT.put(start, len);
			System.out.println(" ... is of length: " + len);
			return len;
		}
		
		protected long doGenerate() {
			return generate0(start, 1);
		}
		
		private int generate0(int num, int count) {
			
			if(num == 1) {
				return count;
			}
			
			int newNum;
			if(num%2 == 0) {
				newNum = num/2;
			} else {
				newNum = 3*num + 1;
			}
			
			return generate0(newNum, count+1);
		}
	}
	
	public static class CollatzSequence2 extends CollatzSequence {
		
		public CollatzSequence2(int start) {
			super(start);
		}
		
		@Override
		protected long doGenerate() {
			long num = start;
			long count=1;
			while(num != 1) {
				if(num%2 == 0) {
					num = num/2;
				} else {
					num = 3*num + 1;
				}
				
				if(COLLATZ_COUNT.containsKey(num)) {
					count += COLLATZ_COUNT.get(num);
					break;
				} else { 
					//System.out.print(num + ", ");
					count++;
				}
			} 
			System.out.println();
			return count;
		}
		
	}
	public static void main(String[] args) {
		CollatzSequence CS_ONE = new CollatzSequence(1);
		CS_ONE.generate();
		CollatzSequence max = CS_ONE;
		
//		for(int i=1000000; i>1; i--) {
		for(int i=2; i<=1000000; i++) {
			CollatzSequence cs = new CollatzSequence2(i);
			cs.generate();
			if(cs.len > max.len) {
				max = cs;
			}
		}
		
		System.out.println("Max cs number: " + max.start + ", of len: " + max.len);
	}

}
