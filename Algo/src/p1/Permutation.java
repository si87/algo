package p1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Permutation {
	// Sn - Ordnungs
	private int numberOfElements;
	private Map<Integer, Integer> permutationMap;
	private List<List<Integer>> tupelList;
	private List<Integer> identity;
	
	public Permutation(int[] intArray) {
		// NO CHECK FOR VALID DATA
		identity = new LinkedList<Integer>();
		tupelList = new LinkedList<List<Integer>>();
		createPermutationMap(intArray);
		createIdentity();
		createTupel();
		numberOfElements = intArray.length;
	}
	
	private void createTupel() {
		
		
	}

	private void createPermutationMap(int[] intArray) {
		permutationMap = new HashMap<Integer, Integer>();
		for (int i=0; i<intArray.length; i++) {
			permutationMap.put(i, intArray[i]);
		}
	}
	
	public void printCycleNotation() {
		List<Integer> usedNumbers = new LinkedList<Integer>();
		
		String tempNotation = "";
		for (int i=0; i<numberOfElements; i++) {
			if (usedNumbers.contains(new Integer(i))) {
				continue;
			}
			
			if (permutationMap.get(i) == i) {
				usedNumbers.add(permutationMap.get(i));
				continue;
			}
			
			Integer currentMappedNumber = permutationMap.get(i);
			List<Integer> newTupel = new LinkedList<Integer>();
			int smallestValueInTupel = numberOfElements;
			while(!usedNumbers.contains(currentMappedNumber)) {
				// die gemappte Nummer zu benutzten Nummern hinzufügen, für Abbruchbedingung der äußeren FOR-Schleife
				usedNumbers.add(currentMappedNumber);
				
				// im neuen Tupel die aktuell gemappte Nummer speichern.
				newTupel.add(currentMappedNumber);
				
				// finde kleinste Nummer
				if (currentMappedNumber.intValue() < smallestValueInTupel) {
					smallestValueInTupel = currentMappedNumber.intValue();
				}
				
				// zum nächsten Mapping springen
				currentMappedNumber = permutationMap.get(currentMappedNumber);
			}
			
			// Shifte Tupel bis zur kleinsten Nummer
			for (int k=0; k<newTupel.size();k++) {
				if (newTupel.get(0).intValue() != smallestValueInTupel) {
					newTupel.add(0, newTupel.get(newTupel.size()-1));
					newTupel.remove(newTupel.size()-1);
				}
			}
			
			// Erzeuge String
			tempNotation += "( ";
			for (int j=0; j<newTupel.size(); j++) {
				tempNotation += newTupel.get(j).toString() + " ";
			}
			tempNotation += ")";
		}
		
		System.out.println("PI = " + tempNotation);
	}
	
	private void createIdentity() {
		for (int i=0; i<numberOfElements; i++) {
			identity.add(new Integer(i));
			permutationMap.put(i, i);
		}
	}
}
