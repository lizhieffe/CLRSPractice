package Util;

public class GraphNode {
	int id;
	public GraphNodeColor color;
	int depth = -1;
	public int parentId;
	public int detectTime = -1;
	public int finishTime = -1;
	
	public GraphNode(int id, GraphNodeColor color, int parentId) {
		this.id = id;
		this.color = color;
		this.parentId = parentId;
	}
}