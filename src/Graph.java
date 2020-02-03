import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Graph {

	public Graph() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	static class Position{
		private int x,y,z,distance;
		
		private int max, min;
		private ArrayList<Integer> path;
		
		public Position() {
			
		}
		public Position(int x, int y)
		{
			this.x=x;
			this.y=y;
		}
		
		public Position(int x, int y,int z)
		{
			this.x=x;
			this.y=y;
			this.z=z;
		}
		
		public Position(int x, int y,int distance, ArrayList<Integer> path)
		{
			this.x=x;
			this.y=y;
			this.distance=distance;
			this.path = path;
		}
		
		
		
		public int getMax() {
			return max;
		}
		public void setMax(int max) {
			this.max = max;
		}
		public int getMin() {
			return min;
		}
		public void setMin(int min) {
			this.min = min;
		}
		public void addV(int v)
		{
			path.add(v);
		}
		public int getV(int index)
		{
			return path.get(index);
		}
		public ArrayList<Integer> getPath() {
			return path;
		}
		public void setPath(ArrayList<Integer> path) {
			this.path = path;
		}
		public int getDistance() {
			return distance;
		}
		public void setDistance(int distance) {
			this.distance = distance;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getZ() {
			return z;
		}
		public void setZ(int z) {
			this.z = z;
		}
		
	}
	
	static void bj2573() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		int graph[][] = new int[N][N];
		int melt[][] = new int[N][N];
		boolean visited[][] = new boolean[N][N];
		
		for(int i=0;i<N;i++)
		{
			strtok = new StringTokenizer(b.readLine()," ");
			for(int j=0;j<M;j++)
			{
				visited[i][j] = false;
				
				graph[i][j] = Integer.parseInt(strtok.nextToken());
				if(graph[i][j]!=0)
					melt[i][j] = graph[i][j] + 1;
				else
					melt[i][j] = 0;
			}
		}
		
	}
	
	static void A_bj2573(int x, int y,int[][] graph,int[][] melt,boolean[][] visited,int N,int M)
	{
		visited[x][y] = true;
		
		if(x!=0)
		{
			if(graph[x-1][y]!=0 && !visited[x-1][y])
			{
				A_bj2573(x-1, y, graph, melt, visited, N, M);
			}
			
		}
		if(x!=N)
		{
			if(graph[x+1][y]!=0 && !visited[x+1][y])
			{
				A_bj2573(x+1, y, graph, melt, visited, N, M);
			}
			
		}
		if(y!=0)
		{
			if(graph[x][y-1]!=0 && !visited[x][y-1])
			{
				A_bj2573(x, y-1, graph, melt, visited, N, M);
			}
			
		}
		if(y!=M)
		{
			if(graph[x][y+1]!=0 && !visited[x][y+1])
			{
				A_bj2573(x, y+1, graph, melt, visited, N, M);
			}
			
		}
	}
	
	static boolean[] visited1707;
	static void bj1707() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int K = Integer.parseInt(b.readLine());
		
		for(int i=0;i<K;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
			int V = Integer.parseInt(strtok.nextToken());//정점수
			int E = Integer.parseInt(strtok.nextToken());//간선수
			
		//	int graph[][] = new int[V+1][V+1];
			ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
			
			visited1707 = new boolean[V+1];
			
			int set[] = new int [V+1];//각 v가 어느 집합에 속해잇는지 0 = 아무데도 안속해잇음, 1 = 첫번째 집합, 2 = 두번째집합
			
			graph.add(new ArrayList<>());
			for(int j=1;j<=V;j++)//초기화
			{
				set[j] = 0;
				visited1707[j] = false;
				
				graph.add(new ArrayList<Integer>());
				
			}
			
			for(int j=0;j<E;j++)
			{
				strtok = new StringTokenizer(b.readLine()," ");
				int x = Integer.parseInt(strtok.nextToken());
				int y = Integer.parseInt(strtok.nextToken());
				
				graph.get(x).add(y);
				graph.get(y).add(x);
				
			}
			
	
			for(int j=1;j<=V;j++)
			{
				if(!visited1707[j])
				{
					visited1707[j] = true;
					set[j] = 1;
					A_bj1707(j, graph, set);
				}
			}
		
		
			boolean result = true;
			for(int j=1;j<=V;j++)
			{
				for(int k=0;k<graph.get(j).size();k++)
				{
					if(set[j] == set[graph.get(j).get(k)])
					{
						System.out.println("NO");
						result=false;
						j = V;
						k = V;
						break;
					}
				}
			}
			if(result)
				System.out.println("YES");
			graph = null;
			visited1707 = null;
			set = null;
		}
	}
	
	static void A_bj1707(int v,ArrayList<ArrayList<Integer>> graph, int[] set)
	{
	
		for(int i=0;i<graph.get(v).size();i++)
		{
			if(set[v]==1)
			{
				set[graph.get(v).get(i)]=2;
			}
			else if(set[v]==2)
			{
				set[graph.get(v).get(i)]=1;
			}
			
			if(!visited1707[graph.get(v).get(i)])
			{
				visited1707[graph.get(v).get(i)] = true;
				A_bj1707(graph.get(v).get(i), graph, set);
			}
		}
	}
	
	static void bj1389() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		int graph[][] = new int[N+1][N+1];
		int result[][] = new int[N+1][N+1];//케빈베이컨 결과들
		boolean visited[] = new boolean[N+1];
		
		for(int i=1;i<=N;i++)//초기화
		{
			visited[i] = false;
			for(int j=1;j<=N;j++)
			{	
				graph[i][j] = 0;
				result[i][j] = 0;
			}
		}
		
		for(int i=0;i<M;i++)
		{
			strtok = new StringTokenizer(b.readLine()," ");
			int x = Integer.parseInt(strtok.nextToken());
			int y = Integer.parseInt(strtok.nextToken());
			
			result[x][y] = 1;
			result[y][x] = 1;
			
			graph[x][y] = 1;
			graph[y][x] = 1;
		}

		
		for(int i=1;i<=N;i++)
		{
		
			for(int j=1;j<=N;j++)
			{
				for(int k=1;k<=N;k++)
				{	
					if(graph[i][k]!=1)
						visited[k] = false;
					else
						visited[k] = true;
				}
				visited[i] = true;
				if(graph[i][j] == 1)
				{
					A_bj1389(i,j, N, graph, visited, 1, result);
				}
				
			}
			
		}
		
		int min=0;
		int user=1;
		for(int i=1;i<=N;i++)
		{
			int sum=0;
			for(int j=1;j<=N;j++)
			{
				sum+=result[i][j];
			}
			if(i==1)
			{
				min=sum;
			}
			else
			{
				if(min > sum)
				{
					min=sum;
					user=i;
				}
			}
			
		}
		System.out.println(user);
	
	}

	static void A_bj1389(int start,int user,int N ,int[][] graph,boolean visited[],int count,int[][] result)
	{
		boolean[] visited_temp = (boolean[]) visited.clone();
		if(result[start][user]==0)
		{
			result[start][user] = count;
		}
		else
		{
			if(result[start][user] > count)
				result[start][user] = count;
		}
		
		for(int i=1;i<=N;i++)
		{
			if(graph[user][i] == 1 && !visited_temp[i])
			{
				visited_temp[i] = true;
				A_bj1389(start, i, N, graph, visited_temp, count+1, result);
			}
		}
		
	}
	
	static char graph10026[][], graph10026_2[][] ;
	static boolean visited10026[][] ;
	static void bj10026() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		graph10026 = new char[N][N];
		graph10026_2 = new char[N][N];
		visited10026 = new boolean[N][N];
		
		for(int i=0;i<N;i++)
		{
			String str = b.readLine();
			for(int j=0;j<N;j++)
			{
				graph10026[i][j] = str.charAt(j);
				if(str.charAt(j)=='G')
					graph10026_2[i][j] = 'R';
				else
					graph10026_2[i][j] = str.charAt(j);
				visited10026[i][j] = false;
			}
		}
		
		int Blind=0, NoBlind=0;
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				if(!visited10026[i][j])
				{
					NoBlind++;
					A_bj10026(i, j, graph10026,N);//색맹아닌 사람
				}
			}
		}
		
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				visited10026[i][j]=false;
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				if(!visited10026[i][j])
				{
					Blind++;
					A_bj10026(i, j, graph10026_2, N);//색맹인 사람
				}
			}
		}
	
		System.out.println(NoBlind+" "+Blind);
	}
	
	static void A_bj10026(int x, int y,char graph[][],int N) 
	{
		visited10026[x][y] = true;
		char color = graph[x][y];
		
		if(x!=0)//상
		{
			if(graph[x-1][y] == color && !visited10026[x-1][y])
			{
				A_bj10026(x-1, y, graph, N);
			}
		}
		if(x!=N-1)//하
		{
			if(graph[x+1][y] == color && !visited10026[x+1][y])
			{
				A_bj10026(x+1, y, graph, N);
			}
		}
		if(y!=0)//좌
		{
			if(graph[x][y-1] == color && !visited10026[x][y-1])
			{
				A_bj10026(x, y-1, graph, N);
			}
		}
		if(y!=N-1)//우
		{
			if(graph[x][y+1] == color && !visited10026[x][y+1])
			{
				A_bj10026(x, y+1, graph, N);
			}
		}
		
	}

	static char graph1987[][];
	
	static void bj1987() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
		int R = Integer.parseInt(strtok.nextToken());
		int C = Integer.parseInt(strtok.nextToken());
		graph1987 = new char[R+1][C+1];

		boolean[] isMeet = new boolean[26];//만난적 있는 알파벳인지
		
		for(int i=0;i<26;i++)
			isMeet[i] = false;
		
		for(int i=0;i<R;i++)
		{
			String str = b.readLine();
			for(int j=0;j<C;j++)
			{
				graph1987[i+1][j+1] = str.charAt(j);

			}
			
		}
		
		int result;
		result = A_bj1987(1, 1, R,C, 0,isMeet);
		System.out.println(result);
		
	}
	
	static int A_bj1987(int x,int y,int R,int C,int count,boolean isMeet[])
	{
		isMeet[graph1987[x][y]-65] = true;
		count++;
		int result=0;

		int max = count;
		
		if(x!=1)//상
		{
			if(!isMeet[graph1987[x-1][y] - 65])
			{
				boolean isMeet2[] = new boolean[26];
				isMeet2 = (boolean[]) isMeet.clone();
				
				result = A_bj1987(x-1, y,R,C,count,isMeet2);
				
				if(max < result)
				{	
					max = result;
				}
			}
		}
		if(x!=R)//하
		{
			if(!isMeet[graph1987[x+1][y] - 65])
			{
				boolean isMeet2[] = new boolean[26];
				isMeet2 = (boolean[]) isMeet.clone();
				
				result = A_bj1987(x+1, y,R,C,count,isMeet2);
				
				
				if(max < result)
				{	
					max = result;
				}
			}
		}
		if(y!=1)//좌
		{
			if(!isMeet[graph1987[x][y-1] - 65])
			{
				boolean isMeet2[] = new boolean[26];
				isMeet2 = (boolean[]) isMeet.clone();
				
				result = A_bj1987(x, y-1,R,C,count,isMeet2);
				
				
				if(max < result)
				{	
					max = result;
				}
			}
		}
		if(y!=C)//우
		{
			if(!isMeet[graph1987[x][y+1] - 65])
			{
				boolean isMeet2[] = new boolean[26];
				isMeet2 = (boolean[]) isMeet.clone();
				
				result = A_bj1987(x, y+1,R,C,count,isMeet2);
				
				
				if(max < result)
				{	
					max = result;
				}	
			}
		}
		
	
		return max;
	}
	
	
	
	static void bj1981() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int n = Integer.parseInt(b.readLine());
		
		int graph[][] = new int[n+1][n+1];
		boolean visited[][] = new boolean[n+1][n+1];
		
		
		for(int i=1;i<=n;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
			for(int j=1;j<=n;j++)
			{
				visited[i][j] = false;
				graph[i][j] = Integer.parseInt(strtok.nextToken());
			}
		}
		
		Queue<Position> q = new LinkedList<>();
		
		Position p = new Position(1,1);
		p.setPath(new ArrayList<>());
		p.getPath().add(graph[1][1]);
		p.setMax(graph[1][1]);
		p.setMin(graph[1][1]);
		visited[1][1] = true;
		q.offer(p);
		
		Position[][] p_graph = new Position[n+1][n+1];
		
		p_graph[1][1] = p;
		
		int subtract[][] = new int [n+1][n+1];
		subtract[1][1] = 0;
		
		while(!q.isEmpty())
		{
			Position pos = q.poll();
			
			int sub = 200;
			
			if(pos.getX()!=1)//상
			{
				int up_min = p_graph[pos.getX()-1][pos.getY()].getMin();
				int up_max = p_graph[pos.getX()-1][pos.getY()].getMax();
				int v = graph[pos.getX()][pos.getY()];
				
				if(v < up_min)
					up_min = v;
				if(v > up_max)
					up_max = v;
				
				pos.setMax(up_max);
				pos.setMin(up_min);
				
				p_graph[pos.getX()][pos.getY()] = pos;
				subtract[pos.getX()][pos.getY()] =  pos.getMax() - pos.getMin();
				sub =  pos.getMax() - pos.getMin();
			}
			
			if(pos.getY()!=1)//좌
			{
				int left_min = p_graph[pos.getX()][pos.getY()-1].getMin();
				int left_max = p_graph[pos.getX()][pos.getY()-1].getMax();
				int v = graph[pos.getX()][pos.getY()];
				
				if(v < left_min)
					left_min = v;
				if(v > left_max)
					left_max = v;
				
				if(sub > left_max - left_min)
				{
					pos.setMax(left_max);
					pos.setMin(left_min);
				}
				
				p_graph[pos.getX()][pos.getY()] = pos;
				subtract[pos.getX()][pos.getY()] = pos.getMax() - pos.getMin();
			}
			
			if(pos.getX()!=n)//하
			{
				if(!visited[pos.getX()+1][pos.getY()])
				{
					q.offer(new Position(pos.getX()+1,pos.getY()));
					visited[pos.getX()+1][pos.getY()] = true;
				}
			}
			
			if(pos.getY()!=n)//우
			{
				if(!visited[pos.getX()][pos.getY()+1])
				{
					q.offer(new Position(pos.getX(),pos.getY()+1));
					visited[pos.getX()][pos.getY()+1] = true;
				}
			}
			
			
		}
		
		for(int i=1;i<=n;i++)
		{
			for(int j=1;j<=n;j++)
				System.out.print(subtract[i][j]+" ");
			System.out.println();
		}
		System.out.println();
		for(int i=1;i<=n ;i++)
		{
			for(int j=1;j<=n;j++)
			{
				System.out.print("("+p_graph[i][j].getMin()+", "+p_graph[i][j].getMax()+")");
			}
			System.out.println();
		}
		
		System.out.println(subtract[n][n]);
	}
	
	static void bj3197() throws Exception//백조
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
		int R = Integer.parseInt(strtok.nextToken());
		int C = Integer.parseInt(strtok.nextToken());
		
		int graph[][] = new int[R][C];
		int melt[][] = new int[R][C];//녹는 날짜
		boolean visited[][] = new boolean[R][C];
		
		//'.' -> 0 ==> 물 공간, 'X' -> 1 ==> 빙판 공간, 'L' -> -1 ==> 백조
		
		Queue<Position> q = new LinkedList<>();
		Position bird = new Position();

		
		for(int i=0;i<R;i++)
		{
			
			String str = b.readLine();
			for(int j=0;j<C;j++)
			{
				melt[i][j] = 0;
				//visited[i][j] = false;
				if(str.charAt(j)=='.')//물
					graph[i][j] = 0;
				else if(str.charAt(j)=='X')//빙판
					graph[i][j] = 1;
				else//백조
				{
					melt[i][j] = -1;
					graph[i][j] = -1;
					bird.setX(i);
					bird.setY(j);
				}
			}
		}
		
		
		//boolean isMeet = false;//백조들이 만났는지 여부
		int day=0;//만나는데 걸리는 최소 일수
		
		//맨처음 물(혹은 백조)랑 바로 닿아있는 얼음들 queue에 미리 추가(시작점들)
		for(int i=0;i<R;i++)
		{
			for(int j=0;j<C;j++)
			{
				if(graph[i][j]== 1 && !visited[i][j]) //빙판 찾기
				{
					if(i!=0)
					{
						if(graph[i-1][j] == 0 || graph[i-1][j] == -1)//백조 혹은 물이랑 닿아잇으면추가
						{
							q.offer(new Position(i,j));
							melt[i][j] = 1;
							visited[i][j] = true;
							continue;
						}
					}
					if(j!=0)
					{
						if(graph[i][j-1] == 0 || graph[i][j-1] == -1)//백조 혹은 물이랑 닿아잇으면추가
						{
							q.offer(new Position(i,j));
							melt[i][j] = 1;
							visited[i][j] = true;
							continue;
						}
					}
					if(i!=R-1)
					{
						if(graph[i+1][j] == 0 || graph[i+1][j] == -1)//백조 혹은 물이랑 닿아잇으면추가
						{
							q.offer(new Position(i,j));
							melt[i][j] = 1;
							visited[i][j] = true;
							continue;
						}
					}
					if(j!=C-1)
					{
						if(graph[i][j+1] == 0 || graph[i][j+1] == -1)//백조 혹은 물이랑 닿아잇으면추가
						{
							q.offer(new Position(i,j));
							melt[i][j] = 1;
							visited[i][j] = true;
							continue;
						}
					}
					
				}
			}
		}
		
		int max=0;
		
		//모든 얼음들이 몇일째에 녹는지 체크
		while(!q.isEmpty())
		{
			Position pos = q.poll();
			if(max < melt[pos.getX()][pos.getY()])
				max = melt[pos.getX()][pos.getY()];
			
			if(pos.getX()!=R-1)
			{
				if((graph[pos.getX()+1][pos.getY()] == 1) && !visited[pos.getX()+1][pos.getY()])//물
				{	
					q.offer(new Position(pos.getX()+1,pos.getY()));
					melt[pos.getX()+1][pos.getY()] = melt[pos.getX()][pos.getY()] + 1;
					visited[pos.getX()+1][pos.getY()] = true;
				}
				
				
			}
			if(pos.getY()!=C-1)
			{
				if((graph[pos.getX()][pos.getY()+1] == 1) && !visited[pos.getX()][pos.getY()+1])
				{
					q.offer(new Position(pos.getX(), pos.getY()+1));
					melt[pos.getX()][pos.getY()+1] = melt[pos.getX()][pos.getY()] + 1;
					visited[pos.getX()][pos.getY()+1] = true;
					
				}
				
			}
			
			if(pos.getX()!=0)
			{
				if((graph[pos.getX()-1][pos.getY()] == 1) && !visited[pos.getX()-1][pos.getY()])
				{	
					q.offer(new Position(pos.getX()-1, pos.getY()));
					melt[pos.getX()-1][pos.getY()] = melt[pos.getX()][pos.getY()] + 1;
					visited[pos.getX()-1][pos.getY()] = true;
				}
				
				
			}
			if(pos.getY()!=0)
			{
				if( (graph[pos.getX()][pos.getY()-1] == 1) && !visited[pos.getX()][pos.getY()-1])
				{
					q.offer(new Position(pos.getX(), pos.getY()-1));			
					melt[pos.getX()][pos.getY()-1] = melt[pos.getX()][pos.getY()] + 1;
					visited[pos.getX()][pos.getY()-1] = true;
				
				}
				
			}
		}
		
		
		
		
		for(int r=0;r<R;r++)
		{
			for(int c=0;c<C;c++)
			{
				visited[r][c] = false; // 초기화
			}
		}
	
		q.offer(bird);//첫번째 백조 위치가 시작점
		visited[bird.getX()][bird.getY()] = true;
			
		Queue<Position> temp_q = new LinkedList<>();//currentMeltLevel+1 에 해당하는 얼음들 임시로 추가해놓고 현재 melt level 탐색 끝나면 q로 옮김
		
		
		int currentMeltLevel = 0;//현재 melt 레벨
		
		//melt level 0일 부터 시작해서 queue가 빌 때마다 melt level 올려서 탐색 하다가 현재 탐색 범위에 다른 bird 위치가 탐색되면 백조가 만날수 있는것이라고 판단
		while(!q.isEmpty())
		{
			Position pos = q.poll();
			
			if(graph[pos.getX()][pos.getY()] == -1 && (pos.getX()!=bird.getX() || pos.getY()!=bird.getY()) )
			{
				System.out.println(currentMeltLevel);
				return;
			}
		
			if(pos.getX()!=R-1)
			{
				if(melt[pos.getX()+1][pos.getY()] <= currentMeltLevel && !visited[pos.getX()+1][pos.getY()])//물
				{	
					Position temp = new Position(pos.getX()+1,pos.getY());
					
					q.offer(temp);
					
					visited[pos.getX()+1][pos.getY()] = true;
				}
				else if(melt[pos.getX()+1][pos.getY()] == currentMeltLevel+1 && !visited[pos.getX()+1][pos.getY()])
				{
					temp_q.offer(new Position(pos.getX()+1,pos.getY()));
					visited[pos.getX()+1][pos.getY()] = true;
					
				}
					
			}
			if(pos.getY()!=C-1)
			{
				if(melt[pos.getX()][pos.getY()+1] <= currentMeltLevel &&!visited[pos.getX()][pos.getY()+1])
				{
					Position temp = new Position(pos.getX(),pos.getY()+1);
					
					
					q.offer(temp);
					
					visited[pos.getX()][pos.getY()+1] = true;
					
				}
				else if(melt[pos.getX()][pos.getY()+1] == currentMeltLevel+1 && !visited[pos.getX()][pos.getY()+1])
				{
					temp_q.offer(new Position(pos.getX(),pos.getY()+1));
					visited[pos.getX()][pos.getY()+1] = true;
				}
					
			}
				
			if(pos.getX()!=0)
			{
				if(melt[pos.getX()-1][pos.getY()] <= currentMeltLevel &&!visited[pos.getX()-1][pos.getY()])
				{	
					Position temp = new Position(pos.getX()-1,pos.getY());
					
			
					q.offer(temp);
			
					visited[pos.getX()-1][pos.getY()] = true;
				}
				else if(melt[pos.getX()-1][pos.getY()] == currentMeltLevel+1 && !visited[pos.getX()-1][pos.getY()])
				{
					temp_q.offer(new Position(pos.getX()-1,pos.getY()));
					visited[pos.getX()-1][pos.getY()] = true;
				}
			}
			if(pos.getY()!=0)
			{
				if(melt[pos.getX()][pos.getY()-1] <= currentMeltLevel &&!visited[pos.getX()][pos.getY()-1])
				{
					Position temp = new Position(pos.getX(),pos.getY()-1);
				
					q.offer(temp);
					
					visited[pos.getX()][pos.getY()-1] = true;
				
				}
				else if(melt[pos.getX()][pos.getY()-1] == currentMeltLevel+1 && !visited[pos.getX()][pos.getY()-1])
				{
					temp_q.offer(new Position(pos.getX(),pos.getY()-1));
					visited[pos.getX()][pos.getY()-1] = true;
				}
				
			}
			
			if(q.isEmpty())//현재 melt 레벨에선 백조가 못만나므로 melt level 증가시킴
			{
				q=null;
				q = temp_q;
				temp_q = null;
				temp_q = new LinkedList<>();
				currentMeltLevel++;
			}
			
		}		
		
	}
	
	static void bj7569() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
		int M = Integer.parseInt(strtok.nextToken());
		int N = Integer.parseInt(strtok.nextToken());
		int H = Integer.parseInt(strtok.nextToken());
		
		int graph[][][] = new int[H][N][M];
		boolean visited[][][] = new boolean[H][N][M];
		
		Queue<Position> q = new LinkedList<>();
		
		int zeroCount=0;//안익은 토마토 개수 헤아리고 안익은게 하나도 없으면 0출력
		
		for(int i=0;i<H;i++)
		{
			for(int j=0;j<N;j++)
			{
				strtok = new StringTokenizer(b.readLine()," ");
				
				for(int k=0;k<M;k++)
				{
					visited[i][j][k] = false;
					graph[i][j][k] = Integer.parseInt(strtok.nextToken());
					
					if(graph[i][j][k]==1)
					{
						Position pos = new Position(i,j,k);
						pos.setDistance(0);
						q.offer(pos);
						visited[i][j][k] = true;
					}
					else if(graph[i][j][k]==0)
					{
						zeroCount++;
					}
					
				}
			}
		}
		
		if(zeroCount==0)
		{
			System.out.println(0);
			return;
		}
		int day = 0;//익는데 필요한 최소일수
		while(!q.isEmpty())
		{
			Position pos = q.poll();
			day = pos.getDistance();
			
			if(pos.getX()!=H-1)
			{
				if(graph[pos.getX()+1][pos.getY()][pos.getZ()] == 0 && !visited[pos.getX()+1][pos.getY()][pos.getZ()])
				{	
					Position p = new Position(pos.getX()+1,pos.getY(), pos.getZ());
					p.setDistance(pos.getDistance()+1);
					q.offer(p);				
					visited[pos.getX()+1][pos.getY()][pos.getZ()] = true;
				
				}
			}
			if(pos.getY()!=N-1)
			{
				if(graph[pos.getX()][pos.getY()+1][pos.getZ()] == 0 && !visited[pos.getX()][pos.getY()+1][pos.getZ()])
				{
					Position p = new Position(pos.getX(),pos.getY()+1, pos.getZ());
					p.setDistance(pos.getDistance()+1);
					q.offer(p);				
					visited[pos.getX()][pos.getY()+1][pos.getZ()] = true;
					
				}
			}
			if(pos.getZ()!=M-1)
			{
				if(graph[pos.getX()][pos.getY()][pos.getZ()+1] == 0 && !visited[pos.getX()][pos.getY()][pos.getZ()+1])
				{
					Position p = new Position(pos.getX(),pos.getY(), pos.getZ()+1);
					p.setDistance(pos.getDistance()+1);
					q.offer(p);				
					visited[pos.getX()][pos.getY()][pos.getZ()+1] = true;
					
				}
			}
			
			if(pos.getX()!=0)
			{
				if(graph[pos.getX()-1][pos.getY()][pos.getZ()] == 0 && !visited[pos.getX()-1][pos.getY()][pos.getZ()])
				{	
					Position p = new Position(pos.getX()-1,pos.getY(), pos.getZ());
					p.setDistance(pos.getDistance()+1);
					q.offer(p);				
					visited[pos.getX()-1][pos.getY()][pos.getZ()] = true;
				
				}
			}
			if(pos.getY()!=0)
			{
				if(graph[pos.getX()][pos.getY()-1][pos.getZ()] == 0 && !visited[pos.getX()][pos.getY()-1][pos.getZ()])
				{
					Position p = new Position(pos.getX(),pos.getY()-1, pos.getZ());
					p.setDistance(pos.getDistance()+1);
					q.offer(p);				
					visited[pos.getX()][pos.getY()-1][pos.getZ()] = true;
				
				}
			}
			if(pos.getZ()!=0)
			{
				if(graph[pos.getX()][pos.getY()][pos.getZ()-1] == 0 && !visited[pos.getX()][pos.getY()][pos.getZ()-1])
				{
					Position p = new Position(pos.getX(),pos.getY(), pos.getZ()-1);
					p.setDistance(pos.getDistance()+1);
					q.offer(p);				
					visited[pos.getX()][pos.getY()][pos.getZ()-1] = true;
					
				}
			}
		}
		
		for(int i=0;i<H;i++)
			for(int j=0;j<N;j++)
				for(int k=0;k<M;k++)
				{
					if(graph[i][j][k]==0 && !visited[i][j][k]){//토마토가 모두 익지 못하는 상황
						System.out.println(-1);
						return;
					}
				}
		
		System.out.println(day);
	}
	
	static void bj2468() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		int graph[][] = new int[N][N];
		boolean visited[][] = new boolean[N][N];
		
		int maxHeight = 0;//최대높이
		
		for(int i=0;i<N;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine()," ");	
			for(int j=0;j<N;j++)
			{
				graph[i][j] = Integer.parseInt(strtok.nextToken());
				visited[i][j] = false;
				if(maxHeight < graph[i][j])
					maxHeight = graph[i][j];
			}
		}
		
		ArrayList<Integer> counts = new ArrayList<>();//각 경우마다 안전영역 개수들
	
		for(int i = 0; i <= maxHeight; i++)//물에 잠긴 기둥 높이 0 ~ maxHeight 까지 다 완전탐색해서 안전영역 제일 많은것 뽑기
		{
			int count=0;
			
			for(int j=0;j<N;j++)
			{
				for(int k=0;k<N;k++)
				{
					if(graph[j][k] > i && !visited[j][k])//물에잠기는 높이보다 크면 안전영역
					{
						count++;//안전영역 개수
						
						Queue<Position> q = new LinkedList<>();
						q.offer(new Position(j,k));
						visited[j][k] = true;
						
						while(!q.isEmpty())
						{
							Position pos = q.poll();
							
							if(pos.getX()!=N-1)
							{
								if(graph[pos.getX()+1][pos.getY()] > i && !visited[pos.getX()+1][pos.getY()])
								{	
									q.offer(new Position(pos.getX()+1,pos.getY(), pos.getDistance()+1));
									visited[pos.getX()+1][pos.getY()] = true;
								
								}
							}
							if(pos.getY()!=N-1)
							{
								if(graph[pos.getX()][pos.getY()+1] > i && !visited[pos.getX()][pos.getY()+1])
								{
									q.offer(new Position(pos.getX(), pos.getY()+1, pos.getDistance()+1));
									visited[pos.getX()][pos.getY()+1] = true;
									
								}
							}
							
							if(pos.getX()!=0)
							{
								if(graph[pos.getX()-1][pos.getY()] > i && !visited[pos.getX()-1][pos.getY()])
								{	
									q.offer(new Position(pos.getX()-1, pos.getY(), pos.getDistance()+1));
									visited[pos.getX()-1][pos.getY()] = true;
								
								}
							}
							if(pos.getY()!=0)
							{
								if(graph[pos.getX()][pos.getY()-1] > i && !visited[pos.getX()][pos.getY()-1])
								{
									q.offer(new Position(pos.getX(), pos.getY()-1, pos.getDistance()+1));
									visited[pos.getX()][pos.getY()-1] = true;
								
								}
							}
						}
					}
					
				}
			}
			
			for(int j=0;j<N;j++)
				for(int k=0;k<N;k++)
					visited[j][k] = false;
			counts.add(count);
		}
		
		int max = counts.get(0);//최대 안전영역 개수
		
		for(int i=0;i<counts.size();i++)
		{
			if(max < counts.get(i))
				max = counts.get(i);
		}
		System.out.println(max);
	}
	
	static void bj2583() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
		int M = Integer.parseInt(strtok.nextToken());
		int N = Integer.parseInt(strtok.nextToken());
		int K = Integer.parseInt(strtok.nextToken());
		
		int graph[][] = new int[N][M];
		boolean visited[][] = new boolean[N][M];
		
		for(int i=0;i<N;i++)
			for(int j=0;j<M;j++)
			{
				visited[i][j] = false;
				graph[i][j] = 0;
			}
		
		for(int i=0;i<K;i++)
		{
			strtok = new StringTokenizer(b.readLine()," ");
			int startX = Integer.parseInt(strtok.nextToken());
			int startY = Integer.parseInt(strtok.nextToken());
			int endX = Integer.parseInt(strtok.nextToken());
			int endY = Integer.parseInt(strtok.nextToken());
			
			for(int n=0;n<N;n++)
				for(int m=0;m<M;m++)
				{
					if(n >= startX && n < endX && m >= startY && m < endY)
					{
						graph[n][m] = 1;
					}
				}
			
		}
		
		Queue<Position> q = new LinkedList<>();
		
		int count=0;//군집개수
		ArrayList<Integer> sizes = new ArrayList<>();//군집넓이들
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				if(graph[i][j]==0 && !visited[i][j])
				{
					count++;
					
					q.offer(new Position(i,j));
					visited[i][j]=true;
					
					int countBlocks = 0;
					
					while(!q.isEmpty())
					{
						Position pos = q.poll();
						
						countBlocks++;
						
						if(pos.getX()!=N-1)
						{
							if(graph[pos.getX()+1][pos.getY()]==0 && !visited[pos.getX()+1][pos.getY()])
							{	
								q.offer(new Position(pos.getX()+1,pos.getY(), pos.getDistance()+1));
								visited[pos.getX()+1][pos.getY()] = true;
							
							}
						}
						if(pos.getY()!=M-1)
						{
							if(graph[pos.getX()][pos.getY()+1]==0 && !visited[pos.getX()][pos.getY()+1])
							{
								q.offer(new Position(pos.getX(), pos.getY()+1, pos.getDistance()+1));
								visited[pos.getX()][pos.getY()+1] = true;
								
							}
						}
						
						if(pos.getX()!=0)
						{
							if(graph[pos.getX()-1][pos.getY()]==0 && !visited[pos.getX()-1][pos.getY()])
							{	
								q.offer(new Position(pos.getX()-1, pos.getY(), pos.getDistance()+1));
								visited[pos.getX()-1][pos.getY()] = true;
							
							}
						}
						if(pos.getY()!=0)
						{
							if(graph[pos.getX()][pos.getY()-1]==0 && !visited[pos.getX()][pos.getY()-1])
							{
								q.offer(new Position(pos.getX(), pos.getY()-1, pos.getDistance()+1));
								visited[pos.getX()][pos.getY()-1] = true;
							
							}
						}
					}
					sizes.add(countBlocks);
				}
			}
		}
		
		
		System.out.println(count);
		
		Object[] temp = sizes.toArray();
		Arrays.sort(temp);
		
		for(int i=0;i<temp.length;i++)
		{
			System.out.print((int)temp[i]+" ");
		}
	}
	
	static void bj6603() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);

		
		StringTokenizer strtok;
		int k;
		
		strtok = new StringTokenizer(b.readLine()," ");
		k = Integer.parseInt(strtok.nextToken());
		
		while(true)
		{

			int s[] = new int[k];
			
			for(int i=0;i<k;i++)
				s[i] = Integer.parseInt(strtok.nextToken());
			
			ArrayList<Integer> path = new ArrayList<>();
			
			for(int i=0;i<s.length;i++)
			{
				if(s.length-i >= 6)
				{
					path = new ArrayList<>();
					path.add(s[i]);
					bj6603_sol(s, i, 1, path);
				}
			}
			
			strtok = new StringTokenizer(b.readLine()," ");
			k = Integer.parseInt(strtok.nextToken());
			
			if(k==0)//0입력시 종료
				break;
			else
				System.out.println();
		}
		
		
	}
	
	static void bj6603_sol(int s[],int i,int depth, ArrayList<Integer> path)
	{
		if(depth == 6)
		{
			for(int j=0;j<path.size();j++)
			{
				System.out.print(path.get(j));
				if(j!=path.size()-1)
					System.out.print(" ");
				
			}
			System.out.println();
			return ;
		}
		
		for(int j=i+1;j<s.length;j++)
		{
			if(depth+s.length-j >= 6)
			{	
				ArrayList<Integer> p = new ArrayList<>();
				p.addAll(path);
				p.add(s[j]);
				bj6603_sol(s, j, depth+1, p);
			}
		}
	}
	
	static void bj14502() throws Exception
	{
		InputStreamReader k = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(k);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		int graph[][] = new int[N][M];
		
		boolean visited[][] = new boolean[N][M];
		
		Queue<Position> q = new LinkedList<>();
		ArrayList<Position> starts = new ArrayList<>();
		
		for(int i=0;i<N;i++)
		{
			strtok = new StringTokenizer(b.readLine()," ");
			for(int j=0;j<M;j++)
			{
				graph[i][j] = Integer.parseInt(strtok.nextToken());
				
				if(graph[i][j] == 2)
				{
					starts.add(new Position(i,j));
				}
					
			}
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				if(graph[i][j]==0)//첫번째 벽놓기
				{
					graph[i][j]=1;
					for(int n=0;n<N;n++)
					{
						for(int m=0;m<M;m++)
						{
							if(graph[n][m]==0)//두번째 벽놓기
							{
								graph[n][m]=1;
								
								for(int y=0;y<N;y++)
								{
									for(int u=0;u<M;u++)
									{
										if(graph[y][u]==0)//세번째 벽놓기
										{
											graph[y][u]=1;
											
											q = new LinkedList<>();
											
											for(int e=0;e<N;e++)
												for(int r=0;r<M;r++)
													visited[e][r]=false;
											
											for(int e=0;e<starts.size();e++)
												q.offer(new Position(starts.get(e).getX(),starts.get(e).getY()));
											
											while(!q.isEmpty())
											{
												Position pos = q.poll();
												
												if(pos.getX()!=0)//상
												{
													if(graph[pos.getX()-1][pos.getY()]==0 && !visited[pos.getX()-1][pos.getY()])
													{
														q.offer(new Position(pos.getX()-1,pos.getY()));
														visited[pos.getX()-1][pos.getY()] = true;
													}
												}
												
												if(pos.getX()!=N-1)//하
												{
													if(graph[pos.getX()+1][pos.getY()]==0 && !visited[pos.getX()+1][pos.getY()])
													{
														q.offer(new Position(pos.getX()+1,pos.getY()));
														visited[pos.getX()+1][pos.getY()] = true;
													}
												}
												
												if(pos.getY()!=0)//좌
												{
													if(graph[pos.getX()][pos.getY()-1]==0 && !visited[pos.getX()][pos.getY()-1])
													{
														q.offer(new Position(pos.getX(),pos.getY()-1));
														visited[pos.getX()][pos.getY()-1] = true;
													}
												}
												
												if(pos.getY()!=M-1)//우
												{
													if(graph[pos.getX()][pos.getY()+1]==0 && !visited[pos.getX()][pos.getY()+1])
													{
														q.offer(new Position(pos.getX(),pos.getY()+1));
														visited[pos.getX()][pos.getY()+1] = true;
													}
												}
											}
											
											int count=0;
											
											for(int e=0;e<N;e++)
											{
												for(int r=0;r<M;r++)
												{
													if(graph[e][r]==0 && !visited[e][r])
														count++;
												}
											}
											result.add(count);
											
											graph[y][u]=0;
										}
									}
								}
								graph[n][m]=0;
							}
							
						}
					}
					graph[i][j]=0;
				}
			}
		}
		
		int max=result.get(0);
		for(int i=0;i<result.size();i++)
		{
			if(max < result.get(i))
				max=result.get(i);
		}
		System.out.println(max);
		
	}
	
	static void bj11724() throws Exception
	{
		InputStreamReader k = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(k);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		int graph[][] = new int[N+1][N+1];
		boolean visited[] = new boolean[N+1];
		
		for(int i=1;i<=N;i++)
			visited[i]=false;
		
		for(int i=1;i<=N;i++)
		{
			for(int j=1;j<=N;j++)
			{
				graph[i][j] = 0;
			}
		}
		
		int x=1,y=1;
		for(int i=0;i<M;i++)
		{
			strtok = new StringTokenizer(b.readLine()," ");
			x = Integer.parseInt(strtok.nextToken());
			y = Integer.parseInt(strtok.nextToken());
			graph[x][y]=1;
			graph[y][x]=1;
		}
		
		Queue<Integer> q = new LinkedList<>();
		int count=0;//연결요소의 개수
		
		for(int i=1;i<=N;i++)
		{
			if(!visited[i])//방문안한 노드일때 탐색시작 & count증가
			{
				count++;
				q = new LinkedList<>();
				q.offer(i);
				visited[i]=true;
				
				while(!q.isEmpty())
				{
					int v = q.poll();
					
					for(int j=1;j<=N;j++)
					{
						if(graph[v][j]==1 && !visited[j])
						{
							q.offer(j);
							visited[j]=true;
						}
					}
				}
			}
		}
		System.out.println(count);
		
	}
	
	static void bj11403() throws Exception
	{
		InputStreamReader k = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(k);
		
		int N = Integer.parseInt(b.readLine());//정점 개수
		
		int graph[][] = new int[N][N];//input
		int result[][] = new int[N][N];//output
		
		boolean visited[] = new boolean[N];
		
		for(int i=0;i<N;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
			for(int j=0;j<N;j++)
			{
				graph[i][j] = Integer.parseInt(strtok.nextToken());
				
				result[i][j] = 0;//초기화
			}
		}
		
		for(int i=0;i<N;i++)//총 N번만큼 경로탐색
		{
			Queue<Integer> q = new LinkedList<>();
			
			for(int j=0;j<N;j++)//visited초기화
					visited[j]=false;
			
			q.offer(i);
			//주의 : 자기자신에게로 돌아오는 경로도 생각해야하기에 i는 visited체크 안함 ex) 0->1->2->0
			while(!q.isEmpty())
			{
				int v = q.poll();
				
				for(int j=0;j<N;j++)
				{
					if(graph[v][j]==1 && !visited[j])
					{
						result[i][j] = 1;//i->j로 가는 경로가 존재
						q.offer(j);
						visited[j]=true;
					}
				}
			}
		}
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				System.out.print(result[i][j]);
				if(j!=N-1)
					System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	static void bj1012() throws Exception
	{
		InputStreamReader k = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(k);
		
		int T = Integer.parseInt(b.readLine());//테스트 케이스 개수
		
		for(int i=0;i<T;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
			int M = Integer.parseInt(strtok.nextToken());//row
			int N = Integer.parseInt(strtok.nextToken());//column
			int K = Integer.parseInt(strtok.nextToken());//배추개수
			
			int graph[][] = new int[M][N];
			boolean visited[][] = new boolean[M][N];
			
			for(int m=0;m<M;m++)
				for(int n=0;n<N;n++)
				{
					graph[m][n] = 0;
					visited[m][n] = false;
				}
			
			Queue<Position> q = new LinkedList<>();
			
			for(int j=0;j<K;j++)
			{
				strtok = new StringTokenizer(b.readLine()," ");
				graph[Integer.parseInt(strtok.nextToken())][Integer.parseInt(strtok.nextToken())] = 1;//배추위치
			}
			
			int count = 0;//배추군집개수(지렁이마리수)
			
			for(int m=0;m<M;m++)
			{
				for(int n=0;n<N;n++)
				{
					if(graph[m][n]==1 && !visited[m][n])//그래프 전체 순회하다가 1 인, 방문안한 위치 만나면 BFS 탐색 
					{
						count++;
						q.offer(new Position(m,n));
						visited[m][n]=true;
						
						while(!q.isEmpty())
						{
							Position pos = q.poll();
							
							if(pos.getX()!=0)//상
							{
								if(graph[pos.getX()-1][pos.getY()]==1 &&!visited[pos.getX()-1][pos.getY()])
								{
									q.offer(new Position(pos.getX()-1,pos.getY()));
									visited[pos.getX()-1][pos.getY()] = true;
								}
							}
							
							if(pos.getX()!=M-1)//하
							{
								if(graph[pos.getX()+1][pos.getY()]==1 &&!visited[pos.getX()+1][pos.getY()])
								{
									q.offer(new Position(pos.getX()+1,pos.getY()));
									visited[pos.getX()+1][pos.getY()] = true;
								}
							}
							
							if(pos.getY()!=0)//좌
							{
								if(graph[pos.getX()][pos.getY()-1]==1 &&!visited[pos.getX()][pos.getY()-1])
								{
									q.offer(new Position(pos.getX(),pos.getY()-1));
									visited[pos.getX()][pos.getY()-1] = true;
								}
							}
							
							if(pos.getY()!=N-1)//우
							{
								if(graph[pos.getX()][pos.getY()+1]==1 &&!visited[pos.getX()][pos.getY()+1])
								{
									q.offer(new Position(pos.getX(),pos.getY()+1));
									visited[pos.getX()][pos.getY()+1] = true;
								}
							}
						}
					}
				}
			}
			System.out.println(count);
		}
		
	}

	static void bj1697() throws Exception
	{
		InputStreamReader k = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(k);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");//지도 크기 N
		int N = Integer.parseInt(strtok.nextToken());
		int K = Integer.parseInt(strtok.nextToken());
		
		int[] graph = new int[100001];
		boolean[] visited = new boolean[100001];

		
		for(int i=0;i<visited.length;i++)
		{
			visited[i] = false; 
		}
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(N);
		visited[N]=true;
		graph[N]=0;
	
		
		while(!q.isEmpty())
		{
			int v = q.poll();
			
			if(v==K)
			{
			
				System.out.println(graph[K]);
				return;
			}
			
			if(v-1 != -1 && !visited[v-1])
			{
				q.offer(v-1);
				visited[v-1] = true;
				graph[v-1] = graph[v]+1;
			
			}
			if(v+1 != 100001 && !visited[v+1])
			{
				q.offer(v+1);
				visited[v+1] = true;
				graph[v+1] = graph[v]+1;
			
			}
			
			if(v*2 < 100001 && !visited[v*2])
			{
				q.offer(v*2);
				visited[v*2] = true;
				graph[v*2] = graph[v]+1;
			
			}
			
		}
	}
	
	
	
	static void bj7576() throws Exception//토마토
	{
		InputStreamReader k = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(k);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");//지도 크기 N
		int M = Integer.parseInt(strtok.nextToken());
		int N = Integer.parseInt(strtok.nextToken());
		
		int[][] graph = new int[N+1][M+1];
		boolean[][] visited = new boolean[N+1][M+1];
		int zeroCount=0;
		
		Queue<Position> q=new LinkedList<>();

		ArrayList<Integer> days = new ArrayList<>();//익는 날짜들
		
		for(int i=1;i<=N;i++)//데이터 입력
		{
			strtok = new StringTokenizer(b.readLine()," ");
			for(int j=1;j<=M;j++)
			{
				graph[i][j]=Integer.parseInt(strtok.nextToken());
				if(graph[i][j]==0)
					zeroCount++;
				
				if(graph[i][j]==1)
				{
					q.offer(new Position(i, j, 0));
					visited[i][j] = true;
					
					days.add(0);
				}
				
				if(graph[i][j]==-1)//빈칸일땐 방문했다고 가정
					visited[i][j]=true;
				else if(graph[i][j]==0)
					visited[i][j]=false;
			}
		}
		
		if(zeroCount==0)//토마토가 전부 익은 상황
		{
			System.out.println(0);
			return;
		}
		
		
		
		
		while(!q.isEmpty())
		{
			Position pos = q.poll();
			
			
			if(pos.getX()!=N)
			{
				if(graph[pos.getX()+1][pos.getY()]==0 && !visited[pos.getX()+1][pos.getY()])
				{	
					q.offer(new Position(pos.getX()+1,pos.getY(), pos.getDistance()+1));
					visited[pos.getX()+1][pos.getY()] = true;
					days.add(pos.getDistance()+1);
				}
			}
			if(pos.getY()!=M)
			{
				if(graph[pos.getX()][pos.getY()+1]==0 && !visited[pos.getX()][pos.getY()+1])
				{
					q.offer(new Position(pos.getX(), pos.getY()+1, pos.getDistance()+1));
					visited[pos.getX()][pos.getY()+1] = true;
					days.add(pos.getDistance()+1);
				}
			}
			
			if(pos.getX()!=1)
			{
				if(graph[pos.getX()-1][pos.getY()]==0 && !visited[pos.getX()-1][pos.getY()])
				{	
					q.offer(new Position(pos.getX()-1, pos.getY(), pos.getDistance()+1));
					visited[pos.getX()-1][pos.getY()] = true;
					days.add(pos.getDistance()+1);
				}
			}
			if(pos.getY()!=1)
			{
				if(graph[pos.getX()][pos.getY()-1]==0 && !visited[pos.getX()][pos.getY()-1])
				{
					q.offer(new Position(pos.getX(), pos.getY()-1, pos.getDistance()+1));
					visited[pos.getX()][pos.getY()-1] = true;
					days.add(pos.getDistance()+1);
				}
			}
		}
		
		for(int i=1;i<=N;i++)
		{
			for(int j=1;j<=M;j++)
			{
				if(!visited[i][j])
				{
					System.out.println(-1);
					return;	
				}
			}
		}
		
		int max = days.get(0);
		
		for(int i=0;i<days.size();i++)
		{
			if(max < days.get(i))
			{
				max=days.get(i);
			}
		}
		System.out.println(max);
	}
	
	static void bj2667() throws Exception
	{
		InputStreamReader k = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(k);
		
		int N = Integer.parseInt(b.readLine());//지도 크기 N
		int[][] graph = new int[N+1][N+1];
		boolean visited[][] = new boolean[N+1][N+1];
		
		for(int i=0;i<N;i++)//지도 데이터 입력
		{
			String str = b.readLine();
			for(int j=0;j<N;j++)
			{
				graph[i+1][j+1] = Integer.parseInt(str.charAt(j)+"");
				visited[i+1][j+1] = false;
			}
		}
		
		int hometownCount=0;//단지수
		ArrayList<Integer> homeCounts = new ArrayList<>();//단지마다의 집 개수. 후에 오름차순 정렬 필요
		int totalSearchCount=0;//총 탐색수가 N*N이되면 종료
		
		int pivot;//0 혹은 1, 한개 단지 탐색 완료할때마다 바뀜
		
		if(graph[1][1]==1)//시작 위치 숫자에 따라서 탐색 pivot값 설정
			pivot=1;

		else
			pivot=0;
		
		Queue<Position> q = new LinkedList<>();
		
		int startX=1,startY=1;
		
		while(totalSearchCount!=N*N)
		{
			totalSearchCount=0;
			if(pivot==1)//바로 bfs 돌려서 집개수 카운트
			{
				q = new LinkedList<>();
				q.offer(new Position(startX, startY));
				
				Position pos;
				
				visited[startX][startY]=true;
				int count=0;//해당단지 집개수
				while(!q.isEmpty())
				{
					//totalSearchCount++;//탐색수 증가
					count++;
					pos = q.poll();
					graph[pos.getX()][pos.getY()]=0;//탐색완료한곳은 0으로 전환
					
					if(pos.getX()!=N)
					{
						if(graph[pos.getX()+1][pos.getY()]==pivot && !visited[pos.getX()+1][pos.getY()])
						{	
							q.offer(new Position(pos.getX()+1,pos.getY()));
							visited[pos.getX()+1][pos.getY()] = true;
						}
					}
					if(pos.getY()!=N)
					{
						if(graph[pos.getX()][pos.getY()+1]==pivot && !visited[pos.getX()][pos.getY()+1])
						{
							q.offer(new Position(pos.getX(), pos.getY()+1));
							visited[pos.getX()][pos.getY()+1] = true;
						}
					}
					
					if(pos.getX()!=1)
					{
						if(graph[pos.getX()-1][pos.getY()]==pivot && !visited[pos.getX()-1][pos.getY()])
						{	
							q.offer(new Position(pos.getX()-1, pos.getY()));
							visited[pos.getX()-1][pos.getY()] = true;
						}
					}
					if(pos.getY()!=1)
					{
						if(graph[pos.getX()][pos.getY()-1]==pivot && !visited[pos.getX()][pos.getY()-1])
						{
							q.offer(new Position(pos.getX(), pos.getY()-1));
							visited[pos.getX()][pos.getY()-1] = true;
						}
					}
					
					
				}
				hometownCount++;
				homeCounts.add(count);
				startX=1;
				startY=1;
				pivot=0;//pivot전환
			}
			else//pivot 0일때, bfs 돌려서 1나오는곳 찾기
			{
				q = new LinkedList<>();
				q.offer(new Position(startX, startY));
				
				Position pos;
				
				visited[startX][startY]=true;
				while(!q.isEmpty())
				{
					
					pos = q.poll();
					
					if(pos.getX()!=N)
					{
						if(graph[pos.getX()+1][pos.getY()]==pivot && !visited[pos.getX()+1][pos.getY()])
						{	
							q.offer(new Position(pos.getX()+1,pos.getY()));
							visited[pos.getX()+1][pos.getY()] = true;
						}
						else if(graph[pos.getX()+1][pos.getY()]==1 && !visited[pos.getX()+1][pos.getY()])
						{
							startX=pos.getX()+1;
							startY=pos.getY();
							break;
						}
					}
					if(pos.getY()!=N)
					{
						if(graph[pos.getX()][pos.getY()+1]==pivot && !visited[pos.getX()][pos.getY()+1])
						{
							q.offer(new Position(pos.getX(), pos.getY()+1));
							visited[pos.getX()][pos.getY()+1] = true;
						}
						else if(graph[pos.getX()][pos.getY()+1]==1 && !visited[pos.getX()][pos.getY()+1])
						{
							startX=pos.getX();
							startY=pos.getY()+1;
							break;
						}
					}
					
					if(pos.getX()!=1)
					{
						if(graph[pos.getX()-1][pos.getY()]==pivot && !visited[pos.getX()-1][pos.getY()])
						{	
							q.offer(new Position(pos.getX()-1, pos.getY()));
							visited[pos.getX()-1][pos.getY()] = true;
						}
						else if(graph[pos.getX()-1][pos.getY()]==1 && !visited[pos.getX()-1][pos.getY()])
						{
							startX=pos.getX()-1;
							startY=pos.getY();
							break;
						}
					}
					if(pos.getY()!=1)
					{
						if(graph[pos.getX()][pos.getY()-1]==pivot && !visited[pos.getX()][pos.getY()-1])
						{
							q.offer(new Position(pos.getX(), pos.getY()-1));
							visited[pos.getX()][pos.getY()-1] = true;
						}
						else if(graph[pos.getX()][pos.getY()-1]==1 && !visited[pos.getX()][pos.getY()-1])
						{
							startX=pos.getX();
							startY=pos.getY()-1;
							break;
						}
					}
				}
				pivot=1;
			}
			
			for(int i=1;i<=N;i++)
			{
				for(int j=1;j<=N;j++)
				{
					if(visited[i][j])
					{
						totalSearchCount++;
						visited[i][j]=false;
					}
				}
			}
				
		}		
		System.out.println(hometownCount);
		Object[] results=homeCounts.toArray();
		Arrays.sort(results);
		for(int i=0;i<results.length;i++)
		{
			System.out.println((Integer)results[i]);
		}
	}
	
	static void bj2178() throws Exception
	{
		InputStreamReader k = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(k);
		
		String str = b.readLine();

		StringTokenizer strtok = new StringTokenizer(str," ");
		int N = Integer.parseInt(strtok.nextToken());//줄수(row)
		int M = Integer.parseInt(strtok.nextToken());//줄마다 정수개수(column)
		
		int[][] graph = new int[N+1][M+1];
		boolean visited[][] = new boolean[N+1][M+1];
		
		for(int i=0;i<N;i++)
		{
			str = b.readLine();
			for(int j=0;j<M;j++)
			{
				graph[i+1][j+1] = Integer.parseInt(str.charAt(j)+"");
				visited[i+1][j+1] = false;
			}
		}
		
		Queue<Position> q = new LinkedList<>();
		
		q.offer(new Position(1, 1, 1));
		
		
		
		while(!q.isEmpty())
		{
			Position pos = q.poll();
			//System.out.println(pos.getX()+""+pos.getY());
			
			visited[pos.getX()][pos.getY()] = true;
			
			if(pos.getX()==N && pos.getY()==M)
			{
				System.out.println(pos.distance);
				return;
			}
			
			if(pos.getX()!=N)
			{
				if(graph[pos.getX()+1][pos.getY()]==1 && !visited[pos.getX()+1][pos.getY()])
				{	
					q.offer(new Position(pos.getX()+1,pos.getY(),pos.distance+1));
					visited[pos.getX()+1][pos.getY()] = true;
				}
			}
			if(pos.getY()!=M)
			{
				if(graph[pos.getX()][pos.getY()+1]==1 && !visited[pos.getX()][pos.getY()+1])
				{
					q.offer(new Position(pos.getX(), pos.getY()+1, pos.distance+1));
					visited[pos.getX()][pos.getY()+1] = true;
				}
			}
			
			if(pos.getX()!=1)
			{
				if(graph[pos.getX()-1][pos.getY()]==1 && !visited[pos.getX()-1][pos.getY()])
				{	
					q.offer(new Position(pos.getX()-1, pos.getY(), pos.distance+1));
					visited[pos.getX()-1][pos.getY()] = true;
				}
			}
			if(pos.getY()!=1)
			{
				if(graph[pos.getX()][pos.getY()-1]==1 && !visited[pos.getX()][pos.getY()-1])
				{
					q.offer(new Position(pos.getX(), pos.getY()-1, pos.distance+1));
					visited[pos.getX()][pos.getY()-1] = true;
				}
			}
			
			
		}
		
	}
	
/*	static void bj1260() throws Exception
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
	
	*/

}
