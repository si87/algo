package p1;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] intArray = {5,4,1,7,8,3,2,0,6};
		
		int[] intArray_2 = {1,4,2,0,3,5,8,6,7};
		
		Permutation p = new Permutation(intArray);
		p.printCycleNotation();

		Permutation inv = p.getInverse();
		inv.printCycleNotation();
		
		System.out.println("Multi");
		
		Permutation p2 = new Permutation(intArray_2);
		
		Permutation idx = p.multiplicate(p2);
		idx.printCycleNotation();
	}

}
