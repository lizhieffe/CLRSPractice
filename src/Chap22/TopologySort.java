package Chap22;

import java.util.LinkedList;
import java.util.List;

public class TopologySort {
	
	int[][] neighbors;
	int[] color;
	
	public List<Integer> sort(int[][] neighbors) {
		
		LinkedList<Integer> result = new LinkedList<Integer>();
		
		if (neighbors == null || neighbors.length == 0)
			return result;
		
		this.neighbors = neighbors;
		
		this.color = new int[neighbors.length];
		LinkedList<Integer> stack = new LinkedList<Integer>();
		
		color[0] = 1;
		stack.add(0);
		
		while(true) {
			if (stack.size() > 0) {
				int cNode = stack.getLast();
				int unvisitedNeighbor = findUnvisitedNeighbor(cNode);
				if (unvisitedNeighbor >= 0) {
					stack.add(unvisitedNeighbor);
					color[unvisitedNeighbor] = 1;
				}
				else {
					stack.removeLast();
					color[cNode] = 2;
					result.addFirst(cNode);
				}
			}
			else {
				int nNode = findUnvisitedNode();
				if (nNode >= 0) {
					stack.add(nNode);
					color[nNode] = 1;
				}
				else
					break;
			}
		}
		
		return result;
	}
	
	private int findUnvisitedNeighbor(int cNode) {
		for (int i = 0; i < neighbors.length; i ++) 
			if (neighbors[cNode][i] > 0 && color[i] == 0)
				return i;
		
		return -1;
	}
	
	private int findUnvisitedNode() {
		for (int i = 0; i < neighbors.length; i ++) 
			if (color[i] == 0)
				return i;
		
		return -1;
	}
	
	public static void main(String[] args) {
		int[][] neighbors = new int[5][5];
		neighbors[0][1] = 1;
		neighbors[0][2] = 1;
		neighbors[1][3] = 1;
		neighbors[2][4] = 1;
		neighbors[3][4] = 1;
		
		TopologySort service = new TopologySort();
		service.sort(neighbors);
	}
}
