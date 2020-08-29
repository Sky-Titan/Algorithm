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

	//모든 연산자 우선순위 경우의 수 만들기
	static void recursive(int depth, int op_index, String expression, String[] operators)
	{
		if(depth == 3)
		{
			long result = calculate(expression, 0, operators);

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

	//operators(연산자 우선순위)에 따라 expression 파싱하여 계산하기
	static long calculate(String expression, int op_index, String[] operators)
	{
		long result = 0;

		//연산자 기준으로 토크나이즈 한 string 리스트 가져오기
		String[] list = getTokenizedList(expression, op_index, operators);

		for(int i = 0;i < list.length;i++)
		{
			long value = 0;

			//연산자가 포함되어있다면 다시 한 번 파싱 필요
			if(isOperatorInExpression(list[i]))
				value = calculate(list[i], op_index + 1, operators);
			//피연산자만 존재하는 경우
			else
				value = Integer.parseInt(list[i]);

			//연산 결과
			result = getResult(result, value, operators, op_index, i);
		}

		return result;
	}

	//연산자 기준으로 토크나이즈 한 string list 얻기
	static String[] getTokenizedList(String expression, int op_index, String[] operators)
	{
		StringTokenizer strtok = new StringTokenizer(expression, operators[op_index]);

		String[] list = new String[strtok.countTokens()];

		int k = 0;

		while(strtok.hasMoreTokens())
			list[k++] = strtok.nextToken();

		return list;
	}

	//연산 결과 얻기
	static long getResult(long result, long value, String[] operators, int op_index, int i)
	{
		if(operators[op_index].equals("+"))
			result += value;
		else if(operators[op_index].equals("-"))
		{
			if(i==0)
				result += value;
			else
				result -= value;
		}
		else
		{
			if(i==0)
				result = 1;
			result *= value;
		}
		return result;
	}


	//expression 안에 아무 연산자가 존재하는지 판단
	static boolean isOperatorInExpression(String expression)
	{
		if( expression.contains("+") || expression.contains("-") || expression.contains("*"))
			return true;
		return false;
	}
}