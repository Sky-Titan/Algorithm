import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Scanner;

public class DynamicProgramming {

	static int count = 0;
	
	public DynamicProgramming() {
		// TODO Auto-generated constructor stub
	}
	
	static void bj2618() throws Exception//������
	{
		InputStreamReader k = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(k);
		
		int n = Integer.parseInt(b.readLine());
		int r = Integer.parseInt(b.readLine());
		
		int[][] event = new int[r][2];
		
		for(int i=0;i<r;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine()," ");
			event[i][0] = Integer.parseInt(strtok.nextToken());
			event[i][1] = Integer.parseInt(strtok.nextToken());
		}
		
		int[][] pos = new int[r][2];//������ 1�� ��ġ - 1
		int[][] pos2 = new int[r][2];//������ 2�� ��ġ - 1
		
		int[][] pos3 = new int[r][2];//������ 1�� ��ġ - 2
		int[][] pos4 = new int[r][2];//������ 2�� ��ġ - 2
		
		int sum=0;
		int[][] min = new int[r][2];
		boolean[][] police = new boolean[r][2];
		boolean[][] police2 = new boolean[r][2];
		
		for(int i=0;i<r;i++)
		{
			if(i==0)
			{
				min[i][0] = Math.abs(event[i][0] - 1) + Math.abs(event[i][1] - 1);//������1 �ذ�
				min[i][1] = Math.abs(event[i][0] - n) + Math.abs(event[i][1] - n);//������2 �ذ�
				
				pos[i][0] = 1;//1�� x��ǥ
				pos[i][1] = 1;//1�� y��ǥ
				pos2[i][0] = n;//2�� x��ǥ
				pos2[i][1] = n;//2�� y��ǥ
				
				pos3[i][0] = 1;//1�� x��ǥ
				pos3[i][1] = 1;//1�� y��ǥ
				pos4[i][0] = n;//2�� x��ǥ
				pos4[i][1] = n;//2�� y��ǥ
			}
			else
			{
				//i �� 1�� �ذ� �� ��
				if(min[i-1][0] + Math.abs(event[i][0] - event[i-1][0]) + Math.abs(event[i][1] - event[i-1][1]) 
				< min[i-1][1] + Math.abs(event[i][0] - pos[i-1][0]) + Math.abs(event[i][1] - pos[i-1][1]))//i - 1 �� 1�� �ذ������� �� ������ 
				{
					min[i][0] = min[i-1][0] + Math.abs(event[i][0] - event[i-1][0]) + Math.abs(event[i][1] - event[i-1][1]);
					pos[i-1][0] = event[i-1][0];
					pos[i-1][1] = event[i-1][1];
					
					
					
					police[i-1][0] = true;
					police[i-1][1] = false;
					if(i!=1)
					{
						pos2[i-1][0] = pos2[i-2][0];
						pos2[i-1][1] = pos2[i-2][1];
						
					}
					else 
					{
						pos2[i-1][0] = n;
						pos2[i-1][1] = n;
					}
				}
				else//i-1�� 2�� �ذ��Ѱ� �� ������
				{
					min[i][0] = min[i-1][1] + Math.abs(event[i][0] - pos[i-1][0]) + Math.abs(event[i][1] - pos[i-1][1]);
					pos2[i-1][0] = event[i-1][0];
					pos2[i-1][1] = event[i-1][1];
					
					police[i-1][0] = false;
					police[i-1][1] = true;
					if(i!=1)
					{
						pos[i-1][0] = pos[i-2][0];
						pos[i-1][1] = pos[i-2][1];
						
						
					}
					else 
					{
						pos[i-1][0] = 1;
						pos[i-1][1] = 1;
					}
					
					
				}
				
				pos[i][0] = event[i][0];
				pos[i][1] = event[i][1];
				pos2[i][0] = pos2[i-1][0];
				pos2[i][1] = pos2[i-1][1];
				
				//i �� 2�� �ذ� �� ��
				if(min[i-1][0] + Math.abs(event[i][0] - pos4[i-1][0]) + Math.abs(event[i][1] - pos4[i-1][1]) 
				< min[i-1][1] + Math.abs(event[i][0] - event[i-1][0]) + Math.abs(event[i][1] - event[i-1][1]))//i - 1 �� 1�� �ذ������� �� ������ 
				{
					min[i][1] = min[i-1][0] + Math.abs(event[i][0] - pos4[i-1][0]) + Math.abs(event[i][1] - pos4[i-1][1]);
					pos3[i-1][0] = event[i-1][0];
					pos3[i-1][1] = event[i-1][1];
					
					police2[i-1][0] = true;
					police2[i-1][1] = false;
					if(i!=1)
					{
						pos4[i-1][0] = pos4[i-2][0];
						pos4[i-1][1] = pos4[i-2][1];
					}
					else
					{
						pos4[i-1][0] = n;
						pos4[i-1][1] = n;
					}
				}
				else//i-1�� 2�� �ذ��Ѱ� �� ������
				{
					min[i][1] = min[i-1][1] + Math.abs(event[i][0] - event[i-1][0]) + Math.abs(event[i][1] - event[i-1][1]);
					pos4[i-1][0] = event[i-1][0];
					pos4[i-1][1] = event[i-1][1];
					
					police2[i-1][0] = false;
					police2[i-1][1] = true;
					if(i!=1)
					{
						pos3[i-1][0] = pos3[i-2][0];
						pos3[i-1][1] = pos3[i-2][1];
					}
					else
					{
						pos3[i-1][0] = 1;
						pos3[i-1][1] = 1;
					}
				}
				pos3[i][0] = pos3[i-1][0];
				pos3[i][1] = pos3[i-1][1];
				pos4[i][0] = event[i][0];
				pos4[i][1] = event[i][1];
				
			}
		}
		
		if(min[r-1][0] < min[r-1][1])//1�� �ذ��Ѱ� ������
		{
			System.out.println(min[r-1][0]);
			police[r-1][0] = true;
			police[r-1][1] = false;

			for(int i=0;i<r;i++)
			{
				if(police[i][0] == true)
				{
					System.out.println(1);
				}
				else
					System.out.println(2);
			}
		}
		else//2�� �ذ��Ѱ� ������
		{
			System.out.println(min[r-1][1]);
			police2[r-1][0] = false;
			police2[r-1][1] = true;
		
			for(int i=0;i<r;i++)
			{
				if(police2[i][0] == true)
				{
					System.out.println(1);
				}
				else
					System.out.println(2);
			}
		}
		
		
		
		
	}
	
	static void bj1932() throws Exception//���� �ﰢ��
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
		
		long[][] t = new long[n+1][2];//���� Ŀ���� �����÷ο� �߻�
		
		for(int i=1;i<=n;i++)
		{
			if(i == 1)//1�ڸ���
			{
				t[i][0] = 0;
				t[i][1] = 1;
			}
			else
			{
				t[i][0] = t[i-1][1] + t[i-1][0];//����� �Ǻ���ġ
				t[i][1] = t[i-1][0];
			}
		}
		System.out.println(t[n][0] + t[n][1]);
		
	}
	
	static void bj1149() throws Exception//rgb �Ÿ�
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
		
		int minimum = 1000000;//�� ���� ����� Ŀ����!!
		
		for(int i=0;i<3;i++)
		{
			if(minimum > min[n-1][i])
				minimum = min[n-1][i];
		}
		
		System.out.println(minimum);
	}
	
	static void bj11726() throws Exception//2 * n Ÿ�ϸ�
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
				t[i] = (t[i-1] + t[i-2])%10007;//�߿�!
			}
		}
		System.out.println(t[n]); 
	}
	
	static void bj1003() throws Exception//�Ǻ���ġ
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
	
	static void bj1463() throws Exception//1�� �����
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
			if(i == 1) //i �� 1�̸� 0
			{
				for(int j=0;j<3;j++)
				{
					min[i][j] = 0;
				}
			}
			else if(i == 2)//i �� 2�϶�
			{
				for(int j=1;j<3;j++)//2���� (2�γ����� ���, 1 ���� ���
				{
					min[i][j] = 1;
				}
			}
			else
			{
				if(i % 3 == 0)//3���� �������� 3���� ������
				{
					int num = i/3;
					for(int j=0;j<3;j++)
					{
						if(min[i][0] > min[num][j])//3���� ���� ���� ��
							min[i][0] = min[num][j] + 1;
					}
				}
				if(i % 2 == 0) //2�� �������� 2�� ������
				{
					int num = i/2;
					for(int j=0;j<3;j++)
					{
						if(min[i][1] > min[num][j])//3���� ���� ���� ��
							min[i][1] = min[num][j] + 1;
					}
				}
				
				int num = i - 1;
				for(int j=0;j<3;j++)
				{
					if(min[i][2] > min[num][j])//3���� ���� ���� ��
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
	
	
	
	static void bj2579() throws Exception//��ܿ����� - bottom-up
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
		
		//sum[i][0] �� �񿬼�(sum[i-2]�� �� �� �߿� ���� ū ��), sum[i][1] �� ����(sum[i-1][0]��!! sum[i-1][1]��  ����ְԵǸ� 3��� ���� ��°��̶� ����)
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
	
	static int stairs(int[] t,int i,int continuous,int sum)//bj2579 - ��ܿ����� ���(�ð��ʰ�)
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
	
	static void cuttingStick ()//���� ���α׷��� - ����� �ڸ��� ����
	{
		int p[] = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};// p[i]�� i ���� ������� ���� i = 0 ~ 10
		
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
				if(pattern[j+1] == 0) //ù��°�ڸ��� ��
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
				else if(pattern[j+1] == 1)//�ι�°�ڸ��� ��
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
				else if(pattern[j+1] == 2)//����° �ڸ��� ��
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
				else//ù��°,����°�ڸ��� ��
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
	
	static int R(int[] p,int n)//R2(n)�� n ������ ������� �ִ밡�� - bottom up
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
	
	static void findingRoute() //���� ���α׷��� - å p270 bottom-up
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
	
	static int matrixPath(int i,int j) //���� ���α׷��� - å p270 top-down (�ߺ�ȣ��o! - ������)
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
