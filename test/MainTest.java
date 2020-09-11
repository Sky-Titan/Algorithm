import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    public String makingNumber(int number, int to)
    {
        //to : 진법
        StringBuilder answer = new StringBuilder();

        while(number >= to)
        {
            int n = number % to;

            if(n < 10)
                answer.insert(0, n);
            else
                answer.insert(0, (char)(n + 55));
            number /= to;
        }

        if(number < 10)
            answer.insert(0, number);
        else
            answer.insert(0, (char)(number + 55));

        return answer.toString();
    }
    @Test
    public void test(){

        assertEquals("8", makingNumber(8, 9));


    }

}