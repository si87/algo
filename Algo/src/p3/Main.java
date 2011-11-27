package p3;

import java.util.LinkedList;
import java.util.List;

import p1.Permutation;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		int[] c4Array = {1,0,2,3};
		Permutation initPerm = new Permutation(c4Array);	
		printAllBahnen(initPerm);
		
	}
	
	// Aufgabe 2c
	public static void printAllBahnen(Permutation inputP) {
		List<Edge> allEdges = createAllEdges(inputP.getNumberOfElements());
		List<Permutation> group = createCyclicGroup(inputP);
		
		for (Edge currentEdge : allEdges) {
			calcBahn(currentEdge, group);
			System.out.println("");
		}
	}
	
	// Aufgabe 2b
	public static List<Edge> createAllEdges(int inputN) {
		List<Edge> allEdges = new LinkedList<Edge>();
		for(int i=0; i<inputN; i++) {
			for(int j=i+1; j<inputN; j++) {
				if (i != j) {
					Edge newEdge = new Edge(i, j);
					allEdges.add(newEdge);
				}
			}
		}
		System.out.println("Number of edges: " + allEdges.size());
		return allEdges;
	}
	
	// Aufgabe 2a
	public static List<Edge> calcBahn(Edge inputEdge, List<Permutation> inputGroup) {
		List<Edge> resultList = new LinkedList<Edge>();
		for (Permutation currentP : inputGroup) {
			Edge newEdge = new Edge(currentP.getNext(inputEdge.getFirstNode()), currentP.getNext(inputEdge.getSecondNode()));
			if (!resultList.contains(newEdge)) {
				resultList.add(newEdge);
				newEdge.printSet();
			}
		}
		return resultList;
	}
	
	public static List<Permutation> createCyclicGroup(Permutation inputPermutation) {
		List<Permutation> permutationList = new LinkedList<Permutation>();
		permutationList.add(inputPermutation);
		
		// Solange die Identität noch nicht errechnet worden ist (als letztes Element)
		while(!permutationList.get(permutationList.size()-1).isIdentity()) {
			int lastIndex = permutationList.size()-1;
			Permutation createPerm = permutationList.get(lastIndex).multiplicate(inputPermutation);
			permutationList.add(createPerm);
		}
		System.out.println("Number of permutations in group: " + permutationList.size());
		return permutationList;
	}

}
