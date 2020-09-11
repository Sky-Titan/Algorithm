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

			String[] files = {
					"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"
			};
			System.out.println(Arrays.toString(s.solution(files)));

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


}