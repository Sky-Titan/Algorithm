import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
<<<<<<< HEAD
=======
import kotlin.collections.ArrayList

import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.max

>>>>>>> de5cec63033b3727b3d0dd505863274133f59d98
import kotlin.math.min

var br = BufferedReader(InputStreamReader(System.`in`))
var bw = BufferedWriter(OutputStreamWriter(System.out))


fun solution() {

    var strtok = StringTokenizer(br.readLine())

<<<<<<< HEAD
    var N = strtok.nextToken().toInt()
    var S = strtok.nextToken().toInt()

    strtok = StringTokenizer(br.readLine())

    var list = Array(N, {strtok.nextToken().toInt()})

    //구간합의 최소값
    var min_length = Int.MAX_VALUE

    var before = 0
    var after = 0

    var sum = list[0]

    while(true)
    {
        //S이상인 수이면 min 값 구하기
        if(sum >= S)
        {
            min_length = min(min_length, after - before + 1)

            if(before < after)
            {
                sum -= list[before]
                before ++
            }
            //before == after인 순간 나올 수 있는 최소길이인 1이 되므로 종료
            else
                break
        }
        else
        {
            if(after < N - 1)
            {
                after++
                sum += list[after]
            }
            //sum이 s보다 작은데 더이상 값을 추가 못하므로 종료
            else
                break
        }

    }


    if(min_length == Int.MAX_VALUE)
        bw.write("0")
    else
        bw.write(min_length.toString())
}
=======
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
>>>>>>> de5cec63033b3727b3d0dd505863274133f59d98


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