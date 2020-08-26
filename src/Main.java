import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	static void solution() throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for(int t = 0;t < T;t++)
		{
			int N = Integer.parseInt(br.readLine());

			StringBuilder list[] = new StringBuilder[N];
			Trie trie = new Trie();

			for(int i = 0;i < N;i++)
			{
				list[i] = new StringBuilder(br.readLine());
				trie.insertString(list[i]);
			}

			for(int i = 0;i < N;i++)
			{
				if(!trie.isConsistent(list[i]))
				{
					bw.write("NO\n");
					break;
				}

				if(i == N-1)
					bw.write("YES\n");
			}
		}
		bw.close();
	}

	public static void main(String[] args) {

		try
		{
			solution();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	static class Trie{

		Node root = new Node();

		public Trie()
		{

		}

		public void insertString(StringBuilder str)
		{
			Node node = root;

			for(int i = 0;i < str.length();i++)
			{
				char c = str.charAt(i);

				node.children.putIfAbsent(c, new Node());
				node = node.children.get(c);

				if(i == str.length()-1)
					node.isFinish = true;
			}
		}

		public boolean isConsistent(StringBuilder str)
		{
			Node node = root;

			for(int i = 0;i < str.length();i++)
			{
				char c = str.charAt(i);

				node = node.children.get(c);

				//아직 끝에 도달하지 않았는데 문자열이 존재한다면(접두어) 일관성 파괴
				if(i < str.length()-1)
				{
					if(node.isFinish)
						return false;
				}
			}

			return true;
		}
	}

	static class Node{

		HashMap<Character, Node> children = new HashMap<>();
		boolean isFinish = false;//현재까지의 문자열이 존재하는 지 판별

		public Node()
		{

		}
	}

}