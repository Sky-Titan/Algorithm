import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Simulation {

	static class Position{
		
		int x,y;
		public Position()
		{
			
		}
		public Position(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
	}
	
	static void bj3190() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());//맵 크기
		int K = Integer.parseInt(b.readLine());//사과 개수
		
		int map[][] = new int[N+1][N+1];
		for(int i=0;i<=N;i++)
		{
			for(int j=0;j<=N;j++)
				map[i][j] = 0;
		}
		
		for(int k=0;k<K;k++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			int x = Integer.parseInt(strtok.nextToken());
			int y = Integer.parseInt(strtok.nextToken());
			
			map[x][y] = -1;//사과
		}
		
		int L = Integer.parseInt(b.readLine());//뱀 방향전환 횟수
		ArrayList<Integer> time = new ArrayList<>();
		char direction[] = new char[L];
		
		for(int l = 0; l < L; l++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			time.add( Integer.parseInt(strtok.nextToken()) );
			direction[l] = strtok.nextToken().charAt(0);
		}
		
		int result = 0;//걸린시간
		map[1][1] = 1;//뱀 위치
		
		Position head = new Position(1,1);
		Position tail = new Position(1,1);
		
		
		
		int d = 4;//1 : 북쪽, 2: 남쪽, 3:서쪽, 4:동쪽 -> 머리 이동방향
		while(true)
		{
			result++;//시간증가
			
			int former_head = map[head.x][head.y];
			
			if(d==1)//북쪽 이동
			{
				if(head.x!=1)
					head.x -= 1;
				else//벽이면 중단
					break;
			}
			else if(d==2)//남쪽 이동
			{
				if(head.x!=N)
					head.x += 1;
				else
					break;
			}
			else if(d==3)//서쪽 이동
			{
				if(head.y!=1)
					head.y -= 1;
				else
					break;
			}
			else//동쪽
			{
				if(head.y!=N)
					head.y += 1;
				else
					break;
			}
			
			
			
			int apple = map[head.x][head.y];
			
			if(map[head.x][head.y] > 0)//뱀의 몸이면 중단
				break;
			else
				map[head.x][head.y] = former_head+1;
			
			if(apple != -1)//사과 없으면 꼬리 이동
			{
				int former_tail = map[tail.x][tail.y];
				
				map[tail.x][tail.y] = 0;//이동
				
				if(tail.x != 1 && map[tail.x-1][tail.y] == former_tail + 1)
					tail.x -= 1;
				else if(tail.x != N && map[tail.x+1][tail.y] == former_tail + 1)
					tail.x += 1;
				else if(tail.y != N && map[tail.x][tail.y+1] == former_tail + 1)
					tail.y += 1;
				else if(tail.y != 1 && map[tail.x][tail.y-1] == former_tail + 1)
					tail.y -= 1;
				
			}
			
			
			
			if(time.contains(result))//방향전환 하는 시간인지 확인
			{
				if(direction[time.indexOf(result)] == 'L')//왼쪽으로 회전
				{
					if(d==1)//북
						d = 3;
					else if(d==2)//남
						d = 4;
					else if(d==3)//서
						d = 2;
					else//동
						d = 1;
				}
				else//오른쪽으로 회전
				{
					if(d==1)//북
						d = 4;
					else if(d==2)//남
						d = 3;
					else if(d==3)//서
						d = 1;
					else//동
						d = 2;
				}
			}
			
			
		}
		System.out.println(result);
	}
	
	static void bj14499() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		int x = Integer.parseInt(strtok.nextToken());
		int y = Integer.parseInt(strtok.nextToken());
		int K = Integer.parseInt(strtok.nextToken());
		
		int map[][] = new int[N][M];//지도
		
		
		for(int i=0;i<N;i++)
		{
			strtok = new StringTokenizer(b.readLine());
			for(int j=0;j<M;j++)
			{
				map[i][j] = Integer.parseInt(strtok.nextToken());
			}
		}
		
		strtok = new StringTokenizer(b.readLine());
		
		int cube[][] = {
				{0,0,0}, {0,0,0}, {0,0,0}, {0,0,0}
		};//주사위
		
		for(int k=0;k<K;k++)
		{
			int order = Integer.parseInt(strtok.nextToken());
			
			
			if(order==1)//동쪽
			{
				if(y != M - 1)
					y = y + 1;
				else
					continue;
			}
			else if(order==2)//서쪽
			{
				if(y != 0)
					y = y - 1;
				else
					continue;
			}
			else if(order==3)//북쪽
			{
				if(x != 0)
					x = x - 1;
				else
					continue;
			}
			else//남쪽
			{
				if(x != N - 1)
					x = x + 1;
				else
					continue;
			}
			cube_change(cube, order);
			
			
			if(map[x][y] == 0)//주사위 바닥면[3][1] 숫자 -> 칸[x][y]
			{
				map[x][y] = cube[3][1];
			}
			else//칸[x][y] 숫자 -> 주사위 바닥면[3][1] (칸 = 0)
			{
				cube[3][1] = map[x][y];
				map[x][y] = 0;
			}

			
			System.out.println(cube[1][1]);//주사기 윗면 숫자 출력
		}
		
		
	}
	
	static void cube_change(int cube[][], int direction)//주사위 굴리기
	{
		//1 : 동쪽, 2 : 서쪽, 3 : 북쪽, 4 : 남쪽
		if(direction==1)
		{
			int temp = cube[1][2];
			for(int i=2;i>=1;i--)
			{
				cube[1][i] = cube[1][i-1];
				
			}
			
			cube[1][0] = cube[3][1];
			cube[3][1] = temp;
		}
		else if(direction==2)
		{
			int temp = cube[1][0];
			
			for(int i=0;i<=1;i++)
			{
				cube[1][i] = cube[1][i+1];	
			}
			
			cube[1][2] = cube[3][1];
			cube[3][1] = temp;
		}
		else if(direction==3)
		{
			int temp = cube[0][1];
			
			for(int i=0;i<=2;i++)
			{
				cube[i][1] = cube[i+1][1];
			}
			
			cube[3][1] = temp;
		}
		else
		{
			int temp = cube[3][1];
			
			for(int i=3;i>=1;i--)
			{
				cube[i][1] = cube[i-1][1];
			}
			cube[0][1] = temp;
		}
		
	}
	
	static void bj1018() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());

		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		char chess[][] = new char[N][M];
		
		for(int i=0;i<N;i++)
		{
			String str = b.readLine();
			for(int j=0;j<M;j++)
			{
				chess[i][j] = str.charAt(j);
			}
		}

		int result=N*M+1;//결과 : 최소 칠해야할 체스 블록 개수

		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				if(N - i >= 8 && M - j >= 8)//8 * 8로 자른 경우
				{
					//1. 왼쪽위 : B 인경우 2. 왼쪽위 : W인 경우
					char start_color[] = {'B', 'W'};
					
					for(int k=0;k<2;k++)
					{
						char former = start_color[k];
						int count=0;//색칠 개수 
						
						for(int n = i;n < i + 8; n++)
						{
							for(int m = j; m < j + 8; m++)
							{
								if(n == i && m == j)//시작지점
								{
									if(chess[n][m] != start_color[k])//지정 색깔과 다르면 증가
										count++;
								}
								else//다른 지점
								{
									if(chess[n][m] == former)//칠해야 되는 경우
										count++;
									
									if(former == 'B')
										former = 'W';
									else
										former = 'B';
								}
							}
							//한줄끝나면 색바꾸기
							if(former == 'B')
								former = 'W';
							else
								former = 'B';
						}
						
						//케이스 하나 끝날때마다 비교
						result = Math.min(result, count);
					}
				}
			}
		}
		System.out.println(result);
	}
	
	static void bj14503() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		strtok = new StringTokenizer(b.readLine());
		int x = Integer.parseInt(strtok.nextToken());
		int y = Integer.parseInt(strtok.nextToken());
		int direction = Integer.parseInt(strtok.nextToken());
		
		int map[][] = new int[N][M];
		
		for(int i=0;i<N;i++)
		{
			strtok = new StringTokenizer(b.readLine());
			for(int j=0;j<M;j++)
			{
				map[i][j] = Integer.parseInt(strtok.nextToken());
			}
		}
		
		int result = 0;//청소한 구역 개수
		
		Queue<Position> q = new LinkedList<>();
		map[x][y]=2;//청소 완료
		result++;//개수 증가
		q.offer(new Position(x, y));
		
		while(!q.isEmpty())
		{
			Position p = q.poll();
			
			int directionCnt = 0;
			int temp= changeDirection(direction);
			while(q.isEmpty())
			{
				directionCnt++;
				
				Position pos = newDirection(p.x, p.y, temp);//해당 방향 위치
				
				if(map[pos.x][pos.y]==0)
				{	
					map[pos.x][pos.y] = 2;
					direction = temp;
					result++;//개수 증가
					q.offer(pos);
					break;
				}
				
				if(q.isEmpty())
					temp = changeDirection(temp);
				
				if(directionCnt==4)//반대방향으로 후진
				{
					if(direction==0 && map[p.x+1][p.y] != 1)//북쪽
					{
						q.offer(new Position(p.x+1, p.y));
					}
					else if(direction==1 && map[p.x][p.y-1] != 1)//동쪽
					{
						q.offer(new Position(p.x, p.y-1));
					}
					else if(direction==2 && map[p.x-1][p.y] != 1)//남쪽
					{
						q.offer(new Position(p.x-1, p.y));
					}
					else if(direction==3 && map[p.x][p.y+1] != 1)//서쪽
					{
						q.offer(new Position(p.x, p.y+1));
					}
					break;
						
				}
				
			}
			
		}
		
		
		System.out.println(result);
	}
	
	static Position newDirection(int x, int y, int direction)
	{
		if(direction == 0)//북쪽
		{
			return new Position(x-1, y);
		}
		else if(direction == 1)//동쪽
		{
			return new Position(x, y+1);
		}
		else if(direction == 2)//남쪽
		{
			return new Position(x+1, y);
		}
		else//서쪽
		{
			return new Position(x, y-1);
		}
	}
	
	static int changeDirection(int direction)
	{
		if(direction!=0)
			return direction-1;
		else
			return 3;
	}
	
	static void bj1966() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int T = Integer.parseInt(b.readLine());
		
		for(int t=0;t<T;t++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			
			int N = Integer.parseInt(strtok.nextToken());//문서개수
			int M = Integer.parseInt(strtok.nextToken());//찾는 문서 위치
			
			int doc[] = new int[N];//중요도
			
			strtok = new StringTokenizer(b.readLine());

			for(int i=0;i<N;i++)
			{
				doc[i] = Integer.parseInt(strtok.nextToken());
			}
			
			int[] sorted = doc.clone();
			Arrays.sort(sorted);//오름차순 정렬
			
			int count=N-1;//내림차순으로
			
			for(int i=0;i<N;i++)
			{
				if(sorted[count] == doc[i])
				{
					if(i==M)
					{
						System.out.println(N-count);
						break;
					}
					
					count--;
				}
				
				if(i==N-1)
					i=-1;
					
			}
			
		}
	}
	
	static void bj1094() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int current = 0;
		int sum=0;
		
		int x = Integer.parseInt(b.readLine());
		
		for(int i=64;i>=1;i/=2)
		{
			if(i <= x)
			{
				if(sum + i == x)
				{	
					current++;
					break;
				}
				else if(sum + i < x)
				{
					current++;
					sum+=i;
				}
			}
		}
		
		System.out.println(current);
	}
	
	static void bj2455() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int current = 0;
		int max = current;
		for(int i=0;i<4;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			
			int out = Integer.parseInt(strtok.nextToken());
			int in = Integer.parseInt(strtok.nextToken());
			current -= out;
			current += in;
			
			max = Math.max(max, current);
		}
		
		System.out.println(max);
	}
	
	public Simulation()
	{
		
	}
	
}
