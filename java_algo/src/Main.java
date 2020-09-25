
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


	public static void solution() throws Exception
	{
		int N = Integer.parseInt(br.readLine());

		Tree tree = new Tree();

		for(int i = 0;i < N;i++)
		{
			String[] list = br.readLine().split(" ");

			tree.insert(tree.root, list[0].charAt(0), list[1].charAt(0), list[2].charAt(0));
		}

		tree.preorder(tree.root);
		System.out.println();
		tree.inorder(tree.root);
		System.out.println();
		tree.postorder(tree.root);

	}



	static class Tree{

		Node root = new Node('A');

		public Tree()
		{

		}

		public void insert(Node node, char root_key, char left, char right)
		{
			if(node.key == root_key)
			{
				if(left != '.')
					node.left = new Node(left);
				if(right != '.')
					node.right = new Node(right);
			}
			else
			{
				if(node.left != null)
					insert(node.left, root_key, left, right);
				if(node.right != null)
					insert(node.right, root_key, left, right);
			}

		}

		public void preorder(Node node)
		{
			if(node != null)
			{
				System.out.print(node.key);
				preorder(node.left);
				preorder(node.right);
			}
		}
		public void inorder(Node node)
		{
			if(node != null)
			{
				inorder(node.left);
				System.out.print(node.key);
				inorder(node.right);
			}
		}

		public void postorder(Node node)
		{
			if(node != null)
			{
				postorder(node.left);
				postorder(node.right);
				System.out.print(node.key);
			}
		}
	}

	static class Node{
		char key;
		Node left;
		Node right;

		public Node(char key)
		{
			this.key = key;
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