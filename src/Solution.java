

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

class Solution {

	int index = 0;

	Tree tree = new Tree();

	public int[][] solution(int[][] nodeinfo) {
		int[][] answer = new int[3][nodeinfo.length];

		int nodes[][] = new int[nodeinfo.length][3];

		for(int i = 0;i < nodeinfo.length;i++)
		{
			nodes[i][0] = nodeinfo[i][0];
			nodes[i][1] = nodeinfo[i][1];
			nodes[i][2] = i+1;
		}

		Arrays.sort(nodes, (o1, o2) -> o2[1] - o1[1]);

		for(int i = 0;i < nodes.length;i++)
		{
			int x = nodes[i][0];
			int y = nodes[i][1];
			int key = nodes[i][2];

			tree.insert(x, key);
		}

		preTraversal(tree.root, answer);
		index = 0;
		inTraversal(tree.root, answer);
		index = 0;
		postTraversal(tree.root, answer);
		return answer;
	}

	public void preTraversal(Node current, int[][] answer)
	{
		if(current != null)
		{
			if(index < answer[0].length)
			answer[0][index++] = current.key;
			preTraversal(current.left, answer);
			preTraversal(current.right, answer);
		}
		/*
		Stack<Node> stack = new Stack<>();
		stack.push(current);

		while(!stack.isEmpty())
		{
			Node node = stack.pop();

			if(index < answer[0].length)
				answer[0][index++] = node.key;
			if(node.right != null)
				stack.push(node.right);
			if(node.left != null)
				stack.push(node.left);
		}*/
	}

	public void inTraversal(Node current, int[][] answer)
	{
		Stack<Node> stack = new Stack<>();
		Stack<Node> stack_post = new Stack<>();


		stack.push(current.right);
		stack.push(current);
		stack.push(current.left);

		while(!stack.isEmpty())
		{
			Node node = stack.pop();
			while (node.left != null)
			{
				stack.push(node.right);
				stack.push(node);
				node = node.left;
			}
			System.out.print(node.key);
		}

		while(!stack_post.isEmpty())
		{
			int key = stack_post.pop().key;
			if(index < answer[2].length)
				answer[2][index++] = key;
		}
	}

	public void postTraversal(Node current, int[][] answer)
	{
		if(current != null)
		{
			postTraversal(current.left, answer);
			postTraversal(current.right, answer);
			if(index < answer[1].length)
				answer[1][index++] = current.key;
		}
		/*
		Stack<Node> stack = new Stack<>();
		Stack<Node> stack_post = new Stack<>();


		stack.push(current);

		while(!stack.isEmpty())
		{
			Node node = stack.pop();

			stack_post.push(node);

			if(node.left != null)
				stack.push(node.left);
			if(node.right != null)
				stack.push(node.right);
		}

		while(!stack_post.isEmpty())
		{
			int key = stack_post.pop().key;
			if(index < answer[1].length)
				answer[1][index++] = key;
		}

		 */
	}



	static class Tree{

		Node root;

		public Tree()
		{
		}

		public void insert(int x, int key)
		{
			if(root == null)
			{
				root = new Node(x, key);
			}
			else
			{
				Node node = root;

				while(node != null)
				{
					if(node.x > x)
					{
						if(node.left != null)
							node = node.left;
						else
						{
							node.left = new Node(x, key);
							return;
						}
					}
					else
					{
						if(node.right != null)
							node = node.right;
						else
						{
							node.right = new Node(x, key);
							return;
						}
					}
				}
			}
		}
	}

	static class Node{
		int key;
		int x;
		Node left;
		Node right;

		public Node()
		{

		}

		public Node(int key)
		{
			this.key = key;
		}

		public Node(int x, int key) {
			this.key = key;
			this.x = x;
		}
	}
}