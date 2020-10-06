import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

import java.util.*

var br = BufferedReader(InputStreamReader(System.`in`))
var bw = BufferedWriter(OutputStreamWriter(System.out))

var x = arrayOf(-1, 1, 0, 0)
var y = arrayOf(0, 0, -1, 1)

var N = 0
var M = 0
lateinit var map : Array<Array<Int>>
lateinit var dist : Array<Array<Int>>

var wall_max = 0

fun solution() {

    var strtok = StringTokenizer(br.readLine())
    M = strtok.nextToken().toInt()
    N = strtok.nextToken().toInt()

    map = Array(N+1, {Array(M+1, {0})})

    for(i in 1..N)
    {
        var str = br.readLine()

        for(j in 1..M)
        {
            map[i][j] = (str[j - 1] - '0')
            wall_max++
        }
    }


    dist = Array(N+1, {Array(M+1, {wall_max})})
    dist[1][1] = 0
    dijkstra()

    bw.write(dist[N][M].toString())
}

fun dijkstra()
{
    var queue = PriorityQueue<Position>(kotlin.Comparator { o1, o2 -> o1.destory - o2.destory })
    queue.offer(Position(1, 1, 0))
    var visited = Array(N + 1, {Array(M + 1, {false})})

    while (!queue.isEmpty())
    {
        var now = queue.poll()

        if(visited[now.x][now.y])
            continue
        visited[now.x][now.y] = true

        for(i in 0..3)
        {
            var next_x = now.x + x[i]
            var next_y = now.y + y[i]

            if(next_x in 1..N && next_y in 1..M)
            {
                if(dist[next_x][next_y] > dist[now.x][now.y] + map[now.x][now.y])
                {
                    dist[next_x][next_y] = dist[now.x][now.y] + map[now.x][now.y]

                    if(!visited[next_x][next_y])
                        queue.offer(Position(next_x, next_y, dist[next_x][next_y]))
                }
            }
        }
    }
}


data class Position(var x : Int, var y : Int, var destory : Int)


fun main() {
    solution()
    br.close()
    bw.close()
}