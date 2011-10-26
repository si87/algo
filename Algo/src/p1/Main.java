package p1;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] intArray = {5,4,1,3,2,0};
		
		Permutation p = new Permutation(intArray);
		p.printCycleNotation();

	}

}
