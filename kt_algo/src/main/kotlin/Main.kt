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

var graph : ArrayList<ArrayList<Edge>> = ArrayList()
var dist = HashMap<Int,IntArray>()
val INF = 200000001
var v1 = 0
var v2 = 0
var N = 0
var E = 0
//dist[1] dist[v1] dist[v2]

fun solution() {

    var strtok = StringTokenizer(br.readLine())
    N = strtok.nextToken().toInt()
    E = strtok.nextToken().toInt()

    for(i in 0 .. N)
        graph.add(ArrayList())

    for(i in 0 until E)
    {
        strtok = StringTokenizer(br.readLine())
        var a = strtok.nextToken().toInt()
        var b = strtok.nextToken().toInt()
        var c = strtok.nextToken().toInt()

        graph.get(a).add(Edge(a, b, c))
        graph.get(b).add(Edge(b, a, c))
    }

    strtok = StringTokenizer(br.readLine())
    v1 = strtok.nextToken().toInt()
    v2 = strtok.nextToken().toInt()

    dist.putIfAbsent(1 , IntArray(N + 1, {INF}))
    dist.putIfAbsent(v1, IntArray(N + 1 , {INF}))
    dist.putIfAbsent(v2, IntArray(N + 1 , {INF}))

    dist[1]?.get(2)
    //1 -> v1, 1->v2
    graph.get(1).forEach { edge ->
        if(edge.to == v1)
        {
            dist[1]?.set(v1, edge.weight)
            dist[v1]?.set(1, edge.weight)
        }
        else if(edge.to == v2)
        {
            dist[1]?.set(v2, edge.weight)
            dist[v2]?.set(1, edge.weight)
        }
    }

    //v1 -> v2, v1 -> N
    graph.get(v1).forEach { edge ->
        if(edge.to == v2)
        {
            dist[v1]?.set(v2, edge.weight)
            dist[v2]?.set(v1, edge.weight)
        }
        else if(edge.to == N)
            dist[v1]?.set(N, edge.weight)
    }

    //v2 -> N
    graph.get(v2).forEach { edge ->
        if(edge.to == N)
            dist[v2]?.set(N, edge.weight)
    }

    dijkstra(1)
    dijkstra(v1)
    dijkstra(v2)

    if((dist[1]?.get(v1) == INF || dist[v1]?.get(v2) == INF || dist[v2]?.get(N) == INF)
            && (dist[1]?.get(v2) == INF || dist[v2]?.get(v1) == INF || dist[v1]?.get(N) == INF))
    {
        bw.write("-1")
        return
    }

    var min1 = dist[1]?.get(v1)!! + dist[v1]?.get(v2)!! + dist[v2]?.get(N)!!
    var min2 = dist[1]?.get(v2)!! + dist[v2]?.get(v1)!! + dist[v1]?.get(N)!!

    bw.write(min(min1, min2).toString())
}


fun dijkstra(start: Int)
{
    dist[start]?.set(start, 0)

    var queue = PriorityQueue<Node>({o1, o2 -> o1.dist - o2.dist})
    queue.offer(Node(start, 0))

    var visited = BooleanArray(N + 1)

    while(!queue.isEmpty())
    {
        var now = queue.poll()

        if(visited[now.num])
            continue
        visited[now.num] = true

        for(next in graph.get(now.num))
        {
            if(dist[start]?.get(next.to)!! > dist[start]?.get(now.num)!! + next.weight)
            {
                dist[start]?.set(next.to, dist[start]?.get(now.num)!! + next.weight)
                queue.offer(Node(next.to, dist[start]?.get(next.to)!!))
            }
        }
    }
}

data class Edge(var from : Int, var to : Int, var weight : Int)
data class Node(var num : Int, var dist : Int)

fun main() {
    solution()


    br.close()
    bw.close()
}