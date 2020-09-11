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

			int n = 2, t = 1,  m = 2, p = 1;

			System.out.println(s.solution(n, t, m, p));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


}