import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class JobHunt {
	static List<List<Edge>> cities;
	static int D, C;
	public static void main(String[] args) throws Exception{
		//Scanner stdin = new Scanner(System.in);
		Scanner stdin = new Scanner(new File("jobhunt.in"));
		PrintWriter out = new PrintWriter(new FileWriter("jobhunt.out"));
		D = stdin.nextInt();
		int P = stdin.nextInt();
		C = stdin.nextInt();
		int F = stdin.nextInt();
		int S = stdin.nextInt();
		
		cities = new ArrayList<>(C+1);
		
		for(int i = 0; i <= C; i++) cities.add(new ArrayList<>());
		
		for(int i = 0; i < P; i++) {
			int a = stdin.nextInt();
			int b = stdin.nextInt();
			cities.get(a).add(new Edge(a, b, 0));
		}
		for(int i = 0; i < F; i++) {
			int a = stdin.nextInt();
			int b = stdin.nextInt();
			int c = stdin.nextInt() * -1;
			cities.get(a).add(new Edge(a, b, c));
		}
		out.println(BF(S));
		out.close();
	}
	public static int BF(int S) {
		int[]dist = new int[C+1];
		boolean[]visited = new boolean[C+1];
		
		Arrays.fill(visited, false);
		Arrays.fill(dist, (int) Double.NEGATIVE_INFINITY);
		visited[S] = true;
		dist[S] = D;
		
		int ans = D;
		for(int i = 0; i < C; i++) {
			for(List<Edge> edges : cities) {
				if(!visited[cities.indexOf(edges)]) continue;
				for(Edge edge : edges) {
					int newCost = D + dist[edge.from] + edge.w;
					if(newCost > dist[edge.to]) {
						dist[edge.to] = newCost;
						visited[edge.to] = true;
						ans = Math.max(newCost, ans);
					}
				}
			}
		}
		return ans;
	}
	static class Edge{
		int from, to, w;
		
		public Edge(int a, int b, int c) {
			from = a;
			to = b;
			w = c;
		}
	}

}
