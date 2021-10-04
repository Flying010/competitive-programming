import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

@SuppressWarnings("unchecked")
//WORKS
public class CycleDetection {
	static LinkedList<Integer>[]cows;
	static boolean[]visited;
	public static void main(String[] args) throws Exception{
		//Scanner stdin = new Scanner(System.in);
		Scanner stdin = new Scanner(new File("cycle.in"));
		PrintWriter out = new PrintWriter(new FileWriter("cycle.out"));
		
		int N = stdin.nextInt();
		int M = stdin.nextInt();
		
		cows = new LinkedList[N+1];
		visited = new boolean[N+1];
		for(int i = 0; i <= N; i++) cows[i] = new LinkedList<Integer>();
		for(int i = 0; i < M; i++) {
			int a = stdin.nextInt();
			int b = stdin.nextInt();
			if(!cows[a].contains(b)) {
				cows[a].add(b);
				cows[b].add(a);
			} 
		}
		String ans = "NO";
		Arrays.fill(visited, false);
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				if(isCyclic(i, visited, -1)) {
					ans = "YES";
					break;
				}
			}
		}
		out.println(ans);
		out.close();
		stdin.close();

	}
	public static boolean isCyclic(int u, boolean[]visited, int parent) {
		
		visited[u] = true;
		LinkedList<Integer> edge = cows[u];
		
		for(int i : edge) {
			if(!visited[i]) {
				if(isCyclic(i, visited, u)) {
					return true;
				}
			}else if(i != parent) {
				return true;
			}
		}
		return false;
		
	}

}
