package p1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Permutation {
	// Sn - Ordnung
	private int numberOfElements;
	private Map<Integer, Integer> permutationMap;
	private List<Integer> identity;
	private int permutationCounter = 0;
	private int numberOfFactors = 0;
	private String tempNotation = "";
	private boolean isIdentity = false;
	private int[] listNotation;
	
	public Permutation(int[] intArray) {
		// NO CHECK FOR VALID DATA
		createPermutationMap(intArray);
		numberOfElements = intArray.length;
		identity = new LinkedList<Integer>();
		createIdentity();
		createCycleNotation();
		listNotation = intArray.clone();
		for (int i=0; i<intArray.length; i++) {
			if (i == intArray[i]) {
				isIdentity = true;
			} else {
				isIdentity = false;
				break;
			}
		}
	}
	
	private void createIdentity() {
		for (int i=0; i<numberOfElements; i++) {
			identity.add(i);
		}
	}
	
	public void printAllPermutations() {
		List<Integer> newList = new LinkedList<Integer>();
		permute(newList, identity);
	}
	
	private void permute(List<Integer> firstPart, List<Integer> rest) {
		if (rest.size() <= 0) {
			System.out.print( ++permutationCounter + ") ");
			for (int i=0;i<firstPart.size(); i++) {
				System.out.print(firstPart.get(i).intValue());
			}
			System.out.println("");
			
		} else {
			int restSize = rest.size();
			for (int i=0; i<restSize;i++) {
				List<Integer> newRest = new LinkedList<Integer>(rest);
				List<Integer> newFirst = new LinkedList<Integer>(firstPart);
				newFirst.add(newRest.get(i));
				newRest.remove(i);
				
				permute(newFirst, newRest);
			}
		}
		
	}

	public Permutation multiplicate(Permutation inputP) {
		// inputP = roh; permutationMap = Pi;
		int[] newPermArray = new int[numberOfElements];
		Map<Integer, Integer> inputMap = inputP.getMap();
		
		for (int i=0; i<numberOfElements;i++) {
			newPermArray[i] = inputMap.get(permutationMap.get(i).intValue()).intValue();
		}
		
		Permutation newPermutation = new Permutation(newPermArray);
		return newPermutation;
	}
	
	public Permutation getInverse() {
		int[] inverseArray = new int[numberOfElements];
		
		for (int i=0; i<numberOfElements; i++) {
			inverseArray[permutationMap.get(i)] = i;
		}
		
		Permutation inversePermutation = new Permutation(inverseArray);
		return inversePermutation;
	}
	
	/*
	 *  ( 0 1 2 3 4 5 )  => MapKey
	 *  ( 4 2 1 0 3 5 )  => MapValues, currentMappedNumber
	 *  
	 *  
	 */
	public void printCycleNotation() {		
		if (tempNotation.length() <= 0) {
			tempNotation = "id"+numberOfElements;
		}
		System.out.println("PI = " + tempNotation);
	}
	
	private void createCycleNotation() {
		// Liste mit bereits benutzten MapKeys
		List<Integer> usedNumbers = new LinkedList<Integer>();
		for (int i=0; i<numberOfElements; i++) {
			// Wenn der MapKey bereits benutzt wurde, gehe zum n�chsten Durchlauf
			if (usedNumbers.contains(new Integer(i))) {
				continue;
			}
			
			// Wenn der MapKey auf sich selber abgebildet wird (1er Zyklus), Zahl in usedNumbers eintragen und zum n�chsten
			// Durchlauf gehen.
			if (permutationMap.get(i) == i) {
				usedNumbers.add(permutationMap.get(i));
				numberOfFactors++;
				continue;
			}
			
			// Wenn keines der ersten beiden Szenarien zutrifft, wird ein neuer Tupel aufgemacht.
			// Die erste Zahl im Tupel entspricht dem Value zum Key. Danach wird der Zyklus bis zum Ende durchlaufen und 
			// die Elemente in den Tupel und in die usedNumbers-List eingetragen.
			Integer currentMappedNumber = permutationMap.get(i);
			List<Integer> newTupel = new LinkedList<Integer>();
			int smallestValueInTupel = numberOfElements;
			numberOfFactors++;
			while(!usedNumbers.contains(currentMappedNumber)) {
				// die gemappte Nummer zu benutzten Nummern hinzuf�gen, f�r Abbruchbedingung der �u�eren FOR-Schleife
				usedNumbers.add(currentMappedNumber);
				
				// im neuen Tupel die aktuell gemappte Nummer speichern.
				newTupel.add(currentMappedNumber);
				
				// finde kleinste Nummer, da diese im Tupel ganz vorne stehen soll
				if (currentMappedNumber.intValue() < smallestValueInTupel) {
					smallestValueInTupel = currentMappedNumber.intValue();
				}
				
				// zum n�chsten Mapping springen = alter Value wird zum neuen Key
				currentMappedNumber = permutationMap.get(currentMappedNumber);
			}
			
			// Shifte Tupel bis zur kleinsten Nummer
			for (int k=0; k<newTupel.size();k++) {
				if (newTupel.get(0).intValue() != smallestValueInTupel) {
					newTupel.add(0, newTupel.get(newTupel.size()-1));
					newTupel.remove(newTupel.size()-1);
				} else {
					break;
				}
			}
			
			// Erzeuge String
			tempNotation += "( ";
			for (int j=0; j<newTupel.size(); j++) {
				tempNotation += newTupel.get(j).toString() + " ";
			}
			tempNotation += ")";
			
			if (usedNumbers.size() == numberOfElements) {
				break;
			}
		}
	}
	
	public Permutation getIdentity() {
		int[] identityArray = new int[numberOfElements];
		for (int i=0; i<numberOfElements; i++) {
			identityArray[i] = identity.get(i).intValue();
		}
		Permutation newPermutation = new Permutation(identityArray);
		return newPermutation;
	}
	
	public Map<Integer, Integer> getMap() {
		return permutationMap;
	}
	
	public int getNumberOfFactors() {
		return numberOfFactors;
	}
	
	public int getNumberOfElements() {
		return numberOfElements;
	}
	
	public int[] getListNotation() {
		return listNotation;
	}
	
	public boolean isIdentity() {
		return isIdentity;
	}
	
	public int getNext(int inputNumber) {
		return permutationMap.get(inputNumber);
	}
	
	private void createPermutationMap(int[] intArray) {
		permutationMap = new HashMap<Integer, Integer>();
		for (int i=0; i<intArray.length; i++) {
			permutationMap.put(i, intArray[i]);
		}
	}
	
}
