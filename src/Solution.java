import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

class Solution {

	HashMap<String, Integer> A = new HashMap();
	HashMap<String, Integer> B = new HashMap();
	int intersection_size = 0;
	int union_size = 0;

	public int solution(String str1, String str2) {
		int answer = 0;

		str1 = str1.toUpperCase();
		str2 = str2.toUpperCase();

		for(int i = 0;i < str1.length() - 1;i++)
		{
			if(Character.isAlphabetic(str1.charAt(i)) && Character.isAlphabetic(str1.charAt(i + 1)))
			{
				String s = str1.charAt(i) +"" + str1.charAt(i + 1)+"";

				A.putIfAbsent(s, 0);
				A.computeIfPresent(s, (s1, integer) -> integer+1);
			}
		}
		for(int i = 0;i < str2.length() - 1;i++)
		{
			if(Character.isAlphabetic(str2.charAt(i)) && Character.isAlphabetic(str2.charAt(i + 1)))
			{
				String s = str2.charAt(i) +"" + str2.charAt(i + 1)+"";

				B.putIfAbsent(s, 0);
				B.computeIfPresent(s, (s1, integer) -> integer+1);
			}

		}

		if(A.size() == 0 && B.size() == 0)
			return 65536;

		Set<String> A_keySet = A.keySet();
		Iterator<String> iterator = A_keySet.iterator();

		while(iterator.hasNext())
		{
			String s = iterator.next();

			if(B.containsKey(s))
			{
				if(A.get(s) < B.get(s))
				{
					intersection_size += A.get(s);
					union_size += B.get(s);
				}
				else
				{
					intersection_size += B.get(s);
					union_size += A.get(s);
				}
			}
			else
				union_size += A.get(s);
		}

		Set<String> B_keySet = B.keySet();
		iterator = B_keySet.iterator();

		while(iterator.hasNext())
		{
			String s = iterator.next();

			if(!A.containsKey(s))
				union_size += B.get(s);
		}

		answer = (int) (((double)intersection_size / (double) union_size) * 65536);
		return answer;
	}
}