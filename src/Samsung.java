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
