
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

			String binary = br.readLine();

			long multi = 1;

			long result = 0;

			long subtract = 0;

			long count = 0;
			for(int i = binary.length()-1; i >= 0; i--)
			{
				int num = binary.charAt(i) - 48;

				if(count != 0 && count % 3 == 0)
				{
					multi *= 10;
					subtract += 3;
				}

				if(num == 1)
					result += Math.pow(2, count - subtract) * multi;
				count++;
			}

			System.out.println(result);


			bw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}