
import java.util.*;

public class Main {

	public static int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];

		Trie right_trie = new Trie();
		Trie reverse_trie = new Trie();


		for(int i = 0;i< words.length;i++)
		{
			String word = words[i];

			insertTrie(0, word, right_trie);
			insertTrie(0, reverseString(word), reverse_trie);
		}

		HashSet<String> set = new HashSet<>();
		set.addAll(Arrays.asList(queries));

		HashMap<String, Integer> answer_map = new HashMap<>();

		Iterator<String> iterator = set.iterator();

		for(int i =0;i<set.size();i++)
		{
			String query = iterator.next();

			int begin = query.indexOf('?');
			int end = query.lastIndexOf('?')+1;

			int[] size = {0};
			//접두사
			if(begin == 0)
			{
				// ????? 인 경우
				getSize(0, reverseString(query), reverse_trie, size);
			}
			else
			{
				//sub = query.substring(0, begin)
				getSize(0, query, right_trie, size);
			}
			answer_map.put(query, size[0]);
		}

		for(int i =0;i< answer.length;i++)
			answer[i] = answer_map.get(queries[i]);

		return answer;
	}

	public static void getSize(int index, String str, Trie trie, int[] size)
	{
		if(index < str.length())
		{
			char c = str.charAt(index);
			//전체 탐색
			if(c == '?')
			{
				Iterator<Character> iterator = trie.map.keySet().iterator();

				while(iterator.hasNext())
				{
					char next = iterator.next();
					getSize(index+1, str, trie.map.get(next), size);
				}
			}
			else
			{
				if(trie.map.containsKey(c))
					getSize(index+1, str, trie.map.get(c), size);
			}
		}
		else//한계
		{
			//존재
			if(trie.map.containsKey('?'))
				size[0] ++;
		}
	}

	public static String reverseString(String str)
	{
		String result = "";

		for(int i = str.length()-1;i>=0;i--)
			result += str.charAt(i);
		return result;
	}

	public static void insertTrie(int index, String str, Trie trie)
	{
		char c = str.charAt(index);

		trie.map.putIfAbsent(c,new Trie(c));

		if(index != str.length() - 1)
		{
			insertTrie(index+1, str, trie.map.get(c));
		}
		else
		{
			trie.map.get(c).map.putIfAbsent('?',new Trie('?'));
		}
	}

	static class Trie{
		char node;

		HashMap<Character, Trie> map = new HashMap<>();

		public Trie()
		{

		}
		public Trie(char node)
		{
			this.node = node;
		}
	}


	public static void main(String[] args) {

		String[] words={"frodo", "front", "frost", "frozen", "frame", "kakao"};
		String[] queries={"fro??", "????o", "fr???", "fro???", "pro?"};

		int[] result = solution(words, queries);

		for(int i =0;i< result.length;i++)
			System.out.println(result[i]);

	}


	
}