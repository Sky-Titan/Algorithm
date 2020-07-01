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
		
		if(isMeet[next])//cycle발견
		{
			cycle.add(now);
			isCycle = true;
			return next;
		}
		else
		{
			isMeet[next] = true;
			
			int cycle_origin = dfs(next,num ,mark);
			
			//cycle 형성완료
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
        
        Stack<String> str_stack = new Stack<>();//문자열 스택
        Stack<Integer> num_stack = new Stack<>();//반복횟수 스택
        Stack<Character> start_stack = new Stack<>();//괄호 스택
        
        for(int i=0;i<compressed.length();i++)
        {
        	char now = compressed.charAt(i);
        	
        	//숫자
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
        		
        		repeat_count_str ="";//초기화
        	}
        	else if(now == ')')
        	{
        		String str = "";
        		String str2 = "";
        		//start_stack.push(now);
        		start_stack.pop();//괄호 스택 하나 비우기
        		//반복문자 비어 있는 경우 -> 안에 pop 해서 반복 후 삽입
        		if(repeat_str.equals(""))
        		{
        			repeat_str = str_stack.pop();
        			repeat_count = num_stack.pop();
        		}
        		else //비어있지 않은 경우 -> 현재 repeat_str 반복 후 연결 후  삽입
        		{
        			if(!str_stack.isEmpty() && !start_stack.isEmpty())
            			str = str_stack.pop();
            		
            		repeat_count = num_stack.pop();//반복횟수 pop
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
        		//괄호 스택이 비어있으면 바로 삽입
        		if(!isRepeat)
        		{
        			String str ="";
        			if(!str_stack.isEmpty())
        				str = str_stack.pop();
        			str += now;
        			str_stack.push(str);
        		}
        		else //안 비어있으면 반복 문자에 삽입
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
        
        //왼쪽으로 빠져나올 수 있는 경우
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
