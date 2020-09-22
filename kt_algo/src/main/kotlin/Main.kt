import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max

var N : Int = 0
lateinit var map : Array<CharArray>
var max = 0

fun solution(){

    N = readLine()!!.toInt()
    map = Array(N, { CharArray(N) })

    for(i in 0 until N)
    {
        var str = readLine()!!

        for(j in 0 until N)
            map[i][j] = str[j];
    }

    for(i in 0 until N)
    {
        for(j in 0 until N)
        {
            if(i < N - 1)
            {
                swap(i, j, i + 1, j)
                max = max(max, countContinuousRow(i))
                max = max(max,countContinuousRow(i + 1))
                max = max(max,countContinuousColumn(j))
                swap(i, j, i + 1, j)
            }

            if(j < N - 1)
            {
                swap(i, j, i, j + 1)
                max = max(max, countContinuousRow(i))
                max = max(max,countContinuousColumn(j + 1))
                max = max(max,countContinuousColumn(j))
                swap(i, j, i, j + 1)
            }
        }
    }

    println(max)
}

fun countContinuousRow(x : Int) : Int
{
    var count = 0
    var max = 0

    var color = map[x][0]

    for(j in 0 until N)
    {
        if(map[x][j] == color)
            count++
        else
        {
            count = 1
            color = map[x][j]
        }
        max = max(max, count)
    }
    return max
}

fun countContinuousColumn(y : Int) : Int
{
    var count = 0
    var max = 0

    var color = map[0][y]

    for(i in 0 until N)
    {
        if(map[i][y] == color)
            count++
        else
        {
            count = 1
            color = map[i][y]
        }
        max = max(max, count)
    }
    return max
}


fun swap(x1 : Int, y1 : Int, x2 : Int, y2 :Int)
{
    var temp = map[x1][y1]
    map[x1][y1] = map[x2][y2]
    map[x2][y2] = temp
}



fun main() {
    solution()
}