package p1;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		 * Arrays werden nicht auf Vollständigkeit überprüft
		 */
		
		int[] intArray = {5,4,1,7,8,3,2,0,6};
		
		int[] intArray_2 = {1,4,2,0,3,5,8,6,7};
		
		int[] permArray = {0,1,2,3};
		
		// Aufgabe 01
		Permutation p = new Permutation(intArray);
		p.printCycleNotation();

		Permutation inv = p.getInverse();
		inv.printCycleNotation();
		
		System.out.println("Multi");
		
		Permutation p2 = new Permutation(intArray_2);
		
		Permutation idx = p.multiplicate(inv);
		idx.printCycleNotation();
		
		System.out.println("Print Permutations");
		Permutation p3 = new Permutation(permArray);
		p3.printAllPermutations();
		
	}

}
