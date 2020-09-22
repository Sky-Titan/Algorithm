
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int max = 0;
	static int N = 0;
	static char [][] map;

	public static void solution() throws Exception
	{
		N = Integer.parseInt(br.readLine());

		map = new char[N][N];

		for(int i = 0;i < N;i++)
		{
			String str = br.readLine();
			for(int j = 0;j < N;j++)
				map[i][j] = str.charAt(j);
		}

		for(int i = 0;i < N;i++)
		{
			for(int j = 0;j < N;j++)
			{
				//오른쪽
				if(j < N -1)
				{
					swap(i, j, i, j + 1);
					max = Math.max(max, countContinuousRow(i));
					max = Math.max(max, countContinuousColumn(j + 1));
					max = Math.max(max, countContinuousColumn(j));
					swap(i, j, i, j + 1);
				}

				if(i < N - 1)
				{
					//아래쪽
					swap(i, j,i + 1,j);
					max = Math.max(max, countContinuousRow(i));
					max = Math.max(max, countContinuousRow(i + 1));
					max = Math.max(max, countContinuousColumn(j));
					swap(i, j,i + 1,j);
				}
			}
		}
		bw.write(max+"");
	}

	public static int countContinuousRow(int x)
	{
		int count = 0;

		int color = map[x][0];

		for(int j = 0;j < N;j++)
		{
			if(color == map[x][j])
				count++;
			else
			{
				color = map[x][j];
				count = 1;
			}
			max = Math.max(count, max);
		}

		return max;
	}

	public static int countContinuousColumn(int y)
	{
		int count = 0;
		int max = 0;

		int color = map[0][y];

		for(int i = 0;i < N;i++)
		{
			if(color == map[i][y])
				count++;
			else
			{
				color = map[i][y];
				count = 1;
			}
			max = Math.max(count, max);
		}

		return max;
	}


	public static void swap(int x1, int y1, int x2, int y2)
	{
		char temp = map[x1][y1];
		map[x1][y1] = map[x2][y2];
		map[x2][y2] = temp;
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