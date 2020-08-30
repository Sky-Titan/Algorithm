import org.omg.PortableInterceptor.INACTIVE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {


	static void solution() throws  Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String str = br.readLine();

		int N = str.length();

		Deque[] deque = new ArrayDeque[N];

		for(int i = 0;i < N;i++)
		{
			deque[i] = new ArrayDeque<Character>();
			deque[i].offer(str.charAt(i));
		}

		int M = Integer.parseInt(br.readLine());

		int cursor = N;

		for(int i = 0;i < M;i++)
		{
			StringTokenizer strtok = new StringTokenizer(br.readLine());

			char order = strtok.nextToken().charAt(0);

			if(order == 'L')
			{
				if(cursor > 0)
					cursor--;
			}
			else if(order == 'D')
			{
				if(cursor < N)
					cursor++;
			}
			//커서 왼쪽 문자 삭제 (덱 앞 삭제
			else if(order == 'B')
			{
				if(cursor == 0)
					deque[cursor].pollFirst();
				else
					deque[cursor-1].pollLast();
			}
			else
			{
				char c = strtok.nextToken().charAt(0);

				if(cursor == 0)
					deque[cursor].offerFirst(c);
				else
					deque[cursor-1].offerLast(c);
			}
		}

		for(int i = 0;i < N;i++)
		{
			while(!deque[i].isEmpty())
			{
				bw.write(deque[i].poll()+"");
			}
		}




		bw.close();
	}




	public static void main(String[] args) {

		try
		{
			solution();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


}