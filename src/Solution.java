import java.util.*;


class Solution {

	static int[][] map;
	static boolean[][] visited;
	static int N;

	public static int solution(int[][] board) {
		int answer = 0;

		map = board;
		N = board.length;

		visited = new boolean[N][N];

		Queue<Robot> queue = new LinkedList<>();
		queue.offer(new Robot(0,0,0,1, 0));

		while (!queue.isEmpty())
		{
			Robot robot = queue.poll();
			int pos_1_x = robot.pos_1_x;
			int pos_1_y = robot.pos_1_y;
			int pos_2_x = robot.pos_2_x;
			int pos_2_y = robot.pos_2_y;
			int time = robot.time;

			if( (pos_1_x == N-1 && pos_1_y == N-1) || (pos_2_x == N-1 && pos_2_y == N-1) )
				return time;

			//상하좌우
			int x[] = {-1, 1, 0, 0};
			int y[] = {0, 0, -1, 1};

			//이동 케이스
			for(int i = 0; i < 4;i++)
			{
				int x_1 = pos_1_x + x[i];
				int y_1 = pos_1_y + y[i];
				int x_2 = pos_2_x + x[i];
				int y_2 = pos_2_y + y[i];

				if(0 <= x_1 && x_1 < N && 0 <= y_1 && y_1 < N
						&& 0 <= x_2 && x_2 < N && 0 <= y_2 && y_2 < N)
				{
					//벽 아닐 때만 방문
					if(map[x_1][y_1] == 0 && map[x_2][y_2] == 0)
					{
						if(!visited[x_1][y_1] || !visited[x_2][y_2])
						{
							visited[x_1][y_1] = true;
							visited[x_2][y_2] = true;

							queue.offer(new Robot(x_1, y_1, x_2, y_2,time+1));
						}
					}
				}
			}

			//회전 케이스
			//1 : 1번축 시계방향
			int axis_x = pos_1_x;//축 x
			int axis_y = pos_1_y;//축 y
			int move_x = pos_2_x;//움직이는 x
			int move_y = pos_2_y;//움직이는 y

			if(axis_x  == move_x) //가로 방향
			{
				//축이 더 뒤 열에 있는 경우
				if(axis_y < move_y)
				{
					//시계 방향
					int x2 = move_x + 1;
					int y2 = move_y - 1;

					if(0 <= x2 && x2 < N && 0 <= y2 && y2 < N)
					{
						//축 대각선 방향 빈 공간인지 확인
						if(map[axis_x+1][move_y] == 0)
						{
							if(map[x2][y2] == 0 &&!visited[x2][y2])
							{
								visited[x2][y2] = true;
								queue.offer(new Robot(axis_x, axis_y, x2,y2, time+1));
							}
						}
					}

					//반시계 방향
					x2 = move_x - 1;
					y2 = move_y - 1;

					if(0 <= x2 && x2 < N && 0 <= y2 && y2 < N)
					{
						//축 대각선 방향 빈 공간인지 확인
						if(map[axis_x-1][move_y] == 0)
						{
							if(map[x2][y2] == 0 &&!visited[x2][y2])
							{
								visited[x2][y2] = true;
								queue.offer(new Robot(axis_x, axis_y, x2,y2, time+1));
							}
						}
					}
				}
				//축이 더 앞열
				else
				{
					int x2 = move_x - 1;
					int y2 = move_y + 1;

					if(0 <= x2 && x2 < N && 0 <= y2 && y2 < N)
					{
						//축 대각선 방향 빈 공간인지 확인
						if(map[axis_x-1][move_y] == 0)
						{
							if(map[x2][y2] == 0 &&!visited[x2][y2])
							{
								visited[x2][y2] = true;
								queue.offer(new Robot(axis_x, axis_y, x2,y2, time+1));
							}
						}
					}
				}
			}
			//세로 방향
			else
			{
				//축이 위에 있음
				if(axis_x < move_x)
				{
					int x2 = move_x - 1;
					int y2 = move_y - 1;

					if(0 <= x2 && x2 < N && 0 <= y2 && y2 < N)
					{
						//축 대각선 방향 빈 공간인지 확인
						if(map[move_x][axis_y-1] == 0)
						{
							if(map[x2][y2] == 0 &&!visited[x2][y2])
							{
								visited[x2][y2] = true;
								queue.offer(new Robot(axis_x, axis_y, x2,y2, time+1));
							}
						}
					}
				}
				//축이 밑에 있음
				{
					int x2 = move_x + 1;
					int y2 = move_y + 1;

					if(0 <= x2 && x2 < N && 0 <= y2 && y2 < N)
					{
						//축 대각선 방향 빈 공간인지 확인
						if(map[move_x][axis_y+1] == 0)
						{
							if(map[x2][y2] == 0 &&!visited[x2][y2])
							{
								visited[x2][y2] = true;
								queue.offer(new Robot(axis_x, axis_y, x2,y2, time+1));
							}
						}
					}
				}
			}


		}


		return answer;
	}




	static class Robot{

		int pos_1_x, pos_1_y;
		int pos_2_x, pos_2_y;
		int time;

		public Robot()
		{

		}

		public Robot(int pos_1_x, int pos_1_y, int pos_2_x, int pos_2_y) {
			this.pos_1_x = pos_1_x;
			this.pos_1_y = pos_1_y;
			this.pos_2_x = pos_2_x;
			this.pos_2_y = pos_2_y;
		}

		public Robot(int pos_1_x, int pos_1_y, int pos_2_x, int pos_2_y, int time) {
			this.pos_1_x = pos_1_x;
			this.pos_1_y = pos_1_y;
			this.pos_2_x = pos_2_x;
			this.pos_2_y = pos_2_y;
			this.time = time;
		}
	}
}