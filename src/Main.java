import sun.nio.cs.ext.MacHebrew;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {


	public static void main(String[] args) {
		try
		{
			Solution s = new Solution();

			String str1 = "FRANCE";
			String str2 = "french";
			System.out.println(s.solution(str1, str2));

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


}