import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
//WORKS
public class DividingtheFarm {
	static int N;
	static boolean[]visited;
	static int[]components;
	static int[]max;
	static LinkedList<Integer>[]pasture;
	public static void main(String[] args) throws Exception{
//		Scanner stdin = new Scanner(System.in);
		Scanner stdin = new Scanner(new File("divide.in"));
		PrintWriter out = new PrintWriter(new FileWriter("divide.out"));
		N = stdin.nextInt();
		int M = stdin.nextInt();
		max = new int[N+1];
		visited = new boolean[N+1];
		components = new int[N+1];
		int[]fp = new int[M];
		int[]sp = new int[M];
		int ans = Integer.MAX_VALUE;
		pasture = new LinkedList[N+1];
		for(int i = 0; i <= N; i++) pasture[i] = new LinkedList<Integer>();
		for(int i = 1; i <= N; i++) {
			max[i] = stdin.nextInt();
		}
		for(int i = 0; i < M; i++) {
			int a = stdin.nextInt();
			int b = stdin.nextInt();
			pasture[a].add(b);
			pasture[b].add(a);
			fp[i] = a;
			sp[i] = b;
		}
		for(int i = 0; i < M; i++) {
			int a = fp[i];
			int b = sp[i];
			pasture[a].removeFirstOccurrence(b);
			pasture[b].removeFirstOccurrence(a);
			ans = Math.min(ans, findDifference());
			findDifference();
			pasture[a].add(b);
			pasture[b].add(a);
			Arrays.fill(visited, Boolean.FALSE);
		}
		out.println(ans);
		out.close();
		stdin.close();
	}
	public static int findDifference() {
		int count = 0;
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
				count++;
				dfs(i, count);
			}
		}
		if(count == 1) {
			return Integer.MAX_VALUE;
		}
		int sum1 = 0, sum2 = 0;
		for(int i = 1; i <= N; i++) {
			if(components[i] == 1) {
				sum1 += max[i];
			}else {
				sum2 += max[i];
			}
		}
		return Math.abs(sum1-sum2);
	}
	public static void dfs(int i, int count) {
		visited[i] = true;
		components[i] = count;
		for(int next: pasture[i]) {
			if(!visited[next]) {
				dfs(next, count);
			}
		}
	}

}
