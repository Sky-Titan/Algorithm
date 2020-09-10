class Solution {

	final int YEAR = 0, MONTH = 1, DATE = 2, HOUR = 3, MIN = 4, SECOND = 5, PROCESS = 6;
	String[] start;

	String[][] finish_parsed_list, start_parsed_list;

	public int solution(String[] end) {
		int answer = 0;

		start = new String[end.length];
		finish_parsed_list = new String[end.length][7];
		start_parsed_list = new String[end.length][7];

		for(int i = 0;i < start.length;i++)
		{
			finish_parsed_list[i] = parsing(end[i]);
			start[i] = makeStart(end[i]);
			start_parsed_list[i] = parsing(start[i]);
		}

		int max = 0;

		for(int i = 0;i < start.length;i++)
		{
			int count = 1;
			int count2 = 1;

			for(int j = 0;j < start.length;j++)
			{
				if(i!=j)
				if(isIn(start_parsed_list[i], start_parsed_list[j], finish_parsed_list[j]) )
					count++;

				if(j > i)
					if(isIn(finish_parsed_list[i], start_parsed_list[j],  finish_parsed_list[j]))
						count2++;
			}

			max = Math.max(max, count);
			max = Math.max(max, count2);
		}


		return max;
	}

	public boolean isIn(String[] start_parsed, String[] checked_start_parsed, String[] checked_finish_parsed)
	{
		String[] end_parsed = start_parsed.clone();

		int end_date = Integer.parseInt(end_parsed[DATE]);

		int end_hour = Integer.parseInt(end_parsed[HOUR]);
		int end_min = Integer.parseInt(end_parsed[MIN]);
		double end_second = Double.parseDouble(end_parsed[SECOND]);

		end_second = makeDouble((makeDouble (end_second) * 1000 + 999)/1000);
		//System.out.println("start time : "+start_time+", checked time : "+checked_start_time +", end time : "+end_hour+":"+end_min+":"+end_second);
		if(end_second >= 60)
		{
			end_second -= 60;
			end_min++;
		}

		if(end_min >= 60)
		{
			end_min -= 60;
			end_hour ++;
		}

		if(end_hour >= 24)
		{
			end_hour -= 24;
			end_date++;
		}

		end_parsed[SECOND] = end_second+"";
		end_parsed[MIN] = end_min+"";
		end_parsed[HOUR] = end_hour+"";
		end_parsed[DATE] = end_date+"";


		if(isFirst(checked_start_parsed, start_parsed) && isFirst(end_parsed, checked_finish_parsed))
			return true;
		else if(isFirst(start_parsed, checked_start_parsed) && isFirst(checked_start_parsed, end_parsed))
			return true;
		else if(isFirst(start_parsed, checked_finish_parsed) && isFirst(checked_finish_parsed, end_parsed))
			return true;

		return false;
	}

	public boolean isFirst(String[] first, String[] second)
	{
		int first_date = Integer.parseInt(first[DATE]);

		int first_hour = Integer.parseInt(first[HOUR]);
		int first_min = Integer.parseInt(first[MIN]);
		double first_second = Double.parseDouble(first[SECOND]);

		//두번째
		int second_date = Integer.parseInt(second[DATE]);

		int second_hour = Integer.parseInt(second[HOUR]);
		int second_min = Integer.parseInt(second[MIN]);
		double second_second = Double.parseDouble(second[SECOND]);

		if(first_date > second_date)
		 	return false;
		else if(first_date == second_date)
		{
			if(first_hour > second_hour)
				return false;
			else if(first_hour == second_hour)
			{
				if(first_min > second_min)
					return false;
				else if(first_min == second_min)
				{
					if(first_second > second_second)
						return false;
				}
			}
		}

		return true;
	}

	public String makeStart(String line)
	{
		String[] parsed = parsing(line);

		int year = Integer.parseInt(parsed[YEAR]);
		int month = Integer.parseInt(parsed[MONTH]);
		int date = Integer.parseInt(parsed[DATE]);


		int hour = Integer.parseInt(parsed[HOUR]);
		int min = Integer.parseInt(parsed[MIN]);
		double second = Double.parseDouble(parsed[SECOND]);

		String process_time_str = parsed[PROCESS];

		double process_time = Double.parseDouble(process_time_str);

		second = makeDouble((makeDouble(second) * 1000 - makeDouble(process_time) * 1000 + 1)/1000);
		//System.out.println("parsing : "+line+", "+second);
		if(second < 0)
		{
			second += 60;
			min -= 1;
		}

		if(min < 0)
		{
			min += 60;
			hour -= 1;
		}

		if(hour < 0)
		{
			hour += 24;
			date -= 1;
		}

		return year+"-"+month+"-"+date+" "+hour+":"+min+":"+second+" "+process_time;
	}

	public String[] parsing(String line)
	{
		String[] result = new String[7];

		String[] times = line.split(" ");
		String days = times[0];

		String[] days_list = days.split("-");
		System.arraycopy(days_list, 0, result,0, days_list.length);

		String end_time_str = times[1];
		String process_time_str = times[2];

		String end_time_list[] = end_time_str.split(":");
		System.arraycopy(end_time_list, 0, result, 3, end_time_list.length);
		result[6] = process_time_str.substring(0, process_time_str.length()-1);

		return result;
	}

	public Double makeDouble(Double origin)
	{
		return Math.floor(origin * 1000) / 1000;
	}
}