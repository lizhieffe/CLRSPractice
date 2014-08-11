package Chap21;

public class DisjointSet {
	
	DisjointSetNode head;
	DisjointSetNode tail;
	int size;
	
	public DisjointSet(DisjointSetNode node) {
		node.set = this;
		
		this.head = node;
		this.tail = head;
		this.size = 1;
	}
	
	public void add(int val) {
		DisjointSetNode tmp = new DisjointSetNode(val);
		
		tmp.set = this;
		this.tail.next = tmp;
		this.tail = this.tail.next;
		this.size ++;
	}
	
	public void addSet(DisjointSet set) {
		if (set.equals(this))
			return;
		
		DisjointSetNode cNode = set.head;
		while (cNode != null) {
			cNode.set = this;
			cNode = cNode.next;
		}
			
		this.tail.next = set.head;
		this.tail = set.tail;
		this.size = this.size + set.size;
	}
}
