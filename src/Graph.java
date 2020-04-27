import java.awt.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Graph {

	public Graph() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	static class Position{
		int x,y,z,distance;
		
		int dir = 0;
		int max, min;
		ArrayList<Integer> path;
		ArrayList<Position> graph_path = new ArrayList<>();
		HashSet set = new HashSet<>();
		
		public Position() {
			
		}
		
		public Position(int x)
		{
			this.x=x;
			
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
	
	static void bj9205() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int T = Integer.parseInt(b.readLine());
		
		for(int t=0;t<T;t++)
		{
			int N = Integer.parseInt(b.readLine());//편의점 개수
			
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			int x = Integer.parseInt(strtok.nextToken());
			int y = Integer.parseInt(strtok.nextToken());
			
			//처음엔 맥주 20병, 1병당 50m => 20병 1000m
			
			ArrayList<Festival> map = new ArrayList<>();
			
			Queue<Festival> q = new LinkedList<>();
			
			//상근이네 집
			map.add(new Festival(x, y, 20));
			q.offer(map.get(0));
			
			for(int i=0;i<N;i++)
			{
				strtok = new StringTokenizer(b.readLine());
				x = Integer.parseInt(strtok.nextToken());
				y = Integer.parseInt(strtok.nextToken());
				map.add(new Festival(x, y));
			}
			
			strtok = new StringTokenizer(b.readLine());
			x = Integer.parseInt(strtok.nextToken());
			y = Integer.parseInt(strtok.nextToken());
			Festival festival = new Festival(x, y);
			map.add(festival);
			
			boolean visited[] = new boolean[map.size()];
			
			visited[0] = true;
			boolean isHappy = false;
			
			while(!q.isEmpty())
			{
				Festival now = q.poll();
				x = now.x;
				y = now.y;
				int beer = now.beer;
				
				if(x==festival.x && y==festival.y)
				{
					System.out.println("happy");
					isHappy = true;
					break;
				}
				
				for(int i=0;i<map.size();i++)
				{
					Festival next = map.get(i);

					if(!visited[i])
					{
						int go_distance = beer * 50;//현재 갈 수 있는 거리
						int distance = Math.abs(x - next.x) + Math.abs(y - next.y);
						
						if(distance <= go_distance)//현재 맥주로 갈 수있으면
						{
							visited[i] = true;
							next.beer = 20;//맥주 삼
							q.offer(next);
						}
					}
				}
			}
			if(!isHappy)
				System.out.println("sad");
		}
	}
	
	static class Festival{
		int x, y;
		int beer;
		
		public Festival()
		{
			
		}
		public Festival(int x, int y)
		{
			this.x = x;
			this.y = y;
			
		}
		public Festival(int x, int y, int beer)
		{
			this.x = x;
			this.y = y;
			this.beer = beer;
		}
	}
	
	static void bj14502_2() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		int map[][] = new int[N][M];
		
		ArrayList<Position> zeros = new ArrayList<>();
		ArrayList<Position> starts = new ArrayList<>();
		
		for(int i=0;i<N;i++)
		{
			strtok = new StringTokenizer(b.readLine()," ");
			for(int j=0;j<M;j++)
			{
				map[i][j] = Integer.parseInt(strtok.nextToken());
				
				if(map[i][j] == 2)//바이러스 위치
				{
					starts.add(new Position(i,j));
				}
				else if(map[i][j] == 0)//벽 세울 수 있는 공간 위치
				{
					zeros.add(new Position(i, j));
				}
					
			}
		}
		int[] max = {Integer.MIN_VALUE};
		
		A_bj14502(0, 0, map, N, M, starts, zeros, max);
		
		System.out.println(max[0]);
		
		
	}
	
	static void A_bj14502(int depth, int index, int map[][], int N, int M,ArrayList<Position> starts, ArrayList<Position> zeros ,int[] max)
	{
		//벽 위치 선정 완료 -> bfs 탐색으로 바이러스 퍼뜨림
		if(depth == 3)
		{
			boolean visited[][] = new boolean[N][M];
			
			Queue<Position> q = new LinkedList<>(starts);
			
			for(int i=0;i<starts.size();i++)
				visited[starts.get(i).x][starts.get(i).y] = true;
			
			while(!q.isEmpty())
			{
				Position p = q.poll();
				int x = p.x;
				int y = p.y;
				
				int x2 = 0;
				int y2 = 0;
				
				if(x!=0)
				{
					x2 = x-1;
					y2 = y;
					
					if(!visited[x2][y2] && map[x2][y2] == 0)
					{
						map[x2][y2] = 2;
						visited[x2][y2] = true;
						Position next = new Position(x2, y2);
						q.offer(next);
					}
				}
				if(x!=N-1)
				{
					x2 = x+1;
					y2 = y;
					
					if(!visited[x2][y2] && map[x2][y2] == 0)
					{
						map[x2][y2] = 2;
						visited[x2][y2] = true;
						Position next = new Position(x2, y2);
						q.offer(next);
					}
				}
				if(y!=0)
				{
					x2 = x;
					y2 = y-1;
					
					if(!visited[x2][y2] && map[x2][y2] == 0)
					{
						map[x2][y2] = 2;
						visited[x2][y2] = true;
						Position next = new Position(x2, y2);
						q.offer(next);
					}
				}
				if(y!=M-1)
				{
					x2 = x;
					y2 = y+1;
					
					if(!visited[x2][y2] && map[x2][y2] == 0)
					{
						map[x2][y2] = 2;
						visited[x2][y2] = true;
						Position next = new Position(x2, y2);
						q.offer(next);
					}
				}
			}
			
			max[0] = Math.max(max[0], countSafeArea(map, N, M));
			
		}
		else
		{
			//벽 3개 위치 선정
			for(int k=index;k<zeros.size();k++)
			{
				int map2[][] = new int[N][M];
				for(int i=0;i<N;i++)
					map2[i] = map[i].clone();
				
				map2[zeros.get(k).x][zeros.get(k).y] = 1;
				A_bj14502(depth+1, k+1, map2, N, M, starts, zeros, max);
			}
		}
	}
	
	static int countSafeArea(int map[][],int N,int M)
	{
		int count=0;
		for(int i=0;i<N;i++)
			for(int j=0;j<M;j++)
			{
				if(map[i][j] == 0)
					count++;
			}
		return count;
	}
	
	static void bj2589() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		char map[][] = new char[N][M];
		boolean visited[][] = new boolean[N][M];

		for(int i=0;i<N;i++)
		{
			String str = b.readLine();
			for(int j=0;j<M;j++)
				map[i][j] = str.charAt(j);
		}
		
		
		int result_max = Integer.MIN_VALUE;
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				if(map[i][j] == 'L')//새로운 섬 발견
				{
					int max = Integer.MIN_VALUE;
					Queue<Position> q = new LinkedList<>();
					Position p = new Position(i, j);
					visited = new boolean[N][M];
					visited[i][j] = true;
					p.distance = 0;
					q.offer(p);
					
					while(!q.isEmpty())
					{
						Position now = q.poll();
						int x = now.x;
						int y = now.y;
						int distance = now.distance;
					
                        max = Math.max(max, distance);
 		
						int x2=x;
						int y2=y;
						
						if(x!=0)
						{
							x2 = x-1;
							y2 = y;
							if(map[x2][y2]=='L' && !visited[x2][y2])
							{
								
								visited[x2][y2] = true;
								Position next = new Position(x2, y2);
								next.distance = distance+1;
								q.offer(next);
							}
						}
						if(x!=N-1)
						{
							x2 = x+1;
							y2 = y;
							if(map[x2][y2]=='L' && !visited[x2][y2])
							{
								
								visited[x2][y2] = true;
								Position next = new Position(x2, y2);
								next.distance = distance+1;
								q.offer(next);
							}
						}
						if(y!=0)
						{
							x2 = x;
							y2 = y-1;
							if(map[x2][y2]=='L' && !visited[x2][y2])
							{
								
								visited[x2][y2] = true;
								Position next = new Position(x2, y2);
								next.distance = distance+1;
								q.offer(next);
							}
						}
						if(y!=M-1)
						{
							x2 = x;
							y2 = y+1;
							if(map[x2][y2]=='L' && !visited[x2][y2])
							{
								
								visited[x2][y2] = true;
								Position next = new Position(x2, y2);
								next.distance = distance+1;
								q.offer(next);
							}
						}
						
					}
					result_max = Math.max(result_max, max);
					
				}
			}
		}

		
		System.out.println(result_max);
	}
	
	static void bj5014() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		int F = Integer.parseInt(strtok.nextToken());//건물의 총 층 개
		int S = Integer.parseInt(strtok.nextToken());//지금 있는 층(현재위치)
		int G = Integer.parseInt(strtok.nextToken());//스타트링크 위치(목표)
		int U = Integer.parseInt(strtok.nextToken());//한 번에 위로 가는 층 개수
		int D = Integer.parseInt(strtok.nextToken());//한 번에 밑으로 가는 층 개수
		
		boolean visited[] = new boolean[F+1];
		Queue<Position> q = new LinkedList<>();
		visited[S] = true;
		Position p = new Position(S);
		p.distance = 0;
		q.offer(p);
		
		while(!q.isEmpty())
		{
			Position now = q.poll();
			int level = now.x;
			int distance = now.distance;
			
			if(level == G)//목표 도달
			{
				System.out.println(distance);
				return;
			}
			
			//위로가기
			if(level + U <= F)
			{
				if(!visited[level + U])
				{
					visited[level + U] = true;
					Position next = new Position(level + U);
					next.distance = distance + 1;
					q.offer(next);
				}
				
			}
			
			//밑으로가기
			if(level - D > 0)
			{
				if(!visited[level - D])
				{
					visited[level - D] = true;
					Position next = new Position(level - D);
					next.distance = distance + 1;
					q.offer(next);
				}
				
			}
		}
		System.out.println("use the stairs");
		
	}
	
	static void bj2251() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		int A = Integer.parseInt(strtok.nextToken());
		int B = Integer.parseInt(strtok.nextToken());
		int C = Integer.parseInt(strtok.nextToken());
	
		boolean visited[][][] = new boolean[A+1][B+1][C+1];
		
		Queue<Position> q = new LinkedList<>();
		
		visited[0][0][C] = true;
		q.offer(new Position(0, 0, C));
		
		HashSet result = new HashSet<>();
		
		while(!q.isEmpty())
		{
			Position now = q.poll();
			int A_now = now.x;
			int B_now = now.y;
			int C_now = now.z;
			
			if(A_now==0)
				result.add(C_now);
			
			int A_next = 0;
			int B_next = 0;
			int C_next = 0;
		
			
			if(A_now!=0)
			{
				//A->B로
				if(A_now + B_now > B)//넘치면
				{
					A_next = A_now + B_now - B;
					B_next = B;
					C_next = C_now;
				}
				else//안넘치는 경우 
				{
					A_next = 0;
					B_next = A_now + B_now;
					C_next = C_now;
				}
				
				if(!visited[A_next][B_next][C_next])
				{
					visited[A_next][B_next][C_next] = true;
					q.offer(new Position(A_next, B_next, C_next));
				}
				
				//A->C로
				if(A_now + C_now > C)//넘치면
				{
					A_next = A_now + C_now - C;
					B_next = B_now;
					C_next = C;
				}
				else//안넘치는 경우 
				{
					A_next = 0;
					B_next = B_now;
					C_next = A_now + C_now;
				}
				
				if(!visited[A_next][B_next][C_next])
				{
					visited[A_next][B_next][C_next] = true;
					q.offer(new Position(A_next, B_next, C_next));
				}
			}
			if(B_now!=0)
			{
				//B->A로
				if(B_now + A_now > A)//넘치면
				{
					A_next = A;
					B_next = B_now + A_now - A;
					C_next = C_now;
				}
				else//안넘치는 경우 
				{
					A_next = B_now + A_now;
					B_next = 0;
					C_next = C_now;
				}
				
				if(!visited[A_next][B_next][C_next])
				{
					visited[A_next][B_next][C_next] = true;
					q.offer(new Position(A_next, B_next, C_next));
				}
				
				//B->C로
				if(B_now + C_now > C)//넘치면
				{
					A_next = A_now;
					B_next = B_now + C_now - C;
					C_next = C;
				}
				else//안넘치는 경우 
				{
					A_next = A_now;
					B_next = 0;
					C_next = B_now + C_now;
				}
				
				if(!visited[A_next][B_next][C_next])
				{
					visited[A_next][B_next][C_next] = true;
					q.offer(new Position(A_next, B_next, C_next));
				}
			}
			if(C_now!=0)
			{
				//C->A로
				if(C_now + A_now > A)//넘치면
				{
					A_next = A;
					B_next = B_now;
					C_next = C_now + A_now - A;
				}
				else//안넘치는 경우 
				{
					A_next = C_now + A_now;
					B_next = B_now;
					C_next = 0;
				}
				
				if(!visited[A_next][B_next][C_next])
				{
					visited[A_next][B_next][C_next] = true;
					q.offer(new Position(A_next, B_next, C_next));
				}
				
				//C->B로
				if(C_now + B_now > B)//넘치면
				{
					A_next = A_now;
					B_next = B;
					C_next = C_now + B_now - B;
				}
				else//안넘치는 경우 
				{
					A_next = A_now;
					B_next = C_now + B_now;
					C_next = 0;
				}
				
				if(!visited[A_next][B_next][C_next])
				{
					visited[A_next][B_next][C_next] = true;
					q.offer(new Position(A_next, B_next, C_next));
				}
			}
		}
		
		
		Object o[] = result.toArray();
		Arrays.sort(o);
		for(int i=0;i<o.length;i++)
		System.out.print((int)o[i]+" ");
	}
	
	static void bj9372() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int T = Integer.parseInt(b.readLine());
		
		for(int t=0;t<T;t++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			int N = Integer.parseInt(strtok.nextToken());//도시 개수
			int M = Integer.parseInt(strtok.nextToken());//비행기 개수
			
			
			ArrayList<ArrayList<Integer>> airplane = new ArrayList<>();
			
			for(int i=0;i<=N;i++)
				airplane.add(new ArrayList<>());
			
			int A=0;
			int B=0;
			for(int i=0;i<M;i++)
			{
				strtok = new StringTokenizer(b.readLine());
				A = Integer.parseInt(strtok.nextToken());
				B = Integer.parseInt(strtok.nextToken());
				
				airplane.get(A).add(B);
				airplane.get(B).add(A);
			}
			
		
				Queue<Position> q = new LinkedList<>();
				boolean visited[][] = new boolean[N+1][N+1];//해당 비행기 탑승여부
				
				for(int i=0;i<airplane.get(A).size();i++)
				{
					B = airplane.get(A).get(i);
					visited[A][B] = true;
					Position p = new Position(A,B);
					p.distance = 1;
					p.graph_path.add(new Position(A, B));
					p.set.add(A);
					p.set.add(B);
					q.offer(p);
				}
				
				
				
				while(!q.isEmpty())
				{
					Position now = q.poll();
					int country = now.y;
					int distance = now.distance;
					HashSet set = now.set;
					
					if(set.size()==N)//모든 국가 방문햇을 경우
					{
						System.out.println(distance);
						for(int i=0;i<now.graph_path.size();i++)
						{
							System.out.println(now.graph_path.get(i).x+" to "+now.graph_path.get(i).y);
						}
						break;
					}
					
					for(int j=0;j<airplane.get(country).size();j++)
					{
						int next_country = airplane.get(country).get(j);
						
						if(!visited[country][next_country])//탑승 안한 비행기인 경우
						{
							
							//visited[next_country][country] = true;
							Position next = new Position(country,next_country);
							if(!visited[next_country][country])
							{	
								next.distance = distance+1;
								System.out.println("count : "+country+" to "+next_country); 
							}
							else
								next.distance = distance;
							visited[country][next_country] = true;
							next.graph_path.addAll(now.graph_path);
							next.graph_path.add(new Position(country, next_country));
							next.set.addAll(now.set);
							next.set.add(country);
							next.set.add(next_country);
							q.offer(next);
						}
					}
				}	
		
		}
		
	}
	
	static void bj1726() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		int map[][] = new int[N+1][M+1];
		boolean visited[][][] = new boolean[N+1][M+1][5]; //동서남북 방향도 따로 방문여부체크
		
		for(int i=1;i<=N;i++)
		{
			strtok = new StringTokenizer(b.readLine());
			
			for(int j=1;j<=M;j++)
			{
				map[i][j] = Integer.parseInt(strtok.nextToken());
			}
		}
		
		strtok = new StringTokenizer(b.readLine());
		Position start = new Position(Integer.parseInt(strtok.nextToken()), Integer.parseInt(strtok.nextToken()));
		start.dir = Integer.parseInt(strtok.nextToken());
		start.distance = 0;//명령횟수
		
		strtok = new StringTokenizer(b.readLine());
		Position finish = new Position(Integer.parseInt(strtok.nextToken()), Integer.parseInt(strtok.nextToken()));
		finish.dir = Integer.parseInt(strtok.nextToken());
		
		Queue<Position> q = new LinkedList<>();
		visited[start.x][start.y][start.dir] = true;
		q.offer(start);
		
		//1234 동서남북
		while(!q.isEmpty())
		{
			Position now = q.poll();
			int distance = now.distance;
			int dir = now.dir;
			int x = now.x;
			int y = now.y;
			
			if(x==finish.x && y == finish.y && now.dir == finish.dir)
			{
				System.out.println(now.distance);
				return;
			}
			
			for(int i=1;i<=4;i++)
			{
				int order = order_count(dir, i);
				if(!visited[x][y][i] && order == 1)//방향 바꿀 때마다 방문여부 체크
				{
					Position next = new Position(x, y);
					next.distance = distance + order;
					next.dir = i;
					visited[next.x][next.y][next.dir] = true;
					q.offer(next);
				}
					
			}
			
			//해당 방향으로 거리 이동 여부
			for(int j=1;j<=3;j++)
			{
				Position next = go_k(now, j, N, M, visited, map);
				if(next != null)
				{
					visited[next.x][next.y][next.dir] = true;
					q.offer(next);
				}
			}
		}
	}
	
	static int order_count(int origin, int change)
	{
		if(change==origin)//같음
			return 0;
		else if(change + origin == 3 || change + origin == 7)
			return 2;
		else
			return 1;
		
	}
	
	static Position go_k (Position now, int k, int N, int M,boolean[][][] visited, int[][] map)
	{
		if(now.dir == 1)//동
		{
			if(now.y + k <= M)
			{
				if(!visited[now.x][now.y+k][now.dir])
				{
					//중간에 벽있는 지 검사
					for(int i=1;i<=k;i++)
					{
						if(map[now.x][now.y+i]==1)
							return null;
					}
					Position p = new Position(now.x, now.y+k);
					p.dir = now.dir;
					p.distance = now.distance+1;
					return p;
				}
				else
					return null;
			}
			else
				return null;
			
		}
		else if(now.dir == 2)//서
		{
			if(now.y - k > 0)
			{
				if(!visited[now.x][now.y-k][now.dir])
				{
					for(int i=1;i<=k;i++)
					{
						if(map[now.x][now.y-i]==1)
							return null;
					}
					Position p = new Position(now.x, now.y-k);
					p.dir = now.dir;
					p.distance = now.distance+1;
					return p;
				}
				else
					return null;
			}
			else
				return null;
		}
		else if(now.dir == 3)//남
		{
			if(now.x + k <= N)
			{
				if(!visited[now.x+k][now.y][now.dir])
				{
					for(int i=1;i<=k;i++)
					{
						if(map[now.x+i][now.y]==1)
							return null;
					}
					Position p = new Position(now.x+k, now.y);
					p.dir = now.dir;
					p.distance = now.distance+1;
					return p;
				}
				else
					return null;
			}
			else
				return null;
		}
		else//북
		{
			if(now.x - k > 0)
			{
				if(!visited[now.x-k][now.y][now.dir])
				{
					for(int i=1;i<=k;i++)
					{
						if(map[now.x-i][now.y]==1)
							return null;
					}
					Position p = new Position(now.x-k, now.y);
					p.dir = now.dir;
					p.distance = now.distance+1;
					return p;
				}
				else
					return null;
			}
			else
				return null;
		}
	}
	
	
	
	static void bj1939() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		ArrayList<ArrayList<Edge>> map = new ArrayList<>();
		
		for(int i=0;i<=N;i++)
		{
			map.add(new ArrayList<>());
		}
		
		int max_weight = Integer.MIN_VALUE;
		int min_weight = Integer.MAX_VALUE;
		for(int i=0;i<M;i++)
		{
			strtok = new StringTokenizer(b.readLine());
			
			int A = Integer.parseInt(strtok.nextToken());
			int B = Integer.parseInt(strtok.nextToken());
			int C = Integer.parseInt(strtok.nextToken());
			
			map.get(A).add(new Edge(B, C));
			map.get(B).add(new Edge(A, C));
			
			max_weight = Math.max(max_weight, C);
			min_weight = Math.min(min_weight, C);
		}
		
		strtok = new StringTokenizer(b.readLine());
		int start = Integer.parseInt(strtok.nextToken());
		int finish = Integer.parseInt(strtok.nextToken());
		
		
	
		int left = min_weight;
		int right = max_weight;
		
		int result = Integer.MIN_VALUE;
		while(left <= right)
		{
			int mid = (left + right)/2;
			boolean isFind = false;
		
			boolean visited[] = new boolean[N+1];
			
			Queue<Integer> q = new LinkedList<Integer>();
			q.offer(start);
			visited[start] = true;
			while(!q.isEmpty())
			{
				int now = q.poll();
				
				if(now == finish)
				{
					isFind = true;
					break;
				}
				
				for(int i=0;i<map.get(now).size();i++)
				{
					int next = map.get(now).get(i).to;
					if(!visited[next] && map.get(now).get(i).weight >= mid)
					{
						visited[next] = true;
						q.offer(next);
					}
				}
				
			}
			
			if(isFind)//mid = weight 로도 통과해서 갈수 있으면 더 큰값 집어 넣어봄
			{
				left = mid + 1;
				result = Math.max(result, mid);
			}
			else//mid = weight 로는 못통과하면 그거보다 더 작은값 집어 넣어봄
			{
				right = mid - 1;
			}
		}
		System.out.println(result);//그중 가장 큰 값 출력
	}
	static class Edge{
		int to;
		int weight;
		public Edge(int to, int weight)
		{
			this.to = to;
			this.weight = weight;
		}
		public Edge()
		{
			
		}
	}
	
	
	static void bj1613_2() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
		int n = Integer.parseInt(strtok.nextToken());//사건개수(정점)
		int k = Integer.parseInt(strtok.nextToken());//사건관계개수(간선)
		
		int graph[][] = new int[n+1][n+1];
		
		boolean visited[] = new boolean[n+1];
		
		for(int i=1;i<=n;i++)
		{	
			for(int j=1;j<=n;j++)
			{
				if(i==j)
					graph[i][j] = 0;
				else
					graph[i][j] = 100000;//무한
			}
			visited[i] = false;
		}
		
		for(int i=0;i<k;i++)
		{
			strtok = new StringTokenizer(b.readLine()," ");
			int x = Integer.parseInt(strtok.nextToken());
			int y = Integer.parseInt(strtok.nextToken());
			
			graph[x][y] = 1;
		}
		
		//플로이드 와샬 알고리즘
		for(int l=1;l<=n;l++)
		{
			//모든 정점이 l을 거쳐감
			for(int i=1;i<=n;i++)
			{
				for(int j=1;j<=n;j++)
				{
					if(graph[i][j] > graph[i][l] + graph[l][j])
						graph[i][j] = graph[i][l] + graph[l][j];
				}
			}
		}
		
		
		int s = Integer.parseInt(b.readLine());//관계를 알고싶은 사건쌍의 개수
		
		for(int i=0;i<s;i++)
		{
			strtok = new StringTokenizer(b.readLine()," ");
			int x = Integer.parseInt(strtok.nextToken());
			int y = Integer.parseInt(strtok.nextToken());
			
			if(graph[x][y] == 100000 && graph[y][x] == 100000)//0
				System.out.println(0);
			else if(graph[x][y] != 100000 && graph[y][x] == 100000)//x가 먼저 일어남 : -1
				System.out.println(-1);
			else
				System.out.println(1);
		}
		
		
	}
	
	static void bj1613() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
		int n = Integer.parseInt(strtok.nextToken());//사건개수(정점)
		int k = Integer.parseInt(strtok.nextToken());//사건관계개수(간선)
		
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		ArrayList<ArrayList<Integer>> graph_reverse = new ArrayList<>();
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		
		
		boolean visited[] = new boolean[n+1];
		
		for(int i=0;i<=n;i++)
		{	
			graph.add(new ArrayList<>());
			graph_reverse.add(new ArrayList<>());
			result.add(new ArrayList<>());
			

			visited[i] = false;
		}
		
		for(int i=0;i<k;i++)
		{
			strtok = new StringTokenizer(b.readLine()," ");
			int x = Integer.parseInt(strtok.nextToken());
			int y = Integer.parseInt(strtok.nextToken());
			
			graph.get(x).add(y);
			graph_reverse.get(y).add(x);
			
		}
		Queue<Position> q = new LinkedList<>();
		
		for(int i=1;i<=n;i++)
		{
			if(graph_reverse.get(i).size()==0)
			{
				Position p = new Position(i);
				p.path = new ArrayList<>();
				p.path.add(i);
				q.offer(p);
				visited[i] = true;
			}
				
		}
		
		int s = Integer.parseInt(b.readLine());//관계를 알고싶은 사건쌍의 개수
		
		while(!q.isEmpty())
		{
			Position pos = q.poll();
			int x = pos.x;
			
			for(int i=0;i<graph_reverse.get(x).size();i++)
			{
				int former = graph_reverse.get(x).get(i);
				for(int j=0;j<result.get(former).size();j++)
				{
					if(!result.get(x).contains(result.get(former).get(j)))
							result.get(x).add(result.get(former).get(j));
				}
			}
			
			for(int i=0;i<graph.get(x).size();i++)
			{
				int next = graph.get(x).get(i);
				
				for(int j=0;j<result.get(x).size();j++)
				{
					if(!result.get(next).contains(result.get(x).get(j)))
					{
						result.get(next).add(result.get(x).get(j));
					}
				}
				for(int j=0;j<pos.path.size();j++)
				{
					if(!result.get(next).contains(pos.path.get(j)))
					{
						result.get(next).add(pos.path.get(j));
					}
				}
				
				
				if(!visited[next])
				{
					Position p = new Position(next);
					p.path = new ArrayList<>();
					p.path.addAll(pos.path);
					p.path.add(next);
					q.offer(p);
					visited[next] = true;
				}
			}
		}
		
	/*	for(int i=0;i<start.size();i++)
		{
			System.out.println(start.get(i));
			if(!visited[start.get(i)])
			{
				ArrayList<Integer> temp = new ArrayList<>();
				A_bj1613(start.get(i), n, temp, graph, result, visited);
			}
		}*/
		
		for(int i=0;i<s;i++)
		{
			strtok = new StringTokenizer(b.readLine()," ");
			int[] list = new int[2];
			list[0] = Integer.parseInt(strtok.nextToken());
			list[1] = Integer.parseInt(strtok.nextToken());

			if(result.get(list[0]).contains(list[1]))//1
			{
				System.out.println(1);
			}
			else if(result.get(list[1]).contains(list[0]))//-1
			{
				System.out.println(-1);
			}
			else
				System.out.println(0);
		}
		
	/*	for(int i=1;i<=n;i++)
		{
			System.out.print(i+" : ");
			for(int j=0;j<result.get(i).size();j++)
			{
				System.out.print(result.get(i).get(j)+" ");
			}
			System.out.println();
		}*/
	}
	
	static void A_bj1613(int now, int n, ArrayList<Integer> temp, ArrayList<ArrayList<Integer>> graph,ArrayList<ArrayList<Integer>> result, boolean[] visited)
	{
		visited[now] = true;
		
		temp.add(now);
		for(int i=0;i<graph.get(now).size();i++)
		{
			int next = graph.get(now).get(i);
			
			for(int j=0;j<temp.size();j++)
			{
				if(!result.get(next).contains(temp.get(j)))
					result.get(next).add(temp.get(j));
			}
			
			if(!visited[next])
			{
				ArrayList<Integer> temp2 = new ArrayList<>();
				temp2.addAll(temp);
				A_bj1613(next, n, temp2, graph, result,visited);
			}
		}
		
	}
	
	static void bj6593() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		while(true)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
			
			int L = Integer.parseInt(strtok.nextToken());//층
			int R = Integer.parseInt(strtok.nextToken());
			int C = Integer.parseInt(strtok.nextToken());
			
			if(L == 0 && R == 0 && C == 0)
			{
				break;
			}
			
			char graph[][][] = new char[L][R][C];
			boolean visited[][][] = new boolean[L][R][C];
			
			Position S = new Position();//시작지점
			Position E = new Position();//탈출지점
			
			for(int i=0;i<L;i++)
			{
				for(int j=0;j<R;j++)
				{
					String str = b.readLine();
					for(int k=0;k<C;k++)
					{
						graph[i][j][k] = str.charAt(k);
						visited[i][j][k] = false;
						if(graph[i][j][k] == 'S')
						{
							S.x = i;
							S.y = j;
							S.z = k;
						}
						else if(graph[i][j][k] == 'E')
						{
							E.x = i;
							E.y = j;
							E.z = k;
						}
					}
				}
				b.readLine();
			}
			
			Queue<Position> q = new LinkedList<>();
			S.distance=0;
			visited[S.x][S.y][S.z] = true;
			q.offer(S);
			
			boolean isSuccess = false;//탈출여부
			int minute = 0;//탈출시간
			while(!q.isEmpty())
			{
				Position pos = q.poll();
				int x = pos.x;
				int y = pos.y;
				int z = pos.z;
				int distance = pos.distance;
				//System.out.println(x+" "+y+" "+z+" ");
				if(x == E.x && y == E.y && z == E.z) //탈출성공
				{
					isSuccess = true;
					minute = pos.distance;
					break;
				}
				
				if(x!=0)
				{
					if(graph[x-1][y][z] != '#' && !visited[x-1][y][z])
					{
						Position p = new Position(x-1, y, z);
						p.distance = distance+1;
						visited[x-1][y][z] = true;
						q.offer(p);
					}
				}
				if(x!=L-1)
				{
					if(graph[x+1][y][z] != '#' && !visited[x+1][y][z])
					{
						Position p = new Position(x+1, y, z);
						p.distance = distance+1;
						visited[x+1][y][z] = true;
						q.offer(p);
					}
				}
				if(y!=0)
				{
					if(graph[x][y-1][z] != '#' && !visited[x][y-1][z])
					{
						Position p = new Position(x, y-1, z);
						p.distance = distance+1;
						visited[x][y-1][z] = true;
						q.offer(p);
					}
				}
				if(y!=R-1)
				{
					if(graph[x][y+1][z] != '#' && !visited[x][y+1][z])
					{
						Position p = new Position(x, y+1, z);
						p.distance = distance+1;
						visited[x][y+1][z] = true;
						q.offer(p);
					}
				}
				if(z!=0)
				{
					if(graph[x][y][z-1] != '#' && !visited[x][y][z-1])
					{
						Position p = new Position(x, y, z-1);
						p.distance = distance+1;
						visited[x][y][z-1] = true;
						q.offer(p);
					}
				}
				if(z!=C-1)
				{
					if(graph[x][y][z+1] != '#' && !visited[x][y][z+1])
					{
						Position p = new Position(x, y, z+1);
						p.distance = distance+1;
						visited[x][y][z+1] = true;
						q.offer(p);
					}
				}
			}
			
			if(isSuccess)//탈출성공
				System.out.println("Escaped in "+minute+" minute(s).");
			else//탈출실패
				System.out.println("Trapped!");
		}
	}
	
	static void bj2668() throws Exception //cycle 문제
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		boolean visited[] = new boolean[N+1];
		boolean finish[] = new boolean[N+1];
		
		for(int i=0;i<=N;i++)
		{
			finish[i] = false;
			visited[i] = false;
			graph.add(new ArrayList<>());
		}
		
		for(int i=1;i<=N;i++)//그래프 입력
		{
			graph.get(i).add(Integer.parseInt(b.readLine()));
		}
		
		ArrayList<Integer> result = new ArrayList<>();
		
		
		for(int i=1;i<=N;i++)
		{
			if(!visited[i])
			{
				A_bj2668(i, graph, visited, finish, result);
				
				for(int j=0;j<=N;j++)
				{
					finish[j] = false;
				}
			}
		}
		
		Object[] r = result.toArray();
		Arrays.sort(r);
		System.out.println(r.length);
		for(int i=0;i<r.length;i++)
			System.out.println((int)r[i]);
	}
	
	static void A_bj2668(int now, ArrayList<ArrayList<Integer>> graph, boolean[] visited, boolean[] finish, ArrayList<Integer> result)
	{
		visited[now] = true;
		finish[now] = true;
		
		for(int i=0;i<graph.get(now).size();i++)
		{
			int next = graph.get(now).get(i);
			
			if(!visited[next])
			{
				A_bj2668(next, graph, visited, finish, result);
			}
			else//방문한 적이 있는경우 cycle인지 아닌지 구분
			{
				if(finish[next])//cycle O
				{
					result.add(now);
					for(int j=next; j!=now; j=graph.get(j).get(0))
					{
						if(!result.contains(j))
							result.add(j);
					}
				}
				
			}
		}
	}
	
	static void bj1325() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		ArrayList<ArrayList<Integer>> graph_reverse = new ArrayList<>();
		
		boolean visited[] = new boolean[N+1];
		
		
		for(int i=0;i<=N;i++)
		{
			graph.add(new ArrayList<>());
			graph_reverse.add(new ArrayList<>());
			visited[i] = false;
		}
		
		for(int i=0;i<M;i++)
		{
			strtok = new StringTokenizer(b.readLine()," ");
			
			int A = Integer.parseInt(strtok.nextToken());
			int B = Integer.parseInt(strtok.nextToken());
			
			graph_reverse.get(A).add(B);//역방향 그래프
			graph.get(B).add(A);//B -> A로 갈 수 있음 (A가 B를 신뢰)
		}
		
		int t[] = {0};//시작 시간, 끝난 시간 쟤는 용도
		
		int start[] = new int[N+1], finish[] = new int[N+1], index[] = new int[N+1];
		
		for(int i=1;i<=N;i++)
		{
			if(!visited[i])
			{	
				A_bj1325(i,t, start, finish, index, graph, visited);
			}
		}
	
		
		
		for(int i=0;i<=N;i++)
			visited[i] = false;//초기화
		
		ArrayList<ArrayList<Integer>> component = new ArrayList<ArrayList<Integer>>();//강결합 컴포넌트들
		
		//finish time 느린 순서대로 역방향 그래프에서 dfs 탐색을 통해서 강결합 컴포넌트들 분리
		for(int i=N*2; i>=1;i--)
		{
			int now = 1;
			for(int j=1;j<=N;j++)
			{
				if(finish[j] == i)//finish time 찾기
				{
					now = j;
					if(!visited[j])
					{
						component.add(new ArrayList<Integer>());
						B_bj1325(now, graph_reverse, component, component.size()-1, visited);
					}
					break;
				}
			}
		}
		
		ArrayList<ArrayList<Integer>> graph_for_componet = new ArrayList<ArrayList<Integer>>();//컴포넌트사이의 그래프
		
		for(int i=0;i<component.size();i++)
			graph_for_componet.add(new ArrayList<>());
		
		//컴포넌트사이의 그래프 만들기
		for(int i=1;i<=N;i++)
		{
			int now_component = find_component(i, component);
		
			for(int j=0;j<graph.get(i).size();j++)
			{
				int next_component = find_component(graph.get(i).get(j), component);

				if(!graph_for_componet.get(now_component).contains(next_component) && now_component!=next_component)//중복제거
					graph_for_componet.get(now_component).add(next_component);//그래프에 추가
			}
			
		}
		
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();//각 component가 해킹시킬수 있는 component들의 리스트
		
		visited = new boolean[component.size()];
		
		for(int i=0;i<visited.length;i++)
		{
			result.add(new ArrayList<>());
			visited[i] = false;
		}
		
		for(int i=0;i<component.size();i++)
		{
			if(!visited[i])
			{
				C_bj1325(i, graph_for_componet, visited, result);
			}
		}
		
		int[] result_final = new int[result.size()];//각 component가 해킹 시킬수 있는 computer들의 개수 list
		
		int max_size = 0;
		
		for(int i=0;i<result_final.length;i++)
		{
			result_final[i] = component.get(i).size();
			for(int j=0;j<result.get(i).size();j++)
			{
				result_final[i] += component.get(result.get(i).get(j)).size();
			}

			if(i==0)
			{
				max_size = result_final[i];
			}
			else
			{
				if(max_size < result_final[i])
				{
					max_size = result_final[i];
				}
			}

		}

		
		ArrayList<Integer> end = new ArrayList<>();//최종 결과

		for(int i=0;i<result_final.length;i++)
		{
			if(max_size == result_final[i]) //최대 개수랑 같으면 해당 component의 멤버들 전부 추가
			{
				for(int j=0;j<component.get(i).size();j++)
					end.add(component.get(i).get(j));
			}
		}
		
		Object[] list =end.toArray();
		Arrays.sort(list);
		
		for(int i=0;i<list.length;i++)
			System.out.print((int)list[i] +" ");

		
	}
	
	static int find_component(int member,  ArrayList<ArrayList<Integer>> component)
	{
		for(int i=0;i<component.size();i++)
		{
			if(component.get(i).contains(member))//해당 멤버를 포함하는 component값 리턴
				return i;
		}
		return -1;
	}
	
	static void A_bj1325(int now, int[] t,int[] start, int[] finish, int[] index, ArrayList<ArrayList<Integer>> graph, boolean[] visited)//강결합 컴포넌트 분리를 위한 finish time 찾기
	{
		visited[now] = true;
		
		start[now] = ++t[0];//시작 시간
		
	
		for(int i=0;i<graph.get(now).size();i++)
		{
			int next = graph.get(now).get(i);
			
			if(!visited[next])
			{
				A_bj1325(next, t, start, finish, index, graph, visited);
			}
			
		}
		finish[now] = ++t[0];//끝난시간
		index[now] = now;
	}
	
	static void B_bj1325(int now, ArrayList<ArrayList<Integer>> graph_reverse, ArrayList<ArrayList<Integer>> component, int c_count,boolean[] visited)//역방향 그래프에서 강결합 컴포넌트 분리
	{
		visited[now] = true;
		component.get(c_count).add(now);
		
		for(int i=0;i<graph_reverse.get(now).size();i++)
		{
			int next = graph_reverse.get(now).get(i);
			
			if(!visited[next])
			{
				B_bj1325(next, graph_reverse, component, c_count, visited);
			}
		}
		
	}
	
	static void C_bj1325(int now, ArrayList<ArrayList<Integer>> graph_for_component, boolean[] visited, ArrayList<ArrayList<Integer>> result)
	{
		visited[now] = true;
		
		for(int i=0; i<graph_for_component.get(now).size(); i++)
		{
			int next = graph_for_component.get(now).get(i);
			if(!visited[next])
			{
				C_bj1325(next, graph_for_component, visited, result);
			}
			
			if(!result.get(now).contains(next))
				result.get(now).add(next);
			
			for(int j=0;j<result.get(next).size();j++)
			{
				if(!result.get(now).contains(result.get(next).get(j)))
					result.get(now).add(result.get(next).get(j));
			}
		}
	}
	
	static void bj11559() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = 12,M=6;
		char graph[][] = new char[N][M];
		boolean visited[][] = new boolean[N][M];
		boolean checked[][] = new boolean[N][M];//콤보 처리 된 부분
		
		for(int i=0;i<N;i++)
		{
			String str = b.readLine();
			for(int j=0;j<M;j++)
			{
				graph[i][j] = str.charAt(j);
				visited[i][j] = false;
				checked[i][j] = false;
			}
		}
		
		int combo = 0;//연쇄 횟수
		
		boolean finish = false;//콤보 있는지 여부
		
		while(!finish)
		{
			for(int i=0;i<N;i++)//상->하
			{
				for(int j=0;j<M;j++)//좌->우
				{
					if(graph[i][j] != '.' && !visited[i][j])
					{
						A_bj11559(i, j, N, M, graph[i][j], graph, visited, checked, new ArrayList<>());
			
					}
				}
			}
			
			
			finish = true;
			
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<M;j++)
				{
					visited[i][j] = false;
					if(checked[i][j])
					{
						goDown(i, j, graph);
						checked[i][j] = false;
						finish = false;
					}
				}
			}
			
			if(!finish)//콤보 있는지 확인
				combo++;
		}
		
		
		System.out.println(combo);
		
	}
	
	static void A_bj11559(int x, int y,int N,int M, char color, char graph[][], boolean visited[][], boolean checked[][], ArrayList<Position> temp) 
	{
		visited[x][y] = true;
		char now = graph[x][y];
		
		temp.add(new Position(x,y));//현재 노드 추가
		
		
		if(x!=0)
		{
			if(graph[x-1][y] == color && !visited[x-1][y])
			{

				A_bj11559(x-1, y, N, M, color, graph, visited, checked, temp);
				
			} 
		}
		if(x!=N-1)
		{
			if(graph[x+1][y] == color && !visited[x+1][y])
			{

				A_bj11559(x+1, y, N, M, color, graph, visited, checked, temp);
				
			}
		}
		if(y!=0)
		{
			if(graph[x][y-1] == color && !visited[x][y-1])
			{

				A_bj11559(x, y-1, N, M, color, graph, visited, checked, temp);
				
			}
		}
		if(y!=M-1)
		{
			if(graph[x][y+1] == color && !visited[x][y+1])
			{
				
				A_bj11559(x, y+1, N, M, color, graph, visited, checked, temp);
	
			}
		}
		
		if(temp.size() >= 4)//콤보 성립
		{
			for(int i=0;i<temp.size();i++)
			{
				checked[temp.get(i).x][temp.get(i).y] = true;
			}
			temp.clear();//초기화
		}
	}
	
	static void goDown(int x, int y, char[][] graph)
	{
		for(int i=x;i>=0;i--)
		{
			if(i==0)
				graph[i][y] = '.';
			else
				graph[i][y] = graph[i-1][y];
		}
	}
	
	static void bj9446() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int T = Integer.parseInt(b.readLine());

		for(int t=0;t<T;t++)
		{
			int n = Integer.parseInt(b.readLine());
			
			ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
			boolean visited[] = new boolean[n+1];
			boolean finished[] = new boolean[n+1];
			
			for(int i=0;i<=n;i++)
			{
				visited[i] = false;
				finished[i] = false;
				graph.add(new ArrayList<Integer>());
			}
			
			StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
			for(int i=1;i<=n;i++)
			{
				graph.get(i).add(Integer.parseInt(strtok.nextToken()));
			}
			
			int[] team = new int[1];
			team[0] = 0;
			for(int i=1;i<=n;i++)
			{
				if(!visited[i])
				{
					Queue<Integer> cycle = new LinkedList<>();
					int cnt = 0;
					A_bj9446(i, visited, graph, team ,cycle, cnt,finished);
				}
			}
			System.out.println(n-team[0]);
		}
	
	}
	
	static void A_bj9446(int s, boolean visited[], ArrayList<ArrayList<Integer>> graph, int[] team ,Queue<Integer> cycle,int cnt,boolean[] finished) 
	{
		visited[s] = true;
		cycle.offer(s);
		cnt++;
		for(int i=0;i<graph.get(s).size();i++)
		{
			if(!visited[graph.get(s).get(i)]) // 방문 x -> 계속 탐색
			{
				A_bj9446(graph.get(s).get(i), visited, graph, team, cycle,cnt, finished);
			}
			else//방문 o -> 1. cycle 있음 2. cycle 없음(이미탐색완료)
			{
				if(!finished[graph.get(s).get(i)])
				{
					team[0]++;
					for(int j=graph.get(s).get(i);j != s; j=graph.get(j).get(0))
					{
						team[0]++;
					}
				}
				
			}
		}
		finished[s] = true;
	}
	
	static void bj2416() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		int graph[][] = new int[N][N];
		boolean visited[][] = new boolean[N][N];
		
		Queue<Position> q = new LinkedList<>();
		Queue<Position> q2 = new LinkedList<>();
		
		for(int i=0;i<N;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
			for(int j=0;j<N;j++)
			{
				graph[i][j] = Integer.parseInt(strtok.nextToken());
				if(graph[i][j] ==1 && q.isEmpty())
				{
					q.offer(new Position(i,j));// bfs 탐색을 시작할 섬의 첫 위치
					visited[i][j] = true;
				}
				else
				{
					visited[i][j] = false;
					
				}
			}
		}
		
		
		ArrayList<Integer> bridge = new ArrayList<>();//섬 간의 거리들 저장
		
		while(!q.isEmpty())
		{			
			//1번 bfs	: 섬 탐색 -> visited true처리 및 섬 끝자락 부분의 바다들 큐에 추가
			while(!q.isEmpty())
			{
				Position pos = q.poll();
				int x = pos.getX();
				int y = pos.getY();
						
				if(x!=0)
				{
					if(graph[x-1][y] == 1 && !visited[x-1][y])//해당 섬일경우
					{
						Position p2 = new Position(x-1,y);
						visited[x-1][y] = true;
						q.add(p2);
					}
					else if(graph[x-1][y] == 0 && !visited[x-1][y])//바다일경우
					{
						Position p2 = new Position(x-1,y);
						p2.distance = 1;
						visited[x-1][y] = true;
						q2.add(p2);
					}
				}
				if(x!=N-1)
				{
					if(graph[x+1][y] == 1 && !visited[x+1][y])//해당 섬일경우
					{
						Position p2 = new Position(x+1,y);
						visited[x+1][y] = true;
						q.add(p2);
					}
					else if(graph[x+1][y] == 0 && !visited[x+1][y])//바다일경우
					{
						Position p2 = new Position(x+1,y);
						p2.distance = 1;
						visited[x+1][y] = true;
						q2.add(p2);
					}
				}
				if(y!=0)
				{
					if(graph[x][y-1] == 1 && !visited[x][y-1])//해당 섬일경우
					{
						Position p2 = new Position(x,y-1);
						visited[x][y-1] = true;
						q.add(p2);
					}
					else if(graph[x][y-1] == 0 && !visited[x][y-1])//바다일경우
					{
						Position p2 = new Position(x,y-1);
						p2.distance = 1;
						visited[x][y-1] = true;
						q2.add(p2);
					}
				}
				if(y!=N-1)
				{
					if(graph[x][y+1] == 1 && !visited[x][y+1])//해당 섬일경우
					{
						Position p2 = new Position(x,y+1);
						visited[x][y+1] = true;
						q.add(p2);
					}
					else if(graph[x][y+1] == 0 && !visited[x][y+1])//바다일경우
					{
						Position p2 = new Position(x,y+1);
						p2.distance = 1;
						visited[x][y+1] = true;
						q2.add(p2);
					}
				}
			}
			q.addAll(q2);
			q2.clear();
				
			//2번 bfs : 다른 섬까지의 거리계산
			while(!q.isEmpty())
			{
				Position pos = q.poll();
				int x = pos.getX();
				int y = pos.getY();
						
				if(x!=0)
				{
					if(graph[x-1][y] == 1 && !visited[x-1][y])//다른 섬일경우
					{
						bridge.add(pos.distance);
						if(q2.isEmpty())
						{
							q2.offer(new Position(x-1,y));
							visited[x-1][y] = true;
						}
					}
					else if(graph[x-1][y] == 0 && !visited[x-1][y])//바다일경우
					{
						Position p2 = new Position(x-1,y);
						p2.distance = pos.distance+1;
						visited[x-1][y] = true;
						q.add(p2);
					}
				}
				if(x!=N-1)
				{
					if(graph[x+1][y] == 1 && !visited[x+1][y])//다른 섬일경우
					{
						bridge.add(pos.distance);
						if(q2.isEmpty())
						{
							q2.offer(new Position(x+1,y));
							visited[x+1][y] = true;
						}
					}
					else if(graph[x+1][y] == 0 && !visited[x+1][y])//바다일경우
					{
						Position p2 = new Position(x+1,y);
						p2.distance = pos.distance+1;
						visited[x+1][y] = true;
						q.add(p2);
					}
				}
				if(y!=0)
				{
					if(graph[x][y-1] == 1 && !visited[x][y-1])//다른 섬일경우
					{
						bridge.add(pos.distance);
						if(q2.isEmpty())
						{
							q2.offer(new Position(x,y-1));
							visited[x][y-1] = true;
						}
					}
					else if(graph[x][y-1] == 0 && !visited[x][y-1])//바다일경우
					{
						Position p2 = new Position(x,y-1);
						p2.distance = pos.distance+1;
						visited[x][y-1] = true;
						q.add(p2);
					}
				}
				if(y!=N-1)
				{
					if(graph[x][y+1] == 1 && !visited[x][y+1])//다른 섬일경우
					{
						bridge.add(pos.distance);
						if(q2.isEmpty())
						{
							q2.offer(new Position(x,y+1));
							visited[x][y+1] = true;
						}
					}
					else if(graph[x][y+1] == 0 && !visited[x][y+1])//바다일경우
					{
						Position p2 = new Position(x,y+1);
						p2.distance = pos.distance+1;
						visited[x][y+1] = true;
						q.add(p2);
					}
				}
			}
			
			q.addAll(q2);
			q2.clear();
					
			if(!q.isEmpty())
			{
				for(int x=0;x<N;x++)
				for(int y=0;y<N;y++)
				{
					if(graph[x][y]==0)
						visited[x][y] = false;
				}
			}
		}
		
		int min = bridge.get(0);
		
		for(int i=0;i<bridge.size();i++)
		{
			if(min > bridge.get(i))
				min = bridge.get(i);
		}
		System.out.println(min);
	}
	
	static void A_bj2416(int x, int y, int pivot, int[][] graph, boolean[][] visited, int N, int island_num)
	{
		visited[x][y] = true;
		if(pivot==1)
			graph[x][y] = island_num;

		if(x!=0)
		{
			if(graph[x-1][y] == pivot && !visited[x-1][y])
			{
				A_bj2416(x-1, y, pivot, graph, visited, N, island_num);
			}
		}
		if(x!=N-1)
		{
			if(graph[x+1][y] == pivot && !visited[x+1][y])
			{
				A_bj2416(x+1, y, pivot, graph, visited, N, island_num);
			}
		}
		if(y!=0)
		{
			if(graph[x][y-1] == pivot && !visited[x][y-1])
			{
				A_bj2416(x, y-1, pivot, graph, visited, N, island_num);
			}
		}
		if(y!=N-1)
		{
			if(graph[x][y+1] == pivot && !visited[x][y+1])
			{
				A_bj2416(x, y+1, pivot, graph, visited, N, island_num);
			}
		}
	}
	
	
	static void bj2573() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		int graph[][] = new int[N][M];
		int melt[][] = new int[N][M];
		boolean visited[][] = new boolean[N][M];
		
		int result[] = new int[2];
		for(int i=0;i<N;i++)
		{
			strtok = new StringTokenizer(b.readLine()," ");
			
			for(int j=0;j<M;j++)
			{
				visited[i][j] = false;
				
				graph[i][j] = Integer.parseInt(strtok.nextToken());
				melt[i][j] = graph[i][j];
				if(graph[i][j] != 0)
				{
					result[0] = i;
					result[1] = j;
				}
			}
			
		}
		
		int count=0;
		boolean NoZero=true;
		
		while(NoZero)
		{
			NoZero=false;
			A_bj2573(result[0], result[1], graph, melt, visited, N, M, result);
		
			count++;
			for(int i=0;i<N;i++)
			{	
				for(int j=0;j<M;j++)
				{
					if(graph[i][j] > 0 )
					{
						NoZero = true;
						if(!visited[i][j])
						{
							System.out.println(count-1);
							return;
						}
					}
					
					graph[i][j] = melt[i][j];
					visited[i][j] = false;
				}
			}
		}
		System.out.println(0);
	}
	
	static void A_bj2573(int x, int y,int[][] graph,int[][] melt,boolean[][] visited,int N,int M,int result[])
	{
		visited[x][y] = true;
		
		int count=0;//현재 물
		
		if(x!=0)
		{
			if(graph[x-1][y] != 0 && !visited[x-1][y])
			{
				A_bj2573(x-1, y, graph, melt, visited, N, M,result);
			}
			else if(graph[x-1][y] == 0)
			{
				count++;
			}
		}
		if(x!=N-1)
		{
			if(graph[x+1][y] != 0 && !visited[x+1][y])
			{
				A_bj2573(x+1, y, graph, melt, visited, N, M,result);
			}
			else if(graph[x+1][y] == 0)
			{
				count++;
			}
			
		}
		if(y!=0)
		{
			if(graph[x][y-1] != 0 && !visited[x][y-1])
			{
				A_bj2573(x, y-1, graph, melt, visited, N, M,result);
			}
			else if(graph[x][y-1] == 0)
			{
				count++;
			}
			
		}
		if(y!=M-1)
		{
			if(graph[x][y+1] != 0 && !visited[x][y+1])
			{
				A_bj2573(x, y+1, graph, melt, visited, N, M,result);
			}
			else if(graph[x][y+1] == 0)
			{
				count++;
			}
			
		}
		
		melt[x][y] -= count;
		if(melt[x][y] < 0)
			melt[x][y] = 0;
		
		if(melt[x][y] > 0)
		{
			result[0] = x;
			result[1] = y;
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
		
		int N = Integer.parseInt(b.readLine());
		
		int map[][] = new int[N+1][N+1];
			
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for(int i=1;i<=N;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
			for(int j=1;j<=N;j++)
			{
				map[i][j] = Integer.parseInt(strtok.nextToken());
				max = Math.max(map[i][j], max);
				min = Math.min(map[i][j], min);
			}
		}
		
		int max_subtract = max - min;
		
		int left = 0;
		int right = max_subtract;

		int result = Integer.MAX_VALUE;
		
		while(left < right) 
		{
			int mid = (left + right) / 2;
			//System.out.println(mid);
			
			for(int down = min;down <=mid+min;down++)
			{
				int up = mid + min + down;
			}
			
			Queue<Position> q = new LinkedList<>();
			boolean visited[][] = new boolean[N+1][N+1];
			
			Position p = new Position(1, 1);
			p.max = Math.max(map[1][1], map[N][N]);
			p.min = Math.min(map[1][1], map[N][N]);
			
			q.offer(p);
			visited[1][1] = true;
			
			boolean isPass = false;//pass가 트루면 왼쪽으로, pass가 false면 오른쪽으로
			
			while(!q.isEmpty())
			{
				Position now = q.poll();
				int x = now.x;
				int y = now.y;
				max = now.max;
				min = now.min;
				int subtract = max - min;
				
				//System.out.println("mid : "+mid +" ("+x+", "+y+") "+map[x][y]+" max : "+max +" min : "+min);
				if(x==N && y==N)
				{	
					if(subtract <= mid)
						isPass = true;
					break;
				}
				
				int r[] = {x - 1, x + 1, x, x};
				int c[] = {y, y, y - 1, y + 1};
				
				for(int i=0;i<4;i++)
				{
					if(r[i] > 0 && r[i] < N + 1 && c[i] > 0 && c[i] < N + 1)
					{
						if(!visited[r[i]][c[i]])
						{
							if(max < map[r[i]][c[i]])//max보다 커서 새로운 max가 될경우
							{
								if(Math.abs(mid - subtract) >= Math.abs(mid - (map[r[i]][c[i]] - min)) )//현재 찾고 있는 수보다 차이가 더 작은 상황일 때만 추가
								{
									visited[r[i]][c[i]] = true;
									Position next = new Position(r[i], c[i]);
									next.max = map[r[i]][c[i]];
									next.min = min;
									q.offer(next);
								}
							}
							else if(min > map[r[i]][c[i]])//min보다 작아서 새로운 min 될 경우
							{
								if(Math.abs(mid - subtract) >= Math.abs(mid - (max - map[r[i]][c[i]])) )//mid에 더 가까이 갈 수 있는 경우
								{
									visited[r[i]][c[i]] = true;
									Position next = new Position(r[i], c[i]);
									next.max = max;
									next.min = map[r[i]][c[i]];
									q.offer(next);
								}
							}
							else//max랑 min 사이의 수인 경우 그냥 추가
							{
								visited[r[i]][c[i]] = true;
								Position next = new Position(r[i], c[i]);
								next.max = max;
								next.min = min;
								q.offer(next);
							}
							
							
						}
					}
				}
				
			}
			
			//더 작은값 넣어보기
			if(isPass)
			{
				right = mid - 1;
				//System.out.println("pass: "+mid);
				result = Math.min(mid, result);
			}
			else
			{
				left = mid + 1;
			}
		}
		
		System.out.println(result);
		
		
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
