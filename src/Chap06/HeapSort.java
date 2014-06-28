package Chap06;

import java.util.ArrayList;
import java.util.Arrays;

import Util.CompleteBinaryTreeUtil;

@SuppressWarnings("rawtypes")
public class HeapSort <T extends Comparable> {
	
	ArrayList<T> array;
	
	public HeapSort(ArrayList<T> array) {
		this.array = array;
	}
	
	void heapSort() {
		buildMaxHeap();
		
		ArrayList<T> tmpArray = new ArrayList<T>();
		int size = array.size();
		
		for (int i = 0; i < size; i++) {
			tmpArray.add(0, array.get(0));
			
			array.set(0, array.get(array.size()-1));
			array.remove(array.size()-1);
			
			heapify(0);
		}
		
		array = tmpArray;
	}
	
	@SuppressWarnings("unchecked")
	protected void heapify(int i) {
		if (i < 0 || i >= array.size())
			return;
		
		T leftChild = getLeftChild(i);
		T rightChild = getRightChild(i);
		
		int indexMax = i;
		if (leftChild != null) {
			indexMax = array.get(indexMax).compareTo(leftChild) >= 0 
					? indexMax : CompleteBinaryTreeUtil.getLeftChildIndex(i);
		}
		if (rightChild != null) {
			indexMax = array.get(indexMax).compareTo(rightChild) >= 0
					? indexMax : CompleteBinaryTreeUtil.getRightChildIndex(i);
		}
		
		if (indexMax != i) {
			T tmp = array.get(i);
			array.set(i, array.get(indexMax));
			array.set(indexMax, tmp);
			
			heapify(indexMax);
		}
	}
	
	protected void buildMaxHeap() {
		for (int i = array.size()/2-1; i >= 0; i--) {
			heapify(i);
		}
	}
	
	protected T getParent(int i) {
		return array.get(CompleteBinaryTreeUtil.getParentIndex(i));
	}
	
	private T getLeftChild(int i) {
		int index = CompleteBinaryTreeUtil.getLeftChildIndex(i);
		if (index >= array.size())
			return null;
		else
			return array.get(index);
	}
	
	private T getRightChild(int i) {
		int index = CompleteBinaryTreeUtil.getRightChildIndex(i);
		if (index >= array.size())
			return null;
		else
			return array.get(index);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Comparable> ArrayList<T> sort(ArrayList<T> array) {
		HeapSort hs = new HeapSort(array);
		hs.heapSort();
		return hs.array;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> al 
			= new ArrayList<Integer>(Arrays.asList(3,1,10,5));
		System.out.println(HeapSort.sort(al));
	}
}
