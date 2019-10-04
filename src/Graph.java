import java.util.ArrayList;

public class Graph {

	int[][] graph = {
   //0,1,2,3,4,5,6,7,8,9,10,11,12,13
	{0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0},//0
	{0,0,1,1,0,0,0,0,0,0, 0, 0, 0, 0},//1
	{0,1,0,0,1,1,1,0,0,0, 0, 0, 0, 0},//2
	{0,1,0,0,0,0,0,1,1,0, 0, 0, 0, 0},//3
	{0,0,1,0,0,0,0,0,0,1, 0, 0, 0, 0},//4
	{0,0,1,0,0,0,0,0,0,0, 0, 0, 0, 0},//5
	{0,0,1,0,0,0,0,0,0,0, 1, 1, 0, 0},//6
	{0,0,0,1,0,0,0,0,0,0, 0, 0, 0, 0},//7
	{0,0,0,1,0,0,0,0,0,0, 0, 0, 1, 1},//8
	{0,0,0,0,1,0,0,0,0,0, 0, 0, 0, 0},//9
	{0,0,0,0,0,0,1,0,0,0, 0, 0, 0, 0},//10
	{0,0,0,0,0,0,1,0,0,0, 0, 0, 0, 0},//11
	{0,0,0,0,0,0,0,0,1,0, 0, 0, 0, 0},//12
	{0,0,0,0,0,0,0,0,1,0, 0, 0, 0, 0}//13
	};
	int[][] graph2 = {
	//0,1,2,3,4,5,6,7,8,9,10,11,12,13
	 {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0},//0
	 {0,0,1,0,0,0,0,0,0,1, 0, 0, 0, 0},//1
	 {0,1,0,1,0,1,1,0,0,0, 0, 0, 0, 0},//2
	 {0,0,1,0,1,0,0,0,0,0, 0, 0, 0, 0},//3
	 {0,0,0,1,0,0,0,0,0,0, 0, 0, 0, 0},//4
	 {0,0,1,0,0,0,0,0,0,0, 0, 0, 0, 0},//5
	 {0,0,1,0,0,0,0,1,1,0, 0, 0, 0, 0},//6
	 {0,0,0,0,0,0,1,0,0,0, 0, 0, 0, 0},//7
	 {0,0,0,0,0,0,1,0,0,0, 0, 0, 0, 0},//8
	 {0,1,0,0,0,0,0,0,0,0, 1, 1, 0, 0},//9
	 {0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 0},//10
	 {0,0,0,0,0,0,0,0,0,1, 0, 0, 1, 1},//11
	 {0,0,0,0,0,0,0,0,0,0, 0, 1, 0, 0},//12
	 {0,0,0,0,0,0,0,0,0,0, 0, 1, 0, 0}//13
				};
	boolean[] visited = new boolean[14];
	ArrayList<Vertex> vertices = new ArrayList<>();
	int sPos=0, qPosStart=0, qPosEnd = 0;
	int[] stack = new int[100];
	int[] queue = new int[100];
	
	public Graph()
	{
		for(int i=0;i<visited.length;i++)
			visited[i] = false;
	}
	public void Stack(int value)
	{
		stack[sPos++] = value;
	}
	public int Pop()
	{
		return stack[--sPos];
	}
	public void Enqueue(int value)
	{
		queue[qPosEnd++] = value;
	}
	public int Dequeue()
	{
		return queue[qPosStart++];
	}
	
	public void BFS()//³Êºñ ¿ì¼±Å½»ö
	{
		Enqueue(1);
		while(qPosEnd!=qPosStart)
		{
			int v = Dequeue();
			visited[v] = true;
			System.out.println(v);
			for(int i=1;i<graph[v].length;i++)
			{
				if(graph[v][i] > 0 && visited[i] == false)
				{	
					Enqueue(i);
				}
			}
		}
	}
	public void DFS()//±íÀÌ ¿ì¼± Å½»ö
	{
		Stack(1);
		while(sPos!=0)
		{
			int v = Pop();
			visited[v] = true;
			System.out.println(v);
			for(int i=graph2[v].length-1;i>0;i--)
			{
				if(graph2[v][i] > 0 && visited[i] == false)
				{
					Stack(i);
				}
			}
		}
	}
	
	static class Vertex{
		
		int value=0;
		ArrayList<Integer> child = new ArrayList<>();
		
		public Vertex(int value)
		{
			this.value = value;
		}
		public void addChild(int value)
		{
			child.add(value);
		}
		public int getChild(int index)
		{
			return child.get(index);
		}
		public int size()
		{
			return child.size();
		}
	}
}
