package Chap07;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("rawtypes")
public class QuickSort <T extends Comparable> {
	ArrayList<T> array;
	
	public QuickSort(ArrayList<T> array) {
		this.array = array;
	}
	
	@SuppressWarnings("unchecked")
	private void quickSort(int startIndex, int endIndex) {
		if ((endIndex - startIndex) <= 1)
			return;
		
		if (endIndex - startIndex == 2) {
			if (array.get(startIndex).compareTo(array.get(endIndex - 1)) > 0)
				exchange(startIndex, endIndex - 1);
			return;
		}
		
		PartitionResult pr = partition(startIndex, endIndex);
		
		quickSort(startIndex, pr.mid);
		quickSort(pr.mid, endIndex);
	}
	
	@SuppressWarnings("unchecked")
	private PartitionResult partition(int startIndex, int endIndex) {
		int mid = startIndex;
		int end = startIndex;
		int size = endIndex - startIndex;
		
		exchange((endIndex - startIndex)/2 + startIndex, endIndex - 1);
		T midValue = array.get(endIndex-1);
		
		while (end < size - 1) {
			if (array.get(end).compareTo(midValue) <= 0) {
				exchange(mid++, end);
			}
			end++;
		}
		
		exchange(end, mid);
		return new PartitionResult(startIndex, mid, endIndex);
	}
	
	private void exchange(int i, int j) {
		if (i < 0 || j < 0 || i >= array.size() || j > array.size())
			return;
		
		T tmp = array.get(i);
		array.set(i, array.get(j));
		array.set(j, tmp);
	}
	
	private class PartitionResult {
		int start, mid, end;
		
		PartitionResult(int start, int mid, int end) {
			this.start = start;
			this.mid = mid;
			this.end = end;
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public static <T extends Comparable> ArrayList<T> quickSort(ArrayList<T> array) {
		QuickSort qs = new QuickSort(array);
		qs.quickSort(0, array.size());
		return qs.array;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> al 
			= new ArrayList<Integer>(Arrays.asList(3,1,10,5,4));
		System.out.println(QuickSort.quickSort(al));
	}
}
