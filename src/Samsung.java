import java.awt.HeadlessException;
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
						for(int k=0;k<4;k++)//동서남북
						{
							start_q.offer(new PinBall(i, j, k, 0));
						}
						
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
			int count=0;
			while(!start_q.isEmpty())
			{
				boolean visited[][][] = new boolean[N][N][4];//맵 위치 + 동서남북
				Queue<PinBall> q = new LinkedList<>();
				
				PinBall p = start_q.poll();
				int start_x = p.x;
				int start_y = p.y;
				//visited[p.x][p.y][p.dir] = true; 시작 위치는 다시 돌아올수 있음
				q.offer(p);
				//System.out.println("start x : "+start_x +" start y : "+start_y + " start dir : "+p.dir);
				count++;
				while(!q.isEmpty())
				{
					 p = q.poll();
					 int x = p.x;
					 int y = p.y;
					 int dir = p.dir;//0123 동서남북
					 int score = p.score;
					 //System.out.println("x : "+x+" y : "+y +" dir : "+dir);
					 if(visited[x][y][dir] && x == start_x && y == start_y )//시작 위치로 돌아옴
					 {
						 //System.out.println("score : "+score);
						 max = Math.max(max, score);
						 break;
					 }
					 if(map[x][y] == -1)//블랙홀
					 {
						 //System.out.println("score : "+score);
						 max = Math.max(max, score);
						 break;
					 }
					 
					 int x2=0,y2=0;
					 if(dir == 0 && y != N-1)//동쪽
					 {
						 x2 = x;
						 y2 = y+1;
						 
						 processVisit(visited, map, wormhole, x2, y2, dir, score, q);
					 }
					 else if(dir == 0 && y == N-1)
					 {
						 if(!visited[x][y][1])
						 {
							 PinBall ball = new PinBall(x, y, 1, score+1);
							 //System.out.println("스코어 추가 : "+x+" "+y);
							 visited[x][y][1] = true;
							 q.offer(ball);
						 }
					 }
					 
					 if(dir == 1 && y != 0)//서쪽
					 {
						 x2 = x;
						 y2 = y-1;
						 
						 processVisit(visited, map, wormhole, x2, y2, dir, score, q);
					 }
					 else if(dir == 1 && y==0)
					 {
						 if(!visited[x][y][0])
						 {
							 PinBall ball = new PinBall(x, y, 0, score+1);
							 //System.out.println("스코어 추가 : "+x+" "+y);
							 visited[x][y][0] = true;
							 q.offer(ball);
						 }
					 }
					 
					 if(dir == 2 && x != N-1)//남쪽
					 {
						 x2 = x+1;
						 y2 = y;
						 
						 processVisit(visited, map, wormhole, x2, y2, dir, score, q);
					 }
					 else if(dir==2 && x==N-1)
					 {
						 if(!visited[x][y][3])
						 {
							 PinBall ball = new PinBall(x, y, 3, score+1);
							// System.out.println("스코어 추가 : "+x+" "+y);
							 visited[x][y][3] = true;
							 q.offer(ball);
						 }
					 }
					 
					 if(dir == 3 && x != 0)//북쪽
					 {
						 x2 = x-1;
						 y2 = y;
						 
						 processVisit(visited, map, wormhole, x2, y2, dir, score, q);
					 }
					 else if(dir == 3 && x==0)
					 {
						 if(!visited[x][y][2])
						 {
							 PinBall ball = new PinBall(x, y, 2, score+1);
							// System.out.println("스코어 추가 : "+x+" "+y);
							 visited[x][y][2] = true;
							 q.offer(ball);
						 }
					 }
				}
				
			}
			
			System.out.println("#"+t+" "+max);
		}
	}
	static void processVisit(boolean[][][] visited,int [][] map,PinBall[][] wormhole,int x2, int y2, int dir, int score ,Queue<PinBall> q)
	{
		if(!visited[x2][y2][dir])
		 {
			 PinBall ball = new PinBall(x2, y2, dir, score);
			 visited[x2][y2][dir] = true;
			 
			 if(0 < map[x2][y2] && map[x2][y2] < 6)//block 인 경우 방향 변경
			 {
				 ball.dir = processBlock(ball.dir, map[x2][y2]);
				 //System.out.println("스코어 추가 : "+x2+" "+y2);
				 ball.score++;
			 }
			 else if(6 <= map[x2][y2])//웜홀
			 {
				 //위치변경
				 int[] pos = processWormhole(map[x2][y2], x2, y2, wormhole);
				 ball.x = pos[0];
				 ball.y = pos[1];
			 }
			 
			 visited[ball.x][ball.y][ball.dir] = true;
			 q.offer(ball);
		 }
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
		int n_dir = 0;
		
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
