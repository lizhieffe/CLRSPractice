package Chap23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Chap21.DisjointSetNode;
import Util.GraphEdge;

public class Kruskal {

	public ArrayList<GraphEdge> findMinSpanningTree(int[][] graph) {
		
		ArrayList<GraphEdge> edges = new ArrayList<GraphEdge>();
		ArrayList<GraphEdge> result = new ArrayList<GraphEdge>();

		if (graph == null || graph.length == 0)
			return result;
		
		for (int i = 0; i < graph.length; i ++) {
			for (int j = 0; j < graph[i].length; j ++) {
				if (graph[i][j] > 0)
					edges.add(new GraphEdge(i, j, graph[i][j]));
			}
		}
		
		ArrayList<DisjointSetNode> nodes = new ArrayList<DisjointSetNode>();
		for (int i = 0; i < graph.length; i ++) {
			DisjointSetNode node = new DisjointSetNode(0);
			node.makeSet();
			nodes.add(node);
		}
		
		Collections.sort(edges, new Comp());
		
		for (GraphEdge edge : edges) {
			int v1 = edge.v1;
			int v2 = edge.v2;
			if (!nodes.get(v1).findSet().equals(nodes.get(v2).findSet())) {
				result.add(edge);
				nodes.get(v1).union(nodes.get(v2));
			}
		}
		
		return result;
	}
	
	static public class Comp implements Comparator<GraphEdge> {

		@Override
		public int compare(GraphEdge e1, GraphEdge e2) {
			return e1.weight - e2.weight;
		}
	}
	
	 public static void main(String[] strs)
	    {
	        int[][] g = new int[9][9];
	        g[0][1] = 4;
	        g[1][0] = 4;
	         
	        g[0][7] = 8;
	        g[7][0] = 8;
	         
	        g[1][7] = 11;
	        g[7][1] = 11;
	         
	        g[8][7] = 7;
	        g[7][8] = 7;
	         
	        g[6][7] = 1;
	        g[7][6] = 1;
	 
	        g[6][8] = 6;
	        g[8][6] = 6;
	 
	        g[1][2] = 8;
	        g[2][1] = 8;
	 
	        g[2][8] = 2;
	        g[8][2] = 2;
	 
	        g[6][5] = 2;
	        g[5][6] = 2;
	 
	        g[2][5] = 4;
	        g[5][2] = 4;
	 
	        g[2][3] = 7;
	        g[3][2] = 7;
	 
	        g[3][5] = 14;
	        g[5][3] = 14;
	 
	        g[4][5] = 10;
	        g[5][4] = 10;
	 
	        g[3][4] = 9;
	        g[4][3] = 9;
	         
	        Kruskal service = new Kruskal();
	        service.findMinSpanningTree(g);
	    }
}
