import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
//WORKS
public class PiggyBack {
	static Queue<Integer>q;
	static List<List<Integer>> barn;
	static boolean[]visited;
	static int[]D1;
	static int[]D2;
	static int []DN;
	static int nodesleft = 1, nodesnext = 0, N;
	public static void main(String[] args) throws Exception{
		//Scanner stdin = new Scanner(System.in);
		Scanner stdin = new Scanner(new File("piggyback.in"));
		PrintWriter out = new PrintWriter(new FileWriter("piggyback.out"));
		int B = stdin.nextInt();
		int E = stdin.nextInt();
		int P = stdin.nextInt();
		N = stdin.nextInt();
		int M = stdin.nextInt();
		barn = new ArrayList<>(N+1);
		visited = new boolean[N+1];
		D1 = new int[N+1];
		D2 = new int[N+1];
		DN = new int[N+1];
		q = new LinkedList<Integer>();
		for(int i = 0; i <= N; i++) barn.add(new ArrayList<>());
		for(int i = 0; i < M; i++) {
			int a = stdin.nextInt();
			int b = stdin.nextInt();
			barn.get(a).add(b);
			barn.get(b).add(a);
		}
		bfs(1, D1);
		bfs(2, D2);
		bfs(N, DN);
		int ans = Integer.MAX_VALUE;
		for(int i = 1; i <= N; i++) {
			ans = Math.min(ans, D1[i]*B + D2[i]*E + DN[i]*P);
		}
		out.println(ans);
		out.close();

	}
	public static void bfs(int start, int[]arr) {
		int count = 1;
		nodesleft = 1;
		nodesnext = 0;
		q.clear();
		Arrays.fill(visited, false);
		q.add(start);
		visited[start] = true;
		while(!q.isEmpty()) {
			int node = q.poll();
			for(int next : barn.get(node)) {
				if(!visited[next]) {
					q.add(next);
					visited[next] = true;
					arr[next] = count;
					nodesnext++;
				}
			}
			nodesleft--;
			if(nodesleft == 0) {
				nodesleft = nodesnext;
				nodesnext = 0;
				count++;
			}
		}
	}
}
