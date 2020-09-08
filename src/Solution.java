

import java.util.*;


class Solution {

	HashMap<Integer, Integer> map = new HashMap<>();

	public int[] solution(String[] operations) {
		int[] answer = new int[2];

		PriorityQueue<Integer> min_heap = new PriorityQueue<>();
		PriorityQueue<Integer> max_heap = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});


		for(int i = 0;i < operations.length;i++)
		{
			String[] list = operations[i].split(" ");

			//삽입
			if(list[0].equals("I"))
			{
				int key = Integer.parseInt(list[1]);
				min_heap.offer(key);
				max_heap.offer(key);

				map.putIfAbsent(key, 0);
				map.computeIfPresent(key, (k, v) -> ++v);
			}
			else
			{
				if(!map.isEmpty())
				{
					//최대값 빼내기
					if(list[1].equals("1"))
					{
						while(!max_heap.isEmpty() && map.get(max_heap.peek()) < 1)
							max_heap.poll();

						if(!max_heap.isEmpty())
						{
							map.computeIfPresent(max_heap.peek(), (k, v) -> --v);
							max_heap.poll();
						}
					}
					//최소값 빼내기
					else if(list[1].equals("-1"))
					{
						while(!min_heap.isEmpty() && map.get(min_heap.peek()) < 1)
							min_heap.poll();

						if(!min_heap.isEmpty())
						{
							map.computeIfPresent(min_heap.peek(), (k, v) -> --v);
							min_heap.poll();
						}
					}
				}

			}
		}

		if(map.isEmpty() || max_heap.isEmpty() || min_heap.isEmpty())
		{
			answer[0] = 0;
			answer[1] = 0;
		}
		else
		{
			while(!max_heap.isEmpty() && map.get(max_heap.peek()) == 0)
				max_heap.poll();
			answer[0] = max_heap.poll();

			while(!min_heap.isEmpty() && map.get(min_heap.peek()) == 0)
				min_heap.poll();
			answer[1] = min_heap.poll();
		}

		return answer;
	}
}