import javafx.geometry.Pos;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int N, M, D;
	static int map[][];
	static int max = 0;

	public static void solution() throws Exception
	{
		String[] list = br.readLine().split(" ");
		N = Integer.parseInt(list[0]);
		M = Integer.parseInt(list[1]);
		D = Integer.parseInt(list[2]);

		map = new int[N][M];
		for(int i = 0;i < N;i++)
		{
			list = br.readLine().split(" ");
			for(int j = 0;j < M;j++)
				map[i][j] = Integer.parseInt(list[j]);
		}

		dfs(0, new ArrayList<>());
		bw.write(max+"");


	}

	public static void dfs(int index, ArrayList<Position> archers)
	{
		if(archers.size() == 3)
		{
			//시뮬레이션
			int sim_map[][] = new int[N][M];

			for(int i = 0;i < N;i++)
				sim_map[i] = map[i].clone();

			ArrayList<Position> enemyList = new ArrayList<>();

			int count = 0;

			//모든 맵이 0될 때 까지
			while(!isFinish(sim_map))
			{
				//죽일 적들 골라오기
				for(int i = 0;i < 3;i++)
				{
					Position enemy = selectEnemy(sim_map, archers.get(i).x, archers.get(i).y);

					//죽일 수 있는 적이 없음
					if(enemy.x == archers.get(i).x && enemy.y == archers.get(i).y)
						continue;

					//중복 아닌 것만
					if(!enemyList.contains(enemy))
						enemyList.add(enemy);
				}

				count += enemyList.size();
				killEnemy(enemyList, sim_map);
				//System.out.println(Arrays.deepToString(sim_map));
				moveEnemy(sim_map);

			}
			//System.out.println();
			max = Math.max(max, count);
		}
		else
		{
			for(int j = index; j < M;j++)
			{
				archers.add(new Position(N, j));
				dfs(j + 1,archers);
				archers.remove(archers.size() - 1);
			}
		}
	}

	//가장가까운 적 고르기 -> 없으면 내 위치 그냥 반환
	public static Position selectEnemy(int map[][], int x, int y)
	{
		int min = D;

		PriorityQueue<Position> queue = new PriorityQueue<Position>((o1, o2) -> {
			int dis1 = Math.abs(x - o1.x) + Math.abs(y - o1.y);
			int dis2 = Math.abs(x - o2.x) + Math.abs(y - o2.y);

			if(dis1 == dis2)
				return o1.y - o2.y;
			else
				return dis1 - dis2;
		});
		for(int i = N - 1;i >= 0;i--)
		{
			for(int j = 0;j < M;j++)
			{
				int dis = Math.abs(x - i) + Math.abs(y - j);
				if(map[i][j] == 1 && dis  <= min)
				{
					min = dis;
					queue.offer(new Position(i,j));
				}
			}
		}

		if(queue.isEmpty())
			return new Position(x, y);
		return queue.poll();
	}

	public static void killEnemy(ArrayList<Position> enemyList, int[][] map)
	{
		for(int i = 0;i < enemyList.size();i++)
		{
			Position now = enemyList.get(i);
			map[now.x][now.y] = 0;
		}
		enemyList.clear();
	}

	public static void moveEnemy(int[][] map)
	{
		for(int i = N - 1;i >= 0;i--)
		{
			for(int j = 0;j < M;j++)
			{
				if(i == 0)
					map[i][j] = 0;
				else
					map[i][j] = map[i-1][j];
			}
		}
	}

	public static boolean isFinish(int[][] map)
	{
		for(int i = 0;i < N;i++)
		{
			for(int j = 0;j < M;j++)
			{
				if(map[i][j] > 0)
					return false;
			}
		}
		return true;
	}

	static class Position{
		int x, y;

		public Position(int x, int y)
		{
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object obj) {
			Position other=  (Position) obj;
			return other.x == x && other.y == y;
		}
	}


	public static void main(String[] args) {
		try
		{
			//solution();
			String 박준현 = "박준현";
			System.out.println(박준현);
			bw.close();
			br.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}