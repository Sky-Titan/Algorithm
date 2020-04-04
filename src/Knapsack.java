import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Knapsack {

	public Knapsack()
	{
		
	}
	
	static void bj1943() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		for(int k=0;k<3;k++)
		{
			int N = Integer.parseInt(b.readLine());
			
			int coin[][] = new int[N][2];//0 : ±Ý¾× , 1 : °³¼ö
			int sum = 0;//µ·ÃÑÇÕ
			
			int min = Integer.MAX_VALUE;
			for(int i=0;i<N;i++)
			{
				StringTokenizer strtok = new StringTokenizer(b.readLine());
				coin[i][0] = Integer.parseInt(strtok.nextToken());
				coin[i][1] = Integer.parseInt(strtok.nextToken());
				sum += coin[i][0] * coin[i][1];
				min = Math.min(min, coin[i][0]);
			}
			
			int dp[][] = new int[sum+1][N];
			
			for(int i=0;i<N;i++)
				dp[coin[i][0]][i] = 1;
			
			for(int i=min;i<dp.length;i++)
			{
				
				for(int j=0;j<=i/2;j++)
				{
					
				}
			}
		}
	}
	
	static void bj1699()throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		int dp[] = new int[N+1];
		
		for(int i=1;i<=N;i++)
		{
			
			dp[i] = i;
			for(int j=1;j*j<=i;j++)
			{
				dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
			}
		}
		System.out.println(dp[N]);
	}
	
}
