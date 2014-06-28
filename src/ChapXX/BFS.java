package ChapXX;

import java.util.ArrayList;
import java.util.Arrays;

public class BFS {
	int numNodes;
	ArrayList<Integer>[] adjancencyList;
	ArrayList<BFSNode> nodes = new ArrayList<BFSNode>();
	ArrayList<BFSNode> nodesToBeExamined = new ArrayList<BFSNode>();
	
	public BFS(ArrayList<Integer>[] adjancencyList) {
		this.adjancencyList = adjancencyList;
		numNodes = adjancencyList.length;
		for (int i = 0; i < numNodes; i ++) {
			nodes.add(new BFSNode(i, BFSNodeColor.WHITE, 0));
		}
	}
	
	public int shortestPath(int startIndex, int endIndex) {
		if (startIndex < 0 || startIndex >= adjancencyList.length 
				|| endIndex < 0 || endIndex >= adjancencyList.length) {
			System.err.println("satrtIndex and endIndex has to be in range: 0 ~ " + adjancencyList.length);
			return -1;
		}
			
		if (startIndex == endIndex)
			return 0;
		
		BFSNode firstNode = nodes.get(startIndex);
		nodesToBeExamined.add(firstNode);
		while (nodesToBeExamined.size() > 0) {
			BFSNode currentNode = nodesToBeExamined.get(0);
			int currentId = currentNode.id;
			currentNode.color = BFSNodeColor.BLACK;
			
			ArrayList<Integer> currentAdjancency = adjancencyList[currentId];
			for (Integer adjancencyId : currentAdjancency) {
				BFSNode adjancencyNode = nodes.get(adjancencyId);
				
				if (adjancencyId == endIndex) {
					adjancencyNode.depth = currentNode.depth + 1;
					
					return currentNode.depth + 1;
				}
				else if (adjancencyNode.color == BFSNodeColor.WHITE) {
					adjancencyNode.color = BFSNodeColor.GREY;
					adjancencyNode.depth = currentNode.depth + 1;
					nodesToBeExamined.add(adjancencyNode);
				}
			}
			
			nodesToBeExamined.remove(currentNode);
		}
		
		// cannot find path
		return -1;
	}
	
	private class BFSNode {
		int id;
		BFSNodeColor color;
		int depth;
		
		BFSNode(int id, BFSNodeColor color, int depth) {
			this.id = id;
			this.color = color;
			this.depth = depth;
		}
	}
	
	enum BFSNodeColor {
		WHITE,
		GREY,
		BLACK;
	}
	
	public static void main(String[] args) {
//		ArrayList[] adjancencyList = new ArrayList[6];
		ArrayList<Integer> adj_0 = new ArrayList<Integer>(Arrays.asList(1, 3));
		ArrayList<Integer> adj_1 = new ArrayList<Integer>(Arrays.asList(4));
		ArrayList<Integer> adj_2 = new ArrayList<Integer>(Arrays.asList(5, 4));
		ArrayList<Integer> adj_3 = new ArrayList<Integer>(Arrays.asList(1));
		ArrayList<Integer> adj_4 = new ArrayList<Integer>(Arrays.asList(3));
		ArrayList<Integer> adj_5 = new ArrayList<Integer>(Arrays.asList(5));
		
		ArrayList[] adjancencyList = {adj_0, adj_1, adj_2, adj_3, adj_4, adj_5};
		
		BFS bst = new BFS(adjancencyList);
		System.out.println(bst.shortestPath(0,4));
	}
}
