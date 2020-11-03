import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

import kotlin.collections.ArrayList

import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.max

import kotlin.math.min

var br = BufferedReader(InputStreamReader(System.`in`))
var bw = BufferedWriter(OutputStreamWriter(System.out))


fun solution() {

    var strtok = StringTokenizer(br.readLine())

    var N = strtok.nextToken().toInt()//정점
    var M = strtok.nextToken().toInt()//간선

    var graph = ArrayList<ArrayList<Int>>()

    for(i in 0..N)
        graph.add(ArrayList())

    var inEdge = Array(N + 1, {0})

    for(i in 0 until M)
    {
        strtok = StringTokenizer(br.readLine())
        var from = strtok.nextToken().toInt()
        var to = strtok.nextToken().toInt()

        graph.get(from).add(to)
        inEdge[to]++
    }

    strtok = StringTokenizer(br.readLine())
    var start = strtok.nextToken().toInt()
    var end = strtok.nextToken().toInt()
    var K = strtok.nextToken().toInt()

    //교차로 집합
    var mid_set = HashSet<Int>()

    for(i in 0 until K)
        mid_set.add(br.readLine().toInt())

    var count = Array(N + 1, {Array(2, {0})})//0 : 방문한 교차로 수, 1 : 교차로를 거친 경로 수

    var queue : Queue<Int> = LinkedList()

    for(i in 1 .. N)
    {
        if(inEdge[i] == 0)
            queue.offer(i)
    }
    count[start][1] = 1

    while(!queue.isEmpty())
    {
        var now = queue.poll()

        //교차로
        if(mid_set.contains(now))
            count[now][0] ++

        if(now == end)
            break

        for(i in 0 until graph.get(now).size)
        {
            var to = graph.get(now).get(i)

            inEdge[to]--

            //크다면 교체
            if(count[now][0] > count[to][0])
            {
                count[to][0] = count[now][0]
                count[to][1] = count[now][1]
            }
            else if(count[now][0] == count[to][0])
                count[to][1] += count[now][1]

            if(inEdge[to] == 0)
                queue.offer(to)
        }
    }

    if(count[end][0] == K)
        bw.write(count[end][1].toString())
    else
        bw.write("0")
}

fun main() {
    solution()


    br.close()
    bw.close()
}