import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int V, E;
	static int start, finish;

	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static boolean visited[];

	public static void solution() throws Exception
	{
		V = Integer.parseInt(br.readLine());

		StringTokenizer strtok = new StringTokenizer(br.readLine());
		start = Integer.parseInt(strtok.nextToken());
		finish = Integer.parseInt(strtok.nextToken());

		E = Integer.parseInt(br.readLine());

		visited = new boolean[V+1];

		for(int i = 0;i <= V;i++)
			graph.add(new ArrayList<>());

		for(int i = 0;i < E;i++)
		{
			strtok = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(strtok.nextToken());
			int to = Integer.parseInt(strtok.nextToken());

			graph.get(from).add(to);
			graph.get(to).add(from);
		}

		Queue<Position> queue = new LinkedList<>();
		queue.offer(new Position(start, 0));
		visited[start] = true;

		while(!queue.isEmpty())
		{
			Position now = queue.poll();

			if(now.node == finish)
			{
				bw.write(now.count+"");
				return;
			}

			for(int i = 0;i < graph.get(now.node).size();i++)
			{
				int next = graph.get(now.node).get(i);

				if(!visited[next])
				{
					visited[next] = true;
					queue.offer(new Position(next, now.count + 1));
				}
			}
		}
		bw.write("-1");
	}

	static class Position{
		int node;
		int count;

		public Position(int node, int count) {
			this.node = node;
			this.count = count;
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