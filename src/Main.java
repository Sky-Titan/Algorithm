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

			String expression = "100-200*300-500+20";

			System.out.println(s.solution(expression));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


}