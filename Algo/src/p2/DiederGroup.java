package p2;

import java.util.LinkedList;
import java.util.List;

import p1.Permutation;

public class DiederGroup {
	private int n;
	private List<Permutation> allPermutations = new LinkedList<Permutation>();
	private Permutation rotatePermutation;
	private Permutation mirrorPermutation;
	
	public DiederGroup(int inputN) {
		n = inputN;
	}
	
	public void printAllElements() {
		System.out.println();
		System.out.println("Diedergruppe D" + n + ": ");
		createAllElements();
		for (Permutation currentPermutation : allPermutations) {
			currentPermutation.printCycleNotation();
		}
		int totalNumberOfElement = 2*n;
		System.out.println("Anzahl Elemente ("+totalNumberOfElement+"): " + allPermutations.size());
	}

	private void createAllElements() {		
		createRotationPermutation();
		createMirrorPermutation();

		allPermutations.add(rotatePermutation);
		
		// create all elements by multiplication with pi
		for (int i=1; i<n-1; i++) {
			Permutation resultPermutation = rotatePermutation.multiplicate(allPermutations.get(i-1));
			allPermutations.add(resultPermutation);
		}
		
		// create all elements by multiplication with roh
		allPermutations.add(mirrorPermutation);
		for (int i=0; i<n-1; i++) {
			Permutation resultPermutation = mirrorPermutation.multiplicate(allPermutations.get(i));
			allPermutations.add(resultPermutation);
		}
		
		allPermutations.add(rotatePermutation.getIdentity());
		
	}

	private void createMirrorPermutation() {
		int[] mirrorPermutationArray = new int[n];
		boolean isEven = ((n % 2) == 0) ? true : false;
		if (isEven) {
			for(int i=0; i<n/2; i++) {
				mirrorPermutationArray[i] = n-1-i;
				mirrorPermutationArray[n-1-i] = i;
			}
		} else {
			mirrorPermutationArray[0] = 0;
			for(int i=1; i<Math.ceil((double)n/2); i++) {
				mirrorPermutationArray[i] = n-i;
				mirrorPermutationArray[n-i] = i;
			}
		}
		mirrorPermutation = new Permutation(mirrorPermutationArray);
		
	}

	private void createRotationPermutation() {
		int[] rotatePermutationArray = new int[n];
		
		for (int i=0; i<n; i++) {
			rotatePermutationArray[i] = (i+1) % n;
		}
		rotatePermutation = new Permutation(rotatePermutationArray);
	}
}
