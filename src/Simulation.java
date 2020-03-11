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
		int time=0;
		public Position()
		{
			
		}
		public Position(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
		
		
	}
	
	static void bj15954() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		int N = Integer.parseInt(strtok.nextToken());
		int K = Integer.parseInt(strtok.nextToken());
		
		int doll[] = new int[N];
		
		strtok = new StringTokenizer(b.readLine());
		
		for(int i=0;i<N;i++)
			doll[i] = Integer.parseInt(strtok.nextToken());
		
		double min = 0;
		
		for(int k=K;k<=N;k++)
		{
			for(int i=0;i<=N-k;i++)
			{
				double sum = 0;
				double m = 0;
				
				for(int j=i;j<i+k;j++)
				{
					sum += doll[j];
				}

				m = sum / k;
				
				sum = 0;
				
				for(int j=i;j<i+k;j++)
				{
					sum += Math.pow (doll[j]-m,2);	
				}
				
				if(i==0 && k==K)
					min = Math.sqrt(sum / k);
				else
				{
					if(min >  Math.sqrt(sum / k))
						min =  Math.sqrt(sum / k);
				}
				
			}
		}
		
		System.out.println(min);
	}
	
	static void bj15953() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		for(int i=0;i<=N;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			int num1 = Integer.parseInt(strtok.nextToken());
			int num2 = Integer.parseInt(strtok.nextToken());
			
			int result = 0;
			if(num1 == 1)
				result += 5000000;
			else if(2 <= num1 && num1 <= 3)//2~3
				result += 3000000;
			else if(4 <= num1 && num1 <= 6)//4~6
				result += 2000000;
			else if(7 <= num1 && num1 <= 10)//7~10
				result += 500000;
			else if(11 <= num1 && num1 <= 15)
				result += 300000;
			else if(16 <= num1 && num1 <= 21)
				result += 100000;
			
			
			if(num2 == 1)
				result += 5120000;
			else if(2 <= num2 && num2 <= 3)
				result += 2560000;
			else if(4 <= num2 && num2 <= 7)
				result += 1280000;
			else if(8 <= num2 && num2 <= 15)
				result += 640000;
			else if(16 <= num2 && num2 <= 31)
				result += 320000;
		
			System.out.println(result);
		}
	}
	
	//��ǰ
	static void bj15997() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		ArrayList<String> country = new ArrayList<>();
		double[] p = {0,0,0,0};
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		for(int i=0;i<4;i++)
		{
			country.add(strtok.nextToken());
		}
		
		for(int i=0;i<6;i++)
		{
			strtok = new StringTokenizer(b.readLine());
			String A = strtok.nextToken();
			String B = strtok.nextToken();
			double W = Double.parseDouble(strtok.nextToken());//A�¸�Ȯ��, B�й�Ȯ��
			double D = Double.parseDouble(strtok.nextToken());//���º� Ȯ��
			double L = Double.parseDouble(strtok.nextToken());//B�¸�Ȯ��, A�й�Ȯ��
			
			int index_A = country.indexOf(A);
			int index_B = country.indexOf(B);
			p[index_A] += W * 3;
			p[index_B] += L * 3;
			p[index_A] += D * 1;
			p[index_B] += D * 1;
		}
		
		double result[] = new double[4];
		
		for(int i=0;i<4;i++)
		{
			
			int count=0;
			result[i] = ( p[i] * (100.0 / 9.0) ) / 100.0;
			for(int j=0;j<4;j++)
			{
				if(p[j] == p[i])
					count++;
			}
		
			result[i] /= count;
			System.out.println(result[i]);
		}
	}
	
	static void bj1547() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
		
		ArrayList<Integer> cup = new ArrayList<>();
		cup.add(1);
		cup.add(2);
		cup.add(3);
		
		for(int i=0;i<N;i++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			
			int num1 = Integer.parseInt(strtok.nextToken());
			int num2 = Integer.parseInt(strtok.nextToken());
			
			int index1 = cup.indexOf(num1);
			int index2 = cup.indexOf(num2);
			cup.remove(index1);
			cup.add(index1,num2);
			cup.remove(index2);
			cup.add(index2,num1);
		}
		System.out.println(cup.get(0));
	}
	
	static void bj14890() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		int N = Integer.parseInt(strtok.nextToken());
		int L = Integer.parseInt(strtok.nextToken());
		
		int map[][] = new int[N][N];
		
		for(int i=0;i<N;i++)
		{
			strtok = new StringTokenizer(b.readLine());
			for(int j=0;j<N;j++)
			{
				map[i][j] = Integer.parseInt(strtok.nextToken());
			}
		}
		
		boolean slide[] = new boolean[N];
		
		for(int i=0;i<N;i++)
			slide[i] = false;//�����̵� �ȳ����մ»���
		
		int road_count=0;//�������� �ִ� �� ����
		
		for(int i=0;i<N;i++)//�� �˻�
		{
			int former = map[i][0];//�� ����
			
			boolean canWalk = true;//�������� �մ��� ����
			
			for(int j=1;j<N;j++)
			{
				if(map[i][j] != former)//���� �Ͱ� ���� �ٸ��ٸ� ���� ������ �ִ��� ���� �˻�
				{
					if(map[i][j] == former + 1)//���� ���� ���̰� ���ٸ� ���� L��ŭ �˻�
					{
						int count=0;
						
						for(int k=j-1;k >= 0;k--)
						{
							if(former == map[i][k] && !slide[k])
								count++;
							else
								break;
						}
						
						if(count < L)//���� ������
						{	
							canWalk = false;
							break;
						}
						else//���� ����
						{
							for(int k=j-1;k >= j-L;k--)
								slide[k] = true;
						}
					}
					else if(map[i][j] + 1 == former)//���� ���� ���� ��� �� �� L��ŭ �˻�
					{
						int count=0;
						
						for(int k=j;k < N;k++)//�������� �˻�
						{
							if(map[i][j] == map[i][k] && !slide[k])
								count++;
							else
								break;
						}
						
						if(count < L)//���� ������
						{	
							canWalk = false;
							break;
						}
						else//���� ����
						{
							for(int k=j;k < j+L;k++)
								slide[k] = true;
						}
					
					}
					else//1���� �������̰� ���̳��� ��� �Ұ�
					{
						canWalk = false;
						break;
					}
				}
				
				former = map[i][j];
			}
			
			for(int k=0;k<N;k++)
				slide[k] = false;
			
			if(canWalk)
				road_count++;
		
		}
		
		for(int j=0;j<N;j++)//�� �˻�
		{
			int former = map[0][j];//�� ����
			
			boolean canWalk = true;//�������� �մ��� ����
			
			for(int i=1;i<N;i++)
			{
				if(map[i][j] != former)//���� �Ͱ� ���� �ٸ��ٸ� ���� ������ �ִ��� ���� �˻�
				{
					if(map[i][j] == former + 1)//���� ���� ���̰� ���ٸ� ���� L��ŭ �˻�
					{
						int count=0;
						
						for(int k=i-1;k >= 0;k--)
						{
							if(former == map[k][j] && !slide[k])
								count++;
							else
								break;
						}
						
						if(count < L)//���� ������
						{	
							canWalk = false;
							break;
						}
						else
						{
							for(int k=i-1;k >= i-L;k--)
							{
								slide[k] = true;
							}
						}
					}
					else if(map[i][j] + 1 == former)//���� ���� ���� ��� �� �� L��ŭ �˻�
					{
						int count=0;
						
						for(int k=i;k < N;k++)//�������� �˻�
						{
							if(map[i][j] == map[k][j] && !slide[k])
								count++;
							else
								break;
						}
						
						if(count < L)//���� ������
						{	
							canWalk = false;
							break;
						}
						else
						{
							for(int k=i;k < i+L;k++)//�������� �˻�
							{
								slide[k] = true;
							}
						}
					
					}
					else//1���� �������̰� ���̳��� ��� �Ұ�
					{
						canWalk = false;
						break;
					}
				}
				
				former = map[i][j];
			}
			
			for(int k=0;k<N;k++)
				slide[k] = false;
			
			if(canWalk)
				road_count++;
		}
		
		System.out.println(road_count);
	}
	
	static void bj3055() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		int R = Integer.parseInt(strtok.nextToken());
		int C = Integer.parseInt(strtok.nextToken());
		
		char map[][] = new char[R][C];
		int water_map[][] = new int[R][C];
		boolean visited[][] = new boolean[R][C];
		boolean visited_w[][] = new boolean[R][C];
		
		
		Queue<Position> q = new LinkedList<>();
		Queue<Position> q_water = new LinkedList<>();
		
		for(int i=0;i<R;i++)
		{
			String str = b.readLine();
			for(int j=0;j<C;j++)
			{
				map[i][j] = str.charAt(j);
				water_map[i][j] = R*C+1;;
				
				visited[i][j] = false;
				visited_w[i][j] = false;
				
				if(map[i][j] == 'S')
				{
					q.offer(new Position(i, j));
					visited[i][j] = true;
				}
				else if(map[i][j] == '*')
				{
					q_water.offer(new Position(i,j));
					visited_w[i][j] = true;
				}
				
			}
		}
		
		
		
		while(!q_water.isEmpty())
		{
			Position pos = q_water.poll();
			int x = pos.x;
			int y = pos.y;
			int time = pos.time;
			water_map[x][y] = time;
			
			if(x != 0 && (map[x-1][y] == '.' || map[x-1][y] == 'S') && !visited_w[x-1][y])
			{
				visited_w[x-1][y] = true;
				Position p = new Position(x-1, y);
				p.time = time+1; 
				q_water.offer(p);
			}
			if(x != R-1 && (map[x+1][y] == '.' || map[x+1][y] == 'S') && !visited_w[x+1][y])
			{
				visited_w[x+1][y] = true;
				Position p = new Position(x+1, y);
				p.time = time+1; 
				q_water.offer(p);
			}
			if(y != 0 && (map[x][y-1] == '.' || map[x][y-1] == 'S') && !visited_w[x][y-1])
			{
				visited_w[x][y-1] = true;
				Position p = new Position(x, y-1);
				p.time = time+1; 
				q_water.offer(p);
			}
			if(y != C-1 && (map[x][y+1] == '.' || map[x][y+1] == 'S') && !visited_w[x][y+1])
			{
				visited_w[x][y+1] = true;
				Position p = new Position(x, y+1);
				p.time = time+1; 
				q_water.offer(p);
			}
			
		}
		

		
		while(!q.isEmpty())
		{
			Position pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			int time = pos.time;

			if(map[x][y] == 'D')
			{
				System.out.println(time);
				return;
			}
			
			if(x != 0 && (map[x-1][y] == '.' || map[x-1][y] == 'D') && time + 1 < water_map[x-1][y] && !visited[x-1][y])
			{
				visited[x-1][y] = true;
				Position p = new Position(x-1, y);
				p.time = time+1; 
				q.offer(p);
			}
			if(x != R-1 && (map[x+1][y] == '.' || map[x+1][y] == 'D') && time + 1 < water_map[x+1][y] && !visited[x+1][y])
			{
				visited[x+1][y] = true;
				Position p = new Position(x+1, y);
				p.time = time+1; 
				q.offer(p);
			}
			if(y != 0 && (map[x][y-1] == '.' || map[x][y-1] == 'D') && time + 1 < water_map[x][y-1] && !visited[x][y-1])
			{
				visited[x][y-1] = true;
				Position p = new Position(x, y-1);
				p.time = time+1; 
				q.offer(p);
			}
			if(y != C-1 && (map[x][y+1] == '.' || map[x][y+1] == 'D') && time + 1 < water_map[x][y+1] && !visited[x][y+1])
			{
				visited[x][y+1] = true;
				Position p = new Position(x, y+1);
				p.time = time+1; 
				q.offer(p);
			}
		}
		
		System.out.println("KAKTUS");
		
		
	}
	
	static void bj2164() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int N = Integer.parseInt(b.readLine());
	
		int result=1;
		
		if(N==1)
		{
			System.out.println(result);
			return;
		}
		result=0;
		
		for(int i=2;i<=N;i++)
		{
			result+=2;
			if(i<result)
			{
				result=2;
			}
		}
		System.out.println(result);
		
	}
	
	static void bj14891() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		ArrayList<Integer> gear[] = new ArrayList[5];//��Ϲ�����
		
		for(int i=1;i<=4;i++)
		{
			String str= b.readLine();
			gear[i] = new ArrayList<>();
			for(int j=0;j<8;j++)
				gear[i].add(str.charAt(j) - 48);
		}
		
		int K = Integer.parseInt(b.readLine());
		
		//ȸ��Ƚ��
		for(int k=0;k<K;k++)
		{
			StringTokenizer strtok = new StringTokenizer(b.readLine());
			int num = Integer.parseInt(strtok.nextToken());//ȸ���ϴ� ���
			int direction[] = new int[5];
			
			direction[num] = Integer.parseInt(strtok.nextToken());
			
			for(int i=num-1; i>=1;i--)
			{
				if(gear[i+1].get(6) == gear[i].get(2))//������ ȸ�� x
				{
					direction[i] = 0;
				}
				else//�ٸ��� ���� �ٸ� ���� 
				{
					if(direction[i+1] == 1)
						direction[i] = -1;
					else if(direction[i+1] == -1)
						direction[i] = 1;
					else
						direction[i] = 0;
				}
			}
			
			for(int i = num+1; i <= 4; i++)
			{
				if(gear[i-1].get(2) == gear[i].get(6))//������ ȸ�� x
				{
					direction[i] = 0;
				}
				else//�ٸ��� ���� �ٸ� ���� 
				{
					if(direction[i-1] == 1)
						direction[i] = -1;
					else if(direction[i-1] == -1)
						direction[i] = 1;
					else
						direction[i] = 0;
					
				}
			}

			for(int i=1;i<=4;i++)
				rotate(gear[i], direction[i]);//���� ȸ��
		}
		
		
		
		int sum = 0;//���� ��
		
		int start = 1;//��������
		for(int i=1;i<=4;i++)
		{
			if(gear[i].get(0) == 1)//s���̸� ���� �߰�
				sum+=start;
			start *= 2;
		}
		System.out.println(sum);
	}
	
	static void rotate(ArrayList<Integer> gear, int direction)
	{
		if(direction == 1)//�ð����
		{
			int last = gear.get(7);
			gear.remove(7);
			gear.add(0,last);
		}
		else if(direction == -1)//�ݽð�
		{
			int first = gear.get(0);
			gear.remove(0);
			gear.add(first);
		}
		else
		{
			
		}
	}
	
	static void bj1057() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		int N = Integer.parseInt(strtok.nextToken());
		int num1 = Integer.parseInt(strtok.nextToken());
		int num2 = Integer.parseInt(strtok.nextToken());
		
		int count = 1;//����
		
		while( (int)Math.ceil((double)num1/2) != (int)Math.ceil((double)num2/2) )
		{
			count++;
			num1 = (int)Math.ceil((double)num1/2);
			num2 = (int)Math.ceil((double)num2/2);
			
		}
		System.out.println(count);
		
	}
	
	static void bj1021() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		ArrayList<Integer> q = new ArrayList<Integer>();
		
		for(int i=1;i<=N;i++)
			q.add(i);
		
		strtok = new StringTokenizer(b.readLine());
		
		int result = 0;//�ʿ��� ����Ƚ��
		
		for(int i=0;i<M;i++)
		{
			int target = Integer.parseInt(strtok.nextToken());
			
			if(q.size() - q.indexOf(target) > q.indexOf(target) - 0)//����
			{
				result += moveList(target, q, 'L');
			}
			else
			{
				result += moveList(target, q, 'R');
			}
		}
		System.out.println(result);
	
		
	}
	
	static int moveList(int finish, ArrayList<Integer> q, char mode)
	{
		int count = 0;
		
		if(mode == 'L')//��������
		{
			int first = q.get(0);
			
			while(first!=finish)
			{
				q.remove(0);
				q.add(first);
				count++;
				first = q.get(0);
			}
		}
		else//����������
		{
			int first = q.get(0);
			
			while(first!=finish)
			{
				int last = q.get(q.size()-1);
				q.remove(q.size()-1);
				q.add(0, last);
				first = last;
				count++;
			}
			
		}
		q.remove(0);
		return count;
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
