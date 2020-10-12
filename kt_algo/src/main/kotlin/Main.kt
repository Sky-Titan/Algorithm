import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

import java.util.*
import java.util.function.BiFunction
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.max
import kotlin.math.min
import kotlin.text.StringBuilder

var br = BufferedReader(InputStreamReader(System.`in`))
var bw = BufferedWriter(OutputStreamWriter(System.out))

var N : Long = 0
var K : Long = 0

fun solution() {

    N = br.readLine().toLong()
    K = br.readLine().toLong()

    var left : Long = 1
    var right : Long = N * N

    var answer : Long = 0
    while(left <= right)
    {
        var mid = (left + right) / 2
        var cnt : Long = 0

        for(i in 1..N)
        {
            var num : Long = mid / i
            if(num > N)
                num = N
            cnt += num
        }

        if(cnt >= K)
        {
            answer = mid
            right = mid - 1
        }
        else
            left = mid + 1
    }
    bw.write(answer.toString())

}


fun main() {
    solution()


    br.close()
    bw.close()
}