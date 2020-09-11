class Solution {

	public String solution(int n, int t, int m, int p) {

		//n : 진법 2~16, t : 구할 숫자 개수 , m : 참여 인원 , p : 내 순서
		StringBuilder stringBuilder = new StringBuilder();

		for(int i = 0;i < t*m;i++)
		{
			stringBuilder.append(makingNumber(i, n));
		}

		StringBuilder result = new StringBuilder();
		int count = 0;

		for(int i = 0;count < t;i++)
		{
			if(i % m == p - 1)
			{
				result.append(stringBuilder.charAt(i));
				count++;
			}
		}

		return result.toString();
	}

	public String makingNumber(int number, int to)
	{
		//to : 진법
		StringBuilder answer = new StringBuilder();

		while(number >= to)
		{
			int n = number % to;

			if(n < 10)
				answer.insert(0, n);
			else
				answer.insert(0, (char)(n + 55));
			number /= to;
		}

		if(number < 10)
			answer.insert(0, number);
		else
			answer.insert(0, (char)(number + 55));

		return answer.toString();
	}
}