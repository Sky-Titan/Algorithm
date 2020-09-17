
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	static int map[][];
	static int x[] = {-1, 1, 0, 0};
	static int y[] = {0, 0, -1, 1};

	public static void solution() throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		Fish baby = new Fish();

		int fish_count = 0;

		for(int i = 0;i < N;i++)
		{
			String[] list = br.readLine().split(" ");

			for(int j = 0;j < N;j++)
			{
				map[i][j] = Integer.parseInt(list[j]);

				if(map[i][j] == 9)
				{
					baby.x = i;
					baby.y = j;
					baby.size = 2;
				}
				else if(map[i][j] > 0)
					fish_count ++;

			}
		}

		int time = 0;

		ArrayList<Fish> result = new ArrayList<>();

		Queue<Fish> queue = new LinkedList<>();
		queue.offer(baby);

		//물고기들이 아직 존재하는 이상 계속
		while (fish_count > 0)
		{
			bfs(result, N ,queue);

			//더 이상 먹을 수 있는 물고기 없음
			if(result.isEmpty())
				break;

			int min_index = getMinIndex(result);

			fish_count--;

			//그 다음 탐색을 시작할 물고기 위치
			queue.offer(result.get(min_index));

			time = result.get(min_index).time;

			result.clear();
		}

		bw.write(time+"");
		bw.close();
		br.close();
	}

	public static void bfs(ArrayList<Fish> result, int N, Queue<Fish> queue)
	{
		boolean[][] visited = new boolean[N][N];

		visited[queue.peek().x][queue.peek().y] = true;
		map[queue.peek().x][queue.peek().y] = 0;

		int min_time = Integer.MAX_VALUE;

		//현재 위치에서 갈 수 있는 최단 거리의 물고기들을 구한다.
		while(!queue.isEmpty())
		{
			Fish now = queue.poll();

			//최소 시간보다 시간이 더걸린다면 더 탐색할 필요 없음
			if(min_time < now.time)
				continue;

			if(0 < map[now.x][now.y] && map[now.x][now.y] < now.size)
			{
				if(min_time == Integer.MAX_VALUE)
					min_time = now.time;

				now.eat++;
				if(now.eat == now.size)
				{
					now.size ++;
					now.eat = 0;
				}

				result.add(now);
				continue;
			}

			for(int i = 0;i < 4;i++)
			{
				int next_x = now.x + x[i];
				int next_y = now.y + y[i];

				if(0 <= next_x && next_x < N && 0 <= next_y && next_y < N)
				{
					if(!visited[next_x][next_y] && now.size >= map[next_x][next_y])
					{
						visited[next_x][next_y] = true;
						queue.offer(new Fish(next_x, next_y, now.size, now.time + 1, now.eat));
					}
				}
			}
		}
	}

	public static int getMinIndex(ArrayList<Fish> result)
	{
		int min_index = 0;

		//거리가 가장 짧고, 가장 위에 있고 가장 왼쪽에 있는 것을 맨 앞으로
		for(int i = 0;i < result.size();i++)
		{
			if(result.get(min_index).time == result.get(i).time)
			{
				if(result.get(min_index).x > result.get(i).x)
					min_index = i;
				else if(result.get(min_index).x == result.get(i).x)
				{
					if(result.get(min_index).y > result.get(i).y)
						min_index = i;
				}
			}
			else
				break;
		}
		return min_index;
	}


	static class Fish{

		int x, y;
		int size;
		int eat = 0;
		int time = 0;

		public Fish() {
		}

		public Fish(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}

		public Fish(int x, int y, int size, int time, int eat) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.time = time;
			this.eat = eat;
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