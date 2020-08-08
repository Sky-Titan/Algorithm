
import java.util.*;
public class Solution {

	private static int N;
	private static HashMap<String, Structure> map;

	public static int[][] solution(int n, int[][] build_frame) {
		int[][] answer = {};

		N = n;
		map = new HashMap<>(); //key : "xya"

		for(int[] frame : build_frame)
		{
			int x = frame[0]; //가로좌표
			int y = frame[1]; //세로좌표
			int a = frame[2]; //구조물 0 :기둥, 1 : 보
			int b = frame[3]; //설치여부 0 : 삭제, 1 : 설치

			//기둥
			if(a == 0)
			{
				Structure pillar = new Structure(x,y,a);
				//삭제
				if(b == 0)
				{
					deletePillar(pillar);
				}
				else//설치
				{
					installPillar(pillar);
				}
			}
			else//보
			{
				Structure beam = new Structure(x,y,a);
				//삭제
				if(b == 0)
				{
					deleteBeam(beam);
				}
				else//설치
				{
					installBeam(beam);
				}
			}

		}

		Iterator<Structure> iterator = map.values().iterator();
		answer = new int[map.values().size()][3];
		int i = 0;

		while(iterator.hasNext())
		{
			Structure structure = iterator.next();

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
					if(answer[i][2] < answer[j][2])
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
	static void installPillar(Structure pillar)
	{
		String key = String.valueOf(pillar.x)+ String.valueOf(pillar.y)+ String.valueOf(pillar.a);

		if(canInstallPillar(pillar, map))
			map.putIfAbsent(key, pillar);

		return;
	}

	//보 설치
	static void installBeam(Structure beam)
	{
		String key = String.valueOf(beam.x)+ String.valueOf(beam.y)+ String.valueOf(beam.a);

		if(canInstallBeam(beam, map))
			map.putIfAbsent(key, beam);

		return;
	}

	static boolean canInstallPillar(Structure pillar, HashMap<String, Structure> map)
	{
		//바닥 위에 있는지, 보 위에 있는지, 기둥 위에 있는지
		if(pillar.y==0)
			return true;
		if(0 < pillar.x)
		{
			if (isPillarOnLeftBeam(pillar))
				return true;
		}

		if(pillar.x < N)
		{
			if(isPillarOnRightBeam(pillar))
				return true;
		}

		if(isPillarOnPillar(pillar))
			return true;

		return false;
	}

	static boolean canInstallBeam(Structure beam, HashMap<String, Structure> map)
	{
		//한쪽끝이 기둥위에 있는지, 양쪽이 동시에 보와 연결됏는지
		if(isBeamOnLeftPillar(beam))
			return true;
		if(isBeamOnRightPillar(beam))
			return true;

		if(0 < beam.x && beam.x < N)
		{
			if(isBeamConnectLeftBeam(beam) && isBeamConnectRightBeam(beam))
				return true;
		}

		return false;
	}

	//기둥 삭제
	static void deletePillar(Structure pillar)
	{
		String key = String.valueOf(pillar.x)+ String.valueOf(pillar.y)+ String.valueOf(pillar.a) ;

		map.remove(key);


		Iterator<Structure> iterator = map.values().iterator();

		while(iterator.hasNext())
		{
			Structure structure = iterator.next();
			if(structure.a==0)//기둥
			{
				if(!canInstallPillar(structure, map))
				{
					map.putIfAbsent(key, pillar);
					return;
				}
			}
			else//보
			{
				if(!canInstallBeam(structure, map))
				{
					map.putIfAbsent(key, pillar);
					return;
				}
			}
		}
	}

	//보 삭제
	static void deleteBeam(Structure beam)
	{
		String key = String.valueOf(beam.x)+ String.valueOf(beam.y)+ String.valueOf(beam.a);
		map.remove(key);


		Iterator<Structure> iterator = map.values().iterator();

		while(iterator.hasNext())
		{
			Structure structure = iterator.next();
			if(structure.a==0)//기둥
			{
				if(!canInstallPillar(structure, map))
				{
					map.putIfAbsent(key, beam);
					return;
				}
			}
			else//보
			{
				if(!canInstallBeam(structure, map))
				{
					map.putIfAbsent(key, beam);
					return;
				}
			}
		}
	}


	//기둥 왼쪽아래에 보 존재 여부
	static boolean isPillarOnLeftBeam(Structure pillar)
	{
		return map.containsKey(String.valueOf(pillar.x - 1) + String.valueOf(pillar.y) + "1");
	}
	//기둥 오른쪽아래에 보 존재 여부
	static boolean isPillarOnRightBeam(Structure pillar)
	{
		return map.containsKey(String.valueOf(pillar.x) + String.valueOf(pillar.y) + "1");
	}


	//기둥 밑에 기둥 존재 여부
	static boolean isPillarOnPillar(Structure pillar)
	{
		return map.containsKey(String.valueOf(pillar.x) + String.valueOf(pillar.y - 1) + "0");
	}

	//보 왼쪽 보 존재 여부
	static boolean isBeamConnectLeftBeam(Structure beam)
	{
		return map.containsKey(String.valueOf(beam.x - 1) + String.valueOf(beam.y) + "1");
	}

	//보 오른쪽 보 존재 여부
	static boolean isBeamConnectRightBeam(Structure beam)
	{
		return map.containsKey(String.valueOf(beam.x + 1) + String.valueOf(beam.y) + "1");
	}

	//보 왼쪽 아래 기둥 존재 여부
	static boolean isBeamOnLeftPillar(Structure beam)
	{
		return map.containsKey(String.valueOf(beam.x) + String.valueOf(beam.y-1) + "0");
	}
	//보 오른쪽 아래 기둥 존재 여부
	static boolean isBeamOnRightPillar(Structure beam)
	{
		return map.containsKey(String.valueOf(beam.x+1) + String.valueOf(beam.y-1) + "0");
	}


	static class Structure{
		int a; //기둥이면 0, 보면 1
		int x, y;


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
}
