package Chap15;

import java.util.ArrayList;
import java.util.Arrays;

public class RodCut {
	ArrayList<Integer> priceList;	// price for cuts of different lengths
	Integer[] maxPrice;	// max price for different total lengths
	
	public RodCut(ArrayList<Integer> priceList) {
		this.priceList = priceList;
		maxPrice = new Integer[priceList.size()];
	}
	
	public Integer getMaxPrice(int length) {
		if (length <= 0)
			return 0;
		else if (length > priceList.size())
			return getMaxPrice(priceList.size());
		
		if (maxPrice[length - 1] != null)
			return maxPrice[length - 1];
		
		Integer tmpMaxPrice = 0;
		for (int i = 0; i < length; i ++) {
			tmpMaxPrice = Math.max(tmpMaxPrice, priceList.get(i) + getMaxPrice(length - i - 1));
		}
		maxPrice[length - 1] = tmpMaxPrice;
		
		return tmpMaxPrice;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> priceList 
			= new ArrayList<Integer>(Arrays.asList(1, 5, 8, 9, 10, 17, 17, 20, 24, 30));
		RodCut rc = new RodCut(priceList);
		
		for (int i = 1; i <= 10; i ++) {
			System.out.println(rc.getMaxPrice(i));
		}
	}
}
