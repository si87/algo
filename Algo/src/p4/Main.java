package p4;

import java.util.LinkedList;
import java.util.List;

import p1.Cycletype;
import p1.Permutation;
import p3.Edge;

public class Main {

	public static List<Integer> numberOfOrbits = new LinkedList<Integer>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		int[] c4IdArray = {0,1,2,3};
		int[] c401Array = {1,0,2,3};
		int[] c401_23Array = {1,0,3,2};
		int[] c4012Array = {1,2,0,3};
		int[] c40123Array = {1,2,3,0};
		
		Permutation idPerm = new Permutation(c4IdArray);	
		Permutation p21Perm = new Permutation(c401Array);	
		Permutation p02Perm = new Permutation(c401_23Array);	
		Permutation p103Perm = new Permutation(c4012Array);	
		Permutation p0001Perm = new Permutation(c40123Array);	
		
//		printAllBahnen(idPerm);
//		printAllBahnen(p21Perm);
//		printAllBahnen(p02Perm);
//		printAllBahnen(p103Perm);
//		printAllBahnen(p0001Perm);
		
		
		Cycletype cy = new Cycletype(4);
		//cy.printCycletypes();
		List<int[]> perms = cy.getSpecificPartitionList();
		List<Integer> doublePartList = cy.getDoublePartList();
		
		
		for(int[] spec : perms) {
			Permutation temp = new Permutation(spec);
			temp.printCycleNotation();
		}
		
		for(int[] spec : perms) {
			Permutation temp = new Permutation(spec);
			printAllBahnen(temp);
		}
		
		printNumberOfIsoClass(numberOfOrbits, doublePartList);
	}
	
	private static void printNumberOfIsoClass(List<Integer> numOfOrbits, List<Integer> doublePartList) {
		double result = 0;
		for (int i=0; i<numOfOrbits.size(); i++) {
			//System.out.print("2^" + numOfOrbits.get(i) + "/" + doublePartList.get(i) + " + ");
			result += Math.pow(2, numOfOrbits.get(i)) / doublePartList.get(i);
		}
		
		System.out.println("Anzahl Bahnen der Isomporphieklassen: " + result);
		
	}

	// Aufgabe 2c
	public static void printAllBahnen(Permutation inputP) {
		List<Edge> allEdges = createAllEdges(inputP.getNumberOfElements());
		List<Permutation> group = createCyclicGroup(inputP);
		List<List<Edge>> allBahnen = new LinkedList<List<Edge>>();
		
		inputP.printCycleNotation();
		
		System.out.print("Alle Bahnen auf Menge: ");
		
		for (Edge currentEdge : allEdges) {
			List<Edge> currentBahn = calcBahn(currentEdge, group);
			if (!bahnAlreadyExistsInList(currentBahn, allBahnen)) {
				allBahnen.add(currentBahn);
			}
		}
		
		System.out.print("{ ");
		for (List<Edge> currentBahn : allBahnen) {
			System.out.print("{");
			for (Edge currentEdge : currentBahn) {
				currentEdge.printSet();
				if (!currentBahn.get(currentBahn.size()-1).equals(currentEdge)) {
					System.out.print(", ");
				}
			}
			System.out.print("} ");
			if(!allBahnen.get(allBahnen.size()-1).equals(currentBahn)) {
				System.out.print(", ");
			}
		}
		System.out.println("}");
		System.out.println("--------------------");
		numberOfOrbits.add(allBahnen.size());
	}
	
	public static boolean bahnAlreadyExistsInList(List<Edge> currentBahn, List<List<Edge>> allBahnen) {
		for (List<Edge> currentEdgeList : allBahnen) {
			if (currentEdgeList.contains(currentBahn.get(0))) {
				return true;
			}
		}
		
		return false;
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
				//newEdge.printSet();
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
