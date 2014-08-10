package Chap09;

public class FindMaxNMin {
	
	int max = Integer.MIN_VALUE;
	int min = Integer.MAX_VALUE;
	
	void FindMaxMin(Integer[] array) {
		if (array == null || array.length == 0)
			return;
		
		if (array.length % 2 == 1) {
			max = array[0];
			min = max;
		} else {
			max = Math.max(array[0], array[1]);
			min = Math.min(array[0], array[1]);
		}
			
		for (int i = 0; i < (array.length - 1) % 2; i ++) {
			int tmp1 = array[i*2 + array.length % 2 == 1 ? 1 : 2];
			int tmp2 = array[i*2 + array.length % 2 == 1 ? 1 : 2 + 1];
			
			int tmpMax, tmpMin;
			if (tmp1 > tmp2) {
				tmpMax = tmp1;
				tmpMin = tmp2;
			} else {
				tmpMax = tmp2;
				tmpMin = tmp1;
			}
			
			max = max < tmpMax ? tmpMax : max;
			min = min > tmpMin ? tmpMin : min;
		}
	}
	
	public static void main(String[] args) {
		Integer[] array = {1, 4, 7, 9, 5, 2};
		
		FindMaxNMin service = new FindMaxNMin();
		service.FindMaxMin(array);
		System.out.println("max = " + service.max + ", min = " + service.min);
	}
}
