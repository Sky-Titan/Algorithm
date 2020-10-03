import org.junit.Test;

import java.util.Arrays;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {


    @Test
    public void test(){
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] b = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(Arrays.hashCode(a), Arrays.hashCode(b));
    }
}