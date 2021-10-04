import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
public class ArtificialLake {

	public static void main(String[] args) throws Exception{
		//Scanner stdin = new Scanner(System.in);
		Scanner stdin = new Scanner(new File("alake.in"));
		PrintWriter out = new PrintWriter(new FileWriter("alake.out"));
		int N = stdin.nextInt();
		Node[]cows = new Node[N+2];
		long[]ans = new long[N+2];
		int min = (int) Double.POSITIVE_INFINITY;
		int pos = 0;
		for(int i = 0; i <= N; i++) {
			if(i == 0) {
				cows[0] = new Node(1, (int)Double.POSITIVE_INFINITY, i-1, i+1);
				cows[N+1] = new Node(1, (int)Double.POSITIVE_INFINITY, N, N+2);
			}else {
				int w = stdin.nextInt();
				int h = stdin.nextInt();
				cows[i] = new Node(w, h, i-1, i+1);
			
				if(min > h) {
					min = h;
					pos = i;
				}
			}
		}
		
		long sum = 0, num = 1;
		int left = 0, right = 0;
		while(num <= N) {
			num++;	
			sum += (long) cows[pos].width;	
			ans[pos] = sum;	
			left = cows[pos].left;			
			right = cows[pos].right;
            sum+= (long) (Math.min(cows[left].height,cows[right].height)-cows[pos].height-1)*cows[pos].width;
            cows[left].right=right;
            cows[right].left=left;
            if(cows[left].height <cows[right].height) {
            	cows[left].width += cows[pos].width;
            	pos = left;
            }else
            {
                cows[right].width += cows[pos].width;
                pos = right;
            } 
            
            while(num <= N)
            {
                left=cows[pos].left;
                right=cows[pos].right;
                if(cows[left].height <cows[pos].height)
                	pos=left;
                else if(cows[right].height <cows[pos].height)
                	pos=right;
                else
                    break;
           }

		}
		
		for(int i = 1; i < N+1; i++) out.println(ans[i]);
		
		out.close();

	}
	static class Node{
		int width, height, left, right;
		public Node(int a, int b, int c, int d) {
			width = a;
			height = b;
			left = c;
			right = d;
		}
	}
}
