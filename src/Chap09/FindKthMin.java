package Chap09;

import Util.RandomNum;

public class FindKthMin {

	int[] array;
	
	/**
	 * 
	 * @param array
	 * @param k: should >= 1
	 * @return
	 */
	int findKthMin(int[] array, int k) {
		if (k < 0 || array == null || array.length < k)
			return Integer.MAX_VALUE;
		
		this.array = array;
		
		return randomizedSelect(0, array.length, k);
		
	}
	
	private int randomizedSelect(int start, int end, int k) {
		if (start >= end || k > end - start)
			return Integer.MAX_VALUE;
		
		int secondPartStart = randomizedPartition(start, end);
		if (k == secondPartStart - start + 1)
			return array[secondPartStart];
		
		if (k > secondPartStart - start + 1)
			return randomizedSelect(secondPartStart, end, k - secondPartStart + start);
		else
			return randomizedSelect(start, secondPartStart, k);	
	}
	
	private int randomizedPartition(int start, int end) {
		if (start >= end)
			return Integer.MAX_VALUE;
		
		if (end - start == 1)
			return start;
		
		int divIndex = RandomNum.randInt(start, end);
		int div = array[divIndex];
		swap(divIndex, end - 1);
		int secondPartStart = start;
		
		for (int i = start; i < end - 1; i ++) {
			if (array[i] < div) {
				swap(i, secondPartStart ++);
			}
		}
		
		swap(end - 1, secondPartStart);
		
		return secondPartStart;
	}
	
	private void swap(int index1, int index2) {
		if (index1 < 0 || index2 < 0 || index1 >= array.length 
				|| index2 >= array.length || index1 == index2)
			return;
		
		int tmp = array[index1];
		array[index1] = array[index2];
		array[index2] = tmp;
	}
	
	public static void main(String[] args) {
		
		FindKthMin service = new FindKthMin();
		int[] array = {1, 3, 5};
		int k = 3;
		System.out.println(service.findKthMin(array, k));
	}
}
