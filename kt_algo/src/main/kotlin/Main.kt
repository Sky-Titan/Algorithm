import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.min

var br = BufferedReader(InputStreamReader(System.`in`))
var bw = BufferedWriter(OutputStreamWriter(System.out))



fun solution() {

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




fun main() {
    solution()


    br.close()
    bw.close()
}