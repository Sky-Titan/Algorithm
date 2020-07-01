import java.awt.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.*;
public class Solution {
	
	 static boolean isFinish = false;
     static boolean isCycle = false;
     static boolean isMeet[];
     
     static int count = 0;
     static ArrayList<Integer> cycle = new ArrayList<>();
	static int solution(int num, int[] mark) {
	     int answer = -1;
	    
	     isMeet = new boolean[mark.length];
	     isMeet[0] = true;
	    
	     answer = dfs(0,num,mark);
	     
	    // for(int i=cycle.size()-1;i>=0;i--)
	  //  	 System.out.println(cycle.get(i));
	     
	     return answer;
	}
	
	static int dfs(int now ,int num ,int[] mark )
	{
		int next = mark[now];
		count++;
		
		if(count == num)
		{
			isFinish = true;
			return next;
		}
		
		if(isMeet[next])//cycle�߰�
		{
			cycle.add(now);
			isCycle = true;
			return next;
		}
		else
		{
			isMeet[next] = true;
			
			int cycle_origin = dfs(next,num ,mark);
			
			//cycle �����Ϸ�
			if(now == cycle_origin)
			{
				if(isCycle)
				{
					isFinish = true;
					cycle.add(now);
					
					//System.out.println(num-count[0]);
					int result_index = (num - count) % cycle.size();
					
					return cycle.get(cycle.size()-1-result_index);
				}
				else
					return cycle_origin;
			}
			else
			{
				if(!isFinish)
					cycle.add(now);
				return cycle_origin;
			}
		}
	}
	
	static String solution2(String compressed) {
        String answer = "";
        
        boolean isRepeat = false;
        String repeat_count_str="";
        int repeat_count=0;
        
        String repeat_str ="";
        
        Stack<String> str_stack = new Stack<>();//���ڿ� ����
        Stack<Integer> num_stack = new Stack<>();//�ݺ�Ƚ�� ����
        Stack<Character> start_stack = new Stack<>();//��ȣ ����
        
        for(int i=0;i<compressed.length();i++)
        {
        	char now = compressed.charAt(i);
        	
        	//����
        	if(Character.isDigit(now))
        	{
        		if(!isRepeat)
        			isRepeat = true;
        		
        		repeat_count_str += now;
        	}
        	else if(now == '(')
        	{
        		num_stack.push(Integer.parseInt(repeat_count_str));
        		start_stack.push(now);
        		
        		repeat_count_str ="";//�ʱ�ȭ
        	}
        	else if(now == ')')
        	{
        		String str = "";
        		String str2 = "";
        		//start_stack.push(now);
        		start_stack.pop();//��ȣ ���� �ϳ� ����
        		//�ݺ����� ��� �ִ� ��� -> �ȿ� pop �ؼ� �ݺ� �� ����
        		if(repeat_str.equals(""))
        		{
        			repeat_str = str_stack.pop();
        			repeat_count = num_stack.pop();
        		}
        		else //������� ���� ��� -> ���� repeat_str �ݺ� �� ���� ��  ����
        		{
        			if(!str_stack.isEmpty() && !start_stack.isEmpty())
            			str = str_stack.pop();
            		
            		repeat_count = num_stack.pop();//�ݺ�Ƚ�� pop
        		}
        		
        		for(int k=0;k<repeat_count;k++)
        		{
        			str += repeat_str;
        		}
    			
    			str_stack.push(str);
        		
        		
        		repeat_str = "";
        		isRepeat = false;
        	}
        	else
        	{
        		//��ȣ ������ ��������� �ٷ� ����
        		if(!isRepeat)
        		{
        			String str ="";
        			if(!str_stack.isEmpty())
        				str = str_stack.pop();
        			str += now;
        			str_stack.push(str);
        		}
        		else //�� ��������� �ݺ� ���ڿ� ����
        		{
        			repeat_str += now;
        		}
        	}
        }
        for(int i=0;i<str_stack.size();i++)
        	System.out.println(str_stack.get(i));
        //answer = str_stack.pop();
        return answer;
    }
	
	static int solution1(String p) {
        int answer = 0;
        
        //�������� �������� �� �ִ� ���
        for(int i=0;i<p.length();i++)
        {
        	int now = p.charAt(i);
        	
        	if(now == '<')
        	{
        		answer++;
        	}
        	else
        		break;
        		
        }
        
        for(int i=p.length()-1;i>=0;i--)
        {
        	int now = p.charAt(i);
        	
        	if(now == '>')
        	{
        		answer++;
        	}
        	else
        		break;
        }
        
        return answer;
    }
	 
	 
}
