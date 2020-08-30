import java.util.*;


class Solution {

	static boolean[] visited;

	static HashMap<Integer, Integer> order_map = new HashMap<>();
	static int visit_count = 0;

	static boolean result = true;

	static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
	static ArrayList<ArrayList<Integer>> tree_nodircet = new ArrayList<>();

	public static boolean solution(int n, int[][] path, int[][] order) {
		boolean answer = true;

		visited = new boolean[n];

		for(int i = 0;i < order.length;i++)
		{
			order_map.putIfAbsent(order[i][0], order[i][1]);
		}

		for(int i = 0;i < n;i++)
		{
			tree.add(new ArrayList<>());
			tree_nodircet.add(new ArrayList<>());
		}

		for(int i = 0;i < path.length;i++)
		{
			for(int j = 0;j < path[i].length;j++)
			{
				tree.get(i).add(j);
				tree.get(j).add(i);
			}
		}

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(0);
		visited[0] = true;

		while(!queue.isEmpty())
		{
			int now = queue.poll();

			for(int i = 0;i < tree.get(now).size();i++)
			{
				int next = tree.get(now).get(i);

				if(!visited[next])
				{
					visited[next] = true;
					queue.offer(next);
					tree_nodircet.get(now).add(next);
				}
			}
		}

		for(int i = 0;i < order.length;i++)
		{
			tree_nodircet.get(order[i][0]).add(order[i][1]);
		}

		visited = new boolean[n];

		for(int i = 0;i < n;i++)
		{
			boolean now_visited[] = new boolean[n];
			if(!visited[i])
			{
				dfs(i, now_visited);
			}
		}


		return result;
	}

	static void dfs(int now, boolean[] now_visited)
	{
		visited[now] = true;
		now_visited[now] = true;

		for(int i = 0;i < tree_nodircet.get(now).size();i++)
		{
			int next = tree_nodircet.get(now).get(i);

			if(now_visited[next])
			{
				result = false;
				return;
			}
			else
			{
				if(!visited[next])
					dfs(next, now_visited);
			}
		}
	}


}