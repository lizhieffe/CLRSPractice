package Chap04;

import java.util.ArrayList;
import java.util.Arrays;

public class DivideAndConquerToFindMaxSubarray {
	
	class SubarrayInfo {
		final Integer sum;
		final Integer startIndex, endIndex;
		
		SubarrayInfo(Integer sum, Integer startIndex, Integer endIndex) {
			this.sum = sum;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}
		
		@Override
		public String toString() {
			return "sum = " + sum + ", startIndex = " + startIndex + ", endIndex = " + endIndex;
		}
	}
	
	private SubarrayInfo findMaxSubarray(ArrayList<Integer> array, int startIndex) {
		int size = array.size();
		if (array == null || size == 0)
			return null;
		
		if (size >= 2) {
			int midIndex = size/2;
			
			ArrayList<Integer> array1 = new ArrayList<Integer>(array.subList(0, midIndex));
			ArrayList<Integer> array2 = new ArrayList<Integer>(array.subList(midIndex, size));
			
			SubarrayInfo maxSubarray1 = findMaxSubarray(array1, startIndex);
			SubarrayInfo maxSubarray2 = findMaxSubarray(array2, startIndex + midIndex);
			SubarrayInfo maxSubarrayCrossingMidPoint = findMaxSubarrayCrossingMidPoint(array, startIndex);

			SubarrayInfo tmpMax = maxSubarray1.sum.compareTo(maxSubarray2.sum) > 0 ? maxSubarray1 : maxSubarray2;
			return (tmpMax.sum.compareTo(maxSubarrayCrossingMidPoint.sum)) > 0 ? tmpMax : maxSubarrayCrossingMidPoint;
		} else // size == 1
			return new SubarrayInfo(array.get(0), startIndex, startIndex + 1);
	}
	
	private SubarrayInfo findMaxSubarrayCrossingMidPoint(ArrayList<Integer> array, int startIndex) {
		int size = array.size();
		int midIndex = size/2;
		Integer leftSum = 0, rightSum = 0;
		Integer leftSumMax = Integer.MIN_VALUE, rightSumMax = Integer.MIN_VALUE;
		Integer tmpStartIndex = 0, tmpEndIndex = null;
		
		for (int i = 0; i < midIndex; i++) {
			leftSum = leftSum + array.get(midIndex-i-1);
			if (leftSum > leftSumMax) {
				leftSumMax = leftSum;
				tmpStartIndex = midIndex-i-1;
			}
		}
		
		for (int i = 0; i < size - midIndex; i++) {
			rightSum = rightSum + array.get(midIndex+i);
			if (rightSum > rightSumMax) {
				rightSumMax = rightSum;
				tmpEndIndex = midIndex+i+1;
			}
		}
		
		return new SubarrayInfo(leftSumMax+rightSumMax, startIndex+tmpStartIndex, startIndex+tmpEndIndex);
	}
	
	public static SubarrayInfo findMaxSubarray(ArrayList<Integer> array) {
		DivideAndConquerToFindMaxSubarray dactfms = new DivideAndConquerToFindMaxSubarray();
		return dactfms.findMaxSubarray(array, 0);
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> al 
		= new ArrayList<Integer>(Arrays.asList(3,1,-1,10,-5));
	System.out.println(findMaxSubarray(al));
	}
}
