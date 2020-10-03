import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static HashSet<Position> visited = new HashSet<>();
	static int end[] = {8, 0, 1, 2, 3, 4, 5, 6, 7};

	//최종 목적지
	static Position finish = new Position(end, 0);

	public static void solution() throws Exception
	{
		//초기 상태 (input)
		int init[] = new int[9];

		for(int i = 0;i < 3;i++)
		{
			StringTokenizer strtok = new StringTokenizer(br.readLine());
			for(int j = 0;j < 3;j++)
			{
				int num = Integer.parseInt(strtok.nextToken());
				int pos = toOneDimension(i, j);
				init[num] = pos;
			}
		}

		bw.write(bfs(init)+"");

	}

	static int bfs(int[] init)
	{
		Queue<Position> queue = new LinkedList<>();
		queue.offer(new Position(init, 0));
		visited.add(queue.peek());

		int x[] = {-1, 1, 0, 0};//상하좌우
		int y[] = {0, 0, -1, 1};

		while(!queue.isEmpty())
		{
			Position now = queue.poll();
			int current_index = now.pos[0];

			//종료
			if(now.equals(finish))
				return now.count;

			int r = getRow(current_index);
			int c = getColumn(current_index);

			//상하좌우
			for(int i = 0;i < 4;i++)
			{
				int next_r = r + x[i];
				int next_c = c + y[i];

				if(0 <= next_r && next_r < 3 && 0 <= next_c && next_c < 3)
				{
					int next_index = toOneDimension(next_r, next_c);

					swap(now.pos, next_index);

					if(!visited.contains(now))
					{
						Position next_pos = new Position(now.pos.clone(), now.count + 1);
						visited.add(next_pos);
						queue.offer(next_pos);
					}

					swap(now.pos, current_index);
				}
			}
		}
		return -1;
	}

	//2차원 배열 -> 1차원 배열 index로
	static int toOneDimension(int x, int y)
	{
		return x * 3 + y;
	}

	static int getRow(int index)
	{
		return index / 3;
	}

	static int getColumn(int index)
	{
		return index % 3;
	}


	static void swap(int[] pos, int next_index)
	{
		//next 위치에 해당하는 번호와 0의 위치를 swap
		for(int i = 0;i < 9;i++)
		{
			if(pos[i] == next_index)
			{
				pos[i] = pos[0];
				pos[0] = next_index;
				return;
			}
		}
	}

	static class Position{
		int[] pos;//i의 현재 위치 비트마스크들
		int count = 0;

		public Position(int[] pos, int count) {
			this.pos = pos;
			this.count = count;
		}

		@Override
		public boolean equals(Object obj) {
			return Arrays.equals(pos, ((Position)obj).pos);
		}

		@Override
		public int hashCode() {
			return Arrays.hashCode(pos);
		}
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