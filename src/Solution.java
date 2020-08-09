import java.util.ArrayList;

public class Solution {

	static int N;
	static int[] weak_a;
	static int[] dist_a;
	static int min_people;
	static int visit_count = 0;
	static boolean visited[];
	static int FRIEND_NUMBER;//친구들 숫자


	public static int solution(int n, int[] weak, int[] dist) {

		N = n;
		weak_a = weak;
		dist_a = dist;

		FRIEND_NUMBER = dist.length;

		visited = new boolean[weak.length];

		min_people = FRIEND_NUMBER+1;

		//한계 인원 결정
		for(int people = 1; people <= FRIEND_NUMBER;people++)
		{
			//투입 인원 결정
			for(int friend = FRIEND_NUMBER-1; friend >= 0;friend--)
			{
				//시작 위치 결정
				for(int start = 0; start < weak_a.length; start++)
				{
					int current = clockVisit(start, dist_a[friend]);
					dfs(people, friend, 1, current);
					clockUnvisit(start, dist_a[friend]);

					//최소 인원 찾음
					if(min_people <= people)
						return min_people;
				}

				for(int start = weak_a.length-1 ; start >= 0; start--)
				{
					int current = counterClockVisit(start, dist_a[friend]);
					dfs_counter(people, friend, 1, current);
					counterClockUnvisit(start, dist_a[friend]);

					//최소 인원 찾음
					if(min_people <= people)
						return min_people;
				}
			}
		}

		return -1;
	}


	public static void dfs(int peopleLimit, int start_friend, int peopleCount, int current)
	{
		//한계 인원 됨
		if(peopleLimit == peopleCount)
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
			for(int friend = start_friend-1;friend >=0; friend--)
			{
				for(int start = current; start < weak_a.length; start++)
				{
					if(!visited[start])
					{
						int current2 = clockVisit(start, dist_a[friend]);
						dfs(peopleLimit, friend, peopleCount+1, current2);
						clockUnvisit(start, dist_a[friend]);

						if(min_people <= peopleLimit)
							return;
					}
				}
			}
		}

	}

	public static void dfs_counter(int peopleLimit, int start_friend, int peopleCount, int current)
	{
		//한계 인원 됨
		if(peopleLimit == peopleCount)
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
			for(int friend = start_friend-1; friend >= 0; friend--)
			{
				for(int start = current; start >=0; start--)
				{
					if(!visited[start])
					{
						int current2 = counterClockVisit(start, dist_a[friend]);
						dfs_counter(peopleLimit, friend, peopleCount+1, current2);
						counterClockUnvisit(start, dist_a[friend]);

						if(min_people <= peopleLimit)
							return;
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


	//시계방향 방문
	public static int clockVisit(int start, int distance)
	{
		int current = start+1;
		int former = start;

		if(start == weak_a.length - 1)
			current = 0;

		setVisit(start);

		while(true)
		{
			int move = weak_a[current] - weak_a[former];

			//원형 순환
			if(move < 0)
				move += N;

			if(distance - move >= 0 && !visited[current])
			{
				distance -= move;
				setVisit(current);

				former = current;

				if(current == weak_a.length-1)
					current = 0;
				else
					current++;

			}
			else
				break;
		}

		return current;
	}

	//시계방향 방문 해제
	public static int clockUnvisit(int start, int distance)
	{
		int current = start+1;
		int former = start;

		if(start == weak_a.length - 1)
			current = 0;

		setUnvisit(start);

		while(true)
		{
			int move = weak_a[current] - weak_a[former];

			//원형 순환
			if(move < 0)
				move += N;

			if(distance - move >= 0 && visited[current])
			{
				distance -= move;
				setUnvisit(current);

				former = current;

				if(current == weak_a.length-1)
					current = 0;
				else
					current++;
			}
			else
				break;
		}

		return current;
	}

	//반시계방향 방문
	public static int counterClockVisit(int start, int distance)
	{
		int current = start-1;
		int former = start;

		if(start == 0)
			current = weak_a.length-1;

		setVisit(start);

		while(true)
		{
			int move = weak_a[former] - weak_a[current];

			//원형 순환
			if(move < 0)
				move += N;

			if(distance - move >= 0 && !visited[current])
			{
				distance -= move;
				setVisit(current);

				former = current;

				if(current == 0)
					current = weak_a.length-1;
				else
					current--;
			}
			else
				break;
		}

		return current;
	}

	//반시계방향 방문 해제
	public static int counterClockUnvisit(int start, int distance)
	{
		int current = start-1;
		int former = start;

		if(start == 0)
			current = weak_a.length-1;

		setUnvisit(start);

		while(true)
		{
			int move = weak_a[former] - weak_a[current];

			//원형 순환
			if(move < 0)
				move += N;

			if(distance - move >= 0 && visited[current])
			{
				distance -= move;
				setUnvisit(current);

				former = current;

				if(current == 0)
					current = weak_a.length-1;
				else
					current--;
			}
			else
				break;
		}

		return current;
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
