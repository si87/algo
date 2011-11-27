package p3;

public class Edge {
	int firstNode;
	int secondNode;
	
	public Edge() {
		
	}
	
	public Edge(int first, int second) {
		if (first < second) {
			firstNode = first;
			secondNode = second;
		} else {
			firstNode = second;
			secondNode = first;
		}
	}
	
	public int getFirstNode() {
		return firstNode;
	}
	
	public int getSecondNode() {
		return secondNode;
	}
	
	public void printSet() {
		System.out.print("{"+firstNode+", "+secondNode+"}");
	}
	
	public boolean equals (Object o) {
		Edge e = (Edge)o;
		if (e.getFirstNode() == firstNode && e.getSecondNode() == secondNode) {
			return true;
		} else {
			return false;
		}
	}
}
