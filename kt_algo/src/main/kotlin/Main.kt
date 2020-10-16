import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList

var br = BufferedReader(InputStreamReader(System.`in`))
var bw = BufferedWriter(OutputStreamWriter(System.out))

var N = 0

lateinit var dp : IntArray
lateinit var times : IntArray


var graph = ArrayList<ArrayList<Int>>()
lateinit var inEdge : IntArray

fun solution() {

    N = br.readLine().toInt()
    inEdge = IntArray(N+1)
    dp = IntArray(N+1)
    times = IntArray(N+1)

    for(i in 0..N)
        graph.add(ArrayList())

    for(from in 1..N)
    {
        var strtok = StringTokenizer(br.readLine())
        var time = strtok.nextToken().toInt()
        times[from] = time

        while(strtok.hasMoreTokens())
        {
            var to = strtok.nextToken().toInt()
            if(to == -1)
                break

            inEdge[to]++
            graph.get(from).add(to)
        }
    }

    topologicalSort()
}

fun topologicalSort()
{
    var queue :Queue<Int> = LinkedList()

    for(i in (1..N).filter { inEdge[it] == 0 })
    {
        dp[i] = times[i]
        queue.offer(i)
    }

    while(!queue.isEmpty())
    {
        var now = queue.poll()

        bw.write("$now ")

        for(next in graph[now])
        {
            inEdge[next.to]--

            if(inEdge[next] == 0)
                queue.offer(next)
        }
    }
}

data class Edge(var from : Int, var to : Int, var time : Int)


fun main() {
    solution()


    br.close()
    bw.close()
}