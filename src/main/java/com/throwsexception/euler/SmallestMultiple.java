package com.throwsexception.euler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SmallestMultiple {

	private static class Factors {
		
		private final int num;
		
		private final Map<Integer, Integer> factorsAndTimes;
		
		private static final Map<Integer, Factors> cache = new HashMap<Integer, Factors>();
		
		static {
			Map<Integer, Integer> factorAndTimesOf1 = new HashMap<Integer, Integer>();
			factorAndTimesOf1.put(1, 1);
			Factors factorsOf1 = new Factors(1, factorAndTimesOf1);
			
			
			Map<Integer, Integer> factorsAndTimesOf2 = new HashMap<Integer, Integer>();
			factorsAndTimesOf2.put(2, 1);
			Factors factorsOf2 = new Factors(2, factorsAndTimesOf2);

			cache.put(1, factorsOf1);
			cache.put(2, factorsOf2);
		}
		
		private Factors(int num, Map<Integer, Integer> factorsAndTimes) {
			this.num = num;
			this.factorsAndTimes = Collections.unmodifiableMap(factorsAndTimes);
		}
		
		private Factors(int num) {
			
			this.num = num;
			Map<Integer, Integer> tmp = new HashMap<Integer, Integer>();
			 
			for(int i=2; i<=num/2; i++) {
				
				if(num % i == 0) {
					
					int other = num/i;
					
					//Factors f1 = getOrCreate(i);
					Factors f2 = getOrCreate(other);
					
					tmp.put(i, 1);
					for(int k : f2.factorsAndTimes.keySet()) {
						if(tmp.containsKey(k)) {
							tmp.put(k,tmp.get(k) + f2.factorsAndTimes.get(k));
						} else {
							tmp.put(k,f2.factorsAndTimes.get(k));
						}
					}
					
					break;
				}
			}
			
			if(tmp.isEmpty()) {
				tmp.put(num, 1);
			} 
			
			factorsAndTimes = Collections.unmodifiableMap(tmp);
		}
		
		private static Factors getOrCreate(int num) {
			if(cache.containsKey(num)) {
				return cache.get(num);
			} else {
				Factors factors = new Factors(num);
				cache.put(num, factors);
				return factors;
			}
		}

		public static Factors get(int num) {
			return getOrCreate(num);
		}
		
		public Map<Integer, Integer> factors() {
			return factorsAndTimes;
		}
		
		public String toString() {
			return "Factors of " + num + " : " + factorsAndTimes;
		}
	}
	
	public static Map<Integer, Integer> getFactors(int num) {
		return Factors.get(num).factors();
	}
	
	public static int smallestMultiple(int lower, int upper) {
		Map<Integer, Integer> allFactors = new HashMap<Integer, Integer>();
		for(int i=lower; i<=upper; i++) {
			Map<Integer, Integer> tmp = getFactors(i);
			for(int k : tmp.keySet()) {
				if(allFactors.containsKey(k)) {
					if(allFactors.get(k)<tmp.get(k)) { 
						allFactors.put(k, tmp.get(k));
					}
				} else {
					allFactors.put(k, tmp.get(k));
				}
			}
			System.out.println("debug: " + i + " all factors: " + allFactors);
		}
		
		int retval = 1;
		for(int k : allFactors.keySet()) {
			int tmp = 1;
			for(int j=1; j<=allFactors.get(k); j++) {
				tmp *= k;
			}
			retval *= tmp;
		}
		
		return retval;
		
	}
	
	public static void main(String[] args) {
//		for(int i=1; i<10; i++) {
//			Factors f = Factors.get(i);
//			System.out.println(f);
//		}
		
//		debug(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
		
		System.out.println("smallest multiple of 1-10: " + smallestMultiple(1,10));
		System.out.println("smallest multiple of 1-20: " + smallestMultiple(1,20));
	}
	
	private static void debug(int... nums) {
		for(int i=nums[0]; i<=nums[nums.length-1]; i++) {
			Factors f = Factors.get(i);
			System.out.println(f);			
		}
	}

}
