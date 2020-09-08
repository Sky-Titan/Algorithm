import java.util.Arrays;
import java.util.Comparator;

class Solution {

	int binaryTree[][] = new int[500001][2];//[][0] : x값, [][1] : key값
	int count = 0;
	int index = 0;

	public int[][] solution(int[][] nodeinfo) {
		int[][] answer = new int[2][nodeinfo.length];

		int nodes[][] = new int[nodeinfo.length][3];

		for(int i = 0;i < nodeinfo.length;i++)
		{
			nodes[i][0] = nodeinfo[i][0];
			nodes[i][1] = nodeinfo[i][1];
			nodes[i][2] = i+1;
		}

		Arrays.sort(nodes, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o2[1] > o1[1])
					return 1;
				else
					return -1;
			}
		});


		for(int i = 0;i < nodes.length;i++)
		{
			int x = nodes[i][0];
			int y = nodes[i][1];

			insert(x, nodes[i][2]);
		}

		preTraversal(1, answer);
		index = 0;
		postTraversal(1, answer);
		return answer;
	}

	public void preTraversal(int current, int[][] answer)
	{
		if(current < binaryTree.length && binaryTree[current][1] != 0)
		{
			if(index < answer[0].length)
				answer[0][index++] = binaryTree[current][1];
			preTraversal(current * 2, answer);
			preTraversal(current * 2 + 1, answer);
		}
	}

	public void postTraversal(int current, int[][] answer)
	{
		if(current < binaryTree.length && binaryTree[current][1] != 0)
		{
			postTraversal(current * 2, answer);
			postTraversal(current * 2 + 1, answer);
			if(index < answer[1].length)
				answer[1][index++] = binaryTree[current][1];
		}
	}

	public void insert(int x, int key)
	{
		if(count == 0)
		{
			binaryTree[1][0] = x;
			binaryTree[1][1] = key;
		}
		else
		{
			int current = 1;

			while(current < binaryTree.length)
			{
				//왼쪽
				if(binaryTree[current][0] > x)
					current = current * 2;
				else
					current = current * 2 + 1;

				if(current < binaryTree.length && binaryTree[current][1] == 0)
				{
					binaryTree[current][0] = x;
					binaryTree[current][1] = key;
					break;
				}
			}
		}

		count++;
	}
}