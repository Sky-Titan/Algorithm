import javafx.geometry.Pos;

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

			String[] operations = {"I 4", "I 3", "I 2", "I 1", "D 1", "D 1", "D -1", "D -1", "I 5", "I 6"};

			System.out.println(Arrays.toString(s.solution(operations)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


}