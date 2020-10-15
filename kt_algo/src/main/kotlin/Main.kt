import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList

import kotlin.math.min

var br = BufferedReader(InputStreamReader(System.`in`))
var bw = BufferedWriter(OutputStreamWriter(System.out))

var V = 0
var E = 0

var graph = ArrayList<ArrayList<Int>>()
lateinit var inEdge : IntArray

fun solution() {

    var strtok = StringTokenizer(br.readLine())
    V = strtok.nextToken().toInt()
    E = strtok.nextToken().toInt()

    inEdge = IntArray(V+1)

    for(i in 0..V)
        graph.add(ArrayList())

    for(i in 0 until E)
    {
        strtok = StringTokenizer(br.readLine())
        var A = strtok.nextToken().toInt()
        var B = strtok.nextToken().toInt()

        inEdge[B]++
        graph.get(A).add(B)
    }

    topologicalSort()
}

fun topologicalSort()
{
    var queue : Queue<Int> = LinkedList()
    for(i in (1..V).filter { inEdge[it] == 0 })
        queue.offer(i)

    while(!queue.isEmpty())
    {
        var now = queue.poll()

        bw.write("$now ")

        for(next in graph[now])
        {
            inEdge[next]--

            if(inEdge[next] == 0)
                queue.offer(next)
        }
    }
}


fun main() {
    solution()


    br.close()
    bw.close()
}