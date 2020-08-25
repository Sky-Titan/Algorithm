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

			int N = Integer.parseInt(br.readLine());

			int list[] = new int[N+1];

			boolean isPalin[][] = new boolean[N+1][N+1];

			StringTokenizer strtok = new StringTokenizer(br.readLine());

			for(int i = 1;i < list.length; i++)
				list[i] = Integer.parseInt(strtok.nextToken());

			for(int k = 1;k <= N;k++)
			{
				int i = 0;

				while(1 <= k - i || k + i <= N)
				{
					int index1 = k - i;
					int index2 = k + i;

					//k 기준으로 좌측
					if(1 <= index1)
					{
						if(k - index1 < 2)
						{
							if(list[index1] == list[k])
								isPalin[index1][k] = true;
						}
						else
						{
							if(list[index1] == list[k] && isPalin[index1+1][k-1])
								isPalin[index1][k] = true;
						}
					}

					//k 기준 우측
					if(index2 <= N)
					{
						if(index2 - k < 2)
						{
							if(list[k] == list[index2])
								isPalin[k][index2] = true;
						}
						else
						{
							if(list[k] == list[index2] && isPalin[k+1][index2-1])
								isPalin[k][index2] = true;
						}
					}

					//k 기준 양측
					if(1 <= index1 && index2 <= N)
					{
						if(index2 - index1 < 2)
						{
							if(list[index1] == list[index2])
								isPalin[index1][index2] = true;
						}
						else
						{
							if(list[index1] == list[index2] && isPalin[index1+1][index2-1])
								isPalin[index1][index2] = true;
						}
					}

					i++;
				}
			}

			int M = Integer.parseInt(br.readLine());

			for(int i = 0;i<M;i++)
			{
				strtok = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(strtok.nextToken());
				int b = Integer.parseInt(strtok.nextToken());

				if(isPalin[a][b])
					bw.write("1\n");
				else
					bw.write("0\n");
			}

			bw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}