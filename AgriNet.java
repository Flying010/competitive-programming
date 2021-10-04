import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
//WORKS
public class AgriNet {
	static List<List<Node>> barn;
	static boolean[]visited;
	static int N;
	public static void main(String[] args) throws Exception{
		//Scanner stdin = new Scanner(System.in);
		Scanner stdin = new Scanner(new File("agrinet.in"));
		PrintWriter out = new PrintWriter(new FileWriter("agrinet.out"));
		N = stdin.nextInt();
		barn = new ArrayList<>(N+1);
		visited = new boolean[N+1];
		for(int i = 0; i <= N; i++) barn.add(new ArrayList<Node>());
		for(int i = 1; i <= N; i++) {
			for(int j = 1;j <= N; j++) {
				int cost = stdin.nextInt();
				if(j != i) {
					barn.get(i).add(new Node(i,j, cost));
				}			}
		}
		out.println(MST());
		out.close();
	}
	public static int MST() {
		int M = N-1;
		int minCost = 0;
		int count = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>(comparator);
		addEdges(1, pq);
		while(!pq.isEmpty()) {
			Node edge = pq.poll();
			if(visited[edge.to]) continue;
			count++;
			minCost += edge.cost;
			addEdges(edge.to,pq);
		}
		return count == M ? minCost : 0;
	}
	public static void addEdges(int i, PriorityQueue<Node> pq) {
		visited[i] = true;
		for(Node n : barn.get(i)) {
			if(!visited[n.to]) pq.add(n);
		}
	}
	public static class Node{
		int from, to, cost;
		public Node(int a, int b, int c) {
			from = a;
			to = b;
			cost = c;
		}
	}
	private static Comparator<Node> comparator = new Comparator<Node>() {
		@Override
		public int compare(Node node1, Node node2) {
		    return (node1.cost - node2.cost) > 0 ? +1 : -1;
		}
	};
}
