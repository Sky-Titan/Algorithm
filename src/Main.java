import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.BufferedReader;
import java.io.InputStreamReader;


import java.lang.reflect.Array;
import java.util.*;

public class Main {

	public static int[] solution(int[] prices) {
		int[] answer = new int[prices.length];

		Queue<Integer> queue = new LinkedList<>();
		
		for(int i=0;i< prices.length;i++)
		{
			int now = prices[i];

			if(!queue.isEmpty())
			{
				int size = queue.size();

				for(int j=0;j<size;j++)
				{
					int index = queue.poll();

					answer[index]++;

					//주식 가격이 떨어지는 시점이 오면 큐에 더이상 넣지 않는다.
					if(prices[index] <= now)
						queue.offer(index);
				}
			}
			queue.offer(i);
		}

		return answer;
	}



	public static void main(String[] args) {
		int[] prices={1,2,3,2,3};

		solution(prices);
	}

	static void printForArray(Object[] o)
	{
		for(int i=0;i<o.length;i++)
			System.out.println(o[i]);
	}
	
}