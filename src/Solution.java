import java.util.Arrays;

class Solution {

	public String[] solution(String[] files) {
		String[] answer = {};

		String[][] parsed = new String[files.length][3];

		for(int i = 0;i < files.length;i++)
		{
			String name = files[i];

			parsed[i][0] = "";
			parsed[i][1] = "";
			parsed[i][2] = "";

			int index = 0;

			//head추출
			String head = "";

			while(!Character.isDigit(name.charAt(index)))
				head += name.charAt(index++);

			//number 추출
			String number = "";

			while(index < name.length() && Character.isDigit(name.charAt(index)))
				number += name.charAt(index++);


			//tail추출
			String tail = "";
			if(index < name.length())
				tail = name.substring(index);

			parsed[i][0] = head;
			parsed[i][1] = number;
			parsed[i][2] = tail;
		}

		Arrays.sort(parsed, (o1, o2) -> {

			int head = o1[0].compareToIgnoreCase(o2[0]);
			if(head == 0)
			{
				int number = Integer.parseInt(o1[1]) - Integer.parseInt(o2[1]);

				return number;
			}
			else
				return head;
		});

		answer = new String[files.length];

		for(int i = 0;i < files.length;i++)
			answer[i] = parsed[i][0] + parsed[i][1] + parsed[i][2];

		return answer;
	}
}