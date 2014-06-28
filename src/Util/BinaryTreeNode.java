package Util;

@SuppressWarnings("rawtypes")
public class BinaryTreeNode <T extends Comparable> {
	
	public T value;
	public BinaryTreeNode<T> parent, left, right;
	
	public BinaryTreeNode(T value) {
		this.value = value;
	}
}
