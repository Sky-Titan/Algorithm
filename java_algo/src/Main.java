
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int x, y;
	static int N, M, K;
	static int map[][];
	static int bottom[] = {3, 1}, top[] = {1, 1}, front[] = {0, 1}, rear[] = {2, 1}, left[] = {1, 0}, right[] = {1, 2};
	static int cube[][] = {
			{0, 0, 0},
			{0, 0 ,0},
			{0, 0, 0},
			{0, 0, 0},
	};

	public static void solution() throws Exception
	{
		String[] list = br.readLine().split(" ");

		N = Integer.parseInt(list[0]);
		M = Integer.parseInt(list[1]);
		x = Integer.parseInt(list[2]);
		y = Integer.parseInt(list[3]);
		K = Integer.parseInt(list[4]);

		map = new int[N][M];

		for(int i = 0;i < N;i++)
		{
			list = br.readLine().split(" ");
			for(int j = 0;j < M;j++)
				map[i][j] = Integer.parseInt(list[j]);
		}

		String[] order_list = br.readLine().split(" ");

		for(int k = 0;k < K;k++)
		{
			int order = Integer.parseInt(order_list[k]);

			//경계선 안에 있는 경우에만 진행
			if(isInBound(order))
			{
				rollCube(order);
				moveCube(order);

				//맵의 칸이 0이면 -> 주사위 바닥면의 수를 복사
				if(map[x][y] == 0)
				{
					map[x][y] = cube[bottom[0]][bottom[1]];
				}
				//맵의 칸이 0이 아니면 -> 칸의 수를 주사위 바닥면에 복사 -> 칸은 0으로
				else
				{
					cube[bottom[0]][bottom[1]] = map[x][y];
					map[x][y] = 0;
				}
				bw.write(cube[top[0]][top[1]]+"\n");
			}
		}
	}

	//경계검사
	static boolean isInBound(int order)
	{
		switch (order)
		{
			//동서북남
			case 1:
				if(y == M - 1)
					return false;
				break;
			case 2:
				if(y == 0)
					return false;
				break;
			case 3:
				if(x == 0)
					return false;
				break;
			case 4:
				if(x == N - 1)
					return false;
				break;
		}
		return true;
	}

	//주사위 움직이기
	static void moveCube(int order)
	{
		switch (order)
		{
			//동서남북
			case 1:
				y++;
				break;
			case 2:
				y--;
				break;
			case 3:
				x--;
				break;
			case 4:
				x++;
				break;
		}
	}

	//주사위 굴리기
	static void rollCube(int order)
	{
		int temp = 0;
		switch (order)
		{
			//동서북남
			case 1:
				temp = cube[bottom[0]][bottom[1]];
				cube[bottom[0]][bottom[1]] = cube[right[0]][right[1]];
				cube[right[0]][right[1]] = cube[top[0]][top[1]];
				cube[top[0]][top[1]] = cube[left[0]][left[1]];
				cube[left[0]][left[1]] = temp;
				break;
			case 2:
				temp = cube[bottom[0]][bottom[1]];
				cube[bottom[0]][bottom[1]] = cube[left[0]][left[1]];
				cube[left[0]][left[1]] = cube[top[0]][top[1]];
				cube[top[0]][top[1]] = cube[right[0]][right[1]];
				cube[right[0]][right[1]] = temp;
				break;
			case 3:
				temp = cube[front[0]][front[1]];

				for(int i = 0;i < 3;i++)
					cube[i][1] = cube[i + 1][1];
				cube[bottom[0]][bottom[1]] = temp;
				break;
			case 4:
				temp = cube[bottom[0]][bottom[1]];

				for(int i = 3;i >= 1;i--)
					cube[i][1] = cube[i-1][1];
				cube[front[0]][front[1]] = temp;
				break;
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