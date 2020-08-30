import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {


	public static String solution(String compressed)
	{
		String result = "";

		Stack<Integer> nubmer_stack = new Stack<>();
		Stack<Character> char_stack = new Stack<>();

		String num = "";

		for(int i = 0;i < compressed.length();i++)
		{
			char c = compressed.charAt(i);

			if(Character.isDigit(c))
			{
				num += c;
			}
			else if(c=='(')
			{
				nubmer_stack.push(Integer.parseInt(num));
				char_stack.push(c);
				num = "";
			}
			else if(c == ')')
			{
				String str = "";

				char popped_c = ' ';

				while( (popped_c = char_stack.pop()) != '(' )
				{
					str = popped_c + str;
				}

				int popped_num = nubmer_stack.pop();

				for(int j=0;j<popped_num;j++)
				{
					for(int k = 0;k < str.length();k++)
					{
						char_stack.push(str.charAt(k));
					}
				}
			}
			else
			{
				char_stack.push(c);
			}
		}

		for(int i = 0;i < char_stack.size();i++)
			result += char_stack.get(i);

		return result;
	}



	public static void main(String[] args) {

		try
		{
			System.out.println(solution("2(2(hi)2(co))x2(bo)"));
		/*	Solution s = new Solution();

			int n = 9;
			int[][] path = {
					{0,1}
			,{0,3}
			,{0,7}
			,{8,1}
			,{3,6}
			,{1,2}
			,{4,7}
			,{7,5}
			};

			int[][] order = {
					{8,5}
			,{6,7}
			,{4,1}
			};


			System.out.println(s.solution(n, path, order));*/
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


}