import java.util.*;


class Solution {

	static String op_list[] = {"+","-","*"};
	static boolean visited[] = new boolean[3];

	static long max = Long.MIN_VALUE;

	public static long solution(String expression) {

		String[] operators = new String[3];

		for(int i = 0;i < 3;i++)
		{
			operators[0] = op_list[i];
			visited[i] = true;
			recursive(1, 1, expression, operators);
			visited[i] = false;
		}


		return max;
	}

	static void recursive(int depth, int op_index, String expression, String[] operators)
	{

		if(depth == 3)
		{
			op_index = 0;

			while(!expression.contains(operators[op_index]))
				op_index++;


			StringTokenizer strtok = new StringTokenizer(expression, operators[op_index]);

			System.out.println(Arrays.toString(operators));
			String[] list = new String[strtok.countTokens()];

			int k = 0;
			while(strtok.hasMoreTokens())
				list[k++] = strtok.nextToken();

			long result = 0;

			if(operators[op_index].equals("*"))
				result = 1;

			for(int i = 0;i < list.length;i++)
			{
				long value = calculate(list[i],op_index+1, operators);

				if(operators[op_index].equals("+"))
					result += value;
				else if(operators[op_index].equals("-"))
					result -= value;
				else
					result *= value;
			}

			System.out.println(result);
			max = Math.max(max, Math.abs(result));
		}
		else
		{
			for(int i = 0;i < op_list.length;i++)
			{
				if(!visited[i])
				{
					visited[i] = true;
					operators[op_index] = op_list[i];
					recursive(depth+1, op_index+1, expression, operators);
					visited[i] = false;
				}
			}

		}
	}

	static long calculate(String expression, int op_index, String[] operators)
	{
		if(expression.contains("+") || expression.contains("-") || expression.contains("*"))
		{
			long result = 0;

			while(!expression.contains(operators[op_index]))
				op_index++;

			if(op_index >= op_list.length)
				return 0;

			if(operators[op_index].equals("*"))
				result = 1;

			StringTokenizer strtok = new StringTokenizer(expression, operators[op_index]);

			String[] list = new String[strtok.countTokens()];

			int k = 0;

			while(strtok.hasMoreTokens())
				list[k++] = strtok.nextToken();

			for(int i = 0;i < list.length;i++)
			{
				if(list[i].contains("+") || list[i].contains("-") || list[i].contains("*"))
				{
					long value = calculate(list[i], op_index + 1, operators);

					if(operators[op_index].equals("+"))
						result += value;
					else if(operators[op_index].equals("-"))
						result -= value;
					else
						result *= value;
				}
				else
				{
					long value = Integer.parseInt(list[i]);

					System.out.println("원자 "+value);
					if(operators[op_index].equals("+"))
						result += value;
					else if(operators[op_index].equals("-"))
						result -= value;
					else
						result *= value;
				}

			}
			return result;
		}
		else
		{
			System.out.println("원자 "+expression);
			return Integer.parseInt(expression);
		}
	}
}