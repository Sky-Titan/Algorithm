import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void solution() throws Exception
	{
		int T = Integer.parseInt(br.readLine());

		for(int t = 0;t < T;t++)
		{
			StringTokenizer strtok = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(strtok.nextToken());//정점 수
			int K = Integer.parseInt(strtok.nextToken());//간선 수
			int constrcut_time[] = new int[N+1];
			int inEdge[] = new int[N+1];

			ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
			strtok = new StringTokenizer(br.readLine());

			for(int i = 0;i <= N;i++)
			{
				graph.add(new ArrayList<>());
				if(i > 0)
					constrcut_time[i] = Integer.parseInt(strtok.nextToken());
			}

			for(int i = 0;i < K;i++)
			{
				strtok = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(strtok.nextToken());
				int to = Integer.parseInt(strtok.nextToken());

				graph.get(from).add(to);
				inEdge[to] ++;
			}
			int W = Integer.parseInt(br.readLine());
			bw.write(topologicalSort(N, W, constrcut_time, graph, inEdge)+"\n");
		}


	}

	static int topologicalSort(int N, int W, int[] complete_time, ArrayList<ArrayList<Integer>> graph, int inEdge[])
	{
		//완료시간이 늦는 건물이 가장 마지막에 나오도록
		PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> complete_time[o1] - complete_time[o2]);

		for(int i = 1;i <= N;i++)
		{
			if(inEdge[i] == 0)
				queue.offer(i);
		}

		while (!queue.isEmpty())
		{
			int now = queue.poll();

			if(now == W)
				break;

			for(int i = 0;i < graph.get(now).size();i++)
			{
				int next = graph.get(now).get(i);

				inEdge[next]--;

				if(inEdge[next] == 0)
				{
					//next를 짓는데 필요한 건물 중 가장 늦게 건설된 건물의 건설 완료시간을 더한다.
					complete_time[next] += complete_time[now];
					queue.offer(next);
				}
			}
		}
		return complete_time[W];
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