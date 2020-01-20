import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Graph {

	public Graph() {
		// TODO Auto-generated constructor stub
	}
	static Queue<Integer> q = new LinkedList<>();
	static int[][] graph = {
			   //0,1,2,3,4,5,6,7,8,9,10,11,12,13
				{0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0},//0
				{0,0,1,1,0,0,0,0,0,0, 0, 0, 0, 0},//1
				{0,1,0,0,1,1,1,0,0,0, 0, 0, 0, 0},//2
				{0,1,0,0,0,0,0,1,1,0, 0, 0, 0, 0},//3
				{0,0,1,0,0,0,0,0,0,1, 0, 0, 0, 0},//4
				{0,0,1,0,0,0,0,0,0,0, 0, 0, 0, 0},//5
				{0,0,1,0,0,0,0,0,0,0, 1, 1, 0, 0},//6
				{0,0,0,1,0,0,0,0,0,0, 0, 0, 0, 0},//7
				{0,0,0,1,0,0,0,0,0,0, 0, 0, 1, 1},//8
				{0,0,0,0,1,0,0,0,0,0, 0, 0, 0, 0},//9
				{0,0,0,0,0,0,1,0,0,0, 0, 0, 0, 0},//10
				{0,0,0,0,0,0,1,0,0,0, 0, 0, 0, 0},//11
				{0,0,0,0,0,0,0,0,1,0, 0, 0, 0, 0},//12
				{0,0,0,0,0,0,0,0,1,0, 0, 0, 0, 0}//13
				
				};

	
	
	static boolean visited[] = new boolean[14];

	
	static int N,M,V;//정점개수,간선개수,시작번호
	
	
	
	static void initialize()
	{
		for(int i=0;i<visited.length;i++)
			visited[i] = false;

	}
	
	static void bj2178() throws Exception
	{
		InputStreamReader k = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(k);
		
		String str = b.readLine();

		StringTokenizer strtok = new StringTokenizer(str," ");
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		graph = new int[N+1][M+1];
		
		for(int i=0;i<N;i++)
		{
			str = b.readLine();
			for(int j=0;j<str.length();j++)
			{
				graph[i+1][j+1] = Integer.parseInt(str.charAt(j)+"");
				
			}
		}
		
		
		
	}
	
	static void bj1260() throws Exception
	{
		InputStreamReader k = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(k);
		
		String str = b.readLine();
		StringTokenizer strtok = new StringTokenizer(str," ");
		N = Integer.parseInt(strtok.nextToken());//정점 개수
		M = Integer.parseInt(strtok.nextToken());//간선 개수
		V = Integer.parseInt(strtok.nextToken());//시작점
		
		visited = new boolean[N+1];
		graph = new int[N+1][N+1];
		
		for(int i=0;i<N+1;i++)//그래프초기화
			for(int j=0;j<N+1;j++)
				graph[i][j] = 0;
		
		for(int i = 0;i<M;i++)//그래프 생성
		{
			str = b.readLine();
			strtok = new StringTokenizer(str," ");
			
			int row = Integer.parseInt(strtok.nextToken());
			int column = Integer.parseInt(strtok.nextToken());
			
			graph[row][column] = 1;
			graph[column][row] = 1;
		}
		
		dfs(V);
		System.out.println();
		bfs(V);
		
	}
	static void dfs(int start)
	{
		initialize();
	
		adfs(start);
		
		
	}
	
	static void adfs(int v)
	{
		visited[v] = true;
		System.out.print(v);
		for(int i=1;i<visited.length;i++)
		{
			if(graph[v][i]==1 && !visited[i])//방문한적없으면 방문
			{
				System.out.print(" ");
				adfs(i);
				
			}
				
		}
	}
	
	static void bfs(int start)
	{
		initialize();
		
		visited[start] = true; //시작점
		q.offer(start);
		//enqueue(start);
		while(!q.isEmpty())
		{
			int v = q.poll();
			//int v = dequeue();
			System.out.print(v);
			
			for(int i=1;i<visited.length;i++)
			{
				if(graph[v][i]==1 && !visited[i])//인접해있으면 enqueue
				{
					visited[i] = true;
					q.offer(i);
					//enqueue(i);
				}
			}
			if(!q.isEmpty())
				System.out.print(" ");
		}
	}
	

}
