
import java.util.*;

public class Main {

	public static int solution(int[] citations) {
		int answer = 0;

		//quickSort(citations,0, citations.length-1);

		Arrays.sort(citations);

		for(int i = citations.length-1;i >= 0;i--)
		{
			if(citations.length - i >= citations[i])
				return citations.length - i;
		}

		return answer;
	}



	public static void main(String[] args) {
		int[] citations = {3, 0, 6, 1, 5};

		System.out.println(solution(citations));
	}


	
}