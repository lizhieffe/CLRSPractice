package Chap22;

import java.util.ArrayList;
import java.util.LinkedList;

public class StronglyConnectedComponents {

	int[][] neighbors;
	int num;
	
	ArrayList<ArrayList<Integer>> findSCC(int[][] neighbors) {
		
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		
		if (neighbors == null || neighbors.length == 0)
			return result;
		
		this.num = neighbors.length;
		this.neighbors = neighbors;
		
		int[] color = new int[num];
		
		for (int i = 0; i < num; i ++)
			color[i] = 0;
		
		int[][] time = new int[num][2];
		int cTime = 0;
		
		LinkedList<Integer> stack = new LinkedList<Integer>();
		
		stack.add(0);
		color[0] = 1;
		time[0][0] = cTime ++;
		
		while (true) {
			if (stack.size() > 0) {
				int cNode = stack.getLast();
				int unvisitedNeighbor = findUnvisitedNeighbor(cNode, color);
				
				if (unvisitedNeighbor == -1) {
					stack.removeLast();
					color[cNode] = 2;
					time[cNode][1] = cTime ++;
				}
				else {
					stack.add(unvisitedNeighbor);
					color[unvisitedNeighbor] = 1;
					time[unvisitedNeighbor][0] = cTime ++;
				}
			}
			else {
				int tmp = findUnvisited(color);
				if (tmp == -1)
					break;
				else {
					stack.add(tmp);
					color[tmp] = 1;
					time[tmp][0] = cTime ++;
				}
			}
		}
		
		this.neighbors = getTransverseNeighbors(this.neighbors);
		
		for (int i = 0; i < num; i ++)
			color[i] = 0;
		
		int root = findUnvisitedMaxFinishTime(color, time);
		stack.add(root);
		color[root] = 1;
		
		int componentGroup = 0;
		ArrayList<Integer> first = new ArrayList<Integer>();
		first.add(root);
		result.add(first);
		
		while (true) {
			if (stack.size() > 0) {
				int cNode = stack.getLast();
				int unvisitedNeighbor = findUnvisitedNeighbor(cNode, color);
				
				if (unvisitedNeighbor == -1) {
					stack.removeLast();
					color[cNode] = 2;
				}
				else {
					stack.add(unvisitedNeighbor);
					color[unvisitedNeighbor] = 1;
					result.get(componentGroup).add(unvisitedNeighbor);
				}
			}
			else {
				int tmp = findUnvisitedMaxFinishTime(color, time);
				if (tmp == -1)
					break;
				else {
					stack.add(tmp);
					color[tmp] = 1;
					
					ArrayList<Integer> addition = new ArrayList<Integer>();
					addition.add(tmp);
					result.add(addition);
					
					componentGroup ++;
				}
			}
		}
		
		return result;
	}
	
	private int findUnvisited(int[] color) {
		for (int i = 0; i < color.length; i ++) {
			if (color[i] == 0)
				return i;
		}
		
		return -1;
	}
	
	private int findUnvisitedMaxFinishTime(int[] color, int[][] time) {
		int maxFinishTime = Integer.MIN_VALUE;
		int result = -1;
		
		for (int i = 0; i < color.length; i ++) {
			if (color[i] == 0 && time[i][1] > maxFinishTime) {
				maxFinishTime = time[i][1];
				result = i;
			}
		}
		
		return result;
	}
	
	private int findUnvisitedNeighbor(int i, int[] color) {
		int j = 0;
		while (j < neighbors[i].length) {
			if (neighbors[i][j] == -1)
				break;
			
			if (color[neighbors[i][j]] == 0)
				return neighbors[i][j];
			
			j ++;
		}
		
		return -1;
	}
	
	private int[][] getTransverseNeighbors(int[][] neighbors) {
		
		ArrayList<ArrayList<Integer>> tNeighborsTmp = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < num; i ++)
			tNeighborsTmp.add(new ArrayList<Integer>());
		
		for (int i = 0; i < num; i ++) {
			for (int j = 0; j < neighbors[i].length; j ++) {
				int tmp = neighbors[i][j];
				
				if (tmp == -1)
					break;
				
				if (tNeighborsTmp.get(tmp).add(i));
			}
		}
		
		int[][] tNeighbors = new int[num][];
		for (int i = 0; i < num; i ++) {
			int[] tmp = new int[tNeighborsTmp.get(i).size()];
			for (int j = 0; j < tNeighborsTmp.get(i).size(); j ++)
				tmp[j] = tNeighborsTmp.get(i).get(j);
			
			tNeighbors[i] = tmp;
		}
		
		return tNeighbors;
	}
	
	public static void main(String[] args) {
//		int[][] neighbors = new int[8][];
		int[] n0 = {1};
		int[] n1 = {2, 4, 5};
		int[] n2 = {3, 6};
		int[] n3 = {2, 7};
		int[] n4 = {0, 5};
		int[] n5 = {6};
		int[] n6 = {5, 7};
		int[] n7 = {7};
		
		int[][] neighbors = {n0, n1, n2, n3, n4, n5, n6, n7};
		StronglyConnectedComponents service = new StronglyConnectedComponents();
		service.findSCC(neighbors);
	}
}
