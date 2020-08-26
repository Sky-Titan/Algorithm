import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	static void failureFunction(int p[], StringBuilder pattern)
	{
		int j = 0;

		for(int i = 1;i < pattern.length();i++)
		{
			while(j > 0 && pattern.charAt(i) != pattern.charAt(j))
				j = p[j-1];

			if(pattern.charAt(i) == pattern.charAt(j))
				++j;
		}
	}

	public static void main(String[] args) {

		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

			StringBuilder text = new StringBuilder(br.readLine());
			StringBuilder pattern = new StringBuilder(br.readLine());

			//pattern 인덱스가 돌아갈 곳 지정
			Stack<Integer> index_stack = new Stack<>();

			//결과 문자열 저장
			Stack<Character> char_stack = new Stack<>();

			int p[] = new int[pattern.length()];

			failureFunction(p, pattern);

			int j = 0;

			//text 길이만큼 순회
			for(int i = 0;i < text.length();i++)
			{
				while(j > 0 && text.charAt(i) != pattern.charAt(j))
					j = p[j-1];

				//문자 push
				char_stack.push(text.charAt(i));

				//문자가 같은 경우
				if(text.charAt(i) == pattern.charAt(j))
				{
					//패턴 매칭 완료
					if(j == pattern.length() - 1)
					{
						//패턴 길이만큼 pop 함으로써 문자 삭제
						char_stack.pop();
						for(int k = 0;k < pattern.length()-1;k++)
						{
							index_stack.pop();
							char_stack.pop();
						}

						//pattern 인덱스 j가 돌아갈 곳을 지정
						if(!index_stack.isEmpty())
							j = index_stack.peek();
						else
							j = 0;
					}
					//일부 일치
					else
						index_stack.push(++j);

				}
				//문자가 같지 않다면 현재 pattern index - 1 push
				else
					index_stack.push(j);
			}

			if(!char_stack.isEmpty())
			{
				for(char a: char_stack)
					bw.write(a);
			}
			else
				bw.write("FRULA");

			bw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}