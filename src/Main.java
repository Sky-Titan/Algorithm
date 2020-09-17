
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	static boolean visited[][];
	static int count = 0;

	public static void solution() throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] list = br.readLine().split(" ");
		int N = Integer.parseInt(list[0]);
		int M = Integer.parseInt(list[1]);


		list = br.readLine().split(" ");

		int start_x = Integer.parseInt(list[0]);
		int start_y = Integer.parseInt(list[1]);
		int start_dir = Integer.parseInt(list[2]);

		int map[][] = new int[N][M];

		for(int i = 0;i < N;i++)
		{
			list = br.readLine().split(" ");

			for(int j = 0;j < M;j++)
				map[i][j] = Integer.parseInt(list[j]);
		}

		visited = new boolean[N+1][M+1];

		Queue<Position> queue = new LinkedList<>();
		queue.offer(new Position(start_x, start_y, start_dir, 1));
		visited[start_x][start_y] = true;

		while (!queue.isEmpty())
		{
			Position now = queue.poll();
			int dir = now.dir;

			count = now.count;

			boolean go_back = true;

			for(int i = 0;i < 4;i++)
			{
				dir = turnLeft(dir);

				int next[] = getFront(now.x, now.y, dir);

				if(0 <= next[0] && next[0] < N && 0 <= next[1] && next[1] < M)
				{
					if(!visited[next[0]][next[1]] && map[next[0]][next[1]] == 0)
					{
						visited[next[0]][next[1]] = true;
						queue.offer(new Position(next[0], next[1], dir,now.count + 1));
						go_back = false;
						break;
					}
				}
			}

			//네방향 모두 갈 수 없는 경우 뒤돌아가야함
			if(go_back)
			{
				int next[] = getBack(now.x, now.y, now.dir);
				if(0 <= next[0] && next[0] < N && 0 <= next[1] && next[1] < M)
				{
					//벽이 아닌 경우에만
					if (map[next[0]][next[1]] == 0)
						queue.offer(new Position(next[0],next[1], now.dir, now.count));
				}
			}
		}

		bw.write(count+"");

		bw.close();
		br.close();
	}

	public static int turnLeft(int dir)
	{
		if(dir == 0)
			return 3;
		return dir - 1;
	}

	public static int[] getBack(int x, int y, int dir)
	{
		int next[] = {x, y};

		//북
		if(dir == 0)
			next[0] ++;
			//동
		else if(dir == 1)
			next[1] --;
			//남
		else if(dir == 2)
			next[0] --;
		else
			next[1] ++;
		return next;
	}

	public static int[] getFront(int x, int y, int dir)
	{
		int next[] = {x, y};

		//북
		if(dir == 0)
			next[0] --;
		//동
		else if(dir == 1)
			next[1] ++;
		//남
		else if(dir == 2)
			next[0] ++;
		else
			next[1] --;
		return next;
	}


	static class Position{
		int x, y, dir;
		int count;

		public Position(int x, int y, int dir, int count) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.count = count;
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