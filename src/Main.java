import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	static void solution() throws  Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		int list[] = new int[N];

		StringTokenizer strtok = new StringTokenizer(br.readLine());

		for(int i = 0;i < N;i++)
			list[i] = Integer.parseInt(strtok.nextToken());

		Arrays.sort(list);

		int M = Integer.parseInt(br.readLine());

		strtok = new StringTokenizer(br.readLine());
		for(int i = 0;i < M;i++)
		{
			int a = Integer.parseInt(strtok.nextToken());

			if(binarySearch(list,a))
				bw.write("1\n");
			else
				bw.write("0\n");
		}

		bw.close();
	}

	//list 안에 a가 존재하는지 확인
	static boolean binarySearch(int[] list, int a)
	{
		//나올 수 있는 최소 index
		int left = 0;

		//나올 수 있는 최대 index
		int right = list.length-1;

		while(left <= right)
		{
			int mid = (left + right) / 2;

			if(list[mid] == a)
				return true;
			else if(list[mid] > a)
				right = mid - 1;
			else
				left = mid + 1;
		}

		return false;
	}



	public static void main(String[] args) {

		try
		{
			solution();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


}