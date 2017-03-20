package com.throwsexception.euler;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class LatticePaths {
	
	private static final class MyClass extends RecursiveAction {
		
		private final int rowSize;
		private final int colSize;
		private long numRoutes;
		
		public MyClass(int rowSize, int colSize) {
			this.rowSize = rowSize;
			this.colSize = colSize;
		}

		@Override
		protected void compute() {
			try {
				compute0();
			} catch(Exception e) {
				System.out.print("ERROR: "); e.printStackTrace();
			}
		}
		
		private void compute0() {
			if(rowSize > 12 && colSize > 12) {
				// break up
				MyClass r1 = new MyClass(rowSize, colSize-1);
				//MyClass r2 = new MyClass(rowSize+1, colSize+1);
				MyClass r3 = new MyClass(rowSize-1, colSize);
//				invokeAll(r1, r2, r3);
//				numRoutes = r1.numRoutes + 2*r2.numRoutes + r3.numRoutes;
				invokeAll(r1, r3);
				numRoutes = r1.numRoutes + r3.numRoutes;
			} else {
				// compute directly
				numRoutes = LatticePaths.getRoutes(0, 0, rowSize, colSize);
			}
		}
		
	}

	public static int getRoutes(int size) {
		//int[][] ia = new int[size][size];
		return getRoutes(0, 0, size, size);
	}
	
	private static int getRoutes(int row, int col, int rowSize, int colSize) {
		if(row == rowSize && col == colSize) {
			return 1;
		}
		if(row == rowSize && col != colSize) {
				return getRoutes(row, col+1, rowSize, colSize);
		} else if(col == colSize && row != rowSize)  {
				return getRoutes(row+1, col, rowSize, colSize);
		} else {
			return getRoutes(row, col+1, rowSize, colSize) + getRoutes(row+1, col, rowSize, colSize); 
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println("Num routes for size 2 grid: " + getRoutes(2));
		System.out.println("Num routes for size 4 grid: " + getRoutes(4));
		System.out.println("Num routes for size 8 grid: " + getRoutes(8));
		System.out.println("Num routes for size 12 grid: " + getRoutes(12));
		
		calculateViaForkJoin(2);
		calculateViaForkJoin(4);
		calculateViaForkJoin(8);
		calculateViaForkJoin(12);
		calculateViaForkJoin(16);
		calculateViaForkJoin(20);
	}
	
	private static void calculateViaForkJoin(int size) {
		MyClass task = new MyClass(size, size);
		ForkJoinPool fjPool = new ForkJoinPool();
		fjPool.invoke(task);
		System.out.println("num routes for size: " + size + " -- " + task.numRoutes);
		
//		System.out.println("Num routes for size 16 grid: " + getRoutes(16));
//		System.out.println("Num routes for size 20 grid: " + getRoutes(20));

	}

}
