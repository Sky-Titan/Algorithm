import sun.nio.cs.ext.MacHebrew;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {


	static void solution() throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] list = br.readLine().split(" ");

		int N = Integer.parseInt(list[0]);
		int M = Integer.parseInt(list[1]);

		int map[][] = new int[N][M];
		int dp[][] = new int[N][M];

		for(int i = 0;i < N;i++)
		{
			String str = br.readLine();
			for(int j = 0;j < M;j++)
				map[i][j] = str.charAt(j) - '0';
		}

		int max = 0;

		for(int i = 0;i < N;i++)
		{
			for(int j = 0;j < M;j++)
			{
				if(map[i][j] == 1)
				{
					dp[i][j] = 1;
					if(i != 0 && j != 0)
					{
						if(map[i-1][j] == 1 && map[i][j-1] == 1 && map[i-1][j-1] == 1)
						{
							int min = dp[i - 1][j];
							min = Math.min(min, dp[i][j - 1]);
							min = Math.min(min, dp[i - 1][j - 1]);
							dp[i][j] = min + 1;
							//System.out.println("i : " + i + ", j : " + j + " " + dp[i][j]);
						}
					}
					max = Math.max(max, dp[i][j]);
				}
			}
		}


		bw.write(max * max+"");

		bw.close();
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