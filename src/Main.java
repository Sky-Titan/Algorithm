import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	static void solution() throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		int M = Integer.parseInt(br.readLine());

		StringBuilder text = new StringBuilder(br.readLine());

		int count = 0;

		int length = 0;

		for(int i = 0;i < text.length() - 2;)
		{
			if(text.charAt(i) == 'I' && text.charAt(i+1) == 'O' && text.charAt(i+2) == 'I')
			{
				length++;
				if(length == N)
				{
					length--;
					count++;
				}
				i+=2;
			}
			else
			{
				length = 0;
				i++;
			}
		}

		bw.write(count+"");

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