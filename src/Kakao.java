import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Kakao {
	
	public Kakao()
	{
		
	}
	
	static void kakao_Intern2019_3() throws Exception
	{
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"fr*d*", "abc1**"};
		
		HashSet<String> result = new HashSet<>();
		
		boolean visited[] = new boolean[banned_id.length];
		boolean visited_user[] = new boolean[user_id.length];
		
		int count[] = {0};
		ArrayList<ArrayList<String>> isIn = new ArrayList<>();
		ArrayList<String> list = new ArrayList<>();
		recursive_3(0,banned_id.length, user_id, banned_id, visited, visited_user,count,list,isIn);
		
		System.out.println(count[0]);
	}
	
	static void recursive_3(int index,int depth ,String[] user_id, String[] banned_id, boolean visited[], boolean visited_user[], int count[],ArrayList<String> list,ArrayList<ArrayList<String>> isIn )
	{
		if(depth == 0)
		{
			boolean isPass = false;
			
			for(int i=0;i<isIn.size();i++)
			{
				isPass = false;
				for(int j=0;j<list.size();j++)
				{
					if(!isIn.get(i).contains(list.get(j)))
					{
						isPass = true;
					}
				}
				//중복
				if(!isPass)
					return;
				
			}
			
			isIn.add(new ArrayList<>(list));
			count[0]++;
		}
		else
		{
			for(int i=index;i<user_id.length;i++)
			{
				String user = user_id[i];
				
				if(!visited_user[i])
				{
					for(int j=0;j<banned_id.length;j++)
					{
						String ban = banned_id[j];
						boolean isBan = true;
						
						if(!visited[j] && ban.length() == user.length())//비교
						{
							for(int k=0;k<ban.length();k++)
							{
								if(ban.charAt(k) != '*' && ban.charAt(k) != user.charAt(k))
								{
									isBan = false;
									break;
								}
							}
							
							if(isBan)
							{
								visited[j] = true;
								visited_user[i] = true;
								list.add(user);
								recursive_3(i+1, depth-1, user_id, banned_id, visited, visited_user,count, list,isIn);
								list.remove(user);
								visited[j] = false;
								visited_user[i] = false;
					
							}
						}
					}
				}
				
			
			}
		}
	}
	
	static void kakao_Intern2019_2() throws Exception
	{
		String input = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
		
		ArrayList<Integer> list = new ArrayList<>();
		int count[] = new int[100000];
		
		StringTokenizer strtok = new StringTokenizer(input,"{");
		
		while(strtok.hasMoreTokens())
		{
			StringTokenizer strtok2 = new StringTokenizer(strtok.nextToken(),"}");
			while(strtok2.hasMoreTokens())
			{
				StringTokenizer strtok3 = new StringTokenizer(strtok2.nextToken(),",");
				while(strtok3.hasMoreTokens())
				{
					int num = Integer.parseInt(strtok3.nextToken());
					count[num]++;
					if(count[num] == 1)
						list.add(num);
				}
			}
		}
		
		Object[] numbers = list.toArray();
		mergeSort(numbers, count, 0, numbers.length-1);
		for(int i=0;i<numbers.length;i++)
			System.out.print(numbers[i]+" ");
	}
	
	static void mergeSort(Object[] numbers,int count[] ,int left, int right)
	{
		if(left < right)
		{
			int mid = (left + right) / 2;
			mergeSort(numbers, count, left, mid);
			mergeSort(numbers, count, mid+1, right);
			merge(numbers, count, left, right, mid);
		}
	}
	
	static void merge(Object[] numbers,int count[] ,int left, int right, int mid)
	{
		int i = left;
		int j = mid+1;
		int k = left;
		
		Object[] list = new Object[numbers.length];
		
		while(i <= mid && j <= right)
		{
			if(count[(int)numbers[i]] > count[(int)numbers[j]])
			{
				list[k++] = numbers[i++];
			}
			else
			{
				list[k++] = numbers[j++];
			}
		}
		
		while(i <= mid)
		{
			list[k++] = numbers[i++];
		}
		
		while(j <= right)
		{
			list[k++] = numbers[j++];
		}
		
		for(k = left;k<=right;k++)
		{
			numbers[k] = list[k];
		}
	}
	
	static void kakao_Intern2019_1() throws Exception
	{
		int board[][]=
				{{0,0,0,0,0},
				{0,0,1,0,3},
				{0,2,5,0,1},
				{4,2,4,4,2},
				{3,5,1,3,1}};
		
		int moves [] = {1,5,3,5,1,2,1,4};
		
		int count=0;
		
		Stack<Integer> stack = new Stack();
		
		for(int n=0;n<moves.length;n++)
		{
			int move = moves[n]-1;
			
			int picked = pickOut(board, move);
			
			if(!stack.isEmpty())
			{
				//터뜨림
				if(picked == stack.peek())
				{
					count += 2;
					stack.pop();
				}
				else
				{
					stack.push(picked);
				}
			}
			else
			{
				stack.push(picked);
			}
			
		}
		System.out.println(count);
	}
	
	static int pickOut(int board[][],int move)
	{
		int result = 0;
		for(int i=0;i<board.length;i++)
		{
			if(board[i][move] > 0)
			{
				result = board[i][move];
				board[i][move] = 0;
				return result;
			}
		}
		return result;
	}
	
	
	//2017카카오 공채 - 추석 트래픽
	static void kakao_recruit2017_7() throws Exception
	{
		String input[] = //{"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"}; 
				
				//{"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};
				
				 {"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s",
				"2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", 
				"2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"};
		
		double end_times[][] = new double[input.length][4];
		double start_times[][] = new double[input.length][3];
		
		double fastest_start[] = new double[3];//가장 빠른 시작 시간 구하기
		double slowest_finish[] = new double[3];//가장 느리게 끝난 시간 구하기
		
		for(int i=0;i<input.length;i++)
		{
			StringTokenizer strtok = new StringTokenizer(input[i]);
			
			String date = strtok.nextToken();//날리기
			String complete_time = strtok.nextToken();
			String process_time = strtok.nextToken();
			
			strtok = new StringTokenizer(complete_time,":");
			
			for(int j=0;j<3;j++)
				end_times[i][j] = Double.parseDouble(strtok.nextToken());
			
			strtok = new StringTokenizer(process_time,"s");
			end_times[i][3] = Double.parseDouble(strtok.nextToken());

			double start_sec = end_times[i][2] - end_times[i][3];
			start_sec *= 1000;
			start_sec += 1;
			start_sec = Math.floor(start_sec);
			start_sec /= 1000;//소숫점 3번째짜리까지
			double start_min = end_times[i][1];
			double start_hour = end_times[i][0];
			
			if(start_sec < 0)
			{	
				start_sec += 60;
				start_min--;
				if(start_min < 0)
				{
					start_min += 60;
					start_hour--;
					
				}
			}
			
			start_times[i][0] = Math.floor(start_hour);
			start_times[i][1] = Math.floor(start_min);
			start_times[i][2] = start_sec;
			
			//가장빠른 시작 시간 구하기
			if(i==0)
			{
				fastest_start[0] = start_hour;
				fastest_start[1] = start_min;
				fastest_start[2] = start_sec;
			}
			else
			{
				boolean copy = false;
				if(fastest_start[0] > start_hour)
					copy = true;
				else if(fastest_start[0] == start_hour)
				{
					if(fastest_start[1] > start_min)
						copy = true;
					else if(fastest_start[1] == start_min)
					{
						if(fastest_start[2] > start_sec)
							copy = true;
					}
				}
				
				if(copy)
				{
					fastest_start[0] = start_hour;
					fastest_start[1] = start_min;
					fastest_start[2] = start_sec;
				}
			}
			
		
		}
		
		slowest_finish[0] = end_times[end_times.length-1][0];
		slowest_finish[1] = end_times[end_times.length-1][1];
		slowest_finish[2] = end_times[end_times.length-1][2];

		
		double s_time[] = new double[3];
		double e_time[] = new double[3];
		
		
		int max = Integer.MIN_VALUE;
		
		//한번에 2번씩 검사
		for(int i=0;i<input.length;i++)
		{
			
			//시작 타임 검사
			s_time = start_times[i].clone();
			e_time = start_times[i].clone();

			if(e_time[2] >= 60)
			{
				e_time[2] -= 60;
				e_time[1] ++;
				if(e_time[1] >= 60)
				{
					e_time[1] -= 60;
					e_time[0] ++;	
				}
			}
			
			e_time[2] *= 1000;
			e_time[2] += 999;
			e_time[2] = Math.floor(e_time[2]);
			e_time[2] /= 1000;
		
			int count=0;
			
			for(int j=0;j<input.length;j++)
			{
				if(isMid(s_time, e_time,start_times[j],end_times[j]))
					count++;
			}

			max = Math.max(count, max);
			
			//끝나는 타임 검사
			s_time = end_times[i].clone();
			e_time = end_times[i].clone();
			
			if(e_time[2] >= 60)
			{
				e_time[2] -= 60;
				e_time[1] ++;
				if(e_time[1] >= 60)
				{
					e_time[1] -= 60;
					e_time[0] ++;	
				}
			}
			
			e_time[2] *= 1000;
			e_time[2] += 999;
			e_time[2] = Math.floor(e_time[2]);
			e_time[2] /= 1000;
			
			count=0;
			
			for(int j=0;j<input.length;j++)
			{
				if(isMid(s_time, e_time,start_times[j],end_times[j]))
					count++;
			}
			
			max = Math.max(count, max);		
		}
		
		System.out.println(max);
		
	}
	
	//해당 시간대에 포함되어있는지
	static boolean isMid(double area_start[], double area_finish[], double start[], double finish[])
	{

		if(start_in(area_start, start) && finish_in(area_start, finish))
			return true;
		else
		{
			if(start_in(area_finish, start) && finish_in(area_finish, finish))
				return true;
			else
				return false;
		}

	}
	
	static boolean start_in(double area[], double start[])
	{
		if(start[0] < area[0])
		{
			return true;
		}
		else if(start[0] == area[0])//시간 같은 경우
		{
			if(start[1] < area[1])
			{
				return true;
			}
			else if(start[1] == area[1])//분 같은 경우
			{
				if(start[2] <= area[2])
				{
					return true;
				}
			}
			
		}
		return false;
	}
	static boolean finish_in(double area[], double finish[])
	{
		if(area[0] < finish[0])
		{
			return true;
		}
		else if(area[0] == finish[0])//시간 같은 경우
		{
			if(area[1] < finish[1])
			{
				return true;
			}
			else if(area[1] == finish[1])//분 같은 경우
			{
				if(area[2] <= finish[2])
				{
					return true;
				}
			}
				
		}
		return false;
	}
	
	//2017카카오 공채 - 프렌즈 4블록
	static void kakao_recruit2017_6() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		
		StringTokenizer strtok = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(strtok.nextToken());
		int M = Integer.parseInt(strtok.nextToken());
		
		char map [][] = new char[N][M];
		
		for(int i=0;i<N;i++)
		{
			String str = br.readLine();
			
			for(int j=0;j<M;j++)
			{
				map[i][j] = str.charAt(j);
			}
		}
		boolean deleted[][] = new boolean[N][M];
		
		boolean isFinished = false;
		
		int count=0;
		while(!isFinished)
		{
			isFinished = true;
			//끝내야하는지 검사
			for(int i=0;i<N-1;i++)
			{
				for(int j=0;j<M-1;j++)
				{
					char ch = map[i][j];
					if(ch!=' ' && map[i+1][j] == ch && map[i][j+1] == ch && map[i+1][j+1] == ch)
					{
						
						if(!deleted[i][j])
						{
							count++;
							deleted[i][j] = true;
						}
						if(!deleted[i+1][j])
						{
							count++;
							deleted[i+1][j] = true;
						}
						if(!deleted[i][j+1])
						{
							count++;
							deleted[i][j+1] = true;
						}
						if(!deleted[i+1][j+1])
						{
							count++;
							deleted[i+1][j+1] = true;
						}
						
						isFinished = false;
					}
				}
			}
		
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<M;j++)
				{
					if(deleted[i][j])
						map[i][j] = ' ';
				}
			}
			
			if(!isFinished)
				drop_block(map, N, M);

			deleted = new boolean[N][M];
			
		}
		System.out.println(count);
	}
	
	static void drop_block(char map[][],int N,int M)
	{
		for(int j=0;j<M;j++)
		{
			for(int i=N-1;i>=0;i--)
			{
				if(map[i][j] != ' ' && i!=N-1)//안지워지는애들
				{
					for(int k=i+1;k<N;k++)
					{
						if(map[k][j] == ' ')
						{
							map[k][j] = map[k-1][j];
							map[k-1][j] = ' ';
						}
						
					}
				}
			}
		}
	}
	
	//2017카카오 공채 - 뉴스 클러스터링
	static void kakao_recruit2017_5() throws Exception
	{
		String str[] = {"FRANCE","french"};
			
		//소문자로 통일
		for(int i=0;i<2;i++)
		{
			String temp = "";
			for(int j=0;j<str[i].length();j++)
			{
				if(Character.isAlphabetic(str[i].charAt(j)))
				{
					char ch = Character.toLowerCase(str[i].charAt(j));
					temp += ch;
				}
				else
				{
					temp += str[i].charAt(j);
				}
			}
			str[i] = temp;
		}
			
		char former = ' ';//그전 문자
			
		ArrayList<String> set[] = new ArrayList[2];//집합
		HashSet<String> union = new HashSet<>();//합집합
		HashSet<String> intersect = new HashSet<>();//교집합
		
		set[0] = new ArrayList<>();
		set[1] = new ArrayList<>();
		
		for(int i=0;i<2;i++)
		{
			for(int j=0;j<str[i].length()-1;j++)
			{
				if(Character.isAlphabetic(str[i].charAt(j)) && Character.isAlphabetic(str[i].charAt(j+1)))
					set[i].add(str[i].charAt(j)+""+str[i].charAt(j+1));
					
			}
		}
		
		Iterator a = set[0].iterator();
			
		union.addAll(set[0]);
		union.addAll(set[1]);//합집합
			
		intersect.addAll(set[0]);
		intersect.retainAll(set[1]);//교집합
			
		ArrayList<String> intersect2 = new ArrayList<>();
		ArrayList<String> union2 = new ArrayList<>();
			
		int size1 = 0;//intersect
		int size2 = 0;//union
		
		for(int i=0;i<intersect.size();i++)
		{
			a = intersect.iterator();
			String e = String.valueOf(a.next());
				
			int min = Integer.MAX_VALUE;
				
			int count=0;
				
			for(int j=0;j<set[0].size();j++)
			{
				if(e.equals(set[0].get(j)))
					count++;
			}
				
			int count2 = 0;
	
			for(int j=0;j<set[1].size();j++)
			{
				if(e.equals(set[1].get(j)))
					count2++;
			}
			min = Math.min(count, count2);
			size1 += min;
		}
			
		for(int i=0;i<union.size();i++)
		{
			a = union.iterator();
			String e = String.valueOf(a.next());
				
			int max = Integer.MIN_VALUE;
				
			int count=0;
		
			for(int j=0;j<set[0].size();j++)
			{
				if(e.equals(set[0].get(j)))
					count++;
			}
				
			int count2 = 0;
			
			for(int j=0;j<set[1].size();j++)
			{
				if(e.equals(set[1].get(j)))
					count2++;
			}
			max = Math.max(count, count2);
			size2 += max;
		}
			
		double result = (double)size1 / (double) size2;
			
		if(size2 == 0)
			result = 1;
			
		result *= 65536;
		System.out.println((int)result);
			
	}
		
	//2017카카오 공채 - 버스
	static void kakao_recruit2017_4() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
			
		StringTokenizer strtok = new StringTokenizer(b.readLine());
		
		int N = Integer.parseInt(strtok.nextToken());//버스 총 댓수
		int T = Integer.parseInt(strtok.nextToken());//간격
		int M = Integer.parseInt(strtok.nextToken());//최대 탑승인원
		
		String timetable_original[] = {"23:59"};
		int timetable[][] = new int[timetable_original.length][2];
		
		for(int i=0;i<timetable_original.length;i++)
		{
			strtok = new StringTokenizer(timetable_original[i],":");
			timetable[i][0] = Integer.parseInt(strtok.nextToken());
			timetable[i][1] = Integer.parseInt(strtok.nextToken());
		}
			
		mergeSort(timetable, 0, timetable.length-1);//정렬
		
		int bus[][] = new int[N][2];
			
		int hour = 9;
		int minute = 0;
			
		bus[0][0] = hour;
		bus[0][1] = minute;
			
		for(int i=1;i<N;i++)
		{
			minute += T;
				
			if(minute >= 60)
			{	
				minute -= 60;
				hour ++;
			}
				
			bus[i][0] = hour;
			bus[i][1] = minute;
				
		}

		int bus_customer[][] = new int[bus.length][M];
			
		for(int i=0;i<bus.length;i++)
			for(int j=0;j<M;j++)
				bus_customer[i][j] = -1;
		
		int j = 0;
		
		int i = 0;
		for(;i<bus.length;i++)
		{
			int count=0;
				
			while(count < M)
			{
				if(j == timetable.length)
					break;
				if(isFaster(bus[i], timetable[j]))
				{
					bus_customer[i][count] = j;
					j++;
				}
				else
					break;
				count++;
			}
				
			if(j == timetable.length)
				break;
		}
			
		if(i==N)
			i--;
		if(bus_customer[i][M-1] != -1)//버스 꽉찬 경우
		{
			int last_hour = timetable[bus_customer[i][M-1]][0];
			int last_min = timetable[bus_customer[i][M-1]][1];
				
			if(last_min==0)
			{
				last_min = 59;
				last_hour--;
			}
			else
				last_min--;
				
			System.out.println(intTotime(last_hour)+":"+intTotime(last_min));
			return;
		}
		else//꽉 안 찬 경우
		{
			int k = M-1;
			for(;k>=0;k--)
			{
				if(bus_customer[i][k] != -1)
					break;
			}
			
			int result_hour = 0;
			int result_min =0;
			
			if(k!=-1)
			{
				result_hour = timetable[bus_customer[i][k]][0];
				result_min = timetable[bus_customer[i][k]][1];
			}
			else
			{
				result_hour = bus[i][0];
				result_min = bus[i][1];
			}
					
			while(true)
			{
				result_min++;
				
				if(result_min>59)
				{
					result_min = 0;
					result_hour++;
				}
				
				int temp[] = {result_hour, result_min};
					
				if(!isFaster(bus[i], temp))
				{
					result_min--;
					
					if(result_min==-1)
					{
						result_min = 59;
						result_hour--;
					}
					System.out.println(intTotime(result_hour)+":"+intTotime(result_min));
					return;
				}
			}
					
		}
			
			
	}
		
	static void mergeSort(int timetable[][],int left, int right)
	{
		if(left<right)
		{
			int mid = (left + right) / 2;
			
			mergeSort(timetable, left, mid);
			mergeSort(timetable, mid+1, right);
			merge(timetable, left, mid, right);
		}
	}
	
	static void merge(int timetable[][], int left, int mid, int right)
	{
		int i = left;
		int j = mid+1;
		int k = 0;
		
		int sorted[][] = new int[timetable.length][2];
		
		while(i<=mid && j<=right)
		{
			if(isFaster(timetable[i], timetable[j]))//j가 더빠름
			{
				sorted[k][0] = timetable[j][0];
				sorted[k][1] = timetable[j][1];
				k++;
				j++;
			}
			else
			{
				sorted[k][0] = timetable[i][0];
				sorted[k][1] = timetable[i][1];
				k++;
				i++;
			}
		}
			
		if(i > mid)
		{
			for(;j<=right;j++)
			{
				sorted[k][0] = timetable[j][0];
				sorted[k][1] = timetable[j][1];
				k++;
			}
		}
		else
		{
			for(;i<=mid;i++)
			{
				sorted[k][0] = timetable[i][0];
				sorted[k][1] = timetable[i][1];
				k++;
			}
		}
			
		k=0;
		for(i = left;i<=right;i++)
		{
			timetable[i][0] = sorted[k][0];
			timetable[i][1] = sorted[k][1];
			k++;
		}
	}
		
	static boolean isFaster(int bus[], int people[])
	{
		if(bus[0] > people[0])
			return true;
		else if(bus[0] == people[0])
		{
			if(bus[1] >= people[1])
				return true;
			else
				return false;
		}
		else
			return false;
	}
		
	static String intTotime(int time)
	{
		if(time < 10)
			return "0"+time;
		else
			return time+"";
	}
		
	//2017카카오 공채 - 캐시
	static void kakao_recruit2017_3() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
		
		int cache_size = Integer.parseInt(b.readLine());
		String city[] = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
			
			
		Queue<String> cache = new LinkedList<String>();
			
		//대소문자 구분안하므로 소문자로 통일
		for(int i=0;i<city.length;i++)
		{
			String str = city[i];
			String str2 = "";
				
			for(int j=0;j<str.length();j++)
				str2 += Character.toLowerCase(str.charAt(j));
				
			city[i] = str2;
		}
		int sum = 0;
					
		for(int i=0;i<city.length;i++)
		{
			if(cache.contains(city[i]))//캐시에 있으면 +1;
				sum += 1;
			else
				sum += 5;
				
			if(cache.size() == cache_size)
				cache.poll();
			cache.offer(city[i]);
		}
			
		System.out.println(sum);
			
	}
		
	//2017카카오 공채 - 다트게임
	static void kakao_recruit2017_2() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
			
		String str = b.readLine();
			
		String darts[][] = new String[3][3];
		
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				darts[i][j] = "";
			
		int index=0;
			
		for(int k=0;k<str.length();k++)
		{
			char ch = str.charAt(k);
			
			if(Character.isDigit(ch))//숫자(점수)
			{
				darts[index][0] += ch+"";
				for(int j=k+1;j<str.length();j++)
				{
					if(Character.isDigit(str.charAt(j)))//숫자면 계속추가
						darts[index][0] += str.charAt(j)+"";
					else
					{
						k = j-1;
						break;
					}
							
				}
			}
			else if(Character.isAlphabetic(ch))//문자(보너스)
			{
				darts[index][1] += ch;
				
				if(k != str.length()-1 && Character.isDigit(str.charAt(k+1)))
					index++;
			}
			else//옵션
			{
				darts[index][2] += ch;
				index++;
			}
		}
			
		int sum = 0;//점수합
		int score[] = new int[3];
			
		for(int i=0;i<3;i++)
		{
			score[i] = Integer.parseInt(darts[i][0]);
				
			if(darts[i][1].equals("S"))//1제곱
				score[i] = (int)Math.pow(score[i], 1);
			else if(darts[i][1].equals("D"))//2제곱
				score[i] = (int)Math.pow(score[i], 2);
			else if(darts[i][1].equals("T"))//3제곱
				score[i] = (int)Math.pow(score[i], 3);
				
			if(darts[i][2].equals("*"))//스타상
			{
				score[i] *= 2;
					
				if(i!=0)
					score[i-1] *= 2; 
			}
			else if(darts[i][2].equals("#"))//아차상
				score[i] *= -1;
					
		}
			
		for(int i=0;i<3;i++)
			sum += score[i];
		
		System.out.println(sum);
	}

		
	//2017카카오 공채 - 비밀지도
	static void kakao_recruit2017_1() throws Exception
	{
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader b = new BufferedReader(is);
			
		int N = Integer.parseInt(b.readLine());
				
		int arr1[] = new int[N];
		int arr2[] = new int[N];
			
		StringTokenizer strtok = new StringTokenizer(b.readLine());
			
		for(int i=0;i<N;i++)
		{
			arr1[i] = Integer.parseInt(strtok.nextToken());
		}
		
		strtok = new StringTokenizer(b.readLine());
			
		for(int i=0;i<N;i++)
		{
			arr2[i] = Integer.parseInt(strtok.nextToken());
		}
			
		char map1[][] = new char[N][N];
		char map2[][] = new char[N][N];
			
		for(int i=0;i<N;i++)
		{
			map1[i] = binary_make(N, arr1[i]).clone();
			map2[i] = binary_make(N, arr2[i]).clone();
		}
			
		char map_all[][] = new char[N][N];
			
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				if(map1[i][j] == '#' || map2[i][j] == '#')
					System.out.print("#");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}
		
	static char[] binary_make(int N, int num)
	{
		//N비트로 표현
		char[] result = new char[N];
			
		for(int i=0;i<N;i++)
			result[i] = ' ';
			
		for(int i=N-1;i>=0;i--)
		{
			if(num == 1)
			{	
				result[i] = '#';
				break;
			}
			int other = num % 2;
			num/=2;
				
			if(other == 1)
				result[i] ='#';
			else
				result[i] =' ';
		}
			
		return result;
	}
}
