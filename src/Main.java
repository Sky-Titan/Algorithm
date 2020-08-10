
import java.util.*;

public class Main {


	public static void main(String[] args) {

		int[][] board = {
				{0, 0, 0, 1, 1},
				{0, 0, 0, 1, 0},
				{0, 1, 0, 1, 1},
				{1, 1, 0, 0, 1},
				{0, 0, 0, 0, 0}
		};


		System.out.println(Solution.solution(board));
	}
}