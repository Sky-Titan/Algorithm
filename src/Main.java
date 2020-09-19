
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	static int map[][];

	static int cases[] = {0, 4, 2, 4, 4, 1};
	static ArrayList<Position> cameras = new ArrayList<>();
	static int min = Integer.MAX_VALUE;

	public static void solution() throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] list = br.readLine().split(" ");

		int N = Integer.parseInt(list[0]);
		int M = Integer.parseInt(list[1]);

		map = new int[N][M];

		for(int i = 0;i < N;i++)
		{
			list = br.readLine().split(" ");

			for(int j = 0;j < M;j++)
			{
				map[i][j] = Integer.parseInt(list[j]);
				if(0 < map[i][j] && map[i][j] < 6)
					cameras.add(new Position(i, j));
			}
		}
		findAllCases(0, N, M);

		if(min == Integer.MAX_VALUE)
			bw.write("0");
		else
			bw.write(min+"");
		bw.close();
		br.close();
	}

	static void findAllCases(int camera, int N, int M)
	{
		if(camera == cameras.size())
		{
			int count = 0;

			for(int i = 0;i < N;i++)
			{
				for(int j = 0;j < M;j++)
				{
					if(map[i][j] == 0)
						count++;
				}
			}
			min = Math.min(min, count);
		}
		else
		{
			int camera_number = map[cameras.get(camera).x][cameras.get(camera).y];
			Stack<Position> recover_stack = new Stack<>();

			for(int i = 0;i < cases[camera_number];i++)
			{
				makeMap(cameras.get(camera).x, cameras.get(camera).y, i, camera_number, recover_stack);

				findAllCases(camera + 1, N, M);

				recoverMap(recover_stack);
			}
		}
	}

	//카메라 타입에 따라서 감시영역 지정
	static void makeMap(int x, int y, int index, int camera_number, Stack<Position> recover_stack)
	{
		//1번 카메라
		if(camera_number == 1)
		{
			setObservation(x, y, index, recover_stack);
		}
		else if(camera_number == 2)
		{
			//남북
			if(index == 0)
			{
				setObservation(x, y, 2, recover_stack);
				setObservation(x, y, 3, recover_stack);
			}
			//동서
			else
			{
				setObservation(x, y, 0, recover_stack);
				setObservation(x, y, 1, recover_stack);
			}
		}
		else if(camera_number == 3)
		{
			//북동
			if(index == 0)
			{
				setObservation(x, y, 3, recover_stack);
				setObservation(x, y, 0, recover_stack);
			}
			//동남
			else if(index == 1)
			{
				setObservation(x, y, 0, recover_stack);
				setObservation(x, y, 2, recover_stack);
			}
			//남서
			else if(index == 2)
			{
				setObservation(x, y, 2, recover_stack);
				setObservation(x, y, 1, recover_stack);
			}
			//서북
			else
			{
				setObservation(x, y, 1, recover_stack);
				setObservation(x, y, 3, recover_stack);
			}
		}
		else if(camera_number == 4)
		{
			//서북동
			if(index == 0)
			{
				setObservation(x, y, 1, recover_stack);
				setObservation(x, y, 3, recover_stack);
				setObservation(x, y, 0, recover_stack);
			}
			//북동남
			else if(index == 1)
			{
				setObservation(x, y, 3, recover_stack);
				setObservation(x, y, 0, recover_stack);
				setObservation(x, y, 2, recover_stack);
			}
			//동남서
			else if(index == 2)
			{
				setObservation(x, y, 0, recover_stack);
				setObservation(x, y, 2, recover_stack);
				setObservation(x, y, 1, recover_stack);
			}
			//남서북
			else
			{
				setObservation(x, y, 2, recover_stack);
				setObservation(x, y, 1, recover_stack);
				setObservation(x, y, 3, recover_stack);
			}
		}
		else
		{
			for(int i = 0;i < 4;i++)
				setObservation(x, y, i, recover_stack);
		}
	}

	//원래대로 복구
	public static void recoverMap(Stack<Position> recover_stack)
	{
		while(!recover_stack.isEmpty())
		{
			Position p = recover_stack.pop();
			map[p.x][p.y] = 0;
		}
	}

	//감시 영역 지정
	public static void setObservation(int x, int y, int index, Stack<Position> recover_stack)
	{
		//동
		if(index == 0)
		{
			for(int j = y + 1;j < map[x].length;j++)
			{
				//감시영역 지정
				if(map[x][j] == 0)
				{
					map[x][j] = -1;
					recover_stack.push(new Position(x, j));
				}
				//벽이면 멈춤
				else if(map[x][j] == 6)
					break;
			}
		}
		//서
		else if(index == 1)
		{
			for(int j = y - 1;j >= 0;j--)
			{
				//감시영역 지정
				if(map[x][j] == 0)
				{
					map[x][j] = -1;
					recover_stack.push(new Position(x, j));
				}
				else if(map[x][j] == 6)
					break;
			}
		}
		//남
		else if(index == 2)
		{
			for(int i = x + 1;i < map.length;i++)
			{
				//감시영역 지정
				if(map[i][y] == 0)
				{
					map[i][y] = -1;
					recover_stack.push(new Position(i, y));
				}
				else if(map[i][y] == 6)
					break;
			}
		}
		//북
		else
		{
			for(int i = x - 1;i >= 0;i--)
			{
				//감시영역 지정
				if(map[i][y] == 0)
				{
					map[i][y] = -1;
					recover_stack.push(new Position(i, y));
				}
				else if(map[i][y] == 6)
					break;
			}
		}
	}

	static class Position{
		int x, y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
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