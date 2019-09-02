import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class Main {
	
	static long[] a;
	
	static int swap=0;
	
	static void mergeSort(int p,int r)
	{
		if(p<r)
		{
			int q= (p+r)/2;
			mergeSort(p, q);
			mergeSort(q+1, r);
			merge(p, q, r);
		}
	}
	static void merge(int p,int q,int r)
	{
		int i=p,j=q+1;
		int t=1;
		long[] temp = new long[a.length];
		
		while(i<=q && j<=r)
		{
			if(a[i] > a[j])
			{
				temp[t++] = a[j++];
				swap++;
			}
			else
				temp[t++] = a[i++];
		}
		while(i<=q)
			temp[t++] = a[i++];
		while(j<=r)
			temp[t++] = a[j++];
		
		i = p;
		t = 1;
		while(i<=r)
			a[i++]=temp[t++];
	}
	
	static void bj1517() throws Exception
	{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		int i=1;
		a= new long[n+1];
		String line = bf.readLine();
		StringTokenizer str = new StringTokenizer(line, " ");
		while(str.hasMoreTokens())
		{
			a[i] = Integer.parseInt(str.nextToken());
			i++;
		}
		mergeSort(1, a.length-1);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		if(swap>0)
			swap++;
		bw.write(swap+"\n");
		bw.flush();
		bw.close();
	}
	
	
	
	public static void main(String[] args) {
		
		DynamicProgramming dp = new DynamicProgramming();
		dp.stoneTable();
		
	}
	
}