import java.util.*;


class Solution {

	public static int[] solution(String[] gems) {
		int[] answer = new int[2];

		HashMap<String, Integer> count = new HashMap<>();

		for(String a : gems)
		{
			count.putIfAbsent(a, 0);
		}

		int total_count = count.size();

		int start = 0, end = 0;

		int min_start = Integer.MAX_VALUE, min_end = Integer.MAX_VALUE;
		int min_length = Integer.MAX_VALUE;

		int gem_count = 0;

		while(end < gems.length || start < gems.length)
		{
			//start 증가
			if(gem_count >= total_count && start <= end)
			{
				count.replace(gems[start], count.get(gems[start])-1);

				if(count.get(gems[start]) == 0)
					gem_count--;
				start++;
			}
			//end 증가
			else if(gem_count < total_count && end < gems.length)
			{
				count.replace(gems[end], count.get(gems[end])+1);

				//첫 방문
				if(count.get(gems[end]) == 1)
					gem_count++;
				end++;
			}


			if(gem_count == total_count)
			{
				//System.out.println(String.valueOf(end-1) + " "+start);
				if(min_length > end - 1 - start)
				{
					min_length = end - 1 - start;
					min_start = start;
					min_end = end - 1;
				}
				else if(min_length == end - 1 - start)
				{
					if(start < min_start)
					{
						min_length = end - 1 - start;
						min_start = start;
						min_end = end - 1;
					}
				}
			}

			if(end == gems.length && gem_count < total_count)
				break;
		}

		answer[0] = min_start+1;
		answer[1] = min_end+1;
		return answer;
	}

}