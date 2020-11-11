import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.min

var br = BufferedReader(InputStreamReader(System.`in`))
var bw = BufferedWriter(OutputStreamWriter(System.out))

var N = 0
var list = ArrayList<Long>()

fun solution() {

    N = br.readLine().toInt()

    if(N == 0)
        bw.write("0")
    else
    {
        if(1022 < N)
        {
            bw.write("-1")
            return
        }
        backTracking(0, 1, 0)
        list.sort()
        bw.write(list[N].toString())

    }
}

fun backTracking(index : Int, t : Long, result: Long)
{
    var result = result
    for(i in index..9)
    {
        result += i * t
        list.add(result)
        backTracking(i + 1, t * 10, result)
        result -= i * t
    }
}





fun main() {
    solution()


    br.close()
    bw.close()
}