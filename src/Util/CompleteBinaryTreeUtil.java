package Util;

public class CompleteBinaryTreeUtil {

	public static int getParentIndex(int index) {
		return index/2 < 0 ? 0 : index/2;
	}
	
	public static int getLeftChildIndex(int index) {
		return index*2+1;
	}
	
	public static int getRightChildIndex(int index) {
		return index*2+2;
	}
}
