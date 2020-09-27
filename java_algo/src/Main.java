import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


	public static void solution() throws Exception
	{
		

	}

	static class Country{
		int x, y;

		public Country(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}




	public static void main(String[] args) {
		try
		{
			solution();

			bw.close();
			br.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}