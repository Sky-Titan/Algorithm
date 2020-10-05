import org.junit.Test

import org.junit.Assert.*
import java.util.*


internal class MainKtTest{

    var N = 5
    @Test
    fun test(){
        var map = arrayOf(
                arrayOf(4, 4, 4, 4, 4),
                arrayOf(2, 2, 2, 2, 2),
                arrayOf(1, 1, 1, 1, 1),
                arrayOf(1, 1, 1, 1, 1),
                arrayOf(0, 0, 0, 0, 0))

        moveMap(UP, map)
        println(Arrays.deepToString(map))
    }
    fun moveMap(dir : Int, map : Array<Array<Int>>)
    {
        //상
        if(dir == UP)
        {
            for(j in 0 until N)
            {
                for(i in N - 1 downTo 1)
                {
                    if(map[i - 1][j] > 0)
                    {
                        //더블
                        if(map[i][j] == map[i - 1][j])
                        {
                            map[i - 1][j] *= 2
                            map[i][j] = 0
                        }
                    }
                    else
                    {
                        map[i - 1][j] = map[i][j]
                        map[i][j] = 0
                    }
                }
            }
        }
        else if(dir == DOWN)
        {

        }
        else if(dir == LEFT)
        {

        }
        else
        {

        }
    }

}


