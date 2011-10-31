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
	
	public Cycletype(int n) {
		number = n;
		specificPartition = new int[number];
		identity = new int[number];
		for(int i=0;i<number;i++) {
			identity[i] = i;
		}
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
		
//		int i=1;
//		int sum=0;
//		while(sum < 4) {
//			sum = sum + mapping.get(i)*i;
//			i++;
//		}
		
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
