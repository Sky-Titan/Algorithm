import javafx.geometry.Pos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	static void solution() throws  Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer strtok = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());

		int[][] map = new int[N+1][M+1];

		int x[] = {-1, 1, 0, 0};
		int y[] = {0, 0, -1, 1};

		for(int i = 1;i <= N;i++)
		{
			String row = br.readLine();

			for(int j = 1;j <= M;j++)
			{
				map[i][j] = row.charAt(j-1)-'0';
			}
		}



		int distance = bfs(map, N, M);

		bw.write(distance+"");
		bw.close();
	}

	static int bfs(int[][] map, int N,int M)
	{
		boolean visited[][][] = new boolean[N+1][M+1][2];

		Queue<Position> queue = new LinkedList<>();
		queue.offer(new Position(1, 1, 1, false));
		visited[1][1][0] = true;
		visited[1][1][1] = true;

		int result = Integer.MAX_VALUE;

		while(!queue.isEmpty())
		{
			Position now = queue.poll();

			if(now.x == N && now.y == M)
			{
				result = Math.min(result, now.distance);
			}

			int x[] = {-1, 1, 0, 0};
			int y[] = {0, 0, -1, 1};

			for(int i = 0;i < x.length;i++)
			{
				int next_x = now.x + x[i];
				int next_y = now.y + y[i];

				if(0 < next_x && next_x <= N && 0 < next_y && next_y <= M)
				{
					//안 깬놈
					if(!now.destory)
					{
						if(!visited[next_x][next_y][0])
						{
							visited[next_x][next_y][0] = true;

							if(map[next_x][next_y] == 0)
								queue.offer(new Position(next_x, next_y, now.distance+1, now.destory));
							else
								queue.offer(new Position(next_x, next_y, now.distance+1, true));
						}
					}
					//벽 깨고 온 놈
					else
					{
						if(!visited[next_x][next_y][1])
						{
							visited[next_x][next_y][1] = true;

							if(map[next_x][next_y] == 0)
								queue.offer(new Position(next_x, next_y, now.distance+1, now.destory));
						}
					}
				}
			}
		}

		if(result == Integer.MAX_VALUE)
			return -1;
		return result;
	}


	static class Position{

		int x, y, distance;
		boolean destory = false;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Position(int x, int y, int distance, boolean destory) {
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.destory = destory;
		}

		@Override
		public boolean equals(Object obj) {
			Position pos = (Position)obj;

			if(x == pos.x && y == pos.y)
				return true;
			return false;
		}
	}


	public static void main(String[] args) {
		try
		{
			solution();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


}