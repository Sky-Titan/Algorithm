import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.max

var br = BufferedReader(InputStreamReader(System.`in`))
var bw = BufferedWriter(OutputStreamWriter(System.out))

fun solution() {

    var N = br.readLine().toInt()

    var max_length = 0

    var words = Array(N, {
        var str = br.readLine()
        max_length = max(max_length, str.length)
        StringBuilder(str)
    })

    var num_list = IntArray(26)

    for(i in 0 until N)
    {
        var ten = 1

        for(j in words[i].length - 1 downTo 0)
        {
            num_list[words[i][j] - 'A'] += ten
            ten *= 10
        }
    }

    //내림차순 정렬
    num_list.sortDescending()

    var result = 0

    //정렬된 수들에 9부터 할당해서 곱하기
    var i = 9

    num_list.forEach {
        result += i * it
        i--
    }

    bw.write(result.toString())

}

fun main() {
    solution()


    br.close()
    bw.close()
}