import java.util.ArrayList;
import java.util.HashMap;

class Solution {

	HashMap<String, Integer> dictionary = new HashMap<>();


	public int[] solution(String msg) {
		int[] answer = {};

		ArrayList<Integer> result = new ArrayList<>();

		for(char i = 'A';i <= 'Z';i++)
			dictionary.putIfAbsent(i+"", i - 'A' + 1);

		String now = "";
		int index = 0;

		for(int i = 0;i < msg.length();i++)
		{
			char c = msg.charAt(i);

			now += c;

			//사전에 있으면 색인 출력
			if(dictionary.containsKey(now))
				index = dictionary.get(now);
			//사전에 없으면 색인 등록 후 한칸 뒤로
			else
			{
				result.add(index);

				dictionary.putIfAbsent(now, dictionary.size() + 1);
				now = "";
				i--;
			}
		}

		result.add(index);

		answer = new int[result.size()];
		for(int i = 0;i < result.size();i++)
			answer[i] = result.get(i);

		return answer;
	}
}