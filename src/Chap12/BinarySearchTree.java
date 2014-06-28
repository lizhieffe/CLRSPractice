package Chap12;

import java.util.ArrayList;

import Util.BinaryTreeNode;

@SuppressWarnings("rawtypes")
public class BinarySearchTree <T extends Comparable> {
	public BinaryTreeNode<T> root;
	
	public BinarySearchTree(BinaryTreeNode<T> root) {
		this.root = root;
	}
	
	@SuppressWarnings("unchecked")
	public BinaryTreeNode<T> search(T value) {
		BinaryTreeNode<T> cNode = root;
		
		while (cNode != null) {
			if (cNode.value.compareTo(value) == 0)
				return cNode;
			else if (cNode.value.compareTo(value) < 0)
				cNode = cNode.left;
			else
				cNode = cNode.right;
		}
		
		return null;
	}
	
	public BinaryTreeNode<T> getMin() {
		BinaryTreeNode<T> cNode = root;
		while (cNode != null && cNode.left != null) {
			cNode = cNode.left;
		}
		return cNode;
	}
	
	public BinaryTreeNode<T> getMax() {
		BinaryTreeNode<T> cNode = root;
		while (cNode != null && cNode.right != null) {
			cNode = cNode.right;
		}
		return cNode;
	}
	
	public BinaryTreeNode<T> getPredecessor(T value) {
		BinaryTreeNode<T> originalNode = search(value);
		if (originalNode == null)
			return null;
		
		if (originalNode.left != null)
			return new BinarySearchTree<T>(originalNode.left).getMax();
		else {
			BinaryTreeNode<T> y = originalNode.parent;
			while (y !=null && y.left == originalNode) {
				originalNode = y;
				y = y.parent;
			}
			return y;
		}
	}
	
	public BinaryTreeNode<T> getSuccessor(T value) {
		BinaryTreeNode<T> originalNode = search(value);
		if (originalNode == null)
			return null;
		
		if (originalNode.right != null)
			return new BinarySearchTree<T>(originalNode.right).getMin();
		else {
			BinaryTreeNode<T> y = originalNode.parent;
			while (y !=null && y.right == originalNode) {
				originalNode = y;
				y = y.parent;
			}
			return y;
		}
	}
	
	public ArrayList<BinaryTreeNode<T>> inOrderTreeWalk() {
		if (root == null)
			return null;
		
		ArrayList<BinaryTreeNode<T>> array = new ArrayList<BinaryTreeNode<T>>();
		if (root.left != null)
			array.addAll(new BinarySearchTree<T>(root.left).inOrderTreeWalk());
		array.add(root);
		if (root.right != null)
			array.addAll(new BinarySearchTree<T>(root.right).inOrderTreeWalk());
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public void insert(T value) {
		if (root == null)
			root = new BinaryTreeNode<T>(value);
		
		BinaryTreeNode<T> cNode = root;
		BinaryTreeNode<T> nNode = cNode.value.compareTo(value) <= 0 ? (BinaryTreeNode<T>)cNode.right : cNode.left;
		while (nNode != null) {
			cNode = nNode;
			nNode = cNode.value.compareTo(value) <= 0 ? cNode.right : cNode.left;
		}
		if (cNode.value.compareTo(value) <= 0)
			cNode.right = new BinaryTreeNode<T>(value);
		else
			cNode.left = new BinaryTreeNode<T>(value);
	}
	
	public void delete(T value) {
		BinaryTreeNode<T> originalNode = search(value);
		if (originalNode == null)
			return;
		
		
	}
}
