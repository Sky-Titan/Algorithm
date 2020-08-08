
import java.util.*;
public class Solution {

	private static final int PILLAR = 0;//기둥
	private static final int BEAM = 1;//보

	private static final int CONSTRUCT = 1;//설치
	private static final int DECONSTRUCT = 0;//삭제

	static boolean map[][][];

	private static int N;

	public static int[][] solution(int n, int[][] build_frame) {
		int[][] answer = {};

		N = n;
		map = new boolean[n+1][n+1][2];

		for(int[] frame : build_frame)
		{
			int x = frame[0];
			int y = frame[1];
			int a = frame[2];
			int b = frame[3];

			Structure structure = new Structure(x,y,a);

			//설치
			if(b == CONSTRUCT)
				construct(structure);
			//삭제
			else
				deConstruct(structure);
		}
		ArrayList<Structure> list = new ArrayList<>();

		for(int i = 0;i<=N;i++)
		{
			for(int j =0;j<=N;j++)
			{
				if(map[i][j][PILLAR])
					list.add(new Structure(i,j,PILLAR));
				if(map[i][j][BEAM])
					list.add(new Structure(i,j,BEAM));
			}
		}

		answer = new int[list.size()][3];

		for(int i = 0;i<list.size();i++)
		{
			Structure structure = list.get(i);

			answer[i][0] = structure.x;
			answer[i][1] = structure.y;
			answer[i][2] = structure.a;
		}

		return answer;
	}

	private static void construct(Structure structure)
	{
		if(structure.a == PILLAR)
		{
			if(canConstructPillar(structure))
				map[structure.x][structure.y][structure.a] = true;
		}
		else
		{
			if(canConstructBeam(structure))
				map[structure.x][structure.y][structure.a] = true;
		}
	}

	//삭제
	private static void deConstruct(Structure structure)
	{
		int x = structure.x;
		int y = structure.y;
		int a = structure.a;

		//일단 삭제
		map[x][y][a] = false;

		for(int i = 0;i<=N;i++)
		{
			for(int j = 0;j<=N;j++)
			{
				//기둥 검사
				if(map[i][j][PILLAR])
				{
					Structure pillar = new Structure(i,j,PILLAR);

					if(!canConstructPillar(pillar))
					{
						map[x][y][a] = true;
						return;
					}
				}

				//보 검사
				if(map[i][j][BEAM])
				{
					Structure beam = new Structure(i,j,BEAM);

					if(!canConstructBeam(beam))
					{
						map[x][y][a] = true;
						return;
					}
				}

			}
		}
	}

	private static boolean canConstructPillar(Structure pillar)
	{
		int x = pillar.x;
		int y = pillar.y;
		int a = pillar.a;
		//바닥 위에 있는지
		if(y==0)
			return true;

		//기둥 위에 있는지
		if(map[x][y-1][PILLAR])
			return true;

		//오른쪽 보 위에 있는지
		if(map[x][y][BEAM])
			return true;

		//왼쪽 보 위에 있는지
		if(x > 0 && map[x-1][y][BEAM])
			return true;
		return false;
	}

	private static boolean canConstructBeam(Structure beam)
	{
		int x = beam.x;
		int y = beam.y;
		int a = beam.a;

		//왼쪽 끝이 기둥 위에 있는지
		if(map[x][y-1][PILLAR])
			return true;

		//오른쪽 끝이 기둥 위에 있는지
		if(map[x+1][y-1][PILLAR])
			return true;

		//양쪽 끝이 동시에 보와 연결되었는지
		if(0 < x && x < N)
		{
			if(map[x-1][y][BEAM] && map[x+1][y][BEAM])
				return true;
		}

		return false;
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
