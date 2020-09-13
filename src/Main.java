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

			int[] cards = {
					12, 7, 11, 6, 2, 12
			};
			System.out.println(s.solution(cards));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}