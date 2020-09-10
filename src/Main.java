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
		int K = Integer.parseInt(list[1]);

		int coin[] = new int[N];

		int dp[] = new int[K+1];
		dp[0] = 1;


		for(int i = 0;i < N;i++)
		{
			coin[i] = Integer.parseInt(br.readLine());
		}

		for(int i = 0;i < N;i++)
		{
			for(int j = coin[i];j <= K ;j++)
			{
				dp[j] += dp[j - coin[i]];
			}
		}

		bw.write(dp[K]+"");
		bw.close();
	}



	public static void main(String[] args) {
		try
		{
			//solution();

			Solution s = new Solution();

			String[] lines = {
					"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"
			};

			System.out.println(s.solution(lines));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


}