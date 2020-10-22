import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

import kotlin.collections.HashMap
import kotlin.math.max

import kotlin.math.min

var br = BufferedReader(InputStreamReader(System.`in`))
var bw = BufferedWriter(OutputStreamWriter(System.out))


fun solution() {

    var N = br.readLine().toInt()

    var list = IntArray(N, {br.readLine().toInt()})

    list.sortDescending()

    var result = 0

    var i = 0

    while(i < N && list[i] > 0)
    {
        if(i == N - 1)
        {
            result += list[i]
            i++
        }
        else
        {
            if(list[i] * list[i + 1] > list[i] + list[i + 1])
            {
                result += list[i] * list[i + 1]
                i += 2
            }
            else
            {
                result += list[i]
                i++
            }
        }
    }

    i = N - 1

    while(i >= 0 && list[i] < 0)
    {
        if(i == 0)
        {
            result += list[i]
            i--
        }
        else
        {
            if(list[i] * list[i - 1] > list[i] + list[i - 1])
            {
                result += list[i] * list[i - 1]
                i -= 2
            }
            else
            {
                result += list[i]
                i--
            }
        }

    }
    bw.write(result.toString())
}



fun main() {
    solution()


    br.close()
    bw.close()
}