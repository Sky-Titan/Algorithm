
import java.util.*;

public class Main {

	public static int[][] solution(int n, int[][] build_frame) {
		int[][] answer = {};

		HashMap<String, Structure> map = new HashMap<>(); //key : "xya"

		for(int i = 0;i < build_frame.length;i++)
		{
			int x = build_frame[i][0]; //가로좌표
			int y = build_frame[i][1]; //세로좌표
			int a = build_frame[i][2]; //구조물 0 :기둥, 1 : 보
			int b = build_frame[i][3]; //설치여부 0 : 삭제, 1 : 설치

			//기둥
			if(a == 0)
			{
				Structure pillar = new Structure(x,y,a);
				//삭제
				if(b == 0)
				{
					deletePillar(pillar, map);
				}
				else//설치
				{
					installPillar(pillar, map);
				}
			}
			else//보
			{
				Structure beem = new Structure(x,y,a);
				//삭제
				if(b == 0)
				{
					deleteBeem(beem, map);
				}
				else//설치
				{
					installBeem(beem, map);
				}
			}

		}

		Iterator<String> iterator = map.keySet().iterator();
		answer = new int[map.size()][3];
		int i = 0;

		while(iterator.hasNext())
		{
			Structure structure = map.get(iterator.next());

			answer[i][0] = structure.x;
			answer[i][1] = structure.y;
			answer[i][2] = structure.a;
			i++;
		}

		mergerSort(answer, 0, answer.length-1);

		return answer;
	}

	static void mergerSort(int answer[][], int left, int right)
	{
		if(left < right)
		{
			int mid = (left + right) / 2;
			mergerSort(answer, left, mid);
			mergerSort(answer, mid+1, right);
			merge(answer, left, mid, right);
		}
	}

	static void merge(int answer[][], int left, int mid, int right)
	{
		int i = left;
		int j = mid + 1;
		int k = 0;
		int temp[][] = new int[right - left + 1][3];

		while(i <= mid && j <= right)
		{
			//x좌표 기준 오름차순 정렬
			if(answer[i][0] < answer[j][0])
			{
				temp[k][0] = answer[i][0];
				temp[k][1] = answer[i][1];
				temp[k][2] = answer[i][2];
				k++;
				i++;
			}
			else if(answer[i][0] > answer[j][0])
			{
				temp[k][0] = answer[j][0];
				temp[k][1] = answer[j][1];
				temp[k][2] = answer[j][2];
				k++;
				j++;
			}
			else if(answer[i][0] == answer[j][0])
			{
				//y좌표 기준 오름차순 정렬
				if(answer[i][1] < answer[j][1])
				{
					temp[k][0] = answer[i][0];
					temp[k][1] = answer[i][1];
					temp[k][2] = answer[i][2];
					k++;
					i++;
				}
				else if(answer[i][1] > answer[j][1])
				{
					temp[k][0] = answer[j][0];
					temp[k][1] = answer[j][1];
					temp[k][2] = answer[j][2];
					k++;
					j++;
				}
				else if(answer[i][1] == answer[j][1])
				{
					//기둥이 보보다 앞에
					if(answer[i][2] == 0)
					{
						temp[k][0] = answer[i][0];
						temp[k][1] = answer[i][1];
						temp[k][2] = answer[i][2];
						k++;
						i++;
					}
					else
					{
						temp[k][0] = answer[j][0];
						temp[k][1] = answer[j][1];
						temp[k][2] = answer[j][2];
						k++;
						j++;
					}
				}
			}
		}

		while(i <= mid)
		{
			temp[k][0] = answer[i][0];
			temp[k][1] = answer[i][1];
			temp[k][2] = answer[i][2];
			k++;
			i++;
		}

		while(j <= right)
		{
			temp[k][0] = answer[j][0];
			temp[k][1] = answer[j][1];
			temp[k][2] = answer[j][2];
			k++;
			j++;
		}

		i = left;
		for(k=0;k < temp.length;k++)
		{
			answer[i][0] = temp[k][0];
			answer[i][1] = temp[k][1];
			answer[i][2] = temp[k][2];
			i++;
		}
	}


	//기둥 설치
	static void installPillar(Structure pillar, HashMap<String, Structure> map)
	{
		String key = String.valueOf(pillar.x)+ String.valueOf(pillar.y)+ String.valueOf(pillar.a)+"";
		String x = String.valueOf(pillar.x);
		String y = String.valueOf(pillar.y);
		String a = String.valueOf(pillar.a);
		//바닥 위에 있는지
		if(pillar.y==0)
		{
			map.put(key, pillar);
			return;
		}

		//보의 한쪽 끝에 있는지
		if(map.containsKey( String.valueOf(pillar.x-1)+ String.valueOf(pillar.y)+"1")
				|| map.containsKey(String.valueOf(pillar.x) + String.valueOf(pillar.y) + "1") )
		{
			pillar.isBottomOnBeem = true;
			map.put(key, pillar);
			return;
		}

		//다른 기둥 위인지
		if (map.containsKey( String.valueOf(pillar.x) + String.valueOf(pillar.y - 1) +"0"))
		{
			pillar.isBottomOnPillar = true;
			map.put(key, pillar);
			return;
		}
	}

	//보 설치
	static void installBeem(Structure beem, HashMap<String, Structure> map)
	{
		String key = String.valueOf(beem.x)+ String.valueOf(beem.y)+ String.valueOf(beem.a) +"";

		//한쪽끝이 기둥위에 있는지
		if(map.containsKey( String.valueOf(beem.x) + String.valueOf(beem.y - 1)+"0") )
		{
			beem.isLeftOnPillar = true;
			map.put(key, beem);
			return;
		}
		if(map.containsKey( String.valueOf(beem.x+1) + String.valueOf(beem.y - 1)+"0") )
		{
			beem.isRightOnPillar = true;
			map.put(key, beem);
			return;
		}

		//양쪽 끝부분이 다른 보와 연결
		if(map.containsKey( String.valueOf(beem.x-1) + String.valueOf(beem.y) + "1")
				&& map.containsKey( String.valueOf(beem.x+1) + String.valueOf(beem.y) + "1" ))
		{
			beem.isBothConnectBeem = true;
			map.put(key, beem);
			return;
		}
	}

	//기둥 삭제
	static void deletePillar(Structure pillar, HashMap<String, Structure> map)
	{
		String key = String.valueOf(pillar.x)+ String.valueOf(pillar.y)+ String.valueOf(pillar.a) +"";

		//삭제할 기둥 위에 기둥 있는지
		if(map.containsKey( String.valueOf(pillar.x) + String.valueOf(pillar.y + 1) + "0"))
			return;

		//양쪽에 있는 보들이 조건 만족하는지 검사
		//오른쪽 보만 있음
		if(!map.containsKey( String.valueOf(pillar.x-1) + String.valueOf(pillar.y+1) + "1")
			&& map.containsKey( String.valueOf(pillar.x) + String.valueOf(pillar.y+1) + "1"))
			return;
		//왼쪽보만 있음
		if(map.containsKey( String.valueOf(pillar.x-1) + String.valueOf(pillar.y+1) + "1")
				&& !map.containsKey( String.valueOf(pillar.x) + String.valueOf(pillar.y+1) + "1"))
			return;

		map.remove(key);
	}

	//보 삭제
	static void deleteBeem(Structure beem, HashMap<String, Structure> map)
	{
		String key = String.valueOf(beem.x)+ String.valueOf(beem.y)+ String.valueOf(beem.a) +"";

		//양쪽 보들이 기둥에 연결되어 있는지
		// 왼쪽 보
		if(map.containsKey( String.valueOf(beem.x-1) + String.valueOf(beem.y) + "1" ))
		{
			Structure leftBeem = map.get( String.valueOf(beem.x-1) + String.valueOf(beem.y) + "1");

			if(!leftBeem.isLeftOnPillar)
				return;
		}
		//오른쪽 보
		if(map.containsKey( String.valueOf(beem.x+1) + String.valueOf(beem.y) + "1"))
		{
			Structure rightBeem = map.get( String.valueOf(beem.x+1) + String.valueOf(beem.y) + "1");

			if(!rightBeem.isRightOnPillar)
				return;
		}

		// 본인 양쪽 끝 위에 기둥이 있는지
		//왼쪽
		if(map.containsKey( String.valueOf(beem.x) + String.valueOf(beem.y) + "0" ))
			return;
		//오른쪽
		if(map.containsKey( String.valueOf(beem.x + 1) + String.valueOf(beem.y) +"0" ))
			return;

		map.remove(key);
	}

	static class Structure{
		int a; //기둥이면 0, 보면 1
		int x, y;

		//보용
		boolean isBothConnectBeem = false;

		boolean isLeftOnPillar = false;
		boolean isRightOnPillar = false;

		//기둥용
		boolean isBottomOnPillar = false;
		boolean isBottomOnBeem = false;

		public Structure()
		{

		}

		public Structure(int x, int y, int a)
		{
			this.x = x;
			this.y = y;
			this.a = a;
		}


	}

	public static void main(String[] args) {

		int n = 5;
		int[][] build_frame = {
				{0,0,0,1},
				{2,0,0,1},
				{4,0,0,1},
				{0,1,1,1},
				{1,1,1,1},
				{2,1,1,1},
				{3,1,1,1},
				{2,0,0,0},
				{1,1,1,0},
				{2,2,0,1}
		};

		int[][] result = solution(n ,build_frame);

		for(int i =0;i< result.length;i++) {
			for (int j = 0; j < result[i].length; j++)
				System.out.print(result[i][j]);
			System.out.println();
		}
	}
}