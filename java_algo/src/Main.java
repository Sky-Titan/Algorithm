import com.sun.javafx.image.BytePixelSetter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int V, E;
	static int K;
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
	static int dist[];
	static int INF = 3000001;

	public static void solution() throws Exception
	{
		StringTokenizer strtok = new StringTokenizer(br.readLine());
		V = Integer.parseInt(strtok.nextToken());
		E = Integer.parseInt(strtok.nextToken());
		K = Integer.parseInt(br.readLine());//시작정점 번호

		dist = new int[V+1];

		for(int i = 0;i <= V;i++)
		{
			graph.add(new ArrayList<>());
			if(i != K)
				dist[i] = INF;
		}

		for(int i = 0;i < E;i++)
		{
			strtok = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(strtok.nextToken());
			int to = Integer.parseInt(strtok.nextToken());
			int weight = Integer.parseInt(strtok.nextToken());

			graph.get(from).add(new Edge(from, to, weight));
		}

		dijkstra();

		for(int i = 1;i <= V;i++)
		{
			if(dist[i] == INF)
				bw.write("INF\n");
			else
				bw.write(dist[i]+"\n");
		}
	}

	static void dijkstra()
	{
		PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.distance - o2.distance);
		queue.offer(new Node(K, dist[K]));

		boolean visited[] = new boolean[V+1];

		while (!queue.isEmpty())
		{
			Node now = queue.poll();

			//최단거리 갱신되기전 것이기에 pass
			if(visited[now.num])
				continue;
			visited[now.num] = true;

			for(int i = 0;i < graph.get(now.num).size();i++)
			{
				Edge e = graph.get(now.num).get(i);

				//최단거리 갱신될 때마다 방문가능한 목록에 노드 추가
				if(dist[e.to] > dist[now.num] + e.weight)
				{
					dist[e.to] = dist[now.num] + e.weight;

					if(!visited[e.to])
						queue.offer(new Node(e.to, dist[e.to]));
				}
			}
		}
	}

	static class Node {
		int num, distance;

		public Node(int num, int distance) {
			this.num = num;
			this.distance = distance;
		}
	}

	static class Edge {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
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