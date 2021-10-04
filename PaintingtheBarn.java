import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
//WORKS
public class PaintingtheBarn {
	static int N;
	static int[]color;
	static LinkedList<Integer>[]barn;
	static boolean works = true;
	public static void main(String[] args) throws Exception{
		//Scanner stdin = new Scanner(System.in);
		Scanner stdin = new Scanner(new File("paintbarns.in"));
		PrintWriter out = new PrintWriter(new FileWriter("paintbarns.out"));
		N = stdin.nextInt();
		int M = stdin.nextInt();
		barn = new LinkedList[N+1];
		color = new int[N+1];
		for(int i = 1; i <= N; i++) barn[i] = new LinkedList<Integer>();
		for(int i = 0; i < M; i++) {
			int a = stdin.nextInt();
			int b = stdin.nextInt();
			barn[a].add(b);
			barn[b].add(a);
		}
		out.println(findDifference()? "YES" : "NO");
		out.close();
		stdin.close();
	}
	public static boolean findDifference() {
		int count = 0;
		for(int i = 1; i <= N; i++) {
			if(color[i] == 0) {
				count++;
				dfs(i, -1);
			}
		}
		return works;
	}
	public static void dfs(int i, int prev) {
		if(prev > -1 && color[prev] == 1) {
			color[i] = 2;
		}else if(prev > -1 && color[prev] == 2) {
			color[i] = 1;
		}else if(prev == -1) {
			color[i] = 1;
		}
		for(int next: barn[i]) {
			if(color[next] > 0 && (color[next] == color[i])) {
				works = false;
			}else if(color[next] == 0) {
				dfs(next, i);
			}
		}
	}

}
