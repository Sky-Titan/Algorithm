import javafx.geometry.Pos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	public static int solution(String s)
	{
		boolean[][] isPenlin = new boolean[s.length()][s.length()];

		for(int i = 0;i < s.length();i++)
			isPenlin[i][i] = true;

		int i = 0;
		int j = 0;
		int k = 0;
		int max = 0;


		while(k < s.length())
		{
			i = k - 1;

			while(i >= 0)
			{
				if(i + 1 <= k - 1)
				{
					if(isPenlin[i + 1][k - 1] && s.charAt(i) == s.charAt(k))
					{
						isPenlin[i][k] = true;
						//max = Math.max(k - i + 1, max);
					}
				}
				else
				{
					if(s.charAt(i) == s.charAt(k))
					{
						isPenlin[i][k] = true;
						//max = Math.max(k - i + 1, max);
					}
				}
				i--;
			}


			j = k + 1;

			while(j < s.length())
			{
				if(k + 1 <= j - 1)
				{
					if(isPenlin[k + 1][j - 1] && s.charAt(k) == s.charAt(j))
					{
						isPenlin[k][j] = true;
						//max = Math.max(j - k + 1, max);
					}
				}
				else
				{
					if(s.charAt(k) == s.charAt(j))
					{
						isPenlin[k][j] = true;
						//max = Math.max(j - k + 1, max);
					}
				}
				j++;
			}

			if(k > 0 && k < s.length() - 1)
			{
				i = k - 1;
				j = k + 1;

				while(i >= 0 || j < s.length())
				{
					if(isPenlin[i + 1][j - 1] && s.charAt(i) == s.charAt(j))
					{
						isPenlin[i][j] = true;
						//max = Math.max(j - i + 1, max);
					}

					if(i == 0 && j  == s.length() - 1)
						break;

					if(i > 0)
						i--;
					if(j < s.length() - 1)
						j++;
				}
			}
			k++;
		}

		for(i = 0;i < s.length();i++)
			for(j = 0;j < s.length();j++)
			{
				if(isPenlin[i][j])
					max = Math.max(max, j - i + 1);
			}

		return max;
	}


	public static void main(String[] args) {
		try
		{

			System.out.println(solution("a"));

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


}