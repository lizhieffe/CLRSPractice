package Chap24;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {
	
	public int[] predecesor;
	public int[] shortestPath;
	
	public boolean findSingleSourceShortestPaths(int[][] neighbors, int rootId) {
		if (neighbors == null || neighbors.length == 0 || rootId < 0 || rootId >= neighbors.length)
			return false;
		
		predecesor = new int[neighbors.length];
		shortestPath = new int[neighbors.length];
		
		for (int i = 0; i < neighbors.length; i ++) {
			if (i == rootId)
				shortestPath[i] = 0;
			else
				shortestPath[i] = Integer.MAX_VALUE;
			
			predecesor[i] = -1;
		}
		
		ArrayList<Integer> finished = new ArrayList<Integer>();
		ArrayList<Integer> unfinished = new ArrayList<Integer>();
		for (int i = 0; i < neighbors.length; i ++)
			unfinished.add(i);
		
		while (unfinished.size() > 0) {
			int cNode = extractMin(unfinished);
			finished.add(cNode);
			for (int i = 0; i < neighbors.length; i ++) {
				if (neighbors[cNode][i] > 0 && shortestPath[i] > shortestPath[cNode] + neighbors[cNode][i]) {
					shortestPath[i] = shortestPath[cNode] + neighbors[cNode][i];
					predecesor[i] = cNode;
				}
			}
		}
		
		return true;
	}
	
	private int extractMin(List<Integer> list) {
		int min = Integer.MAX_VALUE;
		int minId;
		
		int j = -1;
		
		for (int i = 0; i < list.size(); i ++) {
			int cNode = list.get(i);
			if (shortestPath[cNode] < min) {
				j = i;
				min = shortestPath[cNode];
			}
		}
		
		minId = list.get(j);
		list.remove(j);
		
		return minId;
	}
	
	public static void main(String[] args) {
		int[][] neighbors = new int[5][5];
		neighbors[0][1] = 10;
		neighbors[0][3] = 5;
		neighbors[1][2] = 1;
		neighbors[1][3] = 2;
		neighbors[2][4] = 4;
		neighbors[3][1] = 3;
		neighbors[3][2] = 9;
		neighbors[3][4] = 2;
		neighbors[4][0] = 7;
		neighbors[4][2] = 6;
		
		Dijkstra service = new Dijkstra();
		service.findSingleSourceShortestPaths(neighbors, 0);
	}
}
