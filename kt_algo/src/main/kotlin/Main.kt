import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

import java.util.*
import kotlin.collections.ArrayList

var br = BufferedReader(InputStreamReader(System.`in`))
var bw = BufferedWriter(OutputStreamWriter(System.out))

var N = 0//정점 수
var M = 0//간선 수
var graph = ArrayList<ArrayList<Int>>()
lateinit var visited : BooleanArray
var result = 0

fun solution() {

    var strtok = StringTokenizer(br.readLine())
    N = strtok.nextToken().toInt()
    M = strtok.nextToken().toInt()


    visited = BooleanArray(N)

    for(i in 0 until N)
        graph.add(ArrayList())

    for(i in 0 until M)
    {
        strtok = StringTokenizer(br.readLine())
        var from = strtok.nextToken().toInt()
        var to = strtok.nextToken().toInt()

        graph.get(from).add(to)
        graph.get(to).add(from)
    }

    for(i in 0 until N)
    {
        if(!visited[i])
        {
            visited[i] = true
            dfs(i, 0, visited)
            visited[i] = false
        }
        if(result == 1)
            break
    }

    bw.write(result.toString())
}

fun dfs(now : Int, depth : Int, visited : BooleanArray)
{
    if(depth == 4)
    {
        result = 1
        return
    }

    for(i in 0 until graph[now].size)
    {
        var next = graph[now][i]

        if(!visited[next])
        {
            visited[next] = true
            dfs(next, depth + 1, visited)
            visited[next] = false
        }
    }
}

fun main() {
    solution()
    br.close()
    bw.close()
}