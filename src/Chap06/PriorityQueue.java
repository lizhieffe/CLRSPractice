package Chap06;

import java.util.ArrayList;
import java.util.Arrays;

import Util.CompleteBinaryTreeUtil;

@SuppressWarnings("rawtypes")
public class PriorityQueue <T extends Comparable> extends HeapSort<T> {

	public PriorityQueue(ArrayList<T> array) {
		super(array);
		buildMaxHeap();
	}

	public T getMax() {
		if (array == null || array.size() <= 0)
			return null;
		
		return array.get(0);
	}
	
	public T extractMax() {
		if (array == null || array.size() <= 0)
			return null;
		
		T max = array.get(0);
		array.set(0, array.get(array.size()-1));
		array.remove(array.size()-1);
		heapify(0);
		
		return max;
	}
	
	@SuppressWarnings("unchecked")
	public void increaseValue(int index, T newValue) {
		if (index < 0 || index >= array.size()) {
			System.err.println("wrong index value");
			return;
		}
		
		if (array == null || array.size() <= 0) {
			System.err.println("array doesn't exist or size <= 0");
			return;
		}
		
		T oldValue = array.get(index);
		
		if (oldValue.compareTo(newValue) > 0) {
			System.err.println("New value must >= old value");
			return;
		}
		
		int i = index;
		array.set(i, newValue);
		while (array.get(i).compareTo(getParent(i)) > 0) {
			T tmp = getParent(i);
			array.set(CompleteBinaryTreeUtil.getParentIndex(i), newValue);
			array.set(i, tmp);
			i = CompleteBinaryTreeUtil.getParentIndex(i);
		}
	}
	
	public void insert(T value) {
		array.add(value);	// just to add any value in the end of current array
		increaseValue(array.size()-1, value);
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> al 
			= new ArrayList<Integer>(Arrays.asList(3,1,10,5));
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(al);
		pq.insert(14);
		System.out.println(pq.array);
	}
}
