import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Simulation {

	static class Position{
		
		int x,y;
		public Position()
		{
			
		}
		public Position(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
	}
	
	static void bj3190() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());//�� ũ��
		int K = Integer.parseInt(b.readLine());//��� ����
		
		int map[][] = new int[N+1][N+1];
		for(int i=0;i<=N;i++)
		{
			for(int j=0;j<=N;j++)
				map[i][j] = 0;
		}
		
		for(int k=0;k<K;k++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			int x = Integer.parseInt(strtok.nextToken());
			int y = Integer.parseInt(strtok.nextToken());
			
			map[x][y] = -1;//���
		}
		
		int L = Integer.parseInt(b.readLine());//�� ������ȯ Ƚ��
		ArrayList<Integer> time = new ArrayList<>();
		char direction[] = new char[L];
		
		for(int l = 0; l < L; l++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			time.add( Integer.parseInt(strtok.nextToken()) );
			direction[l] = strtok.nextToken().charAt(0);
		}
		
		int result = 0;//�ɸ��ð�
		map[1][1] = 1;//�� ��ġ
		
		Position head = new Position(1,1);
		Position tail = new Position(1,1);
		
		
		
		int d = 4;//1 : ����, 2: ����, 3:����, 4:���� -> �Ӹ� �̵�����
		while(true)
		{
			result++;//�ð�����
			
			int former_head = map[head.x][head.y];
			
			if(d==1)//���� �̵�
			{
				if(head.x!=1)
					head.x -= 1;
				else//���̸� �ߴ�
					break;
			}
			else if(d==2)//���� �̵�
			{
				if(head.x!=N)
					head.x += 1;
				else
					break;
			}
			else if(d==3)//���� �̵�
			{
				if(head.y!=1)
					head.y -= 1;
				else
					break;
			}
			else//����
			{
				if(head.y!=N)
					head.y += 1;
				else
					break;
			}
			
			
			
			int apple = map[head.x][head.y];
			
			if(map[head.x][head.y] > 0)//���� ���̸� �ߴ�
				break;
			else
				map[head.x][head.y] = former_head+1;
			
			if(apple != -1)//��� ������ ���� �̵�
			{
				int former_tail = map[tail.x][tail.y];
				
				map[tail.x][tail.y] = 0;//�̵�
				
				if(tail.x != 1 && map[tail.x-1][tail.y] == former_tail + 1)
					tail.x -= 1;
				else if(tail.x != N && map[tail.x+1][tail.y] == former_tail + 1)
					tail.x += 1;
				else if(tail.y != N && map[tail.x][tail.y+1] == former_tail + 1)
					tail.y += 1;
				else if(tail.y != 1 && map[tail.x][tail.y-1] == former_tail + 1)
					tail.y -= 1;
				
			}
			
			
			
			if(time.contains(result))//������ȯ �ϴ� �ð����� Ȯ��
			{
				if(direction[time.indexOf(result)] == 'L')//�������� ȸ��
				{
					if(d==1)//��
						d = 3;
					else if(d==2)//��
						d = 4;
					else if(d==3)//��
						d = 2;
					else//��
						d = 1;
				}
				else//���������� ȸ��
				{
					if(d==1)//��
						d = 4;
					else if(d==2)//��
						d = 3;
					else if(d==3)//��
						d = 1;
					else//��
						d = 2;
				}
			}
			
			
		}
		System.out.println(result);
	}
	
	static void bj14499() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		int x = Integer.parseInt(strtok.nextToken());
		int y = Integer.parseInt(strtok.nextToken());
		int K = Integer.parseInt(strtok.nextToken());
		
		int map[][] = new int[N][M];//����
		
		
		for(int i=0;i<N;i++)
		{
			strtok = new StringTokenizer(b.readLine());
			for(int j=0;j<M;j++)
			{
				map[i][j] = Integer.parseInt(strtok.nextToken());
			}
		}
		
		strtok = new StringTokenizer(b.readLine());
		
		int cube[][] = {
				{0,0,0}, {0,0,0}, {0,0,0}, {0,0,0}
		};//�ֻ���
		
		for(int k=0;k<K;k++)
		{
			int order = Integer.parseInt(strtok.nextToken());
			
			
			if(order==1)//����
			{
				if(y != M - 1)
					y = y + 1;
				else
					continue;
			}
			else if(order==2)//����
			{
				if(y != 0)
					y = y - 1;
				else
					continue;
			}
			else if(order==3)//����
			{
				if(x != 0)
					x = x - 1;
				else
					continue;
			}
			else//����
			{
				if(x != N - 1)
					x = x + 1;
				else
					continue;
			}
			cube_change(cube, order);
			
			
			if(map[x][y] == 0)//�ֻ��� �ٴڸ�[3][1] ���� -> ĭ[x][y]
			{
				map[x][y] = cube[3][1];
			}
			else//ĭ[x][y] ���� -> �ֻ��� �ٴڸ�[3][1] (ĭ = 0)
			{
				cube[3][1] = map[x][y];
				map[x][y] = 0;
			}

			
			System.out.println(cube[1][1]);//�ֻ�� ���� ���� ���
		}
		
		
	}
	
	static void cube_change(int cube[][], int direction)//�ֻ��� ������
	{
		//1 : ����, 2 : ����, 3 : ����, 4 : ����
		if(direction==1)
		{
			int temp = cube[1][2];
			for(int i=2;i>=1;i--)
			{
				cube[1][i] = cube[1][i-1];
				
			}
			
			cube[1][0] = cube[3][1];
			cube[3][1] = temp;
		}
		else if(direction==2)
		{
			int temp = cube[1][0];
			
			for(int i=0;i<=1;i++)
			{
				cube[1][i] = cube[1][i+1];	
			}
			
			cube[1][2] = cube[3][1];
			cube[3][1] = temp;
		}
		else if(direction==3)
		{
			int temp = cube[0][1];
			
			for(int i=0;i<=2;i++)
			{
				cube[i][1] = cube[i+1][1];
			}
			
			cube[3][1] = temp;
		}
		else
		{
			int temp = cube[3][1];
			
			for(int i=3;i>=1;i--)
			{
				cube[i][1] = cube[i-1][1];
			}
			cube[0][1] = temp;
		}
		
	}
	
	static void bj1018() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());

		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		char chess[][] = new char[N][M];
		
		for(int i=0;i<N;i++)
		{
			String str = b.readLine();
			for(int j=0;j<M;j++)
			{
				chess[i][j] = str.charAt(j);
			}
		}

		int result=N*M+1;//��� : �ּ� ĥ�ؾ��� ü�� ��� ����

		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				if(N - i >= 8 && M - j >= 8)//8 * 8�� �ڸ� ���
				{
					//1. ������ : B �ΰ�� 2. ������ : W�� ���
					char start_color[] = {'B', 'W'};
					
					for(int k=0;k<2;k++)
					{
						char former = start_color[k];
						int count=0;//��ĥ ���� 
						
						for(int n = i;n < i + 8; n++)
						{
							for(int m = j; m < j + 8; m++)
							{
								if(n == i && m == j)//��������
								{
									if(chess[n][m] != start_color[k])//���� ����� �ٸ��� ����
										count++;
								}
								else//�ٸ� ����
								{
									if(chess[n][m] == former)//ĥ�ؾ� �Ǵ� ���
										count++;
									
									if(former == 'B')
										former = 'W';
									else
										former = 'B';
								}
							}
							//���ٳ����� ���ٲٱ�
							if(former == 'B')
								former = 'W';
							else
								former = 'B';
						}
						
						//���̽� �ϳ� ���������� ��
						result = Math.min(result, count);
					}
				}
			}
		}
		System.out.println(result);
	}
	
	static void bj14503() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		strtok = new StringTokenizer(b.readLine());
		int x = Integer.parseInt(strtok.nextToken());
		int y = Integer.parseInt(strtok.nextToken());
		int direction = Integer.parseInt(strtok.nextToken());
		
		int map[][] = new int[N][M];
		
		for(int i=0;i<N;i++)
		{
			strtok = new StringTokenizer(b.readLine());
			for(int j=0;j<M;j++)
			{
				map[i][j] = Integer.parseInt(strtok.nextToken());
			}
		}
		
		int result = 0;//û���� ���� ����
		
		Queue<Position> q = new LinkedList<>();
		map[x][y]=2;//û�� �Ϸ�
		result++;//���� ����
		q.offer(new Position(x, y));
		
		while(!q.isEmpty())
		{
			Position p = q.poll();
			
			int directionCnt = 0;
			int temp= changeDirection(direction);
			while(q.isEmpty())
			{
				directionCnt++;
				
				Position pos = newDirection(p.x, p.y, temp);//�ش� ���� ��ġ
				
				if(map[pos.x][pos.y]==0)
				{	
					map[pos.x][pos.y] = 2;
					direction = temp;
					result++;//���� ����
					q.offer(pos);
					break;
				}
				
				if(q.isEmpty())
					temp = changeDirection(temp);
				
				if(directionCnt==4)//�ݴ�������� ����
				{
					if(direction==0 && map[p.x+1][p.y] != 1)//����
					{
						q.offer(new Position(p.x+1, p.y));
					}
					else if(direction==1 && map[p.x][p.y-1] != 1)//����
					{
						q.offer(new Position(p.x, p.y-1));
					}
					else if(direction==2 && map[p.x-1][p.y] != 1)//����
					{
						q.offer(new Position(p.x-1, p.y));
					}
					else if(direction==3 && map[p.x][p.y+1] != 1)//����
					{
						q.offer(new Position(p.x, p.y+1));
					}
					break;
						
				}
				
			}
			
		}
		
		
		System.out.println(result);
	}
	
	static Position newDirection(int x, int y, int direction)
	{
		if(direction == 0)//����
		{
			return new Position(x-1, y);
		}
		else if(direction == 1)//����
		{
			return new Position(x, y+1);
		}
		else if(direction == 2)//����
		{
			return new Position(x+1, y);
		}
		else//����
		{
			return new Position(x, y-1);
		}
	}
	
	static int changeDirection(int direction)
	{
		if(direction!=0)
			return direction-1;
		else
			return 3;
	}
	
	static void bj1966() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int T = Integer.parseInt(b.readLine());
		
		for(int t=0;t<T;t++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			
			int N = Integer.parseInt(strtok.nextToken());//��������
			int M = Integer.parseInt(strtok.nextToken());//ã�� ���� ��ġ
			
			int doc[] = new int[N];//�߿䵵
			
			strtok = new StringTokenizer(b.readLine());

			for(int i=0;i<N;i++)
			{
				doc[i] = Integer.parseInt(strtok.nextToken());
			}
			
			int[] sorted = doc.clone();
			Arrays.sort(sorted);//�������� ����
			
			int count=N-1;//������������
			
			for(int i=0;i<N;i++)
			{
				if(sorted[count] == doc[i])
				{
					if(i==M)
					{
						System.out.println(N-count);
						break;
					}
					
					count--;
				}
				
				if(i==N-1)
					i=-1;
					
			}
			
		}
	}
	
	static void bj1094() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int current = 0;
		int sum=0;
		
		int x = Integer.parseInt(b.readLine());
		
		for(int i=64;i>=1;i/=2)
		{
			if(i <= x)
			{
				if(sum + i == x)
				{	
					current++;
					break;
				}
				else if(sum + i < x)
				{
					current++;
					sum+=i;
				}
			}
		}
		
		System.out.println(current);
	}
	
	static void bj2455() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int current = 0;
		int max = current;
		for(int i=0;i<4;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			
			int out = Integer.parseInt(strtok.nextToken());
			int in = Integer.parseInt(strtok.nextToken());
			current -= out;
			current += in;
			
			max = Math.max(max, current);
		}
		
		System.out.println(max);
	}
	
	public Simulation()
	{
		
	}
	
}
