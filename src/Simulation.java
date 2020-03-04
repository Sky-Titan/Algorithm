import java.io.BufferedReader;
import java.io.InputStreamReader;
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
