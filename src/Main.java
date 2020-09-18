
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {


	static int map[][] = new int[101][101];

	public static void solution() throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		ArrayList[] dragonCurves = new ArrayList[N];

		for(int i = 0;i < N;i++)
		{
			dragonCurves[i] = new ArrayList<Position>();

			String[] list = br.readLine().split(" ");
			int x = Integer.parseInt(list[0]);
			int y = Integer.parseInt(list[1]);
			int d = Integer.parseInt(list[2]);
			int g = Integer.parseInt(list[3]);

			dragonCurves[i].add(new Position(x, y));
			makeDragonCurve(dragonCurves[i], d, g);
		}

		int count = 0;

		for(int i = 0;i < 100;i++)
		{
			for(int j = 0;j < 100;j++)
			{
				if(map[i][j] == 1 && map[i+1][j] == 1 && map[i][j+1] == 1 && map[i+1][j+1] == 1)
					count++;
			}
		}

		bw.write(count+"");
		bw.close();
		br.close();
	}

	public static void makeDragonCurve(ArrayList<Position> dragonCurve, int d, int g)
	{
		//0세대 드래곤 커브 만들기
		map[dragonCurve.get(0).x][dragonCurve.get(0).y] = 1;

		//x증가 방향 -> 남쪽
		if(d == 0)
		{
			map[dragonCurve.get(0).x + 1][dragonCurve.get(0).y] = 1;
			dragonCurve.add(new Position(dragonCurve.get(0).x + 1, dragonCurve.get(0).y));
		}
		//y감소 방향 -> 서쪽
		else if(d == 1)
		{
			map[dragonCurve.get(0).x][dragonCurve.get(0).y - 1] = 1;
			dragonCurve.add(new Position(dragonCurve.get(0).x, dragonCurve.get(0).y - 1));
		}
		//x감소 방향 -> 북쪽
		else if(d == 2)
		{
			map[dragonCurve.get(0).x - 1][dragonCurve.get(0).y] = 1;
			dragonCurve.add(new Position(dragonCurve.get(0).x - 1, dragonCurve.get(0).y));
		}
		//y증가 방향 -> 동쪽
		else
		{
			map[dragonCurve.get(0).x][dragonCurve.get(0).y + 1] = 1;
			dragonCurve.add(new Position(dragonCurve.get(0).x, dragonCurve.get(0).y + 1));
		}

		//g세대 만큼
		for(int i = 0;i < g;i++)
		{
			//기준점은 끝점 기준
			int size = dragonCurve.size();
			int ref_x = dragonCurve.get(size - 1).x;
			int ref_y = dragonCurve.get(size - 1).y;

			//회전하게 되면 첫 시작점의 회전점이 다음 끝점이 되어야함
			for(int j = size - 2;j >= 0;j--)
			{
				int turn_pos[] = turnPosition(ref_x, ref_y, dragonCurve.get(j).x, dragonCurve.get(j).y);
				map[turn_pos[0]][turn_pos[1]] = 1;
				dragonCurve.add(new Position(turn_pos[0], turn_pos[1]));
			}
		}

	}

	public static int[] turnPosition(int ref_x, int ref_y, int x, int y)
	{
		int turn_pos[] = new int[2];

		//주의 : 반시계 방향으로 회전 (이유 : 예시의 좌표계는 y가 가로줄 담당, x가 세로줄 담당 -> 여기서는 x가 가로줄 담당, y가 세로줄 담당이어서 거꾸로

		//+ + -> + -
		if(ref_x > x && ref_y > y)
		{
			turn_pos[0] = ref_x + Math.abs(ref_y - y);
			turn_pos[1] = ref_y - Math.abs(ref_x - x);
		}
		//+ - -> - -
		else if(ref_x > x && ref_y <= y)
		{
			turn_pos[0] = ref_x - Math.abs(ref_y - y);
			turn_pos[1] = ref_y - Math.abs(ref_x - x);

		}
		//- - -> - +
		else if(ref_x <= x && ref_y <= y)
		{
			turn_pos[0] = ref_x - Math.abs(ref_y - y);
			turn_pos[1] = ref_y + Math.abs(ref_x - x);
		}
		//- + -> + +
		else
		{
			turn_pos[0] = ref_x + Math.abs(ref_y - y);
			turn_pos[1] = ref_y + Math.abs(ref_x - x);
		}

		return turn_pos;
	}

	static class Position{
		int x, y;

		public Position(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		try
		{
			solution();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}