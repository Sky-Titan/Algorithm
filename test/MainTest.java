import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    public String[] parsedList(String line)
    {
        String[] result = new String[7];

        String[] times = line.split(" ");
        String days = times[0];

        String[] days_list = days.split("-");
        System.arraycopy(days_list, 0, result,0, days_list.length);

        String end_time_str = times[1];
        String process_time_str = times[2];

        String end_time_list[] = end_time_str.split(":");
        System.arraycopy(end_time_list, 0, result, 3, end_time_list.length);
        result[6] = process_time_str.substring(0, process_time_str.length()-1);

        return result;
    }

    public String makeStart(String line)
    {
        String[] times = line.split(" ");
        String days = times[0];

        String[] days_list = days.split("-");
        int year = Integer.parseInt(days_list[0]);
        int month = Integer.parseInt(days_list[1]);
        int date = Integer.parseInt(days_list[2]);


        String end_time_str = times[1];
        String process_time_str = times[2];

        String end_time_list[] = end_time_str.split(":");
        int hour = Integer.parseInt(end_time_list[0]);
        int min = Integer.parseInt(end_time_list[1]);
        double second = Double.parseDouble(end_time_list[2]);

        double process_time = Double.parseDouble(process_time_str.substring(0, process_time_str.length()-1));

        second = second - process_time + 0.001;

        if(second < 0)
        {
            second += 60;
            min -= 1;
        }

        if(min < 0)
        {
            min += 60;
            hour -= 1;
        }

        if(hour < 0)
        {
            hour += 24;
            date -= 1;
        }

        return year+"-"+month+"-"+date+" "+hour+":"+min+":"+second+" "+process_time;
    }

    public Double makeDouble(Double origin)
    {
        return Math.floor(origin * 1000) / 1000;
    }
    @Test
    public void test(){

        assertEquals(2.003,(makeDouble(4.002) * 1000 - makeDouble(2.0) * 1000 + 1)/1000);
     //   String[] expected ={"2016", "09", "15", "03", "10", "33.020","0.011"};
      //  assertArrayEquals(expected, parsedList("2016-09-15 03:10:33.020 0.011s"));
      //  assertEquals("2016-9-15 3:10:33.01 0.011",makeStart("2016-09-15 03:10:33.020 0.011s"));

    }

}