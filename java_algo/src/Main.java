import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int N, K;
	static String[] words;
	static int max = 0;

	public static void solution() throws Exception
	{
		StringTokenizer strtok = new StringTokenizer(br.readLine());
		N = Integer.parseInt(strtok.nextToken());
		K = Integer.parseInt(strtok.nextToken());

		words = new String[N];

		for(int i = 0;i < N;i++)
		{
			words[i] = br.readLine();
		}

		//5글자보다 작으면 a c t n i도 못읽음
		if(K < 5)
		{
			bw.write("0");
			return;
		}

		//5글자 이상 가르칠 수 있다면 무조건 a c t n i부터 가르침
		int result = 0;
		result |= (1 << ('a'- 'a'));
		result |= (1 << ('c'- 'a'));
		result |= (1 << ('t'- 'a'));
		result |= (1 << ('n'- 'a'));
		result |= (1 << ('i'- 'a'));

		backTracking(0, 5, result);
		bw.write(max+"");
	}

	static void backTracking(int index, int depth, int result)
	{
		if(depth == K)
		{
			//읽을 수 없는 단어가 나올 때마다 1개씩 깎음
			int count = N;

			for(int i = 0;i < N;i++)
			{
				String word = words[i];

				for(int j = 0;j < word.length();j++)
				{
					//가르친 단어 x
					if(!isCharacterIn(word.charAt(j), result))
					{
						count--;
						break;
					}
				}
			}

			max = Math.max(count, max);
		}
		else
		{
			for(int i = index;i < 26;i++)
			{
				if(!isCharacterIn((char)(i+'a'),result))
				{
					result |= (1 << i);
					backTracking(i + 1, depth + 1, result);
					result &= ~(1 << i);
				}
			}
		}
	}

	//해당 글자를 가르쳤는지 확인
	static boolean isCharacterIn(char c, int result)
	{
		if((result & (1 << (c-'a'))) > 0)
			return true;
		return false;
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