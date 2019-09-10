import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class DynamicProgramming {

	static int count = 0;
	
	public DynamicProgramming() {
		// TODO Auto-generated constructor stub
	}
	
	static void bj1463() throws Exception//1로 만들기
	{
		InputStreamReader k = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(k);
		
		int n = Integer.parseInt(b.readLine());
		
		
	}
	
	static int making(int count)
	{
		
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
