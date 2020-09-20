import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Samsung {
	
	public Samsung()
	{
		
	}
	
	static class Position{
		int x, y;
		public Position()
		{
			
		}
		
		public Position(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
	
	static class AccessPoint{
		int x,y;
		int C,P;
		
		public AccessPoint()
		{
			
		}

		public AccessPoint(int x, int y, int c, int p) {
			super();
			this.x = x;
			this.y = y;
			C = c;
			P = p;
		}
		
	}
	
	static void s5644() throws Exception {
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(b.readLine());
		
		for(int t=1;t<=T;t++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			
			int M = Integer.parseInt(strtok.nextToken());
			int A = Integer.parseInt(strtok.nextToken());
			
			int move[][] = new int[2][M];
			
			for(int i=0;i<2;i++)
			{
				strtok = new StringTokenizer(b.readLine());
				
				for(int j=0;j<M;j++)
					move[i][j] = Integer.parseInt(strtok.nextToken());
			}
			
			AccessPoint ap[] = new AccessPoint[A];
			
			for(int i=0;i<A;i++)
			{
				strtok = new StringTokenizer(b.readLine());
				int x = Integer.parseInt(strtok.nextToken());
				int y = Integer.parseInt(strtok.nextToken());
				int C = Integer.parseInt(strtok.nextToken());
				int P = Integer.parseInt(strtok.nextToken());
				ap[i] = new AccessPoint(x,y,C,P);
			}
			
			int sum = 0;
			
			for(int k=0;k<=M;k++)
			{
				//충전소 찾기
				
				//충전량 검사
				
			}
		}
		
	}

	
	static class YutMap{
		int value = 0;
		YutMap blue;
		YutMap red;
		boolean isBlue = false;
		ArrayList<Horse> currentHorse = new ArrayList<>();
		
		public YutMap()
		{
			
		}
		public YutMap(int value, boolean isBlue) {
			super();
			this.value = value;
			this.isBlue = isBlue;
		}
		public YutMap(int value,YutMap red ,boolean isBlue) {
			super();
			this.value = value;
			this.isBlue = isBlue;
			this.red = red;
			
		}
		public YutMap(int value, YutMap blue, YutMap red, boolean isBlue) {
			super();
			this.value = value;
			this.blue = blue;
			this.red = red;
			this.isBlue = isBlue;
		}
		
		
	}
	
	static class Horse{
		YutMap position;
		
		public Horse()
		{
			
		}
		
		public Horse(YutMap position)
		{
			this.position = position;
		}
	}
	
	static void bj17825() throws Exception
	{
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		
		int numbers[] = new int[10];
		ArrayList<Horse> horses = new ArrayList<>();
		
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		for(int i=0;i<10;i++)
			numbers[i] = Integer.parseInt(strtok.nextToken());
		
		YutMap start = new YutMap(0, new YutMap(2,false), false);
		YutMap node10 = new YutMap(10, new YutMap(12,false), new YutMap(13,false), true);
		YutMap node20 = new YutMap(20, new YutMap(22,false), new YutMap(22,false), true);
		YutMap node25 = new YutMap(25, new YutMap(30,false), false);
		YutMap node30 = new YutMap(30, new YutMap(32,false), new YutMap(28,false), true);
		
		YutMap finish = new YutMap(0, false);
		YutMap node40 = new YutMap(40, finish, false);
		
		YutMap current = start;
		current = makingNodes(current, 2, 8, 2,false);
		current.red = node10;
		
		current = node10;
		current = makingNodes(current, 12, 18, 2,false);
		current.red = node20;
		
		current = node10;
		current = makingNodes(current, 13, 19, 3, true);
		current.red = node25;
		
		current = node20;
		current = makingNodes(current, 22, 28, 2,false);
		current.red = node30;
		
		current = node20;
		current = makingNodes(current, 22, 24, 2,true);
		current.red = node25;
		
		current = node30;
		current = makingNodes(current, 32, 38, 2,false);
		current.red = node40;
		
		current = node30;
		current = makingNodes(current, 28, 26, -1,true);
		current.red = node25;
		
		current = node25;
		current = makingNodes(current, 30, 35, 5,false);
		current.red = node40;
		
		for(int i=0;i<4;i++)
		{
			horses.add(new Horse(start));
			start.currentHorse.add(horses.get(i));
		}
		
		
		int max[] = {Integer.MIN_VALUE};
		
		recursive(finish, 0, numbers, horses, max, 0);
		System.out.println(max[0]);
	}
	
	static void recursive(YutMap finish, int index, int[] numbers, ArrayList<Horse> horses, int max[], int score)
	{
		if(index == numbers.length)
		{
			max[0] = Math.max(max[0], score);

		}
		else
		{
			int num = numbers[index];
			
			for(int i=0;i<horses.size();i++)
			{
				if(horses.get(i).position != finish)
				{
					YutMap prev = horses.get(i).position;
					YutMap now = horses.get(i).position;
					
					for(int j=0;j<num;j++)
					{
						
						if(j==0 && now.isBlue)
							now = now.blue;
						else
							now = now.red;
						
						if(now == finish)
							break;
						
					}
					
					//해당 위치에 말이없다면, 혹은 finish라면 전진가능
					if(now.currentHorse.isEmpty() || now==finish)
					{
						prev.currentHorse.remove(horses.get(i));
						now.currentHorse.add(horses.get(i));
						score += now.value;
						horses.get(i).position = now;

						recursive(finish, index+1, numbers, horses, max, score);

						horses.get(i).position = prev;
						score -= now.value;
						now.currentHorse.remove(horses.get(i));
						prev.currentHorse.add(horses.get(i));
					}
					
				}
				
				
			}
		}
	}
	
	static YutMap makingNodes(YutMap current, int s, int f, int num,boolean isBlue)
	{
		
		if(isBlue)
			current = current.blue;
		else
			current = current.red;
		if(num > 0)
		{
			for(int i=s;i<=f;i+=num)
			{
				
				if(i!=s)
				current = current.red;
				current.value = i;
				current.isBlue = false;
				current.red = new YutMap();
			}
		}
		else
		{
			for(int i=s;i>=f;i+=num)
			{
				
				if(i!=s)
				current = current.red;
				current.value = i;
				current.isBlue = false;
				current.red = new YutMap();
			}
		}
		
		return current;
	}
	
	
	
	static void bj17779() throws Exception
	{
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(b.readLine());
		
		int map[][] = new int[N+1][N+1];
		int people[] = new int[5];//각 선거구 인구
		
		for(int i=1;i<=N;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			for(int j=1;j<=N;j++)
			{
				map[i][j] = Integer.parseInt(strtok.nextToken());
			}
		}
		
		int min = Integer.MAX_VALUE;
		
		for(int d1 = 1;d1 <= N;d1++)
		{
			for(int d2 = 1;d2 <= N;d2++)
			{
				for(int x=1;x<=N;x++)
				{
					for(int y=1;y<=N;y++)
					{
						int sum = 0;
						boolean[][] visited= new boolean[N+1][N+1];
						
						boolean isPass = true;
						int i=x,j=y;
						while(i<=x+d1 && j >= y-d1)
						{
							if(1<=i && i<=N && 1<=j && j<=N)
							{	
								visited[i][j] = true;
								i++;
								j--;
							}
							else
							{
								isPass = false;
								break;
							}
							
						}
						if(!isPass)
							continue;
						
						i=x;
						j=y;
						while(i<=x+d2 && j <= y+d2)
						{
							if(1<=i && i<=N && 1<=j && j<=N)
							{
								visited[i][j] = true;
								i++;
								j++;
							}
							else
							{
								isPass = false;
								break;
							}	
						}
						if(!isPass)
							continue;
						
						i=x+d1;
						j=y-d1;
						while(i<=x+d1+d2 && j <= y-d1+d2)
						{
							if(1<=i && i<=N && 1<=j && j<=N)
							{
								visited[i][j] = true;
								i++;
								j++;
							}
							else
							{
								isPass = false;
								break;
							}	
						}
						if(!isPass)
							continue;
						
						i=x+d2;
						j=y+d2;
						while(i<=x+d2+d1 && j >= y+d2-d1)
						{
							if(1<=i && i<=N && 1<=j && j<=N)
							{
								visited[i][j] = true;
								i++;
								j--;
							}
							else
							{
								isPass = false;
								break;
							}
						}
						if(!isPass)
							continue;
						
						boolean start = false;
						
						for(i=1;i<=N;i++)
						{
							for(j=1;j<=N;j++)
							{
								if(!start)
								{
									if(!visited[i][j])
									{
										if(1 <= i && i< x+d1 && 1<= j && j <= y)//1번 구역
										{
											people[0] += map[i][j];
										}
										else if(1<= i && i <= x+d2 && y < j && j <= N)//2번 구역
										{
											people[1] += map[i][j];
										}
										else if(x+d1 <= i && i <= N && 1 <= j && j < y-d1+d2)//3번구역
										{		
											people[2] += map[i][j];
										}
										else if(x+d2 < i && i <= N && y-d1+d2 <= j && j <= N)//4번 구역
										{
											people[3] += map[i][j];
										}
										else//5번 구역
										{	
											people[4] += map[i][j];
										}
									}
									else
									{
										if(x < i && i < x+d1+d2)
											start = true;
										
										people[4] += map[i][j];
									}
								}
								else
								{
									if(visited[i][j])
										start = false;
									people[4] += map[i][j];
									
								}
							
							}	
						}
						
						Arrays.sort(people);

						if(people[0] != 0)
							min = Math.min(min, people[4] - people[0]);

						for(i=0;i<5;i++)
							people[i] = 0;
					}
				}
			}
		}
		
		System.out.println(min);
		
	}
	
	
	static class Virus{
		int x,y,time=0;
		public Virus()
		{
			
		}
		public Virus(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		public Virus(int x, int y, int time)
		{
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	
	static void bj17142() throws Exception
	{
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		int map[][] = new int[N+1][N+1];
		
		ArrayList<Virus> viruses = new ArrayList<>();
		
		int pass_count = 0;
		for(int i=1;i<=N;i++)
		{
			strtok = new StringTokenizer(b.readLine());
			for(int j=1;j<=N;j++)
			{
				map[i][j] = Integer.parseInt(strtok.nextToken());
				if(map[i][j] == 2)
				{
					viruses.add(new Virus(i, j));
				}
				else if(map[i][j] == 0)
				{
					pass_count++;
				}
			}
		}
		

		ArrayList<Virus> pos = new ArrayList<>();
		int min[] = {Integer.MAX_VALUE};
		
		recursive_17142(0, M, N, min, viruses, map, pos, pass_count);
		
		if(min[0] == Integer.MAX_VALUE || min[0] == Integer.MIN_VALUE)
			System.out.println(-1);
		else
			System.out.println(min[0]);
	}
	
	static void recursive_17142(int index, int depth, int N,int min[],ArrayList<Virus> viruses, int[][] map ,ArrayList<Virus> pos, int pass_count)
	{
		if(depth == 0)
		{
			Queue<Virus> q = new LinkedList<>(pos);
			
			boolean visited[][] = new boolean[N+1][N+1];
			
			for(int i=0;i<pos.size();i++)
				visited[pos.get(i).x][pos.get(i).y] = true;
			
			int r[] = {-1,1,0,0};
			int c[] = {0,0,-1,1};
			
			int finish_time = 0;
			int count = 0;
			
			//바이러스 퍼뜨리기
			while(!q.isEmpty())
			{
				Virus v = q.poll();
				
				if(map[v.x][v.y] == 0)
				{	
					finish_time = Math.max(finish_time, v.time);
					count++;
				}
				
				for(int i=0;i<4;i++)
				{
					int x2 = v.x + r[i];
					int y2 = v.y + c[i];
					
					if(1 <= x2 && x2 <= N && 1 <= y2 && y2 <= N)
					{
						if(!visited[x2][y2])
						{
							if(map[x2][y2] == 0)
							{
								visited[x2][y2] = true;
								q.offer(new Virus(x2, y2, v.time+1));
							}
							else if(map[x2][y2] == 2)//비활성바이러스
							{
								visited[x2][y2] = true;// -> 활성화 시킴
								q.offer(new Virus(x2, y2, v.time+1));
							}
							
						}
					}
				}
			}
			
			if(count == pass_count)
				min[0] = Math.min(min[0], finish_time);
		}
		else
		{
			for(int i = index;i <= viruses.size() - depth; i++)
			{
				Virus v = viruses.get(i);
				
				pos.add(v);
				recursive_17142(i+1, depth-1, N,min,viruses, map,pos, pass_count);
				pos.remove(pos.size()-1);
			}
		}
	}
	
	static void bj17140() throws Exception
	{
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		
		int map[][] = new int[101][101];
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		int R = Integer.parseInt(strtok.nextToken());
		int C = Integer.parseInt(strtok.nextToken());
		int K = Integer.parseInt(strtok.nextToken());
		
		for(int i=1;i<=3;i++)
		{
			strtok = new StringTokenizer(b.readLine());
			for(int j=1;j<=3;j++)
			{
				map[i][j] = Integer.parseInt(strtok.nextToken());
			}
		}
		
		if(map[R][C] == K)
		{
			System.out.println(0);
			return;
		}
		
		for(int t=1;t<=100;t++)
		{
			//행 개수
			int row = Integer.MIN_VALUE;
			for(int j=1;j<=100;j++)
			{
				for(int i=1;i<=100;i++)
				{
					if(map[i][j] == 0)
					{
						row = Math.max(row, i-1);
						break;
					}
				}
			}
			
			//열 개수
			int column = Integer.MIN_VALUE;
			for(int i=1;i<=100;i++)
			{
				for(int j=1;j<=100;j++)
				{
					if(map[i][j] == 0)
					{
						column = Math.max(column, j-1);
						break;
					}
				}
			}
			
			
			//R연산
			if(row >= column)
			{
				int list[] = new int[column+1];
				int count[] = new int[101];
				count[0] = Integer.MAX_VALUE;
				
				for(int i=1;i<=row;i++)
				{
					int n = 1;
					for(int j=1;j<=column;j++)
					{
						if(map[i][j] != 0)
						{
							count[map[i][j]]++;
							if(count[map[i][j]]==1)
								list[n++] = map[i][j];
						}
						map[i][j] = 0;
					}
					
					//연산
					int m = n;
					
					mergeSort(list,count, 1, n-1);
					count[0]=0;
	
					n = 1;
					int j = 1;
					
					while(n < m)
					{
						map[i][j++] = list[n];
						map[i][j++] = count[list[n]];
						count[list[n]] = 0;
						n++;
					}
				}
			}
			else//C연산
			{
				int list[] = new int[row+1];
				int count[] = new int[101];
				count[0] = Integer.MAX_VALUE;
				
				for(int j=1;j<=column;j++)
				{
					int n = 1;
					for(int i=1;i<=row;i++)
					{
						if(map[i][j] != 0)
						{
							count[map[i][j]]++;
							if(count[map[i][j]]==1)
								list[n++] = map[i][j];
						}
						map[i][j] = 0;
					}
					
					//연산
					int m = n;
					
					mergeSort(list, count, 1, n-1);
					count[0]=0;
					
					n = 1;
					int i = 1;
					
					while(n<m)
					{
						map[i++][j] = list[n];
						map[i++][j] = count[list[n]];
						count[list[n]] = 0;
						n++;
					}
				}
			}

			//검사
			if(map[R][C] == K)
			{
				System.out.println(t);
				return;
			}
		}
		System.out.println(-1);
	}
	
	static void mergeSort(int list[], int count[], int left, int right)
	{
		if(left < right)
		{
			int mid = (left + right) / 2;
			mergeSort(list, count, left, mid);
			mergeSort(list, count, mid+1, right);
			merge(list, count, left, right, mid);
			
		}
	}
	
	static void merge(int list[], int count[] ,int left, int right, int mid)
	{
		int i = left;
		int j = mid + 1;
		int k = left;
		int list2[] = new int[list.length];
		
		while(i <= mid && j <= right)
		{
			if(count[list[i]] < count[list[j]])
			{
				list2[k++] = list[i++];
			}
			else if(count[list[i]] > count[list[j]])
			{
				list2[k++] = list[j++];
			}
			else//수가 같은 경우
			{
				if(list[i] < list[j])
				{
					list2[k++] = list[i++];
				}
				else
				{
					list2[k++] = list[j++];
				}
			}
		}
		
		while(i<=mid)
		{
			list2[k++] = list[i++];
		}
		
		while(j<=right)
		{
			list2[k++] = list[j++];
		}
		
		for(int index = left;index <= right;index++)
		{
			list[index] = list2[index];
		}
	}
	
	static class Shark17143{
		int x, y;
		int size=0;
		int velocity = 0;
		int direction = 0;
		public Shark17143()
		{
			
		}
		
		public Shark17143(int x, int y)
		{
			this.x = x;
			this.y = y;
		}

		public Shark17143(int x, int y, int size, int velocity, int direction) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
			this.velocity = velocity;
			this.direction = direction;
		}
		
	}
	
	static void bj17143() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		int T = Integer.parseInt(strtok.nextToken());
		
		if(T==0)
		{	
			System.out.println(0);
			return;
		}
		
		int map[][] = new int[N+1][M+1];
		
		ArrayList<Shark17143> sharks = new ArrayList<>();
		
		sharks.add(new Shark17143());
		
		boolean isDead[] = new boolean[T+1];
		
		for(int t=1;t<=T;t++)
		{
			strtok = new StringTokenizer(b.readLine());
			int x = Integer.parseInt(strtok.nextToken());
			int y = Integer.parseInt(strtok.nextToken());
			int velocity = Integer.parseInt(strtok.nextToken());
			int direction = Integer.parseInt(strtok.nextToken());
			int size = Integer.parseInt(strtok.nextToken());
			
			map[x][y] = t;//상어 고유번호
			sharks.add(new Shark17143(x, y, size, velocity, direction));
		}
	
		int total = 0;
		
		//오른쪽으로 한 칸씩 이동
		for(int j=1;j<=M;j++)
		{
			//상어잡기
			for(int i=1;i<=N;i++)
			{
				if(map[i][j] > 0)
				{
					total += sharks.get(map[i][j]).size;
					isDead[map[i][j]] = true;//죽음
					map[i][j] = 0;
					break;
				}
			}
			
			//상어이동
			moveSharks17143(N, M, map, sharks, isDead);
		}
		
		System.out.println(total);
	}
	
	static void moveSharks17143(int N, int M, int map[][], ArrayList<Shark17143> sharks, boolean isDead[])
	{
		for(int i=1;i<sharks.size();i++)
		{
			Shark17143 shark = sharks.get(i);
			
			//죽은애면 넘어감
			if(isDead[i])
				continue;
			
			//상
			if(shark.direction == 1)
			{
				int x2 = shark.x - shark.velocity;
				int y2 = shark.y;
				
				int pos[] = {x2,y2};
				checkBound(pos, N, M, shark);
				x2 = pos[0];
				y2 = pos[1];
				
				eatShark(i, map, x2, y2, shark, sharks, isDead);
			}
			else if(shark.direction == 2)//하
			{
				int x2 = shark.x + shark.velocity;
				int y2 = shark.y;
				
				int pos[] = {x2,y2};
				checkBound(pos, N, M, shark);
				x2 = pos[0];
				y2 = pos[1];
				
				eatShark(i, map, x2, y2, shark, sharks, isDead);
			}
			else if(shark.direction == 3)//우
			{
				int x2 = shark.x;
				int y2 = shark.y + shark.velocity;
				
				int pos[] = {x2,y2};
				checkBound(pos, N, M, shark);
				x2 = pos[0];
				y2 = pos[1];
				
				eatShark(i, map, x2, y2, shark, sharks, isDead);
			}
			else//좌
			{
				int x2 = shark.x;
				int y2 = shark.y - shark.velocity;
				
				int pos[] = {x2,y2};
				checkBound(pos, N, M, shark);
				x2 = pos[0];
				y2 = pos[1];
				
				eatShark(i, map, x2, y2, shark, sharks, isDead);
			}
		}
	}
	
	static void checkBound(int pos[], int N, int M, Shark17143 shark)
	{
		int x2 = pos[0];
		int y2 = pos[1];
		
		while(x2 > N || x2 < 1 || y2 < 1 || y2 > M)
		{
			if(x2 > N)
			{
				x2 -= (x2 - N) * 2;
				if(shark.direction == 2)
				shark.direction = 1;
			}
			else if(x2 < 1)
			{
				x2 += (1 - x2) * 2;
				if(shark.direction == 1)
				shark.direction = 2;
			}
			
			if(y2 < 1)
			{
				y2 += (1 - y2) * 2;
				if(shark.direction == 4)
				shark.direction = 3;
			}
			else if(y2 > M)
			{
				y2 -= (y2 - M) * 2;
				if(shark.direction == 3)
				shark.direction = 4;
			}	
		}
		
		pos[0] = x2;
		pos[1] = y2;
	}
	
	static void eatShark(int i, int map[][],int x2, int y2, Shark17143 shark, ArrayList<Shark17143> sharks, boolean isDead[])
	{
		if(map[x2][y2] !=0)//다른 상어 존재
		{
			if(map[x2][y2] < i)//이동 마친 상어
			{
				if(sharks.get(map[x2][y2]).size < shark.size)
				{
					isDead[map[x2][y2]] = true;
					
					if(map[shark.x][shark.y] == i)
						map[shark.x][shark.y] = 0;
					map[x2][y2] = i;//지금 상어가 잡아먹음
					shark.x = x2;
					shark.y = y2;
				}
				else
				{
					//지금 상어가 잡아먹힘
					if(map[shark.x][shark.y] == i)
						map[shark.x][shark.y] = 0;
					isDead[i] = true;
				}
			}
			else//이동 안한 상어
			{
				if(map[shark.x][shark.y] == i)
					map[shark.x][shark.y] = 0;
				map[x2][y2] = i;
				shark.x = x2;
				shark.y = y2;
			}
		}
		else//다른 상어 없음
		{
			if(map[shark.x][shark.y] == i)
				map[shark.x][shark.y] = 0;
			map[x2][y2] = i;
			shark.x = x2;
			shark.y = y2;
		}
	}
	
	
	static void bj17144() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		int T = Integer.parseInt(strtok.nextToken());
		
		int map[][] = new int[N+1][M+1];
		
		ArrayList<Dust> air_refresher = new ArrayList<>();
		
		Queue<Dust> q = new LinkedList<>();

		for(int i=1;i<=N;i++)
		{
			strtok = new StringTokenizer(b.readLine());
			
			for(int j=1;j<=M;j++)
			{
				map[i][j] = Integer.parseInt(strtok.nextToken());
				
				//공기청정기
				if(map[i][j] == -1)
				{
					air_refresher.add(new Dust(i, j));
				}
				else if(map[i][j] > 0)//먼지
				{
					Dust d = new Dust(i, j);
					d.volume = map[i][j];
					q.offer(d);
				}
			}
		}

		int r[] = {-1,1,0,0};
		int c[] = {0,0,-1,1};
		
		for(int t=1;t<=T;t++)
		{
			//미세먼지 확산
			while(!q.isEmpty())
			{
				Dust now = q.poll();
				int x = now.x;
				int y = now.y;
				int volumne = now.volume;
				int divide_volume = volumne / 5;
				
				for(int i=0;i<4;i++)
				{
					int x2 = x + r[i];
					int y2 = y + c[i];
					
					if(1 <= x2 && x2 <= N && 1 <= y2 && y2 <= M)
					{
						if(map[x2][y2] != -1)
						{
							map[x2][y2] += divide_volume;
							map[x][y] -= divide_volume;
						}
					}
				}
				
			}
			
			
			//공기청정기 윗부분 이동
			upDustMove(N, M, map, air_refresher.get(0).x, air_refresher.get(0).y);
			//공기청정기 밑부분 이동
			downDustMove(N, M, map, air_refresher.get(1).x, air_refresher.get(1).y);
			//그 다음 작업할 애들추가
			for(int i=1;i<=N;i++)
			{
				for(int j=1;j<=M;j++)
				{
					if(map[i][j] > 0)
					{
						Dust d = new Dust(i, j);
						d.volume = map[i][j];
						q.offer(d);
					}
				}
			}
		}
		
		int sum = 0;
		
		for(int i=1;i<=N;i++)
		{	for(int j=1;j<=M;j++)
			{
				//System.out.print(map[i][j]+" ");
				if(map[i][j] > 0)
					sum += map[i][j];
			}
		//System.out.println();
		}
		System.out.println(sum);
			
	}
	
	static void upDustMove(int N, int M, int map[][], int x, int y)
	{
		int phase = 0;
		int origin_x = x;
		int origin_y = y;
		x--;
		
		while(true)
		{
			if(phase == 0)
			{
				map[x][y] = map[x-1][y];
				
				x--;
				if(x==1)
					phase = 1;
			}
			else if(phase == 1)
			{
				map[x][y] = map[x][y+1];
				y++;
				if(y==M)
					phase = 2;
			}
			else if(phase == 2)
			{
				map[x][y] = map[x+1][y];
				x++;
				if(x == origin_x)
					phase = 3;
			}
			else
			{
				map[x][y] = map[x][y-1];
				y--;
				if(y==origin_y+1)
				{
					map[x][y] = 0;
					break;
				}
			}
		}
		
	}
	
	static void downDustMove(int N, int M, int map[][], int x, int y)
	{
		int phase = 0;
		int origin_x = x;
		int origin_y = y;
		x++;
		
		while(true)
		{
			if(phase == 0)
			{
				map[x][y] = map[x+1][y];
				
				x++;
				if(x==N)
					phase = 1;
			}
			else if(phase == 1)
			{
				map[x][y] = map[x][y+1];
				y++;
				if(y==M)
					phase = 2;
			}
			else if(phase == 2)
			{
				map[x][y] = map[x-1][y];
				x--;
				if(x == origin_x)
					phase = 3;
			}
			else
			{
				map[x][y] = map[x][y-1];
				y--;
				if(y==origin_y+1)
				{
					map[x][y] = 0;
					break;
				}
			}
		}
		
	}
	
	static class Dust{
		int x, y, volume = 0;
		public Dust()
		{
			
		}
		
		public Dust(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
	
	static void bj16236() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		int map[][] = new int[N][N];
		
		Queue<Shark> q = new LinkedList<>();
		
		boolean isFish = false;
		
		
		boolean visited[][] = new boolean[N][N];
		
		for(int i=0;i<N;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			for(int j=0;j<N;j++)
			{
				map[i][j] = Integer.parseInt(strtok.nextToken());
				if(map[i][j] == 9)
				{	
					map[i][j] = 0;
					q.offer(new Shark(i, j));
					visited[i][j] = true;
				}
				else if(map[i][j] < 2 && map[i][j] > 0)
					isFish = true;
			}
		}
		
		if(!isFish)
		{
			System.out.println(0);
			return;
		}
		
		int r[] = {-1,0,1,0};
		int c[] = {0,-1,0,1};
		
		
		int recent_time = 0;
		
		while(!q.isEmpty())
		{
			Shark shark = q.poll();
			int x = shark.x;
			int y = shark.y;
			int size = shark.size;
			int time = shark.time;
			int fish = shark.fish;
			
			//먹을 수 있음
			if(canEat16236(x, y, map, size))
			{
				while(!q.isEmpty())
				{
					Shark temp = q.poll();
					
					if(temp.time == time && canEat16236(temp.x, temp.y, map, temp.size))// 같은 거리에 먹을 수 있는 물고기가 여러개 있을 시
					{
						if(temp.x < x)//가장 위쪽에 있는 물고기 먹음
						{
							shark = temp;
							x = shark.x;
							y = shark.y;
							size = shark.size;
							time = shark.time;
							fish = shark.fish;
						}
						else if(temp.x == x)//높이가 같으면 가장 왼쪽에 있는 물고기 먹음
						{
							if(temp.y < y)
							{
								shark = temp;
								x = shark.x;
								y = shark.y;
								size = shark.size;
								time = shark.time;
								fish = shark.fish;
							}
						}
					}
				}
				map[x][y] = 0;
				
				fish++;
				
				if(fish == size)//크기 커짐
				{	
					size ++;
					fish = 0;
				}

				visited = new boolean[N][N];
				visited[x][y] = true;
				
				recent_time = time;//가장 최근 물고기 먹은 시간 = 결과

				q.clear();
			}
			
			for(int i=0;i<4;i++)
			{
				int x2 = x + r[i];
				int y2 = y + c[i];
				
				if(0 <= x2 && x2 < N && 0 <= y2 && y2 < N)
				{
					if(!visited[x2][y2] && map[x2][y2] <= size)
					{
						Shark next = new Shark(x2, y2);
						next.time = time + 1;
						next.fish = fish;
						next.size = size;
						
						visited[x2][y2] = true;
						
						q.offer(next);
					}
				}
			}
			
		}
		
		System.out.println(recent_time);
	}
	
	static boolean canEat16236(int x, int y, int map[][], int size)
	{
		if(map[x][y] < size && map[x][y] > 0)
			return true;
		else
			return false;
	}
	
	static class Shark{
		int x, y;
		int size=2;
		int time=0;
		int fish = 0;
		public Shark()
		{
			
		}
		
		public Shark(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
	
	/*
	static void bj16235() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		int K = Integer.parseInt(strtok.nextToken());
		
		ArrayList<ArrayList<ArrayList<Tree>>> map = new ArrayList<>();
		map.add(new ArrayList<>());
		
		int nutrition[][] = new int[N+1][N+1];
		
		for(int i=1;i<=N;i++)
		{
			strtok = new StringTokenizer(b.readLine());
			map.add(new ArrayList<>());
			map.get(i).add(new ArrayList<>());
			
			for(int j=1;j<=N;j++)
			{
				nutrition[i][j] = Integer.parseInt(strtok.nextToken());
				
				map.get(i).add(new ArrayList<>());
				map.get(i).get(j).add(new Tree(0, 5));
				//map[i][j][0] = 5;
			}
			
		}
		
		boolean isDead[][] = new boolean[N+1][N+1];
		
		for(int i=1;i<=M;i++)
		{
			int x = Integer.parseInt(strtok.nextToken());
			int y = Integer.parseInt(strtok.nextToken());
			int age = Integer.parseInt(strtok.nextToken());
			map[x][y][1] = age;
		}
		
		
		for(int k = 1;k<=K;k++)
		{
			for(int i=1;i<=N;i++)
			{
				for(int j=1;j<=N;j++)
				{
					// 봄 
					if(isTree(map, i, j))//나무 심겨진 땅에
					{
						if(!isDead[i][j])//살아있는
						{
							if(map[i][j][0] >= map[i][j][1])//양분흡수가능
							{
								map[i][j][0] -= map[i][j][1];
								map[i][j][1]++;
							}
							else//죽음
							{
								isDead[i][j] = true;
								map[i][j][0] = 0;
							}
						}
					}
					
						
					// 여름 
					if(isDead[i][j])//죽은 나무
						map[i][j][0] += map[i][j][1]/2;
					
					// 가을 
					if(!isDead[i][j] && isTree(map, i, j) && map[i][j][1] % 5 == 0)//번식가능
					{
						for(int r = -1;r <= 1; r++)
						{
							for(int c = -1; c <= 1; c++)
							{
								int x = i + r;
								int y = j + c;
								
								if(x >= 1 && x <= N && y >= 1 && y <= N)
								{
									
								}
							}
						}
					}
				}
			}
		}
		
		
	}
	
	static class Tree{
		
		int age=0, nutrition=0;

		public Tree(int age, int nutrition)
		{
			this.age = age;
			this.nutrition = nutrition;
		}
	}
	
	static boolean isTree(int[][][] map,int x, int y)
	{
		if(map[x][y][1] >= 1)
			return true;
		else
			return false;
	}
	*/
	static void bj14889() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		int S[][] = new int[N+1][N+1];
		
		for(int i=1;i<=N;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			
			for(int j=1;j<=N;j++)
			{
				S[i][j] = Integer.parseInt(strtok.nextToken());
			}
		}
		boolean isA[] = new boolean[N+1];
		
		int min[] = {Integer.MAX_VALUE};
		recursive_14889(1, N/2, N, S, isA, min);
		System.out.println(min[0]);
	}
	
	static void recursive_14889(int index,int depth, int N, int S[][], boolean isA[], int[] min)
	{
		if(depth == 0)
		{
			int sum1 = 0;
			int sum2 = 0;
			
			for(int i=1;i<=N;i++)
			{
				for(int j=i+1;j<=N;j++)
				{
					if(isA[i] && isA[j])
					{
						sum1 += S[i][j];
						sum1 += S[j][i];
					}
					else if(!isA[i] && !isA[j])
					{	
						sum2 += S[i][j];
						sum2 += S[j][i];
					}
				}
			}
			min[0] = Math.min(min[0], Math.abs(sum1 - sum2));
			return;
		}
		
		for(int i=index;i<=N-depth + 1;i++)
		{
			isA[i] = true;
			recursive_14889(i+1, depth-1, N, S, isA, min);
			isA[i] = false;
		}
	}
	
	static void bj14888() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		int numbers[] = new int[N];
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		for(int i=0;i<N;i++)
			numbers[i] = Integer.parseInt(strtok.nextToken());
		
		int operators[] = new int[4];//+,-,*,/
		
		strtok = new StringTokenizer(b.readLine());
		
		for(int i=0;i<4;i++)
			operators[i] = Integer.parseInt(strtok.nextToken());
		
		int max[] = {Integer.MIN_VALUE};
		int min[] = {Integer.MAX_VALUE};
		
		recursive_14888(1, N, numbers, operators, max, min, numbers[0]);
		System.out.println(max[0]+" "+min[0]);
		
	}
	
	static void recursive_14888(int index, int N,int numbers[], int operators[], int[] max, int[] min, int result)
	{
		
		for(int i=0;i<4;i++)
		{
			if(operators[i] > 0)
			{
				int result2=result;
				
				if(i==0)//+
					result2 += numbers[index];
				else if(i==1)//-
					result2 -= numbers[index];
				else if(i==2)//*
					result2 *= numbers[index];
				else// /
				{
					if(result2 < 0)//음수
					{	
						result2 = Math.abs(result2) / numbers[index];
						result2 = -result2;
					}
					else
						result2 /= numbers[index];
						
				}
					
				operators[i]--;
				if(index < N-1)
				{
					recursive_14888(index+1, N, numbers, operators, max, min, result2);
				}
				else
				{
					max[0] = Math.max(max[0], result2);
					min[0] = Math.min(min[0], result2);
				}
				operators[i]++;
			}
			
		}
	}
	
	static void bj16234() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		int N = Integer.parseInt(strtok.nextToken());
		int L = Integer.parseInt(strtok.nextToken());
		int R = Integer.parseInt(strtok.nextToken());
		
		int map[][] = new int[N+1][N+1];
		
		for(int i=1;i<=N;i++)
		{	
			strtok = new StringTokenizer(b.readLine());
			
			for(int j=1;j<=N;j++)
			{
				map[i][j] = Integer.parseInt(strtok.nextToken());
			}
		}
		int r[] = {-1,1,0,0};
		int c[] = {0,0,-1,1};
		Queue<Position> list = new LinkedList<>();
		
		for(int count=0;count<=2000;count++)
		{
			boolean visited[][] = new boolean[N+1][N+1];
			boolean isMoved = false;
			
			for(int i=1;i<=N;i++)
			{
				for(int j=1;j<=N;j++)
				{
					if(!visited[i][j])
					{
						int sum[] = {0};

						
						dfs_16234(i, j, N,L,R, map, visited, r, c, sum,list);
						
						if(list.size() > 1)
						{	
							move_human(N, list,sum[0]/list.size(), map);
							isMoved = true;
						}
						list.clear();
					}
				}
			}
			
			if(isMoved == false)
			{
				System.out.println(count);
				return;
			}
		}
			
	}
	
	static void dfs_16234(int x, int y, int N,int L, int R ,int[][] map, boolean visited[][],int r[], int c[], int[] sum, Queue<Position> list)
	{
		visited[x][y] = true;

		list.offer(new Position(x, y));
		sum[0] += map[x][y];
		
		for(int i=0;i<4;i++)
		{
			int x2 = x + r[i];
			int y2 = y + c[i];
			if(x2 >= 1 && x2 <= N && y2 >= 1 && y2 <= N)
			{
				int subtract = Math.abs(map[x][y] - map[x2][y2]);
				
				if(!visited[x2][y2] && subtract >= L && subtract <= R)
				{
					dfs_16234(x2, y2, N,L,R, map, visited, r, c, sum, list);
				}
			}
		}
	}
	static void move_human(int N, Queue<Position> list, int num, int map[][])
	{
		while(!list.isEmpty())
		{
			Position pos = list.poll();
			map[pos.x][pos.y] = num;
		}
	}
	
	static void bj14500() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		int N = Integer.parseInt(strtok.nextToken());//행
		int M = Integer.parseInt(strtok.nextToken());//열
		
		int map[][] = new int[N][M];
		
		for(int i=0;i<N;i++)
		{
			strtok = new StringTokenizer(b.readLine());
			
			for(int j=0;j<M;j++)
			{
				map[i][j] = Integer.parseInt(strtok.nextToken());
			}
		}
		
		ArrayList<Position[]> shapes = new ArrayList<>();
		
		shapes.add(new Position[4]);
		shapes.get(0)[0] = new Position(0,0);
		shapes.get(0)[1] = new Position(1,0);
		shapes.get(0)[2] = new Position(2,0);
		shapes.get(0)[3] = new Position(3,0);
		
		shapes.add(new Position[4]);
		shapes.get(1)[0] = new Position(0,0);
		shapes.get(1)[1] = new Position(1,0);
		shapes.get(1)[2] = new Position(2,0);
		shapes.get(1)[3] = new Position(2,1);
		
		shapes.add(new Position[4]);
		shapes.get(2)[0] = new Position(0,0);
		shapes.get(2)[1] = new Position(0,1);
		shapes.get(2)[2] = new Position(0,2);
		shapes.get(2)[3] = new Position(0,3);
		
		shapes.add(new Position[4]);
		shapes.get(3)[0] = new Position(0,0);
		shapes.get(3)[1] = new Position(0,1);
		shapes.get(3)[2] = new Position(0,2);
		shapes.get(3)[3] = new Position(1,2);
		
		shapes.add(new Position[4]);
		shapes.get(4)[0] = new Position(0,0);
		shapes.get(4)[1] = new Position(1,0);
		shapes.get(4)[2] = new Position(2,0);
		shapes.get(4)[3] = new Position(2,-1);
		
		shapes.add(new Position[4]);
		shapes.get(5)[0] = new Position(0,0);
		shapes.get(5)[1] = new Position(0,1);
		shapes.get(5)[2] = new Position(0,2);
		shapes.get(5)[3] = new Position(1,0);
		
		shapes.add(new Position[4]);
		shapes.get(6)[0] = new Position(0,0);
		shapes.get(6)[1] = new Position(0,1);
		shapes.get(6)[2] = new Position(1,1);
		shapes.get(6)[3] = new Position(2,1);
		
		shapes.add(new Position[4]);
		shapes.get(7)[0] = new Position(0,0);
		shapes.get(7)[1] = new Position(1,0);
		shapes.get(7)[2] = new Position(0,1);
		shapes.get(7)[3] = new Position(2,0);
		
		shapes.add(new Position[4]);
		shapes.get(8)[0] = new Position(0,0);
		shapes.get(8)[1] = new Position(1,0);
		shapes.get(8)[2] = new Position(1,-1);
		shapes.get(8)[3] = new Position(1,-2);
		
		shapes.add(new Position[4]);
		shapes.get(9)[0] = new Position(0,0);
		shapes.get(9)[1] = new Position(1,0);
		shapes.get(9)[2] = new Position(1,1);
		shapes.get(9)[3] = new Position(1,2);
		
		shapes.add(new Position[4]);
		shapes.get(10)[0] = new Position(0,0);
		shapes.get(10)[1] = new Position(1,0);
		shapes.get(10)[2] = new Position(1,1);
		shapes.get(10)[3] = new Position(0,1);
		
		shapes.add(new Position[4]);
		shapes.get(11)[0] = new Position(0,0);
		shapes.get(11)[1] = new Position(0,1);
		shapes.get(11)[2] = new Position(0,2);
		shapes.get(11)[3] = new Position(1,1);
		
		shapes.add(new Position[4]);
		shapes.get(12)[0] = new Position(0,0);
		shapes.get(12)[1] = new Position(1,0);
		shapes.get(12)[2] = new Position(1,-1);
		shapes.get(12)[3] = new Position(1,1);
		
		shapes.add(new Position[4]);
		shapes.get(13)[0] = new Position(0,0);
		shapes.get(13)[1] = new Position(1,0);
		shapes.get(13)[2] = new Position(2,0);
		shapes.get(13)[3] = new Position(1,1);
		
		shapes.add(new Position[4]);
		shapes.get(14)[0] = new Position(0,0);
		shapes.get(14)[1] = new Position(1,0);
		shapes.get(14)[2] = new Position(2,0);
		shapes.get(14)[3] = new Position(1,-1);
		
		shapes.add(new Position[4]);
		shapes.get(15)[0] = new Position(0,0);
		shapes.get(15)[1] = new Position(0,1);
		shapes.get(15)[2] = new Position(1,1);
		shapes.get(15)[3] = new Position(1,2);
		
		shapes.add(new Position[4]);
		shapes.get(16)[0] = new Position(0,0);
		shapes.get(16)[1] = new Position(1,0);
		shapes.get(16)[2] = new Position(1,1);
		shapes.get(16)[3] = new Position(2,1);
		
		shapes.add(new Position[4]);
		shapes.get(17)[0] = new Position(0,0);
		shapes.get(17)[1] = new Position(1,0);
		shapes.get(17)[2] = new Position(2,-1);
		shapes.get(17)[3] = new Position(1,-1);
		
		shapes.add(new Position[4]);
		shapes.get(18)[0] = new Position(0,0);
		shapes.get(18)[1] = new Position(1,0);
		shapes.get(18)[2] = new Position(1,-1);
		shapes.get(18)[3] = new Position(0,1);
		
		int total = Integer.MIN_VALUE;
		
		for(int k=0;k<shapes.size();k++)
		{
			Position[] pos = shapes.get(k);
			
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<M;j++)
				{
					int sum = 0;
					
					boolean isPass = true;
					
					for(int index = 0;index<4;index++)
					{
						int x = i + pos[index].x;
						int y = j + pos[index].y;
						
						if(x >= 0 && x < N && y < M && y >= 0)
							sum += map[x][y];
						else
						{
							isPass = false;
							break;
						}
					}
					
					if(isPass)
					{
						total = Math.max(total, sum);
					}
				}
			}
		}
		System.out.println(total);
	}
	
	static void bj15684() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		int N = Integer.parseInt(strtok.nextToken());//열
		int M = Integer.parseInt(strtok.nextToken());//가로선 개수
		int H = Integer.parseInt(strtok.nextToken());//행
		
		char map[][] = new char[H+1][N+1];
		
		ArrayList<Position> pos_list = new ArrayList<>();
		
		for(int i=1;i<=H;i++)
		{
			for(int j=1;j<=N;j++)
			{	
				map[i][j] = 'D';
			}
		}
		
		for(int i=0;i<M;i++)
		{
			strtok = new StringTokenizer(b.readLine());
			int A = Integer.parseInt(strtok.nextToken());
			int B = Integer.parseInt(strtok.nextToken());
			//(A,B)
			
			map[A][B] = 'R';
			map[A][B+1] = 'L';	
		}
		
		if(canPass(H, N, map))
		{
			System.out.println(0);
		}
		else
		{
			for(int i=1;i<=H;i++)
			{
				for(int j=1;j<N;j++)
				{	
					if(map[i][j] == 'D' && map[i][j+1] == 'D')
					{
						pos_list.add(new Position(i, j));
					}
				}
			}
			
			if(pos_list.isEmpty())
			{
				System.out.println(-1);
			}
			else
			{
				int[] min = {Integer.MAX_VALUE};
				add_line(0, 3, H, N, map, pos_list, min);
				
				if(min[0] == Integer.MAX_VALUE)
					System.out.println(-1);
				else
					System.out.println(min[0]);
			}
		}
		
	}
	
	static void add_line(int index, int depth, int H,int N, char map[][], ArrayList<Position> pos_list, int[] min)
	{
		for(int i=index;i<pos_list.size();i++)
		{
			int x = pos_list.get(i).x;
			int y = pos_list.get(i).y;
			
			if(map[x][y] == 'D' && map[x][y+1] == 'D')
			{	
				map[x][y] = 'R';
				map[x][y+1] = 'L';

				if(canPass(H, N, map))
				{
					min[0] = Math.min(min[0], 3-depth + 1);
					
					map[x][y] = 'D';
					map[x][y+1] = 'D';
					break;
				}
				else
				{
					if(depth > 1)
						add_line(index+1, depth-1, H, N, map, pos_list, min);
					
					map[x][y] = 'D';
					map[x][y+1] = 'D';
				}
				
				
			}
			
		}
	
	}
	
	static boolean canPass(int H,int N, char map[][])
	{
		boolean isPass = true;
		for(int i=1;i<=N;i++)
		{
			int output = output_line(i, H, N, map);
			if(output != i)
			{
				isPass = false;
				break;
			}
		}
		return isPass;
	}
	
	static int output_line(int line,int H, int N, char map[][])
	{
		int i = 1;
		int j = line;
		
		while(i <= H)
		{
			if(map[i][j] =='L')
			{
				j--;
			}
			else if(map[i][j] == 'R')//오른쪽
			{
				j++;
			}
			i++;
		}
		return j;
	}
	
	static void bj5373() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int T = Integer.parseInt(b.readLine());
		
		//윗면 : U (3,3)~(5,5), 아랫면 : D (9,3)~(11,5), 앞면 : F (6,3)~(8,5), 뒷면 : B (0,3)~(2,5), 왼쪽면 : L (3,0)~(5,2), 오른쪽면 : R (3,6)~(5,8)
		
		for(int i=0;i<T;i++)
		{
			char cube[][] = {
					//0   1   2   3   4   5   6   7   8
					{'.','.','.','o','o','o','.','.','.'},//0
					{'.','.','.','o','o','o','.','.','.'},//1
					{'.','.','.','o','o','o','.','.','.'},//2
					{'g','g','g','w','w','w','b','b','b'},//3
					{'g','g','g','w','w','w','b','b','b'},//4
					{'g','g','g','w','w','w','b','b','b'},//5
					{'.','.','.','r','r','r','.','.','.'},//6
					{'.','.','.','r','r','r','.','.','.'},//7
					{'.','.','.','r','r','r','.','.','.'},//8
					{'.','.','.','y','y','y','.','.','.'},//9
					{'.','.','.','y','y','y','.','.','.'},//10
					{'.','.','.','y','y','y','.','.','.'}//11
			};
			
			int N = Integer.parseInt(b.readLine());
			
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			
			for(int j=0;j<N;j++)
			{
				String order = strtok.nextToken();//명령
				turn_cube(cube, order);
				
			}
			
			//윗면 결과 출력
			for(int n=3;n<6;n++)
			{
				for(int m=3;m<6;m++)
				{
					System.out.print(cube[n][m]);
				}
				System.out.println();
			}
		}
	}
	
	static void turn_cube(char[][] cube, String order)
	{
		char side = order.charAt(0);
		char dir = order.charAt(1);
		
		if(side == 'U')//윗면
		{
			char line[] = {cube[2][3],cube[2][4],cube[2][5], //상
					cube[3][6],cube[4][6],cube[5][6],//우
					cube[6][5],cube[6][4],cube[6][3],//하
					cube[5][2],cube[4][2],cube[3][2]};//좌
			
			char line2[] = {cube[3][3],cube[3][4],cube[3][5], 
					cube[4][5],cube[5][5],cube[5][4],
					cube[5][3],cube[4][3]};
			
			change_dir(dir, line, line2);
			
			cube[3][3] = line2[0];
			cube[3][4] = line2[1];
			cube[3][5] = line2[2];
			cube[4][5] = line2[3];
			cube[5][5] = line2[4];
			cube[5][4] = line2[5];
			cube[5][3] = line2[6];
			cube[4][3] = line2[7];
			
			cube[2][3] = line[0];
			cube[2][4] = line[1];
			cube[2][5] = line[2];
			cube[3][6] = line[3];
			cube[4][6] = line[4];
			cube[5][6] = line[5];
			cube[6][5] = line[6];
			cube[6][4] = line[7];
			cube[6][3] = line[8];
			cube[5][2] = line[9];
			cube[4][2] = line[10];
			cube[3][2] = line[11];
		}
		else if(side == 'D')//아랫면
		{
			char line[] = {cube[8][3],cube[8][4],cube[8][5], //상
					cube[5][8],cube[4][8],cube[3][8],//우
					cube[0][5],cube[0][4],cube[0][3],//하
					cube[3][0],cube[4][0],cube[5][0]};//좌
			
			char line2[] = {cube[9][3],cube[9][4],cube[9][5], 
					cube[10][5],cube[11][5],cube[11][4],
					cube[11][3],cube[10][3]};
			
			change_dir(dir, line, line2);
			
			cube[9][3] = line2[0];
			cube[9][4] = line2[1];
			cube[9][5] = line2[2];
			cube[10][5] = line2[3];
			cube[11][5] = line2[4];
			cube[11][4] = line2[5];
			cube[11][3] = line2[6];
			cube[10][3] = line2[7];
			
			cube[8][3] = line[0];
			cube[8][4] = line[1];
			cube[8][5] = line[2];
			cube[5][8] = line[3];
			cube[4][8] = line[4];
			cube[3][8] = line[5];
			cube[0][5] = line[6];
			cube[0][4] = line[7];
			cube[0][3] = line[8];
			cube[3][0] = line[9];
			cube[4][0] = line[10];
			cube[5][0] = line[11];
		}
		else if(side == 'F')//앞면
		{
			char line[] = {cube[5][3],cube[5][4],cube[5][5], //상
					cube[5][6],cube[5][7],cube[5][8],//우
					cube[9][5],cube[9][4],cube[9][3],//하
					cube[5][0],cube[5][1],cube[5][2]};//좌
			
			char line2[] = {cube[6][3],cube[6][4],cube[6][5], 
					cube[7][5],cube[8][5],cube[8][4],
					cube[8][3],cube[7][3]};
			
			change_dir(dir, line, line2);
			
			cube[6][3] = line2[0];
			cube[6][4] = line2[1];
			cube[6][5] = line2[2];
			cube[7][5] = line2[3];
			cube[8][5] = line2[4];
			cube[8][4] = line2[5];
			cube[8][3] = line2[6];
			cube[7][3] = line2[7];
			
			cube[5][3] = line[0];
			cube[5][4] = line[1];
			cube[5][5] = line[2];
			cube[5][6] = line[3];
			cube[5][7] = line[4];
			cube[5][8] = line[5];
			cube[9][5] = line[6];
			cube[9][4] = line[7];
			cube[9][3] = line[8];
			cube[5][0] = line[9];
			cube[5][1] = line[10];
			cube[5][2] = line[11];
		}
		else if(side == 'B')//뒷면
		{
			char line[] = {cube[3][5],cube[3][4],cube[3][3], //상
					cube[3][2],cube[3][1],cube[3][0],//우
					cube[11][3],cube[11][4],cube[11][5],//하
					cube[3][8],cube[3][7],cube[3][6]};//좌
			
			char line2[] = {cube[2][5],cube[2][4],cube[2][3], 
					cube[1][3],cube[0][3],cube[0][4],
					cube[0][5],cube[1][5]};
			
			change_dir(dir, line, line2);
			
			cube[2][5] = line2[0];
			cube[2][4] = line2[1];
			cube[2][3] = line2[2];
			cube[1][3] = line2[3];
			cube[0][3] = line2[4];
			cube[0][4] = line2[5];
			cube[0][5] = line2[6];
			cube[1][5] = line2[7];
			
			cube[3][5] = line[0];
			cube[3][4] = line[1];
			cube[3][3] = line[2];
			cube[3][2] = line[3];
			cube[3][1] = line[4];
			cube[3][0] = line[5];
			cube[11][3] = line[6];
			cube[11][4] = line[7];
			cube[11][5] = line[8];
			cube[3][8] = line[9];
			cube[3][7] = line[10];
			cube[3][6] = line[11];
		}
		else if(side == 'L')//왼쪽면
		{
			char line[] = {cube[3][3],cube[4][3],cube[5][3], //상
					cube[6][3],cube[7][3],cube[8][3],//우
					cube[9][3],cube[10][3],cube[11][3],//하
					cube[0][3],cube[1][3],cube[2][3]};//좌
			
			char line2[] = {cube[3][2],cube[4][2],cube[5][2], 
					cube[5][1],cube[5][0],cube[4][0],
					cube[3][0],cube[3][1]};
			
			change_dir(dir, line, line2);
			
			cube[3][2] = line2[0];
			cube[4][2] = line2[1];
			cube[5][2] = line2[2];
			cube[5][1] = line2[3];
			cube[5][0] = line2[4];
			cube[4][0] = line2[5];
			cube[3][0] = line2[6];
			cube[3][1] = line2[7];
			
			cube[3][3] = line[0];
			cube[4][3] = line[1];
			cube[5][3] = line[2];
			cube[6][3] = line[3];
			cube[7][3] = line[4];
			cube[8][3] = line[5];
			cube[9][3] = line[6];
			cube[10][3] = line[7];
			cube[11][3] = line[8];
			cube[0][3] = line[9];
			cube[1][3] = line[10];
			cube[2][3] = line[11];
		}
		else//오른쪽면
		{
			char line[] = {cube[5][5],cube[4][5],cube[3][5], //상
					cube[2][5],cube[1][5],cube[0][5],//우
					cube[11][5],cube[10][5],cube[9][5],//하
					cube[8][5],cube[7][5],cube[6][5]};//좌
			
			char line2[] = {cube[5][6],cube[4][6],cube[3][6], 
					cube[3][7],cube[3][8],cube[4][8],
					cube[5][8],cube[5][7]};
			
			change_dir(dir, line, line2);
			
			cube[5][6] = line2[0];
			cube[4][6] = line2[1];
			cube[3][6] = line2[2];
			cube[3][7] = line2[3];
			cube[3][8] = line2[4];
			cube[4][8] = line2[5];
			cube[5][8] = line2[6];
			cube[5][7] = line2[7];
			
			cube[5][5] = line[0];
			cube[4][5] = line[1];
			cube[3][5] = line[2];
			cube[2][5] = line[3];
			cube[1][5] = line[4];
			cube[0][5] = line[5];
			cube[11][5] = line[6];
			cube[10][5] = line[7];
			cube[9][5] = line[8];
			cube[8][5] = line[9];
			cube[7][5] = line[10];
			cube[6][5] = line[11];
		}
	}
	
	static void change_dir(char dir, char[] line, char[] line2)
	{
		if(dir == '+')//시계방향
		{
			for(int k=0;k<3;k++)
			{
				char last = line[line.length-1];
				for(int i=line.length-1;i>0;i--)
				{
					line[i] = line[i-1];
				}
				line[0] = last;	
			}
			for(int k=0;k<2;k++)
			{
				char last = line2[line2.length-1];
				for(int i=line2.length-1;i>0;i--)
				{
					line2[i] = line2[i-1];
				}
				line2[0] = last;	
			}
		}
		else//반시계방향
		{
			for(int k=0;k<3;k++)
			{
				char first = line[0];
				for(int i=0;i<line.length-1;i++)
				{
					line[i] = line[i+1];
				}
				line[line.length-1] = first;	
			}
			for(int k=0;k<2;k++)
			{
				char first = line2[0];
				for(int i=0;i<line2.length-1;i++)
				{
					line2[i] = line2[i+1];
				}
				line2[line2.length-1] = first;	
			}
		}
	}
	
	static void s5648() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int  T = Integer.parseInt(b.readLine());
		
		for(int t=1;t<=T;t++)
		{
			int N = Integer.parseInt(b.readLine());
			
			ArrayList<Atomic> atomics = new ArrayList<>();
			
			for(int i=0;i<N;i++)
			{
				StringTokenizer strtok = new StringTokenizer(b.readLine());
				
				int y = Integer.parseInt(strtok.nextToken()) * 2;
				int x = Integer.parseInt(strtok.nextToken()) * 2;
				int dir = Integer.parseInt(strtok.nextToken());
				int energy = Integer.parseInt(strtok.nextToken());
				
				atomics.add(new Atomic(getX(x), getY(y), 1, energy, dir,i));
			}

			ArrayList<Atomic> crushes = new ArrayList<>();
			
			for(int i=0;i<atomics.size();i++)
			{
				Atomic first = atomics.get(i);
				
				for(int j=i+1;j<atomics.size();j++)
				{
					Atomic second = atomics.get(j);
					
					//2개가 충돌 가능
					if(isCrushed(first, second))
					{
						int time = whenCrushed(first, second);
						crushes.add(new Atomic(first.num, second.num, time));
					}
				}
			}
			
			Object[] o = crushes.toArray();
			
			mergeSort(o, 0, o.length-1);
			
			boolean isFinished[] = new boolean[N];
			
			int finishTime[] = new int[N];
			
			int sum = 0;
			for(int i=0;i<o.length;i++)
			{
				Atomic first = atomics.get(((Atomic)o[i]).x);
				Atomic second = atomics.get(((Atomic)o[i]).y);
				int time = ((Atomic)o[i]).time;
				
				if(!isFinished[first.num] && !isFinished[second.num])
				{
					isFinished[first.num] = true;
					sum += first.energy;
					
					isFinished[second.num] = true;
					sum += second.energy;
					
					finishTime[first.num] = time;
					finishTime[second.num] = time;
				}
				else if(isFinished[first.num] && !isFinished[second.num])//first는 이미 터진 상태
				{
					//동시에 터진거면 추가
					if(finishTime[first.num] == time)
					{
						isFinished[second.num] = true;
						sum += second.energy;
						finishTime[second.num] = time;
					}
				}
				else if(!isFinished[first.num] && isFinished[second.num])//second는 이미 터진 상태
				{
					//동시에 터진거면 추가
					if(finishTime[second.num] == time)
					{
						isFinished[first.num] = true;
						sum += first.energy;
						finishTime[first.num] = time;
					}
				}
			}
			
			System.out.println("#"+t+" "+sum);
		}
	}
	static void mergeSort(Object[] o, int left, int right)
	{
		if(left<right)
		{
			int mid = (left + right)/2;
			
			mergeSort(o, left, mid);
			mergeSort(o, mid+1, right);
			merge(o, left, right);
		}
	}
	static void merge(Object[] o, int left, int right)
	{
		Object[] list = new Object[o.length];
		
		int mid = (left + right)/2;
		int i = left;
		int j = mid+1;
		int k = left;
		
		while(i <= mid && j <= right)
		{
			if(((Atomic)o[i]).time <  ((Atomic)o[j]).time)
			{
				list[k++] =  o[i++];
			}
			else
			{
				list[k++] =  o[j++];
			}
		}
		
		for(;i<=mid;)
		{
			list[k++] = o[i++];
		}
		for(;j<=right;)
		{
			list[k++] = o[j++];
		}
		
		for(k=left;k<=right;k++)
		{
			o[k] = list[k];
		}
	}
	
	static int whenCrushed(Atomic first, Atomic second)
	{
		//같은 축 반대 반향 으로 움직이는 경우들
		if(first.dir==0 && second.dir==1)//상하
			return Math.abs((second.x - first.x) / 2);
		if(first.dir==1 && second.dir==0)//하상
			return Math.abs((second.x - first.x) / 2);
		if(first.dir==2 && second.dir==3)//좌우
			return Math.abs((second.y - first.y) / 2);
		if(first.dir==3 && second.dir==2)//우좌
			return Math.abs((second.y - first.y) / 2);
				
		//다른 축방향으로 움직이는 경우들
		if(first.dir==0 && second.dir==2)//상좌
			return Math.abs(first.x - second.x);
		if(first.dir==2 && second.dir==0)//좌상
			return Math.abs(first.x - second.x);
		if(first.dir==0 && second.dir==3)//상우
			return Math.abs(first.x - second.x);
		if(first.dir==3 && second.dir==0)//우상
			return Math.abs(first.x - second.x);
		if(first.dir==1 && second.dir==2)//하좌
			return Math.abs(first.x - second.x);
		if(first.dir==2 && second.dir==1)//좌하
			return Math.abs(first.x - second.x);
		if(first.dir==1 && second.dir==3)//하우
			return Math.abs(first.x - second.x);
		if(first.dir==3 && second.dir==1)//우하
			return Math.abs(first.x - second.x);
		
		return 0;
	}
	
	//두 원자가 충돌하는가
	static boolean isCrushed(Atomic first, Atomic second)
	{
		//같은 축 같은 방향
		if(first.dir == second.dir)
			return false;
		//같은 축 반대 반향 으로 움직이는 경우들
		if(first.dir==0 && second.dir==1)//상하
		{
			if(first.x < second.x)
				return false;
			else
			{
				if(first.y != second.y)
					return false;
			}
		}
		if(first.dir==1 && second.dir==0)//하상
		{
			if(first.x > second.x)
				return false;
			else
			{
				if(first.y != second.y)
					return false;
			}
		}
		if(first.dir==2 && second.dir==3)//좌우
		{
			if(first.y < second.y)
				return false;
			else
			{
				if(first.x != second.x)
					return false;
			}
		}
		if(first.dir==3 && second.dir==2)//우좌
		{
			if(first.y > second.y)
				return false;
			else
			{
				if(first.x != second.x)
					return false;
			}
		}
		
		//다른 축방향으로 움직이는 경우들
		if(first.dir==0 && second.dir==2)//상좌
		{
			if(first.x <= second.x || first.y >= second.y)
				return false;
			else
			{
				if(Math.abs(first.x - second.x) != Math.abs(first.y - second.y))
					return false;
			}
		}
		if(first.dir==2 && second.dir==0)//좌상
		{
			if(first.x >= second.x || first.y <= second.y)
				return false;
			else
			{
				if(Math.abs(first.x - second.x) != Math.abs(first.y - second.y))
					return false;
			}
		}
		if(first.dir==0 && second.dir==3)//상우
		{
			if(first.x <= second.x || first.y <= second.y)
				return false;
			else
			{
				if(Math.abs(first.x - second.x) != Math.abs(first.y - second.y))
					return false;
			}
		}
		if(first.dir==3 && second.dir==0)//우상
		{
			if(first.x >= second.x || first.y >= second.y)
				return false;
			else
			{
				if(Math.abs(first.x - second.x) != Math.abs(first.y - second.y))
					return false;
			}
		}
		//
		if(first.dir==1 && second.dir==2)//하좌
		{
			if(first.x >= second.x || first.y >= second.y)
				return false;
			else
			{
				if(Math.abs(first.x - second.x) != Math.abs(first.y - second.y))
					return false;
			}
		}
		if(first.dir==2 && second.dir==1)//좌하
		{
			if(first.x <= second.x || first.y <= second.y)
				return false;
			else
			{
				
				if(Math.abs(first.x - second.x) != Math.abs(first.y - second.y))
					return false;
			}
		}
		if(first.dir==1 && second.dir==3)//하우
		{
			
			if(first.x >= second.x || first.y <= second.y)
			{
				return false;
			}
			else
			{
				if(Math.abs(first.x - second.x) != Math.abs(first.y - second.y))
					return false;
			}
		}
		if(first.dir==3 && second.dir==1)//우하
		{
			if(first.x <= second.x || first.y >= second.y)
				return false;
			else
			{
				if(Math.abs(first.x - second.x) != Math.abs(first.y - second.y))
					return false;
			}
		}
		return true;
	}
	

	
	static int getX(int x)
	{
		return 2000 - x;
	}
	static int getY(int y)
	{
		return 2000 + y;
	}
	
	static class Atomic
	{
		int x, y;
		int time;
		int energy;
		int dir;
		int num;
		
		public Atomic(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		public Atomic(int x, int y, int time)
		{
			this.x = x;
			this.y = y;
			this.time = time;
		}
		public Atomic(int x, int y, int time, int energy, int dir, int num) {
			
			this.x = x;
			this.y = y;
			this.time = time;
			this.energy = energy;
			this.dir = dir;
			this.num = num;
		}

	}
	
	//핀볼
	static void s5650() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int  T = Integer.parseInt(b.readLine());
		
		for(int t=1;t<=T;t++)
		{
			int N = Integer.parseInt(b.readLine());
			
			int map[][] = new int[N][N];
		
			PinBall[][] wormhole = new PinBall[11][2];//6~10, 2개
			
			
			Queue<PinBall> start_q = new LinkedList<>();
			
			for(int i=0;i<N;i++)
			{
				StringTokenizer strtok = new StringTokenizer(b.readLine());
				for(int j=0;j<N;j++)
				{
					map[i][j] = Integer.parseInt(strtok.nextToken());
					
					//출발점
					if(map[i][j] == 0)
					{
						start_q.offer(new PinBall(i, j));
					}
					else if(map[i][j] >= 6)//웜홀
					{
						if(wormhole[map[i][j]][0] == null)
							wormhole[map[i][j]][0] = new PinBall(i, j);
						else
							wormhole[map[i][j]][1] = new PinBall(i, j);
					}
				}
			}
			// block : 1~5 , 웜홀 : 6~10, 블랙홀: -1
			
			
			int max = 0;
			
			while(!start_q.isEmpty())
			{
				Queue<PinBall> q = new LinkedList<>();
				
				PinBall p = start_q.poll();
				int start_x = p.x;
				int start_y = p.y;
	
				for(int i=0;i<4;i++)
					q.offer(new PinBall(p.x, p.y, i, 0, 0));
				
				while(!q.isEmpty())
				{
					 p = q.poll();
					 int x = p.x;
					 int y = p.y;
					 int dir = p.dir;//0123 동서남북
					 int score = p.score;
					 int depth = p.depth;
	
					 if(x == start_x && y == start_y)
					 {
						 if(p.depth!=0)//시작 위치로 돌아옴
						 {
							max = Math.max(max, score);
							continue;
						 }
					 }
						 
					 if(map[x][y] == -1)//블랙홀
					 {
						 max = Math.max(max, score);
						 continue;
					 }
					 
			
					 int x2=0,y2=0;
					 
					 if(dir == 0)//동쪽
					 {
						 x2 = x;
						 y2 = y+1;
						 
						 if(y != N-1)
						 {
							 if(isReverse(dir, map[x2][y2]))
							 {
								max = Math.max(max, 2 * score +1 );
								continue;
							}
							 processVisit(map, wormhole, x2, y2, dir, score, depth ,q);
						 }
						 else//벽만나서 되돌아감
						 {
							 max = Math.max(max, 2 * score +1 );
							 continue;
						 }
					 }
					 else if(dir == 1)//서쪽
					 {
						 x2 = x;
						 y2 = y-1;
						 
						 if(y != 0)
						 {
							if(isReverse(dir, map[x2][y2]))
							{
								max = Math.max(max, 2 * score +1 );
								continue;
							}
							processVisit(map, wormhole, x2, y2, dir, score, depth ,q);
						 }
						 else//벽만나서 되돌아감
						 {
							 max = Math.max(max, 2 * score +1 );
							 continue;
						 }
					 }
					 else if(dir == 2)//남쪽
					 {
						 x2 = x+1;
						 y2 = y;
						 
						 if(x != N-1)
						 {
							if(isReverse(dir, map[x2][y2]))
							{
								max = Math.max(max, 2 * score +1 );
								continue;
							}
							processVisit(map, wormhole, x2, y2, dir, score, depth ,q);
						 }
						 else//벽만나서 되돌아감
						 {
							 max = Math.max(max, 2 * score +1 );
							 continue;
						 }
					 }
					 else if(dir == 3)//북쪽
					 {
						 x2 = x-1;
						 y2 = y;
						 
						 if(x != 0)
						 {
							if(isReverse(dir, map[x2][y2]))
							{
								max = Math.max(max, 2 * score +1 );
								continue;
							}
							processVisit(map, wormhole, x2, y2, dir, score, depth ,q);
						 }
						 else//벽만나서 되돌아감
						 {
							 max = Math.max(max, 2 * score +1 );
							 continue;
						 }
					 }
				}
				
			}
			
			System.out.println("#"+t+" "+max);
		}
	}
	
	static boolean isBlock(int map_num)
	{
		if(0 < map_num && map_num < 6)
			return true;
		else
			return false;
	}
	static boolean isWormhole(int map_num)
	{
		if(map_num >= 6)
			return true;
		else
			return false;
	}
	
	static void processVisit(int [][] map,PinBall[][] wormhole,int x, int y, int dir, int score,int depth ,Queue<PinBall> q)
	{
		
			PinBall ball = new PinBall(x, y, dir, score, depth+1);
				
			if(isBlock(map[ball.x][ball.y]))//block 인 경우 방향 변경
			{
				ball.dir = processBlock(ball.dir, map[ball.x][ball.y]);
				ball.score++;
			}
			if(isWormhole(map[ball.x][ball.y]))//웜홀
			{
				//위치변경
				int[] pos = processWormhole(map[ball.x][ball.y], ball.x, ball.y, wormhole);
				ball.x = pos[0];
				ball.y = pos[1];
			}
				
			q.offer(ball);
	}
	static boolean isReverse(int dir, int block)
	{
		if(dir==0)//동
		{
			if(block == 1 || block == 2 || block == 5)//벽
				return true;
		}
		else if(dir==1)//서
		{
			if(block == 3 || block == 4 || block == 5)//벽
				return true;
		}
		else if(dir==2)//남
		{
			if(block == 2 || block == 3 || block == 5)//벽
				return true;
		}
		else//북
		{
			if(block == 1 || block == 4 || block == 5)//벽
				return true;
		}
		return false;
	}
	
	static int[] processWormhole(int hole_num,int x2, int y2, PinBall wormhole[][])
	{
		int[] pos = new int[2];
		
		if(wormhole[hole_num][0].x == x2 && wormhole[hole_num][0].y == y2)
		{
			pos[0] = wormhole[hole_num][1].x;
			pos[1] = wormhole[hole_num][1].y;
		}
		else
		{
			pos[0] = wormhole[hole_num][0].x;
			pos[1] = wormhole[hole_num][0].y;
		}
		return pos;
	}
	
	static int processBlock(int dir, int block)
	{
		if(dir==0)//동
		{
			if(block == 1 || block == 2 || block == 5)//벽
				return 1;
			else if(block == 3)//남쪽으로 꺾임
				return 2;
			else//북쪽으로 꺾임
				return 3;
			
		}
		else if(dir==1)//서
		{
			if(block == 3 || block == 4 || block == 5)//벽
				return 0;
			else if(block == 2)//남쪽으로 꺾임
				return 2;
			else//북쪽으로 꺾임
				return 3;
		}
		else if(dir==2)//남
		{
			if(block == 2 || block == 3 || block == 5)//벽
				return 3;
			else if(block == 1)//동쪽으로 꺾임
				return 0;
			else//서쪽으로 꺾임
				return 1;
		}
		else//북
		{
			if(block == 1 || block == 4 || block == 5)//벽
				return 2;
			else if(block == 2)//동쪽으로 꺾임
				return 0;
			else//서쪽으로 꺾임
				return 1;
		}
	}
	
	static class PinBall{
		int x,y;
		int dir;
		int score;
		int depth;
		public PinBall(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		public PinBall(int x, int y, int dir)
		{
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		public PinBall(int x, int y, int dir, int score)
		{
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.score = score;
		}
		public PinBall(int x, int y, int dir, int score, int depth)
		{
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.score = score;
			this.depth = depth;
		}
	}
	
	static void s5653() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int  T = Integer.parseInt(b.readLine());
		
		for(int t=0;t<T;t++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			int N = Integer.parseInt(strtok.nextToken());
			int M = Integer.parseInt(strtok.nextToken());
			int K = Integer.parseInt(strtok.nextToken());
			
			int m_size = 700;
			int map[][] = new int[m_size][m_size]; // 초기배양상태
			int activate[][] = new int[m_size][m_size];//활성화시간 표시
			int dead[][] = new int[m_size][m_size];//죽는 시간
			int s_time[][] = new int[m_size][m_size];//시작 시간
			
			
			ArrayList<Cell> s_list = new ArrayList<>();
			
			for(int i = m_size/2-N;i < m_size / 2; i++)
			{
				strtok = new StringTokenizer(b.readLine());
				for(int j = m_size/2 - M;j < m_size / 2; j++)
				{
					map[i][j] = Integer.parseInt(strtok.nextToken());
					
					if(map[i][j]>0)
					{
						activate[i][j] = map[i][j];
						dead[i][j] = map[i][j] * 2;
						s_list.add(new Cell(i, j));
					}
				}
			}
			
			//K 시간 시뮬레이터
			for(int k=1;k<=K;k++)
			{
				ArrayList<Cell> n_list = new ArrayList<>();
				for(int s=0;s<s_list.size();s++)
				{
					Cell cell = s_list.get(s);
					int i = cell.x;
					int j = cell.y;
					if(activate[i][j] < k && k <= dead[i][j])//활성화 됐고 살아있는 애들이면 번식
					{
						int i2=0,j2=0;
						if(i!=0)
						{
							i2 = i-1;
							j2 = j;
							breeding(k, i, j, i2, j2, map, activate, dead, s_time, n_list);
							
						}
						if(i!=m_size-1)
						{
							i2 = i+1;
							j2 = j;
							breeding(k, i, j, i2, j2, map, activate, dead, s_time, n_list);
						}
						if(j!=0)
						{
							i2 = i;
							j2 = j-1;
							breeding(k, i, j, i2, j2, map, activate, dead, s_time, n_list);
						}
						if(j!=m_size-1)
						{
							i2 = i;
							j2 = j+1;
							breeding(k, i, j, i2, j2, map, activate, dead, s_time, n_list);
						}
					}
					else
					{
						if(k < dead[i][j])
							n_list.add(cell);
					}
						
				}
				s_list = new ArrayList<>(n_list);

			}
			
			int count=0;
			for(int i=0;i<m_size;i++)
			{
				for(int j=0;j<m_size;j++)
				{
					if(dead[i][j] > K && map[i][j] > 0)
						count++;
					
				}
			}
			System.out.println("#"+String.valueOf(t+1)+" "+count);
			
		}
	}
	
	
	static void breeding(int k, int i, int j,int i2, int j2, int[][] map, int activate[][], int dead[][],int s_time[][], ArrayList<Cell> n_list)
	{
		//번식시키는 경우
		if(map[i2][j2] == 0 || (s_time[i2][j2] == k && map[i2][j2] < map[i][j] && dead[i2][j2] > k))//번식 안되있으면 바로 번식시킴 || 번식되어있어도 동시 방문 && 내가 더 높음
		{
			map[i2][j2] = map[i][j];
			s_time[i2][j2] = k;
			activate[i2][j2] = k + map[i2][j2];
			dead[i2][j2] = k + map[i2][j2] * 2;
			n_list.add(new Cell(i2, j2));
		}
		
	}
	
	static class Cell{
		
		int x,y;
		public Cell()
		{
			
		}
		public Cell(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
	}

	static void s5656() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int  T = Integer.parseInt(b.readLine());
		
		for(int t=0;t<T;t++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			int N = Integer.parseInt(strtok.nextToken());
			int W = Integer.parseInt(strtok.nextToken());
			int H = Integer.parseInt(strtok.nextToken());
			
			int map[][] = new int[H][W];
			
			int origin_blocks = 0;//원래 블록수
			
			for(int i=0;i<H;i++)
			{
				strtok = new StringTokenizer(b.readLine());
				for(int j=0;j<W;j++)
				{
					map[i][j] = Integer.parseInt(strtok.nextToken());
					if(map[i][j] != 0)
						origin_blocks++;
				}
			}
			
			ArrayList<Integer> counts = new ArrayList<>();
			A_s5656(N, map, H, W, counts);
			
			int max = Integer.MIN_VALUE;
			int result = 0;
			for(int j=0;j<counts.size();j++)
			{
				
				if(max < origin_blocks-counts.get(j))
				{
					max = origin_blocks-counts.get(j);
					result = counts.get(j);
				}
			}
			
			System.out.println("#"+String.valueOf(t+1) +" "+result);
		}
	}
	
	static void A_s5656(int N, int map[][], int H, int W, ArrayList<Integer> counts)
	{
		if(N==0)
		{
			counts.add(count_blocks(map, H, W));		
		}
		else
		{
			for(int j=0;j<W;j++)
			{
				for(int i=0;i<H;i++)
				{
					if(map[i][j] != 0)//맨위에 있는 벽돌만 폭파
					{
						int map2[][] = new int[H][W];
						for(int k=0;k<H;k++)
							map2[k] = map[k].clone();
						
						boolean visited[][] = new boolean[H][W];
						explosive(map2, visited,i, j, H, W);//폭파할 벽돌들 구하기
						for(int h=0;h<H;h++)
						{
							for(int w=0;w<W;w++)
							{
								if(visited[h][w])//폭파
									map2[h][w] = 0;
							}
						}
						dropBlocks(map2, H, W);//벽돌 드랍
						
						A_s5656(N-1, map2, H, W, counts);
						break;
					}
				}
				
			
			}
		}
	}
	//남은 블록 세기
	static int count_blocks(int map[][], int H, int W)
	{
		int count=0;
		for(int i=0;i<H;i++)
		{
			
			for(int j=0;j<W;j++)
			{
				if(map[i][j] != 0)
					count++;
			}
			
			
		}
		return count;
	}
	
	static void explosive(int map[][],boolean visited[][] ,int x,int y, int H,int W)
	{
		int n = map[x][y];
		
		//연쇄적으로 터지는 블록들 위치 추가
		ArrayList<Integer> h = new ArrayList<>();
		ArrayList<Integer> w = new ArrayList<>();
		
		for(int i=x;i<x+n;i++)
		{
			if(i!=H)
			{
				if(map[i][y] != 0 && map[i][y] != 1)
				{
					if(i!=x)//지금 위치 추가 x
					{
						if(!visited[i][y])//중복 추가 방지
						{
							h.add(i);
							w.add(y);
						}
					}
				}
				visited[i][y] = true;
				
			}
			else
				break;	
		}
		
		for(int i=x;i>x-n;i--)
		{
			if(i!=-1)
			{
				if(map[i][y] != 0 && map[i][y] != 1)
				{
					if(i!=x)//지금 위치 추가 x
					{
						if(!visited[i][y])//중복 추가 방지
						{
							h.add(i);
							w.add(y);
						}
					}
				}
				visited[i][y] = true;
			}
			else
				break;
			
		}
		
		for(int j=y;j<y+n;j++)
		{
			if(j!=W)
			{
				if(map[x][j] != 0 && map[x][j] != 1)
				{
					if(j!=y)//지금 위치 추가 x
					{
						if(!visited[x][j])//중복 추가 방지
						{
							h.add(x);
							w.add(j);
						}
					}
				}
				visited[x][j] = true;
			}
			else
				break;
			
		}
		
		for(int j=y;j>y-n;j--)
		{
			if(j!=-1)
			{
				if(map[x][j] != 0 && map[x][j] != 1)
				{
					if(j!=y)//지금 위치 추가 x
					{
						if(!visited[x][j])//중복 추가 방지
						{
							h.add(x);
							w.add(j);
						}
					}
				}
				visited[x][j] = true;
			}
			else
				break;
			
		}
		
		for(int i=0;i<h.size();i++)
			explosive(map,visited ,h.get(i), w.get(i), H, W);//이 후로 연속으로 터질 수 있는 애들 explosive 시킴
	}
	
	//벽돌 떨구기
	static void dropBlocks(int map[][], int H, int W)
	{
		
		for(int j=0;j<W;j++)
		{
			for(int i=H-1;i>=0;i--)
			{
				if(map[i][j] != 0 & i!=H-1)//drop해야함
				{
					for(int k=i+1;k<H;k++)
					{
						if(map[k][j] != 0)//멈춤
							break;
						else
						{
							map[k][j] = map[k-1][j];
							map[k-1][j] = 0;
						}
					}
				}
			}
		}
	}
	
	static void s5658() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int  T = Integer.parseInt(b.readLine());
		
		for(int t=0;t<T;t++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			int N = Integer.parseInt(strtok.nextToken());//숫자 4의 배수
			int K = Integer.parseInt(strtok.nextToken());//K번째로 큰 숫자
			char numbers[] = new char[N+1];
			
			String str = b.readLine();
			for(int i=1;i<=N;i++)
			{
				numbers[i] = str.charAt(i-1);
			}
			
			HashSet<Integer> set = new HashSet<>();
			
			int list[] = bigNum(numbers, N);
			
			for(int i=0;i<list.length;i++)
			{
				set.add(list[i]);
			}
			
			for(int i=0;i<N/4;i++)
			{
				moveNext(numbers, N);
				list = bigNum(numbers, N);
				
				for(int j=0;j<list.length;j++)
				{
					set.add(list[j]);
				}
			
			}
			
			Object[] o = set.toArray();
			
			Arrays.sort(o);
			System.out.println("#"+String.valueOf(t+1)+" "+(int)o[o.length-K]);
		}
	}
	
	static int[] bigNum(char numbers[], int N)
	{
		int round = N/4;
		
		int list[] = new int[4];
		
		for(int i=1;i<=4;i++)
		{
			String num = "";
			for(int j = round *(i-1) + 1; j<=round * i;j++ )
			{
				num += numbers[j]+"";
			}
		
			list[i-1] = HexToint(num);
		}
		return list;
	}
	static int HexToint(String hex)
	{
		int num = 0;
		for(int i=0;i<hex.length();i++)
		{
			int p = hex.length() - i - 1;
			char c = hex.charAt(i);
			int n = 0;
			
			if(Character.isAlphabetic(c))
			{
				n = c-55;
			}
			else
			{
				n = c - 48;
			}
			
			num += (int)Math.pow(16, p) * n;
			
		}
		
		return num;
	}
	static void moveNext(char numbers[], int N)
	{
		for(int i=N;i>=1;i--)
		{
			if(i==N)
				numbers[0] = numbers[i];
			numbers[i] = numbers[i-1];
		}
	}

}
