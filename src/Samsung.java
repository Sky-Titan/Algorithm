import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Samsung {
	
	public Samsung()
	{
		
	}
	static void s5656() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int  T = Integer.parseInt(b.readLine());
		
		for(int t=0;t<T;t++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			int N = Integer.parseInt(strtok.nextToken());
			int W = Integer.parseInt(strtok.nextToken());
			int H = Integer.parseInt(strtok.nextToken());
			
			int map[][] = new int[H][W];
			
			int origin_blocks = 0;//���� ��ϼ�
			
			for(int i=0;i<H;i++)
			{
				strtok = new StringTokenizer(b.readLine());
				for(int j=0;j<W;j++)
				{
					map[i][j] = Integer.parseInt(strtok.nextToken());
					if(map[i][j] != 0)
						origin_blocks++;
				}
			}
			
			ArrayList<Integer> counts = new ArrayList<>();
			A_s5656(N, map, H, W, counts);
			
			int max = Integer.MIN_VALUE;
			int result = 0;
			for(int j=0;j<counts.size();j++)
			{
				
				if(max < origin_blocks-counts.get(j))
				{
					max = origin_blocks-counts.get(j);
					result = counts.get(j);
				}
			}
			
			System.out.println("#"+String.valueOf(t+1) +" "+result);
		}
	}
	
	static void A_s5656(int N, int map[][], int H, int W, ArrayList<Integer> counts)
	{
		if(N==0)
		{
			counts.add(count_blocks(map, H, W));		
		}
		else
		{
			for(int j=0;j<W;j++)
			{
				for(int i=0;i<H;i++)
				{
					if(map[i][j] != 0)//������ �ִ� ������ ����
					{
						int map2[][] = new int[H][W];
						for(int k=0;k<H;k++)
							map2[k] = map[k].clone();
						
						boolean visited[][] = new boolean[H][W];
						explosive(map2, visited,i, j, H, W);//������ ������ ���ϱ�
						for(int h=0;h<H;h++)
						{
							for(int w=0;w<W;w++)
							{
								if(visited[h][w])//����
									map2[h][w] = 0;
							}
						}
						dropBlocks(map2, H, W);//���� ���
						
						A_s5656(N-1, map2, H, W, counts);
						break;
					}
				}
				
			
			}
		}
	}
	//���� ��� ����
	static int count_blocks(int map[][], int H, int W)
	{
		int count=0;
		for(int i=0;i<H;i++)
		{
			
			for(int j=0;j<W;j++)
			{
				if(map[i][j] != 0)
					count++;
			}
			
			
		}
		return count;
	}
	
	static void explosive(int map[][],boolean visited[][] ,int x,int y, int H,int W)
	{
		int n = map[x][y];
		
		//���������� ������ ��ϵ� ��ġ �߰�
		ArrayList<Integer> h = new ArrayList<>();
		ArrayList<Integer> w = new ArrayList<>();
		
		for(int i=x;i<x+n;i++)
		{
			if(i!=H)
			{
				if(map[i][y] != 0 && map[i][y] != 1)
				{
					if(i!=x)//���� ��ġ �߰� x
					{
						if(!visited[i][y])//�ߺ� �߰� ����
						{
							h.add(i);
							w.add(y);
						}
					}
				}
				visited[i][y] = true;
				
			}
			else
				break;	
		}
		
		for(int i=x;i>x-n;i--)
		{
			if(i!=-1)
			{
				if(map[i][y] != 0 && map[i][y] != 1)
				{
					if(i!=x)//���� ��ġ �߰� x
					{
						if(!visited[i][y])//�ߺ� �߰� ����
						{
							h.add(i);
							w.add(y);
						}
					}
				}
				visited[i][y] = true;
			}
			else
				break;
			
		}
		
		for(int j=y;j<y+n;j++)
		{
			if(j!=W)
			{
				if(map[x][j] != 0 && map[x][j] != 1)
				{
					if(j!=y)//���� ��ġ �߰� x
					{
						if(!visited[x][j])//�ߺ� �߰� ����
						{
							h.add(x);
							w.add(j);
						}
					}
				}
				visited[x][j] = true;
			}
			else
				break;
			
		}
		
		for(int j=y;j>y-n;j--)
		{
			if(j!=-1)
			{
				if(map[x][j] != 0 && map[x][j] != 1)
				{
					if(j!=y)//���� ��ġ �߰� x
					{
						if(!visited[x][j])//�ߺ� �߰� ����
						{
							h.add(x);
							w.add(j);
						}
					}
				}
				visited[x][j] = true;
			}
			else
				break;
			
		}
		
		for(int i=0;i<h.size();i++)
			explosive(map,visited ,h.get(i), w.get(i), H, W);//�� �ķ� �������� ���� �� �ִ� �ֵ� explosive ��Ŵ
	}
	
	//���� ������
	static void dropBlocks(int map[][], int H, int W)
	{
		
		for(int j=0;j<W;j++)
		{
			for(int i=H-1;i>=0;i--)
			{
				if(map[i][j] != 0 & i!=H-1)//drop�ؾ���
				{
					for(int k=i+1;k<H;k++)
					{
						if(map[k][j] != 0)//����
							break;
						else
						{
							map[k][j] = map[k-1][j];
							map[k-1][j] = 0;
						}
					}
				}
			}
		}
	}
	
	static void s5658() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int  T = Integer.parseInt(b.readLine());
		
		for(int t=0;t<T;t++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			int N = Integer.parseInt(strtok.nextToken());//���� 4�� ���
			int K = Integer.parseInt(strtok.nextToken());//K��°�� ū ����
			char numbers[] = new char[N+1];
			
			String str = b.readLine();
			for(int i=1;i<=N;i++)
			{
				numbers[i] = str.charAt(i-1);
			}
			
			HashSet<Integer> set = new HashSet<>();
			
			int list[] = bigNum(numbers, N);
			
			for(int i=0;i<list.length;i++)
			{
				set.add(list[i]);
			}
			
			for(int i=0;i<N/4;i++)
			{
				moveNext(numbers, N);
				list = bigNum(numbers, N);
				
				for(int j=0;j<list.length;j++)
				{
					set.add(list[j]);
				}
			
			}
			
			Object[] o = set.toArray();
			
			Arrays.sort(o);
			System.out.println("#"+String.valueOf(t+1)+" "+(int)o[o.length-K]);
		}
	}
	
	static int[] bigNum(char numbers[], int N)
	{
		int round = N/4;
		
		int list[] = new int[4];
		
		for(int i=1;i<=4;i++)
		{
			String num = "";
			for(int j = round *(i-1) + 1; j<=round * i;j++ )
			{
				num += numbers[j]+"";
			}
		
			list[i-1] = HexToint(num);
		}
		return list;
	}
	static int HexToint(String hex)
	{
		int num = 0;
		for(int i=0;i<hex.length();i++)
		{
			int p = hex.length() - i - 1;
			char c = hex.charAt(i);
			int n = 0;
			
			if(Character.isAlphabetic(c))
			{
				n = c-55;
			}
			else
			{
				n = c - 48;
			}
			
			num += (int)Math.pow(16, p) * n;
			
		}
		
		return num;
	}
	static void moveNext(char numbers[], int N)
	{
		for(int i=N;i>=1;i--)
		{
			if(i==N)
				numbers[0] = numbers[i];
			numbers[i] = numbers[i-1];
		}
	}

}
