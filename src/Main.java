
import java.util.*;

public class Main {


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

		int[][] result = Solution.solution(n ,build_frame);

		for(int i =0;i< result.length;i++) {
			for (int j = 0; j < result[i].length; j++)
				System.out.print(result[i][j]);
			System.out.println();
		}
	}
}