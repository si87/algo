package p1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Cycletype {
	List<int[]> allPartitions = new LinkedList<int[]>();
	int number = 0;
	int[] specificPartition;
	int[] identity;
	List<int[]> specificPermutationList = new LinkedList<int[]>();
	List<Integer> doublePartList = new LinkedList<Integer>(); 
	
	public Cycletype(int n) {
		number = n;
		specificPartition = new int[number];
		identity = new int[number];
		for(int i=0;i<number;i++) {
			identity[i] = i;
		}
	}
	
	public List<int[]> getSpecificPartitionList() {
		gen_partitions( number, number, 0);
		for (int[] currentPartition : allPartitions) {
			Map<Integer, Integer> mapping = new HashMap<Integer, Integer>();
			generateCycletypes( currentPartition, mapping );
			generateSpecificPermutationList( mapping );
			getDoublePart( mapping );
		}
		return specificPermutationList;
	}
	
	private void getDoublePart(Map<Integer, Integer> mapping) {
		int num = 1;
		for (int i=1; i <= number; i++) {
			if (mapping.containsKey(i)) {
				int a = mapping.get(i);
				double temp = (Math.pow(i, a) * getFactorial(a));
				num *= temp;
			}
			
		}
		doublePartList.add(num);
		
	}
	
	public List<Integer> getDoublePartList() {
		return doublePartList;
	}
	
	public static int getFactorial(int inputN) {
		int n = inputN;
		int result = 1;
		for (int i=1; i<=n; i++) {
			result = result*i;
		}
		
		return result;
	}

	private void generateSpecificPermutationList(Map<Integer, Integer> mapping) {
		int[] currentPermutationArray = identity.clone();
		int actualPosition = 0;
		for (int tupelSize = 1; tupelSize <= number; tupelSize++) {
			if (mapping.containsKey(tupelSize)) {
				int numberOfTupelOfSize = mapping.get(tupelSize);
				for (int i = 0; i < numberOfTupelOfSize; i++) {
					for(int j = 0; j < tupelSize-1; j++) {
						swap(actualPosition, currentPermutationArray);
						actualPosition++;
					}
					//changeList(currentPermutationArray, tupelSize);
					actualPosition++;
				} 
			}
		}
		specificPermutationList.add(currentPermutationArray);
	}

	private void swap(int i, int[] arr) {
		int temp = arr[i];
		arr[i] = arr[i+1];
		arr[i+1] = temp;
	}

	public void printCycletypes() {
		gen_partitions( number, number, 0);
		for (int[] currentPartition : allPartitions) {
			Map<Integer, Integer> mapping = new HashMap<Integer, Integer>();
			generateCycletypes( currentPartition, mapping );
			printSpecificCycletype( mapping );
		}
	}
	
	private void printSpecificCycletype(Map<Integer, Integer> mapping) {
		for (int i=1; i<=number; i++) {
			if (mapping.containsKey(i)) {
				System.out.print(mapping.get(i));
			} else {
				System.out.print(0);
			}
			
		}
		System.out.print( "->");
		printExample(mapping);
		System.out.println();
		
	}

	private void printExample(Map<Integer, Integer> mapping) {
		int counter = 0;
		for (int i=1;i<=number;i++) { // für alle keys 
			Integer value = mapping.get(i); // hole value
			if (value==null) {
				value = 0;
			}
			for(int j=0;j<value;j++) { // value entspricht anzahl der tupel für diese wertigkeit
				System.out.print("(");
				for(int k=0;k<i;k++) { 
					System.out.print(identity[counter]);
					counter++;
				}
				System.out.print(")");
			}
		//System.out.println(",");	
		if (counter >= number) 
			break;
		}		
	}
	
	private void save_partition(int k) {
		int[] newArray = new int[number];
		for (int i = 0; i<k; i++) {
			//System.out.print(specificPartition[i]);
			newArray[i] = specificPartition[i];
		}
		allPartitions.add(newArray);
		System.out.println();
	}
	
	private void generateCycletypes( int[] currentPartition, Map<Integer, Integer> mapping ) {
		for (int currentNumber : currentPartition) {
			if (mapping.containsKey(currentNumber)) {
				mapping.put(currentNumber, mapping.get(currentNumber)+1);
			} else {
				mapping.put(currentNumber, 1);
			}
		}
	}
	
	private void gen_partitions(int m, int bound, int k) {
		if (m == 0) {
			save_partition(k);
		}
		else {
			for (int i=1; i<= Math.min(bound, m); i++) {
				specificPartition[k] = i;
				gen_partitions(m-i, i, k+1);
			}
		}
	}

}
