package p1;

import java.util.LinkedList;
import java.util.List;

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
		
		int[] permArray_sn3 = {0,1,2};
		int[] permArray_sn4 = {0,1,2,3};
		int[] permArray_sn5 = {0,1,2,3,4};
		int[] permArray_sn6 = {0,1,2,3,4,5};
		
		// Aufgabe 01
		Permutation p = new Permutation(intArray);
		p.printCycleNotation();

		Permutation inv = p.getInverse();
		inv.printCycleNotation();
		
		System.out.println("Multi");
		
		Permutation p2 = new Permutation(intArray_2);
		
		Permutation idx = p.multiplicate(p2);
		idx.printCycleNotation();
		
		System.out.println("Print Permutations");
		Permutation p3 = new Permutation(permArray_sn4);
		p3.printAllPermutations();
		
		System.out.println("Print Cycletype");
		Cycletype cy1 = new Cycletype(6);
		cy1.printCycletypes();
		
	}
	


}
