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
	
	static class Position{
		private int x,y,path;
		public Position() {
			
		}
		public Position(int x, int y)
		{
			this.x=x;
			this.y=y;
			this.path=path;
		}
		public Position(int x, int y,int path)
		{
			this.x=x;
			this.y=y;
			this.path=path;
		}
		
		public int getPath() {
			return path;
		}
		public void setPath(int path) {
			this.path = path;
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
		
		for(int i=0;i<N;i++)
		{
			strtok = new StringTokenizer(b.readLine()," ");
			for(int j=0;j<M;j++)
			{
				graph[i][j] = Integer.parseInt(strtok.nextToken());
				
				if(graph[i][j] == 0)
					visited[i][j] = false;
				else if(graph[i][j] == 1)
					visited[i][j] = true;
				else if(graph[i][j] == 2)
				{
					q.offer(new Position(i,j));
					visited[i][j] = true;
				}
					
			}
		}
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				if(graph[i][j]==0)//첫번째 벽놓기
				{
					graph[i][j]=1;
					
					for(int n=0;n<N;n++)
					{
						for(int m=0;m<M;m++)
						{
							if(graph[n][m]==0)//두번째 벽놓기
						}
					}
				}
			}
		}
		
		while(!q.isEmpty())
		{
			Position pos = q.poll();
			
			if(pos.getX()!=0)//상
			{
				if(graph[pos.getX()-1][pos.getY()]==0 &&!visited[pos.getX()-1][pos.getY()])
				{
					q.offer(new Position(pos.getX()-1,pos.getY()));
					graph[pos.getX()-1][pos.getY()] = 2;
					visited[pos.getX()-1][pos.getY()] = true;
				}
			}
			
			if(pos.getX()!=N-1)//하
			{
				if(graph[pos.getX()+1][pos.getY()]==0 &&!visited[pos.getX()+1][pos.getY()])
				{
					q.offer(new Position(pos.getX()+1,pos.getY()));
					graph[pos.getX()+1][pos.getY()] = 2;
					visited[pos.getX()+1][pos.getY()] = true;
				}
			}
			
			if(pos.getY()!=0)//좌
			{
				if(graph[pos.getX()][pos.getY()-1]==0 &&!visited[pos.getX()][pos.getY()-1])
				{
					q.offer(new Position(pos.getX(),pos.getY()-1));
					graph[pos.getX()][pos.getY()-1] = 2;
					visited[pos.getX()][pos.getY()-1] = true;
				}
			}
			
			if(pos.getY()!=M-1)//우
			{
				if(graph[pos.getX()][pos.getY()+1]==0 &&!visited[pos.getX()][pos.getY()+1])
				{
					q.offer(new Position(pos.getX(),pos.getY()+1));
					graph[pos.getX()][pos.getY()+1] = 2;
					visited[pos.getX()][pos.getY()+1] = true;
				}
			}
		}
		
		int count=0;
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				System.out.print(graph[i][j]+" " );
				//if(graph[i][j]==0)
					//count++;
			}
			System.out.println();
		}
		//System.out.println(count);
		
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
					q.offer(new Position(pos.getX()+1,pos.getY(), pos.getPath()+1));
					visited[pos.getX()+1][pos.getY()] = true;
					days.add(pos.getPath()+1);
				}
			}
			if(pos.getY()!=M)
			{
				if(graph[pos.getX()][pos.getY()+1]==0 && !visited[pos.getX()][pos.getY()+1])
				{
					q.offer(new Position(pos.getX(), pos.getY()+1, pos.getPath()+1));
					visited[pos.getX()][pos.getY()+1] = true;
					days.add(pos.getPath()+1);
				}
			}
			
			if(pos.getX()!=1)
			{
				if(graph[pos.getX()-1][pos.getY()]==0 && !visited[pos.getX()-1][pos.getY()])
				{	
					q.offer(new Position(pos.getX()-1, pos.getY(), pos.getPath()+1));
					visited[pos.getX()-1][pos.getY()] = true;
					days.add(pos.getPath()+1);
				}
			}
			if(pos.getY()!=1)
			{
				if(graph[pos.getX()][pos.getY()-1]==0 && !visited[pos.getX()][pos.getY()-1])
				{
					q.offer(new Position(pos.getX(), pos.getY()-1, pos.getPath()+1));
					visited[pos.getX()][pos.getY()-1] = true;
					days.add(pos.getPath()+1);
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
				System.out.println(pos.path);
				return;
			}
			
			if(pos.getX()!=N)
			{
				if(graph[pos.getX()+1][pos.getY()]==1 && !visited[pos.getX()+1][pos.getY()])
				{	
					q.offer(new Position(pos.getX()+1,pos.getY(),pos.path+1));
					visited[pos.getX()+1][pos.getY()] = true;
				}
			}
			if(pos.getY()!=M)
			{
				if(graph[pos.getX()][pos.getY()+1]==1 && !visited[pos.getX()][pos.getY()+1])
				{
					q.offer(new Position(pos.getX(), pos.getY()+1, pos.path+1));
					visited[pos.getX()][pos.getY()+1] = true;
				}
			}
			
			if(pos.getX()!=1)
			{
				if(graph[pos.getX()-1][pos.getY()]==1 && !visited[pos.getX()-1][pos.getY()])
				{	
					q.offer(new Position(pos.getX()-1, pos.getY(), pos.path+1));
					visited[pos.getX()-1][pos.getY()] = true;
				}
			}
			if(pos.getY()!=1)
			{
				if(graph[pos.getX()][pos.getY()-1]==1 && !visited[pos.getX()][pos.getY()-1])
				{
					q.offer(new Position(pos.getX(), pos.getY()-1, pos.path+1));
					visited[pos.getX()][pos.getY()-1] = true;
				}
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
