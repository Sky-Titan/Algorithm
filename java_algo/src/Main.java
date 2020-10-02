
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int N, M;
	static boolean visited[][][][];
	static char[][] map;

	public static void solution() throws Exception
	{
		String[] list = br.readLine().split(" ");
		N = Integer.parseInt(list[0]);
		M = Integer.parseInt(list[1]);

		map = new char[N][M];
		visited = new boolean[N][M][N][M];

		Balls start = new Balls(0,0,0,0,0);

		for(int i = 0;i < N;i++)
		{
			String line = br.readLine();
			for(int j = 0;j < M;j++)
			{
				map[i][j] = line.charAt(j);

				if(map[i][j] == 'R')
				{
					start.r_x = i;
					start.r_y = j;
					map[i][j] = '.';
				}
				else if(map[i][j] == 'B')
				{
					start.b_x = i;
					start.b_y = j;
					map[i][j] = '.';
				}
			}
		}

		bw.write(bfs(start)+"");
	}

	static int bfs(Balls start)
	{
		Queue<Balls> queue = new LinkedList<>();
		visited[start.r_x][start.r_y][start.b_x][start.b_y] = true;
		queue.offer(start);

		while(!queue.isEmpty())
		{
			Balls now = queue.poll();

			//종료
			if(now.count > 10)
				return -1;
			if(map[now.r_x][now.r_y] == 'O')
				return now.count;

			//상하좌우 기울이기
			for(int i = 0;i < 4;i++)
			{
				int next[] = tilt(now, i);
				//System.out.println(now.toString());
				if(isInBoundary(next))
				{
					//방문 x && 파란 볼이 구멍에 안빠지면
					if(!visited[next[0]][next[1]][next[2]][next[3]] && map[next[2]][next[3]] != 'O')
					{
						visited[next[0]][next[1]][next[2]][next[3]] = true;
						queue.offer(new Balls(next[0],next[1], next[2], next[3], now.count + 1));
					}
				}
			}
		}

		return -1;
	}

	static boolean isInBoundary(int next[])
	{
		if(0 <= next[0] && next[0] < N && 0 <= next[1] && next[1] < M
				&& 0 <= next[2] && next[2] < N && 0 <= next[3] && next[3] < M)
			return true;
		return false;
	}

	static int[] tilt(Balls now, int dir)
	{
		int next[] = {now.r_x, now.r_y, now.b_x, now.b_y};

		//상하 좌우
		if(dir == 0)
		{
			//빨간공 먼저 기울이기
			if(now.r_x < now.b_x)
			{
				for(int i = now.r_x;i >= 0 && map[i][now.r_y] != '#'; i--)
				{
					next[0] = i;
					if(map[next[0]][next[1]] == 'O')
						break;
				}

				for(int i = now.b_x;i >= 0 && (map[i][now.b_y] != '#' && (next[0] != i || next[1] != now.b_y)) || map[i][now.b_y] == 'O'; i--)
				{
					next[2] = i;
					if(map[next[2]][next[3]] == 'O')
						break;
				}
			}
			else
			{
				for(int i = now.b_x;i >= 0 && map[i][now.b_y] != '#'; i--)
				{
					next[2] = i;
					if(map[next[2]][next[3]] == 'O')
						break;
				}

				for(int i = now.r_x;i >= 0 && (map[i][now.r_y] != '#' && (next[2] != i || next[3] != now.r_y)) || map[i][now.r_y] == 'O'; i--)
				{
					next[0] = i;
					if(map[next[0]][next[1]] == 'O')
						break;
				}
			}
		}
		//하
		else if(dir == 1)
		{
			//빨간공 먼저
			if(now.r_x > now.b_x)
			{
				for(int i = now.r_x;i < N && map[i][now.r_y] != '#'; i++)
				{
					next[0] = i;
					if(map[next[0]][next[1]] == 'O')
						break;
				}

				for(int i = now.b_x;i < N && (map[i][now.b_y] != '#' && (next[0] != i || next[1] != now.b_y)) || map[i][now.b_y] == 'O'; i++)
				{
					next[2] = i;
					if(map[next[2]][next[3]] == 'O')
						break;
				}
			}
			else
			{
				for(int i = now.b_x;i < N && map[i][now.b_y] != '#'; i++)
				{
					next[2] = i;
					if(map[next[2]][next[3]] == 'O')
						break;
				}

				for(int i = now.r_x;i < N && (map[i][now.r_y] != '#'&& (next[2] != i || next[3] != now.r_y)) || map[i][now.r_y] == 'O'; i++)
				{
					next[0] = i;
					if(map[next[0]][next[1]] == 'O')
						break;
				}
			}
		}
		//좌
		else if(dir == 2)
		{
			//빨간공 먼저
			if(now.r_y < now.b_y)
			{
				for(int j = now.r_y;j >= 0 && map[now.r_x][j] != '#'; j--)
				{
					next[1] = j;
					if(map[next[0]][next[1]] == 'O')
						break;
				}

				for(int j = now.b_y;j >= 0 && (map[now.b_x][j] != '#' && (next[0] != now.b_x || next[1] != j)) || map[now.b_x][j] == 'O'; j--)
				{
					next[3] = j;
					if(map[next[2]][next[3]] == 'O')
						break;
				}
			}
			else
			{
				for(int j = now.b_y;j >= 0 && map[now.b_x][j] != '#'; j--)
				{
					next[3] = j;
					if(map[next[2]][next[3]] == 'O')
						break;
				}

				for(int j = now.r_y;j >= 0 && (map[now.r_x][j] != '#' && (next[2] != now.r_x || next[3] != j)) || map[now.r_x][j] == 'O'; j--)
				{
					next[1] = j;
					if(map[next[0]][next[1]] == 'O')
						break;
				}
			}
		}
		//우
		else
		{
			//빨간공 먼저
			if(now.r_y > now.b_y)
			{
				for(int j = now.r_y;j < M && map[now.r_x][j] != '#'; j++)
				{
					next[1] = j;
					if(map[next[0]][next[1]] == 'O')
						break;
				}

				for(int j = now.b_y;j < M && (map[now.b_x][j] != '#' && (next[0] != now.b_x || next[1] != j)) || map[now.b_x][j] == 'O'; j++)
				{
					next[3] = j;
					if(map[next[2]][next[3]] == 'O')
						break;
				}
			}
			else
			{
				for(int j = now.b_y;j < M && map[now.b_x][j] != '#'; j++)
				{
					next[3] = j;
					if(map[next[2]][next[3]] == 'O')
						break;
				}

				for(int j = now.r_y;j < M && (map[now.r_x][j] != '#' && (next[2] != now.r_x || next[3] != j)) || map[now.r_x][j] == 'O'; j++)
				{
					next[1] = j;
					if(map[next[0]][next[1]] == 'O')
						break;
				}
			}
		}

		return next;
	}


	static class Balls
	{
		int r_x, r_y, b_x, b_y, count;

		public Balls(int r_x, int r_y, int b_x, int b_y, int count) {
			this.r_x = r_x;
			this.r_y = r_y;
			this.b_x = b_x;
			this.b_y = b_y;
			this.count = count;
		}

		@Override
		public String toString() {
			return "("+r_x+", "+r_y+"), ("+b_x+", "+b_y+"), count : "+count;
		}
	}



	public static void main(String[] args) {
		try
		{
			solution();
			bw.close();
			br.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}