package Chap09;

import java.util.ArrayList;

import Util.TreeNode;

/**
 * the idea is from:
 * http://stackoverflow.com/questions/3628718/find-the-2nd-largest-element-in-an-array-with-minimum-of-comparisom
 * @author zhili
 *
 */
public class FindSecondMin {
	
	int findSecondMin(int[] array) {
		if (array == null || array.length <= 1)
			return Integer.MAX_VALUE;
		
		ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
		
		for (int i = 0; i < array.length; i ++) {
			nodes.add(new TreeNode(array[i]));
		}
		
		while (nodes.size() > 1) {
			ArrayList<TreeNode> tmpNodes = new ArrayList<TreeNode>();
			for (int i = 0; i < nodes.size() / 2; i ++) {
				TreeNode left = nodes.get(i);
				TreeNode right = nodes.get(i + 1);
				
				TreeNode parent = new TreeNode(left.val < right.val
						? left.val : right.val);
				parent.left = left;
				parent.right = right;
				tmpNodes.add(parent);
			}
			
			if (nodes.size() % 2 == 1)
				tmpNodes.add(nodes.get(nodes.size() - 1));
			
			nodes = tmpNodes;
		}
		
		TreeNode cNode = nodes.get(0);
		int min = cNode.val;
		int secondMin = Integer.MAX_VALUE;

		while (cNode.left != null && cNode.right != null) {
			int leftVal = cNode.left.val;
			int rightVal = cNode.right.val;
			
			if (leftVal == min) {
				secondMin = rightVal;
				cNode = cNode.left;
			}
			else {
				secondMin = leftVal;
				cNode = cNode.right;
			}
		}
		
		return secondMin;
	}
	
	public static void main(String[] args) {
		int[] array = {1, 3, 5, 7, 9, 8, 6, 4, 2};
		
		FindSecondMin service = new FindSecondMin();
		System.out.println(service.findSecondMin(array));
	}
}
