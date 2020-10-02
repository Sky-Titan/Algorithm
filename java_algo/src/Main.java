
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
	static int dist[];
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();


	public static void solution() throws Exception
	{
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());


		for(int i = 0;i <= V;i++)
			graph.add(new ArrayList<>());

		for(int i = 0;i < E;i++)
		{
			String[] list = br.readLine().split(" ");
			int from = Integer.parseInt(list[0]);
			int to = Integer.parseInt(list[1]);
			int weight = Integer.parseInt(list[2]);

			graph.get(from).add(new Edge(from, to, weight));
			graph.get(to).add(new Edge(to, from, weight));
		}

		String[] list = br.readLine().split(" ");
		start = Integer.parseInt(list[0]);
		finish = Integer.parseInt(list[1]);

		dist = new int[V+1];
		for(int i = 0; i <= V;i++)
		{
			if(i == start)
				dist[i] = 0;
			else
				dist[i] = Integer.MAX_VALUE;
		}

		bw.write(dijkstra()+"");
	}

	static int dijkstra()
	{
		PriorityQueue<Integer> nodes = new PriorityQueue<>((o1, o2) -> dist[o1] - dist[o2]);
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);

		boolean visited[] = new boolean[V+1];
		visited[start] = true;

		while (!queue.isEmpty())
		{
			int now = queue.poll();
			//System.out.println(now);
			int min_index = 0;
			int min = Integer.MAX_VALUE;

			for(int i = 0;i < graph.get(now).size();i++)
			{
				Edge e = graph.get(now).get(i);

				if(dist[e.to] > dist[now] + e.weight)
					dist[e.to] = dist[now] + e.weight;

				if(min > dist[e.to] && !visited[e.to])
				{
					min = dist[e.to];
					min_index = e.to;
				}
			}

			if(min_index != 0)
				nodes.offer(min_index);

			if(!nodes.isEmpty())
			{
				visited[nodes.peek()] = true;
				queue.offer(nodes.poll());
			}
		}

		return dist[finish];
	}


	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public boolean equals(Object obj) {
			Edge other = (Edge) obj;

			if((from == other.from && to == other.to) || (from == other.to && to == other.from))
				return true;
			return false;
		}

		@Override
		public int compareTo(Edge o) {
			return weight - o.weight;
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