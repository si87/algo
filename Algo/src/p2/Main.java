package p2;

import p1.Permutation;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] permArray_sn6 = {3,0,2,1,5,4};
		Permutation p1 = new Permutation(permArray_sn6);
		
		System.out.println("Signum für S6: " + (int)signum(p1));
		System.out.println("Fakultät: " + getFactorial(p1.getNumberOfElements()));
		System.out.println("Anzahl Elemente mit Signum=1: " + numberOfElementsWithSignumOne(p1.getNumberOfElements()));
		
		System.out.println();
		
		DiederGroup d4 = new DiederGroup(4);
		DiederGroup d5 = new DiederGroup(5);
		DiederGroup d6 = new DiederGroup(6);
		d4.printAllElements();
		d5.printAllElements();
		d6.printAllElements();

	}
	
	public static double signum(Permutation inputPermutation) {
		int numberOfFactors = inputPermutation.getNumberOfFactors();
		int numberOfElements = inputPermutation.getNumberOfElements();
		
		int numberOfTranspositions = numberOfElements - numberOfFactors;
		
		return Math.pow(-1, numberOfTranspositions);
	}
	
	public static int numberOfElementsWithSignumOne(int n) {		
		return getFactorial(n)/2;
	}
	
	public static int getFactorial(int inputN) {
		int n = inputN;
		int result = 1;
		for (int i=1; i<=n; i++) {
			result = result*i;
		}
		
		return result;
	}

}
