package Chap24;

public class BellmanFord {
	int[] shortestPath;
	int[] predecessor;
	
	public boolean findSingleSourceShortestPaths(int rootId, int[][] neighbors) {
		if (neighbors == null || neighbors.length == 0 || rootId == -1 || rootId >= neighbors.length)
			return false;
		
		shortestPath = new int[neighbors.length];
		predecessor = new int[neighbors.length];
		
		for (int i = 0; i < neighbors.length; i ++) {
			if (i == rootId)
				shortestPath[i] = 0;
			else
				shortestPath[i] = Integer.MAX_VALUE;

			predecessor[i] = -1;
		}
		
		for (int i = 0; i < neighbors.length - 1; i ++) {
			for (int j = 0; j < neighbors.length; j ++) {
				for (int k = 0; k < neighbors.length; k ++) {
					if (neighbors[j][k] > Integer.MIN_VALUE && shortestPath[k] > shortestPath[j] + neighbors[j][k]) {
						shortestPath[k] = shortestPath[j] + neighbors[j][k];
						predecessor[k] = j;
					}
				}
			}
		}
		
		for (int i = 0; i < neighbors.length - 1; i ++) {
			for (int j = 0; j < neighbors.length; j ++) {
				for (int k = 0; k < neighbors.length; k ++) 
					if (neighbors[j][k] > Integer.MIN_VALUE && shortestPath[k] > shortestPath[j] + neighbors[j][k])
						return false;
			}
		}
	
		return true;
	}
	
	public static void main(String[] args) {
		int[][] neighbors = new int[5][5];
		for (int i = 0; i < 5; i ++)
			for (int j = 0; j < 5; j ++)
				neighbors[i][j] = Integer.MIN_VALUE;
		
		neighbors[0][1] = 6;
		neighbors[0][3] = 7;
		neighbors[1][2] = 5;
		neighbors[1][3] = 8;
		neighbors[1][4] = -4;
		neighbors[2][1] = -2;
		neighbors[3][2] = -3;
		neighbors[3][4] = 9;
		neighbors[4][0] = 2;
		neighbors[4][2] = 7;
		
		int rootId = 0;
		
		BellmanFord service = new BellmanFord();
		service.findSingleSourceShortestPaths(rootId, neighbors);
	}
}
