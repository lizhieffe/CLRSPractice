package Chap21;

public class DisjointSetNode {

	int val;
	DisjointSetNode next;
	DisjointSet set;
	
	public DisjointSetNode(int val) {
		this.val = val;
		this.next = null;
		this.set = null;
	}
	
	public DisjointSet makeSet() {
		return new DisjointSet(this);
	}
	
	public DisjointSet findSet() {
		return this.set;
	}
	
	public DisjointSet union(DisjointSetNode x) {
		if (!this.set.equals(x.findSet()))
			this.set.addSet(x.findSet());
		
		return this.set;
	}
}
