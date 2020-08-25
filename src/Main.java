import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	static void failureFunction(int p[], char[] pattern)
	{
		int j = 0;

		for(int i = 1;i < pattern.length;i++)
		{
			while(j > 0 && pattern[i] != pattern[j])
				j = p[j-1];

			if(pattern[i] == pattern[j])
				p[i] = ++j;
		}
	}

	static ArrayList<Integer> kmp(char[] text, char[] pattern, int[] p)
	{
		int j = 0;

		ArrayList<Integer> position = new ArrayList<>();

		for(int i = 0;i < text.length;i++)
		{
			while(j > 0 && text[i] != pattern[j])
				j = p[j-1];

			if(text[i] == pattern[j])
			{
				if(j == pattern.length - 1)
				{
					position.add(i - pattern.length + 2);
					j = p[j];
				}
				else
					j++;
			}
		}

		return position;
	}


	public static void main(String[] args) {

		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

			String text = br.readLine();
			String pattern = br.readLine();

			int length = 0;

			while(text.length() != length)
			{
				length = text.length();

				text = text.replaceAll(pattern,"");
			}

			if(text.isEmpty())
				bw.write("FRULA");
			else
				bw.write(text);

			bw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}