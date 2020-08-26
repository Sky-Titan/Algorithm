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


		for(int i = 0;i < N;i++)
		{
			String str = br.readLine();

			boolean visited[] = new boolean[26];
			int sum = 2015;

			for(int j = 0;j < str.length();j++)
			{
				if(!visited[str.charAt(j) - 'A'])
				{
					sum -= str.charAt(j);
					visited[str.charAt(j) - 'A'] = true;
				}
			}

			bw.write(sum+"\n");
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