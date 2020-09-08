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

			int[][] nodeinfo = {
					{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}
			};

			int[][] result = s.solution(nodeinfo);

			System.out.println(Arrays.toString(result[0]));
			System.out.println(Arrays.toString(result[1]));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


}