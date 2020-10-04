import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.HashSet

var br = BufferedReader(InputStreamReader(System.`in`))
var bw = BufferedWriter(OutputStreamWriter(System.out))

fun solution() {

    var T = br.readLine().toInt()

    var isPrime = BooleanArray(10001, {true})

    for(i in 2 .. 10000)
    {
        for(j in 2 * i .. 10000 step i)
            isPrime[j] = false
    }

    for(t in 0 until T)
    {
        var strtok = StringTokenizer(br.readLine())

        var N = strtok.nextToken().toInt()
        var M = strtok.nextToken().toInt()

        var conversion = IntArray(10000)
        var visited = BooleanArray(10000)

        var queue:Queue<Int> = LinkedList()
        queue.offer(N)
        visited[N] = true

        while(!queue.isEmpty())
        {
            var now = queue.poll()

            if(now == M)
            {
                println("${conversion[now]}")
                break
            }

            var str = now.toString()
            var sb = StringBuilder(str)

            for(i in 0 until 4)
            {
                for(j in 0 .. 9)
                {
                    var next = sb.replace(i, i + 1, j.toString()).toString().toInt()

                    if(1000 <= next && next <= 9999 && !visited[next] && isPrime[next])
                    {
                        visited[next] = true
                        queue.offer(next)
                        conversion[next] = conversion[now] + 1
                    }

                }
                sb.replace(i, i + 1, str[i].toString())
            }


        }

    }
}




fun main() {
    solution()
    br.close()
    bw.close()
}