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

			//KMP
			char[] text = br.readLine().toCharArray();
			char[] pattern = br.readLine().toCharArray();

			int p[] = new int[pattern.length];

			failureFunction(p, pattern);
			ArrayList<Integer> position = kmp(text, pattern, p);

			bw.write(position.size()+"\n");

			for(int a : position)
				bw.write(a+" ");

			bw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}