import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    public void test(){

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
        int[][] answer ={
                {0,0,0},
                {0,1,1},
                {1,1,1},
                {2,1,1},
                {3,1,1},
                {4,0,0}
        };


         Solution.solution(n, build_frame);


    }

}