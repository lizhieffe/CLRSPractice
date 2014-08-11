package Chap22;

import java.util.ArrayList;
import java.util.Arrays;

import Util.GraphNode;
import Util.GraphNodeColor;

public class DFS {
	ArrayList<ArrayList<Integer>> adjancencyList;
	ArrayList<GraphNode> nodes;
	ArrayList<GraphNode> nodesToBeExamined;
	int time = 0;
	int cId;	// id of the current node
	
	public DFS(ArrayList<ArrayList<Integer>> adjancencyList) {
		this.adjancencyList = adjancencyList;
		
		if (adjancencyList.size() <= 0) {
			System.err.println("size of adjancency list must > 0");
			System.exit(0);
		}
		
		// initialize nodes
		nodes = new ArrayList<GraphNode>();
		for (int i = 0; i < adjancencyList.size(); i ++)
			nodes.add(new GraphNode(i, GraphNodeColor.WHITE, -1));
		
		for (int i = 0; i < adjancencyList.size(); i ++) {
			if (nodes.get(i).color == GraphNodeColor.WHITE) {
				cId = i;
				DFSVisit(i);
			}
		}
	}
	
	private void DFSVisit(int i) {
		GraphNode cNode = nodes.get(i);
		cNode.color = GraphNodeColor.GREY;
		cNode.detectTime = time++;
		cNode.parentId = cId;
		cId = i;
		
		for (Integer adjancencyId : adjancencyList.get(i)) {
			if (nodes.get(adjancencyId).color == GraphNodeColor.WHITE) {
				cId = i;
				DFSVisit(adjancencyId);
			}
		}
		
		cNode.color = GraphNodeColor.BLACK;
		cNode.finishTime = time++;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> adj_0 = new ArrayList<Integer>(Arrays.asList(1, 3));
		ArrayList<Integer> adj_1 = new ArrayList<Integer>(Arrays.asList(4));
		ArrayList<Integer> adj_2 = new ArrayList<Integer>(Arrays.asList(5, 4));
		ArrayList<Integer> adj_3 = new ArrayList<Integer>(Arrays.asList(1));
		ArrayList<Integer> adj_4 = new ArrayList<Integer>(Arrays.asList(3));
		ArrayList<Integer> adj_5 = new ArrayList<Integer>(Arrays.asList(5));
		
		ArrayList<ArrayList<Integer>> adjancencyList = new ArrayList<ArrayList<Integer>>();
		adjancencyList.add(adj_0);
		adjancencyList.add(adj_1);
		adjancencyList.add(adj_2);
		adjancencyList.add(adj_3);
		adjancencyList.add(adj_4);
		adjancencyList.add(adj_5);
		
		DFS dfs = new DFS(adjancencyList);
		
	}
}
