
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	static int S[], P[], card[];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void solution() throws Exception
	{
		int N = Integer.parseInt(br.readLine());

		S = new int[N];
		P = new int[N];
		card = new int[N];

		String[] list = br.readLine().split(" ");

		for(int i = 0;i < N;i++)
		{
			P[i] = Integer.parseInt(list[i]);
			card[i] = i % 3;
		}

		list = br.readLine().split(" ");

		for(int i = 0;i < N;i++)
			S[i] = Integer.parseInt(list[i]);

		int count = 0;

		while(!Arrays.equals(card, P))
		{
			int temp[] = card.clone();

			//i번째 위치의 카드를 S[i] 위치로
			for(int i = 0;i < N;i++)
				card[i] = temp[S[i]];

			//이미 존재하는 조합 -> 사이클 존재 -> 절대 못구함
			if(count > 120119)
			{
				bw.write("-1");
				return;
			}

			count++;
		}

		bw.write(count+"");
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