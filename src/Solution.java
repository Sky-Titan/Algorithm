import java.util.ArrayList;

public class Solution {

	static int N;
	static int[] weak_a;
	static int[] dist_a;
	static int min_people;
	static int visit_count = 0;
	static boolean visited[], friend_visited[];


	static int wall[];

	public static int solution(int n, int[] weak, int[] dist) {
		int answer = 0;

		N = n;
		weak_a = weak;
		dist_a = dist;

		wall = new int[n];

		int current = weak[0];
		int next = 0;

		int value = 1;

		friend_visited = new boolean[dist.length];

		/*
		for(int i = 0;i < weak.length;i++)
		{
			if(i < weak.length-1)
				next = weak[i+1];
			else
				next = n;


			for(int j = current;j < next;j++)
			{
				wall[j] = value;
			}

			current = next;
			value++;
		}
*/
		visited = new boolean[weak.length];

		min_people = friend_visited.length+1;

		//한계 인원 결정
		for(int people = 1; people <= friend_visited.length;people++)
		{
			//투입 인원 결정
			for(int friend = friend_visited.length-1; friend >= 0;friend--)
			{
				//시작 위치 결정
				for(int start = 0; start< weak_a.length; start++)
				{
					friend_visited[friend] = true;
					clockVisit(start, dist_a[friend]);
					dfs(people, friend, 1);
					clockUnvisit(start, dist_a[friend]);

					//최소 인원 찾음
					if(min_people <= people)
						return min_people;

					counterClockVisit(start, dist_a[friend]);
					dfs_counter(people, friend, 1);
					counterClockUnvisit(start, dist_a[friend]);
					friend_visited[friend] = false;

					//최소 인원 찾음
					if(min_people <= people)
						return min_people;
				}
			}
		}

		return -1;
	}


	public static void dfs(int peopleLimit, int index, int peopleCount)
	{
		//한계 인원 됨
		if(peopleLimit == peopleCount || isCompleteVisit())
		{
			//완전 방문 완료
			if(isCompleteVisit())
			{
				min_people = Math.min(min_people, peopleCount);
			}
		}
		else
		{
			//그 다음 친구 결정
			for(int friend = friend_visited.length-1;friend >=0; friend--)
			{
				if(!friend_visited[friend])
				{
					for(int start = 0; start < weak_a.length; start++)
					{
						if(!visited[start])
						{
							friend_visited[friend] = true;
							clockVisit(start, dist_a[friend]);
							dfs(peopleLimit, friend, peopleCount+1);
							clockUnvisit(start, dist_a[friend]);
							friend_visited[friend] = false;

							if(min_people <= peopleLimit)
								return;
						}
					}
				}

			}
		}

	}

	public static void dfs_counter(int peopleLimit, int index, int peopleCount)
	{
		//한계 인원 됨
		if(peopleLimit == peopleCount || isCompleteVisit())
		{
			//완전 방문 완료
			if(isCompleteVisit())
			{
				min_people = Math.min(min_people, peopleCount);
			}
		}
		else
		{
			//그 다음 친구 결정
			for(int friend = friend_visited.length-1; friend >= 0; friend--)
			{
				if(!friend_visited[friend])
				{
					for(int start = 0; start < weak_a.length; start++)
					{
						if(!visited[start])
						{
							friend_visited[friend] = true;
							counterClockVisit(start, dist_a[friend]);
							dfs_counter(peopleLimit, friend, peopleCount+1);
							counterClockUnvisit(start, dist_a[friend]);
							friend_visited[friend] = false;

							if(min_people <= peopleLimit)
								return;
						}
					}
				}
			}
		}

	}

	//모든 weak 지점 방문 끝났는지 체크
	public static boolean isCompleteVisit()
	{
		if(visit_count == visited.length)
			return true;
		return false;
	}


	public static int clockVisit(int start, int distance)
	{
		int current = start+1;
		int former = start;

		if(start == weak_a.length - 1)
			current = 0;

		int count = 0;

		setVisit(start);

		while(true)
		{
			int move = weak_a[current] - weak_a[former];

			if(move < 0)
				move += N;

			if(distance - move >= 0 && !visited[current])
			{
				distance -= move;
				setVisit(current);
				count++;

				if(current == weak_a.length-1)
					current = 0;
				else
					current++;

				if(former == weak_a.length-1)
					former = 0;
				else
					former++;
			}
			else
				break;
		}

		return count;
	}

	public static int clockUnvisit(int start, int distance)
	{
		int current = start+1;
		int former = start;

		if(start == weak_a.length - 1)
			current = 0;

		int count = 0;

		setUnvisit(start);

		while(true)
		{
			int move = weak_a[current] - weak_a[former];

			if(move < 0)
				move += N;

			if(distance - move >= 0 && visited[current])
			{
				distance -= move;
				setUnvisit(current);
				count++;

				if(current == weak_a.length-1)
					current = 0;
				else
					current++;

				if(former == weak_a.length-1)
					former = 0;
				else
					former++;
			}
			else
				break;
		}

		return count;
	}

	//반시계방향
	public static int counterClockVisit(int start, int distance)
	{
		int current = start-1;
		int former = start;

		if(start == 0)
			current = weak_a.length-1;

		int count = 0;

		setVisit(start);

		while(true)
		{
			int move = weak_a[former] - weak_a[current];

			if(move < 0)
				move += N;

			if(distance - move >= 0 && !visited[current])
			{
				distance -= move;
				setVisit(current);
				count++;

				if(current == 0)
					current = weak_a.length-1;
				else
					current--;

				if(former == 0)
					former = weak_a.length-1;
				else
					former--;
			}
			else
				break;
		}

		return count;
	}

	public static int counterClockUnvisit(int start, int distance)
	{
		int current = start-1;
		int former = start;

		if(start == 0)
			current = weak_a.length-1;

		int count = 0;

		setUnvisit(start);

		while(true)
		{
			int move = weak_a[former] - weak_a[current];

			if(move < 0)
				move += N;

			if(distance - move >= 0 && visited[current])
			{
				distance -= move;
				setUnvisit(current);
				count++;

				if(current == 0)
					current = weak_a.length-1;
				else
					current--;

				if(former == 0)
					former = weak_a.length-1;
				else
					former--;
			}
			else
				break;
		}

		return count;
	}

	//방문 처리
	public static void setVisit(int index)
	{
		if(!visited[index])
		{
			visited[index] = true;
			visit_count++;
		}
	}

	//방문 해제
	public static void setUnvisit(int index)
	{
		if(visited[index])
		{
			visited[index] = false;
			visit_count--;
		}
	}
}
