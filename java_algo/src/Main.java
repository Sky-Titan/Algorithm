import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static HashSet<Position> visited = new HashSet<>();
	static int map[][] = new int[3][3];

	public static void solution() throws Exception
	{
		int init[] = new int[9];

		int end[] = new int[9];
		end[0] = 8;

		for(int i = 1;i < 9;i++)
			end[i] = i - 1;

		//되야할 경우
		Position finish = new Position(end, 0);

		for(int i = 0;i < 3;i++)
		{
			StringTokenizer strtok = new StringTokenizer(br.readLine());
			for(int j = 0;j < 3;j++)
			{
				int num = Integer.parseInt(strtok.nextToken());
				int pos = i * 3 + j;
				init[num] = pos;
			}
		}

		Queue<Position> queue = new LinkedList<>();
		queue.offer(new Position(init, 0));
		visited.add(queue.peek());

		int x[] = {-1, 1, 0, 0};//상하좌우
		int y[] = {0, 0, -1, 1};

		while(!queue.isEmpty())
		{
			Position now = queue.poll();
			int current = now.pos[0];

			//종료
			if(now.hashCode() == finish.hashCode())
			{
				bw.write(now.count+"");
				return;
			}

			int r = current / 3;
			int c = current % 3;

			for(int i = 0;i < 4;i++)
			{
				int next_r = r + x[i];
				int next_c = c + y[i];

				if(0 <= next_r && next_r < 3 && 0 <= next_c && next_c < 3)
				{
					int next = next_r * 3 + next_c;
					swap(now.pos, next);

					//System.out.println(visited.contains(now));
					if(!visited.contains(now))
					{
						Position next_pos = new Position(now.pos.clone(), now.count + 1);
						visited.add(next_pos);
						queue.offer(next_pos);
					}

					swap(now.pos, current);
				}
			}
		}
		bw.write("-1");
	}

	static void swap(int[] pos, int next)
	{
		for(int i = 0;i < 9;i++)
		{
			if(pos[i] == next)
			{
				pos[i] = pos[0];
				pos[0] = next;
				break;
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