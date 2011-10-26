package p1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Permutation {
	// Sn - Ordnungs
	private int numberOfElements;
	private Map<Integer, Integer> permutationMap;
	private List<Integer> permutation;
	private List<Integer> identity;
	
	public Permutation(int[] intArray) {
		// NO CHECK FOR VALID DATA
		permutation = new LinkedList<Integer>();
		identity = new LinkedList<Integer>();
		createPermutationMap(intArray);
		createIdentity();
		numberOfElements = intArray.length;
	}
	
	private void createPermutationMap(int[] intArray) {
		permutationMap = new HashMap<Integer, Integer>();
		for (int i=0; i<intArray.length; i++) {
			permutationMap.put(i, intArray[i]);
		}
	}

	public void addNumber(int inputNumber) {
		if ( !(inputNumber < numberOfElements) ) {
			System.out.println("Element ist nicht im definierten Bereich Sn = " + numberOfElements);
		}
		
		Integer number = new Integer(inputNumber);
		
		if (permutation.contains(number)) {
			System.out.println("Element ist bereits in der Permutation vorhanden");
		}
		
		permutation.add(number);
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
			
//			// Fall 1: Nummer bildet auf sich selber ab.
//			if (usedNumbers.contains(new Integer(i))) {
//				continue;
//			}
//			
//			if (permutation.get(i).intValue() == identity.get(i).intValue()) {
//				//skip, wird nicht angezeigt
//				usedNumbers.add(permutation.get(i));
//				continue;
//			}
//			
//			// Fall 2: Nummer bildet auf anderes Element ab
//			Integer firstNumber = identity.get(i);
//			Integer mappedNumber = permutation.get(i);
//			tempNotation += firstNumber.toString() + " ";
//			for (int j=0; j<numberOfElements;j++) {
//				
//			}

//		}
	}
	
	private void createIdentity() {
		for (int i=0; i<numberOfElements; i++) {
			identity.add(new Integer(i));
			permutationMap.put(i, i);
		}
	}
}
