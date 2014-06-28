package Chap02;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("rawtypes")
public class MergeSort<T extends Comparable> {
	
	private ArrayList<T> performSort(ArrayList<T> array) {
		int size = array.size();
		if (array == null || size == 0)
			return null;
		
		if (size >= 2) {
			int midIndex = size/2;
			ArrayList<T> array_1 = new ArrayList<T>(array.subList(0, midIndex));
			ArrayList<T> array_2 = new ArrayList<T>(array.subList(midIndex, size));
			
			array_1 = performSort(array_1);
			array_2 = performSort(array_2);
			return performMerge(array_1, array_2);
		} else // size == 1
			return array;
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList<T> performMerge(ArrayList<T> array_1, ArrayList<T> array_2) {
		if (array_1 == null || array_2 == null)
			return null;
		
		int size = array_1.size() + array_2.size();
		ArrayList<T> array = new ArrayList<T>();
		int index_1 = 0, index_2 = 0, index = 0;
		while (index < size) {
			if (index_1 == array_1.size())
				array.add(array_2.get(index_2++));
			else if (index_2 == array_2.size())
				array.add(array_1.get(index_1++));
			else {
				if (array_1.get(index_1).compareTo(array_2.get(index_2)) < 0)
					array.add(array_1.get(index_1++));
				else
					array.add(array_2.get(index_2++));
			}
			index++;
		}
		return array;
	}
	
	public static <T extends Comparable> ArrayList<T> sort(ArrayList<T> array) {
		MergeSort<T> ms = new MergeSort<T>();
		return ms.performSort(array);
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> al 
			= new ArrayList<Integer>(Arrays.asList(3,1));
		System.out.println(MergeSort.sort(al));
	}
}
