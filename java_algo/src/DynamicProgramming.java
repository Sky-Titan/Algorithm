import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class DynamicProgramming {

	static int count = 0;
	
	public DynamicProgramming() {
		// TODO Auto-generated constructor stub
	}
	
	static class Position{
		
		public int x,y;
		int way;
		public Position()
		{
			
		}
		public Position(int x, int y)
		{
			this.x=x;
			this.y=y;
		}
		public Position(int x, int y,int way)
		{
			this.x=x;
			this.y=y;
			this.way = way;
		}
	}
	
	static void bj1937_2() throws Exception
	{
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(b.readLine());
		
		int map[][] = new int[N][N];
		
		for(int i=0;i<N;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			
			for(int j=0;j<N;j++)
			{
				map[i][j] = Integer.parseInt(strtok.nextToken());
			}
		}
		
		int dp[][] = new int[N][N];
		boolean visited[][] = new boolean[N][N];
		
		int max = Integer.MIN_VALUE;
		
		int r[] = {0,0,1,-1};
		int c[] = {1,-1,0,0};
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				if(!visited[i][j])
				{
					dfs1937(i, j, N, map, dp, visited,r ,c);
				}
				max = Math.max(max, dp[i][j]);
			}
		}
		
		System.out.println(max);
	}
	
	static int dfs1937(int x, int y, int N, int map[][], int dp[][], boolean visited[][], int r[], int c[])
	{
		visited[x][y] = true;
		
		dp[x][y] = 1;
		
		int x2;
		int y2;
		for(int i=0;i<4;i++)
		{
			x2 = x + r[i];
			y2 = y + c[i];
			
			if(0 <= x2 && x2 < N && 0 <= y2 && y2 < N)
			{
				if(map[x2][y2] > map[x][y])
				{
					if(!visited[x2][y2])
						dp[x][y] = Math.max(dp[x][y], dfs1937(x2, y2, N, map, dp, visited,r,c) + 1);
					else
						dp[x][y] = Math.max(dp[x][y], dp[x2][y2] + 1);
				}
				
			}
		}
		
		return dp[x][y];
	}
	
	static void bj1965_2() throws Exception
	{
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(b.readLine());
		
		int box[] = new int[N];
		
		int dp[] = new int[N];
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		for(int i=0;i<N;i++)
		{
			box[i] = Integer.parseInt(strtok.nextToken());
		}
		
		int max = Integer.MIN_VALUE;
		
		for(int i=0;i<N;i++)
		{
			dp[i] = 1;
			
			if(i!=0)
			{
				for(int j=i-1;j>=0;j--)
				{
					if(box[j] < box[i] && dp[j] + 1 > dp[i])
					{
						dp[i] = dp[j] + 1;
					}
				}
			}
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
	
	static void bj11054_2() throws Exception
	{
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(b.readLine());
		
		int number[] = new int[N];
		int dp[] = new int[N];
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		for(int i=0;i<N;i++)
		{
			number[i] = Integer.parseInt(strtok.nextToken());
		}
		
		for(int i=0;i<N;i++)
		{
			dp[i] = 1;
			
			if(i!=0)
			{
				for(int j=i-1;j>=0;j--)
				{
					if(number[j] < number[i] && dp[j] + 1 > dp[i])
					{
						dp[i] = dp[j] + 1;
					}
				}
			}
		}
		
		int dp2[] = new int[N];
		
		for(int i=N-1;i>=0;i--)
		{
			dp2[i] = 1;
			
			if(i!=0)
			{
				for(int j=i+1;j<N;j++)
				{
					if(number[j] < number[i] && dp2[j] + 1 > dp2[i])
					{
						dp2[i] = dp2[j] + 1;
					}
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		
		for(int i=0;i<N;i++)
		{
			max = Math.max(max, dp[i] + dp2[i] - 1);
		}
		System.out.println(max);
		
	}
	
	static void bj1915() throws Exception
	{
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		int map[][] = new int[N+1][M+1];
		
		for(int i=1;i<=N;i++)
		{
			strtok = new StringTokenizer(b.readLine());
			for(int j=1;j<=M;j++)
			{
				map[i][j] = Integer.parseInt(strtok.nextToken());
			}
		}
		
		int dp[][] = new int[N+1][M+1];
		for(int i=1;i<N;i++)
		{
			for(int j=1;j<M;j++)
			{
				if(i==1)
				{
					
				}
				
			}
		}
	}
	
	static void bj6359() throws Exception
	{
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(b.readLine());
		
		for(int t=0;t<T;t++)
		{
			int N = Integer.parseInt(b.readLine());
			
			boolean[] isOpen = new boolean[N+1];
			int count=0;
			//라운드
			for(int i=1;i<=N;i++)
			{
				for(int n = 1;n<=N;n++)
				{
					if(i==1)
					{
						count++;
						isOpen[n] = true;
					}
					else if(n%i==0)
					{
						if(!isOpen[n])
						{
							count++;
							isOpen[n] = true;
						}
						else
						{
							count--;
							isOpen[n] = false;
						}
					}
				}
			}
			System.out.println(count);
		}
	}
	
	static void bj9252() throws Exception
	{
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
	
		String input[] = new String[2];
		
		for(int i=0;i<2;i++)
			input[i] = b.readLine();
		int dp_len[][] = new int[input[0].length()][input[1].length()];
		String dp[][] = new String[input[0].length()][input[1].length()];

		for(int i = 0; i < input[0].length(); i++)
		{
			for(int j = 0;j < input[1].length(); j++)
			{
				dp[i][j] = "";
				if(input[0].charAt(i) == input[1].charAt(j))
				{
					char common_c = input[0].charAt(i);
					if(i==0 || j==0)
					{
						dp[i][j] = common_c+"";
						dp_len[i][j] = 1;
					}
					else
					{
						dp[i][j] = dp[i-1][j-1] + common_c;
						dp_len[i][j] = dp_len[i-1][j-1] + 1;
					}
						
				}
				else//다른 경우
				{
					if(i > 0 && j==0)
					{
						dp[i][j] = dp[i-1][j];
						dp_len[i][j] = dp_len[i-1][j];
						
					}
					else if(i==0 && j > 0)
					{
						dp[i][j] = dp[i][j-1];
						dp_len[i][j] = dp_len[i][j-1];
					}
					else if(i>0&& j>0)
					{
						if(dp_len[i-1][j] > dp_len[i][j-1])
						{
							dp[i][j] = dp[i-1][j];
							dp_len[i][j] = dp_len[i-1][j];
						}
						else
						{
							dp[i][j] = dp[i][j-1];
							dp_len[i][j] = dp_len[i][j-1];
						}
					}
				}
			}
		}
		System.out.println(dp_len[input[0].length()-1][input[1].length()-1]);

		System.out.println(dp[input[0].length()-1][input[1].length()-1]);
	}
	
	static void bj1965() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		int input[] = new int[N];
		int dp[] = new int[N];
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		for(int i=0;i<N;i++)
		{
			input[i] = Integer.parseInt(strtok.nextToken());
		}
		
		int max = Integer.MIN_VALUE;
		
		for(int i=0;i<N;i++)
		{
			if(i==0)
			{
				dp[i] = 1;
			}
			else
			{
				dp[i] = 1;
				
				for(int j=i-1;j>=0;j--)
				{
					if(input[j] < input[i] && dp[j] + 1 > dp[i])
					{
						dp[i] = dp[j] + 1;
					}
				}
			}
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
		
	}
	
	static void bj1937() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		int map[][] = new int[N][N];
		int dp[][] = new int[N][N];
		
		boolean visited[][] = new boolean[N][N];
		
		for(int i=0;i<N;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			
			for(int j=0;j<N;j++)
			{
				map[i][j] = Integer.parseInt(strtok.nextToken());
				dp[i][j] = 1;
			}
		}
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				if(!visited[i][j])
				{
					dfs_1937(i, j, 1, map, dp, N, visited);
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				max = Math.max(max, dp[i][j]);
		//		System.out.print(dp[i][j]+ " ");
			}
		//	System.out.println();
		}
		System.out.println(max);
	}
	
	static int dfs_1937(int x, int y,int count ,int map[][], int dp[][], int N, boolean visited[][])
	{
		visited[x][y] = true;
		
		int r[] = {x - 1, x + 1, x, x};
		int c[] = {y, y, y - 1, y + 1};
		
		dp[x][y] += count;
		for(int i=0;i<4;i++)
		{
			if(r[i] < N && r[i] >= 0 && c[i] >= 0 && c[i] < N)
			{
				if(map[x][y] < map[r[i]][c[i]])
				{
					if(!visited[r[i]][c[i]])
					{
						dp[x][y] = Math.max(dp[x][y], dfs_1937(r[i], c[i], count + 1, map, dp, N, visited) + count + 1);
					}
					else
					{
						dp[x][y] = Math.max(dp[x][y], dp[r[i]][c[i]] + count + 1);
					}
				}
			}
			
		}
		dp[x][y] -= count;
		return dp[x][y];
	}
	
	static void bj1309() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		int dp[][][] = new int[N+1][2][2];
		int dp2[][] = new int[N+1][3];
		
		for(int i=1;i<=N;i++)
		{
			if(i==1)
			{
				dp[i][0][0] = 2;//왼쪽에 사자가 안들어가 있는 경우
				dp[i][0][1] = 1;//왼쪽에 사자가 들어가 있는 경우
				dp[i][1][0] = 2;//오른쪽에 사자가 안들어가 있는 경우
				dp[i][1][1] = 1;//오른쪽에 사자가 들어가 있는 경우
			}
			else
			{
				dp[i][0][0] = (dp[i-1][1][0] % 9901) * 2 + dp[i-1][1][1] % 9901 ;//왼쪽에 사자가 안들어가 있는 경우
				dp[i][0][1] = dp[i-1][0][0] % 9901;//왼쪽에 사자가 들어가 있는 경우
				dp[i][1][0] = (dp[i-1][0][0] % 9901) * 2 + dp[i-1][0][1] % 9901;//오른쪽에 사자가 안들어가 있는 경우
				dp[i][1][1] = dp[i-1][1][0] % 9901;//오른쪽에 사자가 들어가 있는 경우
			}
		}
		
		for(int i=1;i<=N;i++)
		{
			if(i==1)
			{
				dp2[i][0] = 1;//01
				dp2[i][1] = 1;//10
				dp2[i][2] = 1;//00
			}
			else
			{
				dp2[i][0] = (dp2[i-1][1] + dp2[i-1][2]) % 9901;
				dp2[i][1] = (dp2[i-1][0] + dp2[i-1][2]) % 9901;
				dp2[i][2] = (dp2[i-1][0] + dp2[i-1][1] + dp2[i-1][2]) % 9901;
			}
		}
		System.out.println((dp[N][1][0] + dp[N][1][1]) % 9901 );
		System.out.println((dp2[N][0] + dp2[N][1] + dp2[N][2]) % 9901);
	}
	
	static void bj2225() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		int N = Integer.parseInt(strtok.nextToken());
		int K = Integer.parseInt(strtok.nextToken());
		
		long dp[][] = new long[N+1][K+1];
		
		System.out.println(count_2225(N, K, dp)%1000000000);
	}
	
	static long count_2225(int n,int k,long dp[][])
	{
		if(n==0 || k==1)
		{	
			dp[n][k] = 1;
			return 1;
		}
		
		for(int i=0;i<=n;i++)
		{
			if(dp[n-i][k-1] == 0)
				dp[n][k] += count_2225(n-i, k-1, dp) % 1000000000;
			else
				dp[n][k] += dp[n-i][k-1] % 1000000000;
		}
		return dp[n][k] % 1000000000;
	}
	
	
	static void bj1890() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		int map[][] = new int[N][N];
		long dp[][] = new long[N][N];
		boolean visited[][] = new boolean[N][N];
		
		for(int i=0;i<N;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			
			for(int j=0;j<N;j++)
			{
				map[i][j] = Integer.parseInt(strtok.nextToken());
			}
		}
		
		dfs_bj1890(0, 0, dp, map, visited, N);
		
	
		System.out.println(dp[0][0]);
	}
	
	static long dfs_bj1890(int x, int y, long[][] dp, int[][] map, boolean[][] visited,int N)
	{
		int distance = map[x][y];//점프해서 갈 수 있는 거리
		visited[x][y] = true;
		
		if(x==N-1 && y==N-1)
		{
			dp[x][y] = 1;
			return 1;
		}
		
		int r[] = {x, x + distance};
		int c[] = {y + distance, y};
		
		for(int i=0;i<2;i++)
		{
			if(r[i] < N && c[i] < N)
			{
				if(!visited[r[i]][c[i]])
				{
					dp[x][y] += dfs_bj1890(r[i], c[i], dp, map, visited, N);
				}
				else
				{
					dp[x][y] += dp[r[i]][c[i]];
				}
			}
		}
		return dp[x][y];
		
	}
	
	static void bj2294() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		int N = Integer.parseInt(strtok.nextToken());
		int K = Integer.parseInt(strtok.nextToken());
		
		int dp[] = new int[100001];
		for(int i=0;i<=100000;i++)
			dp[i] = 100001;
		
		for(int i=0;i<N;i++)
		{	
			int coin = Integer.parseInt(b.readLine());
			dp[coin] = 1;
		
		}
		
		for(int i=1;i<=K;i++)
		{
			if(dp[i] != 1)
			{
				for(int j=1;j<=i/2;j++)
				{
					dp[i] = Math.min(dp[i], dp[i-j] + dp[j]);
				}
			}
			
		}
		if(dp[K]==100001)
			System.out.println(-1);
		else
		System.out.println(dp[K]);
	}
	
	static void bj2493() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		Stack<Integer> index = new Stack<>();
		Stack<Integer> value = new Stack<>();
		
		for(int i=0;i<N;i++)
		{
			int item = Integer.parseInt(strtok.nextToken());
			
			while(!index.isEmpty())
			{
				if(value.peek() > item)
				{
					System.out.print(index.peek()+" ");
					break;
				}
				else
				{
					index.pop();
					value.pop();
				}
			}
			
			if(index.isEmpty())
				System.out.print(0+" ");
		
			index.push(i+1);
			value.push(item);
		}

		
		
	}
	
	static void bj1520() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		int map[][] = new int[N][M];
		boolean visited[][] = new boolean[N][M];
		int output[][] = new int[N][M];
		
		for(int i=0;i<N;i++)
		{
			strtok = new StringTokenizer(b.readLine());
			for(int j=0;j<M;j++)
			{
				map[i][j] = Integer.parseInt(strtok.nextToken());
			}
		}
		
		A_bj1520(0, 0, N, M, map, output,visited);
		System.out.println(output[0][0]);
	}
	
	static int A_bj1520(int x,int y,int N, int M, int map[][], int output[][],boolean visited[][])
	{
		if(x==N-1 && y==M-1)//끝
			return 1;
		if(visited[x][y])
			return output[x][y];
		
		visited[x][y] = true;
		if(x!=0)
		{
			int x2 = x-1;
			int y2 = y;
			if(map[x2][y2] < map[x][y])
			{
				output[x][y] += A_bj1520(x2, y2, N, M, map, output,visited);
			}
		}
		if(x!=N-1)
		{
			int x2 = x+1;
			int y2 = y;
			if(map[x2][y2] < map[x][y])
			{
				output[x][y] += A_bj1520(x2, y2, N, M, map, output,visited);
			}
		}
		if(y!=0)
		{
			int x2 = x;
			int y2 = y-1;
			if(map[x2][y2] < map[x][y])
			{
				output[x][y] += A_bj1520(x2, y2, N, M, map, output,visited);
			}
		}
		if(y!=M-1)
		{
			int x2 = x;
			int y2 = y+1;
			if(map[x2][y2] < map[x][y])
			{
				output[x][y] += A_bj1520(x2, y2, N, M, map, output,visited);
			}
		}
		return output[x][y];
	}
	
	
	static void bj11054() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		int input[] = new int[N];
		int output[][] = new int[N][2];
	
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		for(int i=0;i<N;i++)
		{
			input[i] = Integer.parseInt(strtok.nextToken());
			output[i][0] = 1;
			output[i][1] = 1;
		}
		
		for(int i=1;i<N;i++)
		{
			for(int j=i-1;j>=0;j--)
			{
				if(input[j] < input[i] && output[j][0] + 1 > output[i][0])
					output[i][0] = output[j][0] + 1;
			}
		}
		
		for(int i = N-2;i>=0;i--)
		{
			for(int j=i+1;j<N;j++)
			{
				if(input[j] < input[i] && output[j][1] + 1 > output[i][1])
					output[i][1] = output[j][1] + 1;
			}
		}
		
		int max = output[0][0] + output[0][1] - 1;
		for(int i=0;i<N;i++)
			max = Math.max(max, output[i][0] + output[i][1] - 1);
		System.out.println(max);
	}
	
	static void bj11722() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		int input[][] = new int[N][2];
		
		
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		for(int i=0;i<N;i++)
		{
			input[i][0] = Integer.parseInt(strtok.nextToken());
			input[i][1] = 1;
		}
		
		int total_max=0;
		
		for(int i=0;i<N;i++)
		{
			if(i>0)
			{
				int max_length = input[i][1];
				
				for(int j=i-1 ; j>=0;j--)
				{
					if(input[j][0] > input[i][0] && input[j][1] + 1 > max_length)
						max_length = input[j][1] + 1;
				}
				input[i][1] = max_length;
				
				total_max = Math.max(total_max, input[i][1]);
			}
			else
			{
				total_max = input[i][1];
			}
		}
		System.out.println(total_max);
	}
	
	static void bj1904() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		int dp[] = new int[N+1];
		
		for(int i=0;i<=N;i++)
		{
			if(i==0)
			{
				dp[i] = 0;

			}
			else if(i==1)
			{
				dp[i] = 1;
			}
			else if(i==2)
			{
				dp[i] = 2;
			}
			else
			{
				dp[i] = ((dp[i-1] % 15746) + (dp[i-2] % 15746)) % 15746;
			}
		}
		System.out.println(dp[N]%15746);
	}
	
	static void bj2133() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		int dp[] = new int[N+1];
		dp[0] = 0;
		for(int i=1;i<=N;i++)
		{
			if(i==1)
			{
				dp[i] = 0;
			}
			else if(i==2)
			{
				dp[i] = 3;
			}
			else
			{
				if(i%2 == 1)//홀수
					dp[i] = 0;
				else
					dp[i] = (dp[i - 2] * dp[2]) + dp[i - 4] * 2 + 2;
			}
		}
		
		System.out.println(dp[N]);
	}
	
	static void bj9251() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		String input[] = new String[2];
		
		for(int i=0;i<2;i++)
		{
			String str = b.readLine();
			input[i] = str;
		}
		
		int[][] dp = new int[input[0].length()][input[1].length()];
		
		for(int i=0;i<input[0].length();i++)
		{
			for(int j=0;j<input[1].length();j++)
			{
				
				if(input[0].charAt(i) == input[1].charAt(j))//두 문자 같은 경우
				{
					if(i==0||j==0)//둘 중 하나가 맨 첫번째 글자일경우 무조건 1
						dp[i][j] = 1;
					else//이전 문자들까지의 가장 긴 것 포함 후 +1
						dp[i][j] = dp[i-1][j-1] + 1;
				}
				else//두 문자 다른 경우
				{
					if(i==0 || j==0)
					{
						if(i > 0 && j==0)
							dp[i][j] = dp[i-1][j];
						else if(i == 0 && j > 0)
							dp[i][j] = dp[i][j-1];
					}
					else
					{
						//그 전까지의 문자열 비교한 것 중 가장 긴것
						dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
					}
				}
			}
		}
		
		System.out.println(dp[input[0].length()-1][input[1].length()-1]);
	}
	
	static void bj11051() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		int N = Integer.parseInt(strtok.nextToken());
		int K = Integer.parseInt(strtok.nextToken());
		
		int choose[][] = new int[N+1][K+1];
		
		for(int n=1;n<=N;n++)
		{
			for(int k = 0; k<=K; k++)
			{
			
				if(k==0)
					choose[n][k] = 1;
				else if(k==n)	
					choose[n][k] = 1;
				else
					choose[n][k] = (choose[n-1][k-1] + choose[n-1][k]) % 10007;
					
			}
		}
		
		System.out.println(choose[N][K]%10007);
		
	}
	
	static void bj11048() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		int maze[][] = new int[N+1][M+1];
		int dp[][] = new int[N+1][M+1];
		
		for(int i=1;i<=N;i++)
		{
			strtok = new StringTokenizer(b.readLine());
			for(int j=1;j<=M;j++)
			{
				maze[i][j] = Integer.parseInt(strtok.nextToken());
			}
		}
		
		for(int i=1;i<=N;i++)
		{
			for(int j=1;j<=M;j++)
			{
				if(i==1)
				{
					if(j==1)
						dp[i][j] = maze[i][j];
					else
						dp[i][j] = maze[i][j] + dp[i][j-1];
				}
				else
				{
					if(j==1)
						dp[i][j] = maze[i][j] + dp[i-1][j];
					else
					{
						int max = Math.max(dp[i-1][j], dp[i][j-1]);
						max = Math.max(dp[i-1][j-1], max);
						
						dp[i][j] = maze[i][j] + max;
					}
				}
			}
		}
		
		System.out.println(dp[N][M]);
	}
	
	static void bj11055() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		int input[] = new int[N];
		int output[] = new int[N];
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		for(int i=0;i<N;i++)
		{
			input[i] = Integer.parseInt(strtok.nextToken());
		}
		
		int max = 0;
		
		for(int i=0;i<N;i++)
		{
			if(i==0)
			{
				output[i] = input[i];//수열 합
				
			}
			else
			{
				output[i] = 0;
			
				for(int j=i-1;j>=0;j--)
				{
					if(input[j] < input[i] && output[j] > output[i])
						output[i] = output[j];
				}
				output[i] += input[i];
			}
			max = Math.max(max, output[i]);
		}
		
		System.out.println(max);
	}
	
	static void bj2167() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		int input[][] = new int[N+1][M+1];
		int sum[][] = new int[N+1][M+1];
		
		for(int i = 1;i <= N; i++)
		{
			strtok = new StringTokenizer(b.readLine()," ");
			
			for(int j=1;j<=M;j++)
			{	
				input[i][j] = Integer.parseInt(strtok.nextToken());
			}
		}
		
		for(int i=N;i>=1;i--)
		{
			for(int j=M;j>=1;j--)
			{
				if(i==N)
				{
					if(j==M)
						sum[i][j] = input[i][j];
					else
						sum[i][j] = input[i][j] + sum[i][j+1];
				}
				else
				{
					if(j==M)
						sum[i][j] = input[i][j] + sum[i+1][j];
					else
						sum[i][j] = input[i][j] + sum[i+1][j] + (sum[i][j+1] - sum[i+1][j+1]);
				}
				
			}
		}
	
		int K = Integer.parseInt(b.readLine());
		
		for(int k=0;k<K;k++)
		{
			strtok = new StringTokenizer(b.readLine()," ");
			int x1 = Integer.parseInt(strtok.nextToken());
			int y1 = Integer.parseInt(strtok.nextToken());
			int x2 = Integer.parseInt(strtok.nextToken());
			int y2 = Integer.parseInt(strtok.nextToken());
			
			if(x2==N)
			{
				if(y2==M)
					System.out.println(sum[x1][y1]);
				else
					System.out.println(sum[x1][y1] - sum[x1][y2+1]);
			}
			else
			{
				if(y2==M)
					System.out.println(sum[x1][y1] - sum[x2+1][y1]);
				else
					System.out.println(sum[x1][y1] - sum[x2+1][y1] - sum[x1][y2+1] + sum[x2+1][y2+1]);
			}
		}
	}
	
	//안품
	static void bj1699() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		int dp[] = new int[N+1];
		dp[0] = 1;
		
		
		for(int i=1;i<=N;i++)
		{
			
		}
	}
	
	static void bj11057() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		int dp[][] = new int[N][10];
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<10;j++)
			{
				if(i==0)
				{
					dp[i][j] = j+1;
				}
				else
				{
					if(j==0)
						dp[i][j] = 1;
					else
					{
						dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 10007;
					}
				}
			}
		}
		System.out.println(dp[N-1][9] % 10007);
	}
	
	static void bj2293() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
		int N = Integer.parseInt(strtok.nextToken());
		int K = Integer.parseInt(strtok.nextToken());
		
		int[] input = new int[N+1];
		int output[] = new int[K+1];
		output[0]=1;
		for(int i=1;i<=N;i++)
		{
			input[i] = Integer.parseInt(b.readLine());
		}
		
		for(int i=1;i<=N;i++)
		{
			for(int j=input[i];j<=K;j++)
				output[j] += output[j-input[i]];
		}
		
		System.out.println(output[K]);
	}
	
	
	static int triple_max(int a, int b, int c)
	{
		int result=a;
		
		if(result < b)
		{
			result = b;
		}
		if(result < c)
		{
			result = c;
		}
		return result;
	}
	
	static void bj9465() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int T = Integer.parseInt(b.readLine());
		
		for(int t=0;t<T;t++)
		{
			int N = Integer.parseInt(b.readLine());
			
			int input[][] = new int[2][N];
			int output[][] = new int[2][N];
			
			for(int i=0;i<2;i++)
			{
				StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
				for(int j=0;j<N;j++)
				{
					input[i][j] = Integer.parseInt(strtok.nextToken());
				}
			}
			
			
			for(int j=0;j<N;j++)
			{
				for(int i=0;i<2;i++)
				{
					if(j==0)
					{
						output[i][j] = input[i][j];
					}
					else if(j==1)
					{
						if(i==0)
						{
							output[i][j] = input[i][j] + input[1][j-1];
						}
						else
						{
							output[i][j] = input[i][j] + input[0][j-1];
						}
					}
					else
					{
						if(i==0)
						{
							output[i][j] = input[i][j] + triple_max(output[1][j-1], output[0][j-2], output[1][j-2]);
						}
						else
						{
							output[i][j] = input[i][j] + triple_max(output[0][j-1], output[0][j-2], output[1][j-2]);
						}
					}
					
				}
			}
			
			
			System.out.println(Math.max(output[1][N-1],output[0][N-1]));
			
			
		}
	}
	
	//못품
	static void bj1010() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int T = Integer.parseInt(b.readLine());
		
		for(int t=0;t<T;t++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			
			int N = Integer.parseInt(strtok.nextToken());
			int M = Integer.parseInt(strtok.nextToken());
			
			int dp[][] = new int[N][M];

			for(int i=0;i<N;i++)
			{
				for(int j=i;j<=M-N+i;j++)
				{
					dp[i][j] = 1;
					if(j!=0)
					{
						if(i!=0)
							dp[i][j] *= dp[i-1][j-1];
						
						dp[i][j] += dp[i][j-1];
						
						
					}
					
				
				}
			}
			System.out.println(dp[N-1][M-1]);
		}
	}
	
	static void bj2163() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		int result= N*M-1;
		
		
		System.out.println(result);
			
	}
	
	static void bj14501() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		int T[] = new int[N+1];//기간
		int P[] = new int[N+1];//금액
		int output[][] = new int[N+2][2];//결과
		
		for(int i=1;i<=N;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
			T[i] = Integer.parseInt(strtok.nextToken());
			P[i] = Integer.parseInt(strtok.nextToken());
		}
		
		int total_max = 0;
		
		for(int i=1;i<=N+1;i++)
		{
			if(i == 1)
			{
				output[i][0] = i + T[i];//완료일
				output[i][1] = 0;//가격
			}
			else
			{
				if(i!=N+1)
					output[i][0] = i + T[i];
				
				output[i][1] = output[i-1][1];
				
				int max = output[i][1];
				
				for(int j=i-1;j>=1;j--)
				{
					if(output[j][0] == i && output[j][1] + P[j] > max)
					{
						output[i][1] = output[j][1] + P[j];
						max = output[j][1] + P[j];
					}
				}
					
			}
			total_max = Math.max(total_max, output[i][1]);
		}
		
		System.out.println(total_max);
	}
	
	static void bj9461() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int T = Integer.parseInt(b.readLine());
		
		for(int t=0;t<T;t++)
		{
			int N = Integer.parseInt(b.readLine());
			long P[] = new long[N+1];
			
			for(int i=1;i<=N;i++)
			{
				if(i <= 3)
				{
					P[i] = 1;
				}
				else if(i == 4 || i==5)
				{
					P[i] = 2;
				}
				else
				{
					P[i] = P[i-1] + P[i-5];
				}
			}
			System.out.println(P[N]);
		}
		
	}
	
	static void bj11052() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		int P[] = new int[N+1];
		int output[] = new int[N+1];
		
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
		
		for(int i=1;i<=N;i++)
		{
			P[i] = Integer.parseInt(strtok.nextToken());
		}
		
		for(int i=1;i<=N;i++)
		{
			if(i==1)
			{
				output[i] = P[i];
			}
			else if(i==2)
			{
				output[i] = Math.max(P[i], P[i-1] * 2);
			}
			else
			{
				int max = Math.max(output[i-1] + P[1], P[i]);
				
				if(i%2 == 1)//홀수
				{
					for(int j=2;j<Math.ceil(i/2);j++)
					{
						max = Math.max(max, output[i-j] + output[j]);
					}
					
				}
				else
				{
					for(int j=2;j<=Math.ceil(i/2);j++)
					{
						max = Math.max(max, output[i-j] + output[j]);
					}
				}
				output[i] = max;
			}
		}
		System.out.println(output[N]);
		
	}
	
	static void bj11053() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		int input[] = new int[N];
		int output[] = new int[N];
		
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
		for(int i=0;i<N;i++)
		{
			input[i] = Integer.parseInt(strtok.nextToken());
		}
		int max = 1;
		
		for(int i=0;i<N;i++)
		{
			if(i==0)
			{
				output[i] = 1;//현재 길이
				
			}
			else
			{
				output[i] = 1;
				
				
				int current_length = 1;
				
				for(int j=i-1;j >=0;j--)
				{
					if(output[j] >= current_length && input[j] < input[i])
					{
						output[i] = output[j]+1;
						current_length = output[j];
					}
				}
				
			}
			max = Math.max(output[i], max);
		}

		System.out.println(max);
	}
	
	static void bj11727() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		int result[] = new int[N+1];
		
		for(int i=1;i<=N;i++)
		{
			if(i==1)
			{
				result[i] = 1;
			}
			else
			{
				if(i % 2 == 1)//홀수
					result[i] = (result[i-1] * 2 - 1) % 10007 ;
				else//짝수
					result[i] = (result[i-1] * 2 + 1) % 10007 ;
			}
		}
		System.out.println(result[N] % 10007);
	}
	
	static void bj10844() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		int result[][] = new int[N+1][10];
		
		for(int i=1;i<=N;i++)
		{
			if(i==1)
			{
				result[i][0] = 0;
				for(int j=1;j<10;j++)
				{
					result[i][j] = 1;
				}
			}
			else
			{
				result[i][0] = result[i-1][1] % 1000000000;//꼭 해줄 필요는 없는 부분
				for(int j=1;j<9;j++)
				{
					result[i][j] = (result[i-1][j-1] + result[i-1][j+1]) % 1000000000 ; // 꼭 해줘야하는 부분!!
				}
				result[i][9] = result[i-1][8] % 1000000000;//꼭 해줄 필요는 없는 부분
			}
		}
		
		long sum = 0;
		for(int i=0;i<10;i++)
			sum += (result[N][i]);
		System.out.println(sum % 1000000000);// 꼭해줘야 하는부분!!!
	}
	
	static void bj1912() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		int input[] = new int[N];
		int result[] = new int[N];
	
		StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
		
		for(int i=0;i<N;i++)
		{
			input[i] = Integer.parseInt(strtok.nextToken());
		}
		
		int total_max = input[0];
		
		for(int i=0;i<N;i++)
		{
			if(i==0)
			{
				result[i] = input[i];
			}
			else
			{
				if(input[i] < result[i-1])
					result[i] = result[i-1] + input[i];
				else
				{
					if(result[i-1] + input[i] < input[i])
						result[i] = input[i];
					else
						result[i] = result[i-1] + input[i];
				}
				if(total_max < result[i])
					total_max = result[i];
			}
		}
	
		System.out.println(total_max);
	}
	
	static void bj2156() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		int wine[] = new int[N];
		
		for(int i=0;i<N;i++)
		{
			wine[i] = Integer.parseInt(b.readLine());
		}
		
		int result[][] = new int[N][3];//0 : i-1 안더함(비연속), 1: i-1 더함(연속)
		
		for(int i=0;i<N;i++)
		{
			if(i==0)
			{
				result[i][0] = wine[i];
				result[i][1] = wine[i];
				result[i][2] = wine[i];
			}
			else if(i==1)
			{
				result[i][0] = wine[i] + wine[i-1];
				result[i][1] = wine[i] + wine[i-1];
				result[i][2] = wine[i] + wine[i-1];
			}
			else if(i==2)
			{
				result[i][0] = wine[i] + wine[i-1];
				result[i][1] = wine[i] + wine[i-2];
				result[i][2] = wine[i] + wine[i-2];
			}
			else
			{
				result[i][0] = wine[i] + (result[i-1][1] > result[i-1][2] ? result[i-1][1] : result[i-1][2]);
				result[i][1] = wine[i] + bj_2156_max(result[i-2]);
				result[i][2] = wine[i] + bj_2156_max(result[i-3]);
			}
		}
		
		int max = wine[0];
		for(int i=0;i<N;i++)
		{
			int temp = bj_2156_max(result[i]);
			if(max < temp)
				max = temp;
		}
		System.out.println(max);
	}
	
	static int bj_2156_max(int result[])
	{
		int mid = result[1] > result[2] ? result[1] : result[2];
		int max = result[0] > mid ? result[0] : mid;
		return max;
	}
	
	static void bj2618() throws Exception//경찰차
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		int W = Integer.parseInt(b.readLine());
		
		int[][] event = new int[W+1][2];
		int[][] event2 = new int[W+1][2];
		
		event[0][0] = 1;
		event[0][1] = 1;
		
		event2[0][0] = N;
		event2[0][1] = N;
		
		for(int i=1;i<=W;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
			event[i][0] = Integer.parseInt(strtok.nextToken());
			event[i][1] = Integer.parseInt(strtok.nextToken());
			event2[i][0] = event[i][0];
			event2[i][1] = event[i][1];
		}
		
		int dp[][] = new int[W+1][W+1];//2대의 경찰차, 경찰차 1 : (1,1), 경찰차 2 : (N,N)
		
		
		for(int i=0;i<W;i++)
		{
			
			for(int j=0;j<W;j++)
			{
				dp[i][j] = -1;

			}
			
		}

		System.out.println(find(0, 0, W, dp, event, event2));
	
		
		tracking(0, 0, W, dp, event, event2);
	}
	static int find(int c1, int c2, int W,int dp[][],int event[][], int event2[][])
	{
		if(c1 == W || c2 == W)
			return 0;
		
		if(dp[c1][c2] != -1)
		{
			return dp[c1][c2];
		}
		
		int np = Math.max(c1, c2) + 1;
		int n1 = distance_bj2618(event[c1][0], event[c1][1], event[np][0], event[np][1]);
		int n2 = find(np, c2, W, dp, event, event2) + n1;
		
		int m1 = distance_bj2618(event2[c2][0], event2[c2][1], event2[np][0], event2[np][1]);
		int m2 = find(c1, np, W, dp, event, event2) + m1;
		
		dp[c1][c2] = Math.min(n2, m2);
		return dp[c1][c2];
	}
	static void tracking(int c1,int c2,int W,int dp[][],int event[][], int event2[][])
	{
		if(c1 == W || c2 == W)
			return;
		
		int np = Math.max(c1, c2) + 1;
		int n1 = distance_bj2618(event[c1][0], event[c1][1], event[np][0], event[np][1]);
		int n2 = dp[np][c2] + n1;
		
		int m1 = distance_bj2618(event2[c2][0], event2[c2][1], event2[np][0], event2[np][1]);
		int m2 = dp[c1][np] + m1;
		
		if(n2 > m2)
		{
			System.out.println(2);
			tracking(c1, np, W, dp, event, event2);
		}
		else
		{
			System.out.println(1);
			tracking(np, c2, W, dp, event, event2);
		}
			
		
		
	}
	
	
	
	static int distance_bj2618(int x, int y, int x2, int y2)
	{
		return Math.abs(x-x2) + Math.abs(y-y2);
	}
	
	static void bj1932() throws Exception//정수 삼각형
	{
		InputStreamReader k = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(k);
		
		int n = Integer.parseInt(b.readLine());
		
		int[][] t = new int[n][n];
		
		for(int i=0;i<n;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
			for(int j=0;j<i+1;j++)
			{
				t[i][j] = Integer.parseInt(strtok.nextToken());
			}
		}
		
		int[][] max = new int[n][n];
		max[0][0] = t[0][0];
		for(int i=1;i<n;i++)
		{
			
			for(int j=0;j<i+1;j++)
			{
				if(j==0)
					max[i][j] = max[i-1][j] + t[i][j];
				else if(j == i)
				{
					max[i][j] = max[i-1][j-1] + t[i][j];
				}
				else
				{
					max[i][j] = max[i-1][j-1] > max[i-1][j] ? max[i-1][j-1] + t[i][j] : max[i-1][j] + t[i][j];
				}
				
			}
		}
		int maximum = -1;
		
		for(int i=0;i<n;i++)
		{
			if(maximum < max[n-1][i])
				maximum = max[n-1][i];
		}
		System.out.println(maximum);
	}
	
	static void bj2193() throws Exception
	{
		InputStreamReader k = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(k);
		
		int n = Integer.parseInt(b.readLine());
		
		long[][] t = new long[n+1][2];//수가 커지면 오버플로우 발생
		
		for(int i=1;i<=n;i++)
		{
			if(i == 1)//1자리수
			{
				t[i][0] = 0;
				t[i][1] = 1;
			}
			else
			{
				t[i][0] = t[i-1][1] + t[i-1][0];//결론은 피보나치
				t[i][1] = t[i-1][0];
			}
		}
		System.out.println(t[n][0] + t[n][1]);
		
	}
	
	static void bj1149() throws Exception//rgb 거리
	{
		InputStreamReader k = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(k);
		
		int n = Integer.parseInt(b.readLine());
		int[][] cost = new int[n][3];
		
		for(int i=0;i<n;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine(), " ");
			cost[i][0] = Integer.parseInt(strtok.nextToken());
			cost[i][1] = Integer.parseInt(strtok.nextToken());
			cost[i][2] = Integer.parseInt(strtok.nextToken());
		}
		
		int[][] min = new int[n][3];
		
		for(int i=0;i<n;i++)
		{
			if(i==0)
			{
				min[i][0] = cost[i][0];
				min[i][1] = cost[i][1];
				min[i][2] = cost[i][2];
			}
			else
			{
				min[i][0] = min[i-1][1] > min[i-1][2] ? cost[i][0] + min[i-1][2] : cost[i][0] + min[i-1][1];
				min[i][1] = min[i-1][0] > min[i-1][2] ? cost[i][1] + min[i-1][2] : cost[i][1] + min[i-1][0];
				min[i][2] = min[i-1][1] > min[i-1][0] ? cost[i][2] + min[i-1][0] : cost[i][2] + min[i-1][1];
			}
		}
		
		int minimum = 1000000;//이 값이 충분히 커야함!!
		
		for(int i=0;i<3;i++)
		{
			if(minimum > min[n-1][i])
				minimum = min[n-1][i];
		}
		
		System.out.println(minimum);
	}
	
	static void bj11726() throws Exception//2 * n 타일링
	{
		InputStreamReader k = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(k);
		
		int n = Integer.parseInt(b.readLine());
	
		int[] t = new int[n+1];
		
		for(int i=1;i<=n;i++)
		{
			if(i==1)
			{
				t[i] = 1;
			}
			else if(i==2)
			{
				t[i] = 2;
			}
			else
			{
				t[i] = (t[i-1] + t[i-2])%10007;//중요!
			}
		}
		System.out.println(t[n]); 
	}
	
	static void bj1003() throws Exception//피보나치
	{
		InputStreamReader k = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(k);
		
		int n = Integer.parseInt(b.readLine());
		int[] t= new int[n];
		
		for(int i=0;i<n;i++)
		{
			t[i] = Integer.parseInt(b.readLine());
		}
		
		for(int i=0;i<=n;i++)
		{
			int[][] count = new int[t[i] + 1][2];
			
			for(int j=0;j<=t[i];j++)
			{
				if(j==0)
				{
					count[j][0] = 1;
					count[j][1] = 0;
				}
				else if(j==1)
				{
					count[j][0] = 0;
					count[j][1] = 1;
				}
				else
				{
					count[j][0] = count[j-1][0] + count[j-2][0];
					count[j][1] = count[j-1][1] + count[j-2][1];
				}
			}
			System.out.println(count[t[i]][0] + " "+count[t[i]][1]);
		}
		
	}
	
	static void bj1463() throws Exception//1로 만들기
	{
		InputStreamReader k = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(k);
		
		int n = Integer.parseInt(b.readLine());
		int[][] min = new int[n + 1][3];
		
		
		for(int i=1;i<=n;i++)
		{
			for(int j=0;j<3;j++)
				min[i][j] = 10000;
		}
		
		for(int i=1;i<=n;i++)
		{
			if(i == 1) //i 가 1이면 0
			{
				for(int j=0;j<3;j++)
				{
					min[i][j] = 0;
				}
			}
			else if(i == 2)//i 가 2일때
			{
				for(int j=1;j<3;j++)//2개만 (2로나누는 경우, 1 뺴는 경우
				{
					min[i][j] = 1;
				}
			}
			else
			{
				if(i % 3 == 0)//3으로 나눠지면 3으로 나누기
				{
					int num = i/3;
					for(int j=0;j<3;j++)
					{
						if(min[i][0] > min[num][j])//3개중 가장 작은 값
							min[i][0] = min[num][j] + 1;
					}
				}
				if(i % 2 == 0) //2로 나눠지면 2로 나누기
				{
					int num = i/2;
					for(int j=0;j<3;j++)
					{
						if(min[i][1] > min[num][j])//3개중 가장 작은 값
							min[i][1] = min[num][j] + 1;
					}
				}
				
				int num = i - 1;
				for(int j=0;j<3;j++)
				{
					if(min[i][2] > min[num][j])//3개중 가장 작은 값
						min[i][2] = min[num][j] + 1;
				}
			}	
		}
		int result = 10000;
		
		for(int i=0;i<3;i++)
		{
			if(result > min[n][i])
				result = min[n][i];
		}
		System.out.println(result);
	}
	
	
	
	static void bj2579() throws Exception//계단오르기 - bottom-up
	{
		
		InputStreamReader k = new InputStreamReader(System.in);
	    BufferedReader scan = new BufferedReader(k);
		
		int n = Integer.parseInt(scan.readLine());
		int[] t = new int[n];
		int[][] sum = new int[n][2];
		
		for(int i=0;i<n;i++)
		{
			t[i] = Integer.parseInt(scan.readLine());
			for(int j =0;j < 2; j++)
				sum[i][j] = 0;
		}
	//	System.out.println(stairs(t,n - 1, 0, 0));
		
		//sum[i][0] 은 비연속(sum[i-2]의 값 둘 중에 가장 큰 값), sum[i][1] 은 연속(sum[i-1][0]만!! sum[i-1][1]을  집어넣게되면 3계단 연속 밟는것이라서 오류)
		for(int i = 0 ;i<n; i++)
		{
			if(i==0)
			{
				sum[i][0] = 0;
				sum[i][1] = t[i];
			}
			else if(i==1)
			{
				sum[i][0] = sum[i-1][0] + t[i];
				sum[i][1] = sum[i-1][1] + t[i];
			}
			else
			{
				if(sum[i-2][0] > sum[i-2][1])
				{
					sum[i][0] = sum[i-2][0] + t[i];
				}
				else
				{
					sum[i][0] = sum[i-2][1] + t[i];
				}
				
				sum[i][1] = sum[i-1][0] + t[i];
			}
				 
		}
		System.out.println(sum[n-1][0] > sum[n-1][1] ? sum[n-1][0] : sum[n-1][1]);
	}
	
	static int stairs(int[] t,int i,int continuous,int sum)//bj2579 - 계단오르기 재귀(시간초과)
	{
		
		if(i>=0)
		{
			int sum1=0,sum2=0;
			
			sum2 = stairs(t,i - 2, 0, sum + t[i]);
			if(continuous != 1)
				sum1 = stairs(t,i - 1, continuous + 1, sum + t[i]);
			
			return sum1 > sum2  ? sum1 : sum2;
		}
		return sum;
	}
	
	static void bj9095()
	{
		Scanner scan = new Scanner(System.in);
		
		
		int n = scan.nextInt();
		int[] t = new int[n];
		
		for(int i=0;i<n;i++)
		{
			t[i] = scan.nextInt();
		}
		
		for(int i=0;i<n;i++)
		{
			count = 0;
			calculate(t[i],0);
			System.out.println(count);
		}
	}
	
	static void calculate(int n,int sum)
	{
	
		for(int i=1;i<=3;i++)
		{
			if(sum + i == n)
				count++;
			else if(sum + i < n)
				calculate(n, sum + i);
		}
	}
	
	static void cuttingStick ()//동적 프로그래밍 - 막대기 자르기 문제
	{
		int p[] = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};// p[i]는 i 길이 막대기의 가격 i = 0 ~ 10
		
		System.out.println(R(p,7));
	}
	
	static void stoneTable () //p275
	{
		int n = 8;
		int map[][] = {
				{6,7,12,-5,5,3,11,3},
				{-8,10,14,9,7,13,8,5},
				{11,12,7,4,8,-2,9,4}
		};
		
		int max[] = {3,5,4,7};
		int[] pattern = new int[8];
		
		for(int i=0;i<4;i++)
		{
			pattern[7] = i;
			for(int j=6;j>=0;j--)
			{
				if(pattern[j+1] == 0) //첫번째자리에 돌
				{
					if(map[1][j] + map[0][j+1] > map[2][j] + map[0][j+1])
					{	
						pattern[j] = 1;
						max[i] += map[1][j];
					}
					else
					{
						pattern[j] = 2;
						max[i] += map[2][j];
					}
					
				}
				else if(pattern[j+1] == 1)//두번째자리에 돌
				{
					int temp[] = {map[0][j] + map[1][j+1], 0,map[2][j] + map[1][j+1], map[0][j] + map[2][j] + map[1][j+1]};
					int result=0;
					for(int k=0;k<4;k++)
					{
						if(result < temp[k])
						{
							result = temp[k];
							pattern[j] = k;
						}
					}
					if(pattern[j] == 0)
						max[i] += map[0][j];
					else if(pattern[j] == 2)
						max[i] += map[2][j];
					else
						max[i] += map[0][j] + map[2][j];
				}
				else if(pattern[j+1] == 2)//세번째 자리에 돌
				{
					if(map[0][j] + map[0][j+1] > map[1][j] + map[0][j+1])
					{
						pattern[j] = 0;
						max[i] += map[0][j];
					}
					else
					{
						pattern[j] = 1;
						max[i] += map[1][j];
					}
				}
				else//첫번째,세번째자리에 돌
				{
					pattern[j] = 1;
					max[i] += map[1][j];
				}
			}
		}
		for(int i=0;i<4;i++)
		{
			System.out.println(max[i]);
		}
		
	}
	
	static int R(int[] p,int n)//R2(n)은 n 길이의 막대기의 최대가격 - bottom up
	{
		int max=0;
		int r[]=new int[p.length];
		
		for(int i=0;i<r.length;i++)
			r[i]=0;
		
		for(int j=0;j<=n;j++)
		{
			max=0;
			for(int i=0;i<=j;i++)
			{
				max = Math.max(max, p[i] + r[j-i]);
			}
			r[j] = max;
		}
		return r[n];
	}
	
	static void findingRoute() //동적 프로그래밍 - 책 p270 bottom-up
	{
		int n = 4;
		int map[][] = {
				{0,0,0,0,0},
				{0,6,7,12,5},
				{0,5,3,11,18},
				{0,7,17,3,3},
				{0,8,10,14,9},
		};
		
		int max[][] = {
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
		};
		
		max[1][1] = map[1][1];
		for(int i=2;i<=n;i++)
		{
			max[i][1] = max[i-1][1] + map[i][1];
		}
		
		for(int i=2;i<=n;i++)
		{
			max[1][i] = max[1][i-1] + map[1][i];
		}
		
		for(int j=2;j<=n;j++)
		for(int i=2;i<=n;i++)
		{
			max[j][i] = Math.max(max[j-1][i] + map[j][i], max[j][i-1] + map[j][i]);
		}
		
		System.out.println(max[n][n]);
	}
	
	static int matrixPath(int i,int j) //동적 프로그래밍 - 책 p270 top-down (중복호출o! - 안좋음)
	{
		int map[][] = {
				{0,0,0,0,0},
				{0,6,7,12,5},
				{0,5,3,11,18},
				{0,7,17,3,3},
				{0,8,10,14,9},
		};
		if(i==0 || j==0)
			return 0;
		else
			return map[i][j] + Math.max(matrixPath(i-1, j), matrixPath(i, j-1));
	}
	
}
