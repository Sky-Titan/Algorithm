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

			String msg = "KAKAO";
			System.out.println(Arrays.toString(s.solution(msg)));

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


}