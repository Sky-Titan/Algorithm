import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
<<<<<<< HEAD
import kotlin.collections.HashMap
import kotlin.math.max
=======
import kotlin.math.min
>>>>>>> f70cc8ea2cabd0bfff35083ce0e67cb272ecc0ca

var br = BufferedReader(InputStreamReader(System.`in`))
var bw = BufferedWriter(OutputStreamWriter(System.out))

<<<<<<< HEAD
fun solution() {

    var N = br.readLine().toInt()
=======

>>>>>>> f70cc8ea2cabd0bfff35083ce0e67cb272ecc0ca

    var max_length = 0

<<<<<<< HEAD
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
=======
    var T = br.readLine().toInt()

    for(t in 0 until T)
    {
        var K = br.readLine().toInt()
        var strtok = StringTokenizer(br.readLine())

        var sum = IntArray(K + 1, {0})
        var cost = IntArray(K + 1, {

            if(it > 0)
                strtok.nextToken().toInt()
            else
                0
        })

        for(i in 1..K)
            sum[i] = sum[i-1] + cost[i]

        var dp = Array(K + 1, {i -> IntArray(K + 1, { 0 })})

        for(size in 1 until K)
        {
            for(i in 1 .. K - size)
            {
                var j = i + size
                dp[i][j] = Int.MAX_VALUE

                for(k in i until j)
                {
                    dp[i][j] = min(dp[i][j], dp[i][k] + dp[k+1][j] + sum[j] - sum[i-1])
                }
            }
        }


        bw.write(dp[1][K].toString())
        bw.newLine()
    }

}


>>>>>>> f70cc8ea2cabd0bfff35083ce0e67cb272ecc0ca

}

fun main() {
    solution()


    br.close()
    bw.close()
}