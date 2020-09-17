import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {


    @Test
    public void test(){

        Main.Fish fish = new Main.Fish(0, 1, 2);
        Main.Fish fish1 = new Main.Fish(0, 1, 3);

        HashSet<Main.Fish> set = new HashSet<>();
        set.add(fish);
        System.out.println(set.contains(fish));

    }

}