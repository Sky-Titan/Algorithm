import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {


	public static void main(String[] args) {

		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

			String a = br.readLine();
			String b = br.readLine();

			if(a.length() >= b.length())
				System.out.println("go");
			else
				System.out.println("no");

			bw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}