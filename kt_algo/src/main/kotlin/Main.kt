import java.util.*
import kotlin.collections.ArrayList


lateinit var map : Array<IntArray>
lateinit var sharkList : List<Shark>
lateinit var queue_smell : Array<Queue<Smell>>
lateinit var priority : Array<Array<IntArray>>
lateinit var isDead : BooleanArray
var N : Int = 0
var M : Int = 0
var K : Int = 0


fun solution(){

    var str = readLine()!!.split(" ")
    N = str[0].toInt()
    M = str[1].toInt()//상어 마리 수
    K = str[2].toInt()//상어가 k번 이동하면 냄새가 사라짐

    isDead = BooleanArray(M + 1)
    isDead[0] = true
    map = Array<IntArray>(N + 1, {IntArray(N + 1)})
    sharkList = List<Shark>(M + 1,{i ->  Shark(i, 0, 0, 0)})
    queue_smell = Array(M + 1, {LinkedList<Smell>()})

    //맵 -> 상어 위치
    for(i in 1..N)
    {
        str = readLine()!!.split(" ")

        for(j in 1..N)
        {
            map[i][j] = str[j - 1].toInt()
            if(map[i][j] > 0)
            {
                sharkList[map[i][j]].x = i
                sharkList[map[i][j]].y = j
                queue_smell[map[i][j]].offer(Smell(i, j))
            }
        }
    }


    str = readLine()!!.split(" ")

    //방향 입력
    for(i in 1..M)
        sharkList[i].dir = str[i - 1].toInt()

    //각 상어마다의 우선순위 [상어번호][현재방향][우선순위별 방향]
    priority = Array<Array<IntArray>>(M + 1, {Array<IntArray>(5, { IntArray(5) })})

    for(i in 1..M)
    {
        for(j in 1..4)
        {
            str = readLine()!!.split(" ")
            priority[i][j][1] = str[0].toInt()
            priority[i][j][2] = str[1].toInt()
            priority[i][j][3] = str[2].toInt()
            priority[i][j][4] = str[3].toInt()
        }
    }


    for(i in 1..1000)
    {
        //이동
        moveSharks()
        putSmell()

        if(i >= K)
            pollSmell()

        //println(isDead.count { b -> b == false })
        if(isDead.count { b-> b == false } == 1 && !isDead[1]) //0번과 1번 -> 즉 1번 상어만 남아있으면 종료
        {
            print(i)
            return
        }
    }

    print(-1)
}

fun moveSharks()
{
    var visited = Array(N + 1, {BooleanArray(N + 1)})

    first@for(i in (1 until sharkList.size).filter { i -> !isDead[i] })
    {
        var index = sharkList[i].index
        var x = sharkList[i].x
        var y = sharkList[i].y
        var dir = sharkList[i].dir

        //냄새 없는 곳부터 찾기
        for(j in 1..4)
        {
            var next = getNext(x, y, priority[index][dir][j])

            //맵 안에 있는지
            if(next[0] in 1..N && next[1] in 1..N)
            {
                //냄새가 없는 경우
                if(map[next[0]][next[1]] == 0)
                {
                    if(!visited[next[0]][next[1]])
                    {
                        visited[next[0]][next[1]] = true
                        sharkList[i].x = next[0]
                        sharkList[i].y = next[1]
                        sharkList[i].dir = priority[index][dir][j]
                        queue_smell[i].offer(Smell(next[0],next[1]))
                        //map[next[0]][next[1]] = index
                    }
                    else
                        isDead[index] = true
                    continue@first
                }
                //자기보다 번호가 낮은 상어 번호
           /*     else if(map[next[0]][next[1]] < index)
                {
                    //충돌!
                    if(sharkList[map[next[0]][next[1]]].x == next[0] && sharkList[map[next[0]][next[1]]].y == next[1])
                    {
                        isDead[index] = true
                        continue@first
                    }
                }*/
            }
        }

        //자기 냄새 있는 곳
        for(j in 1..4)
        {
            //현재 방향 기준 우선순위별로 다음 갈 곳
            var next = getNext(x, y, priority[index][dir][j])

            //맵 안에 있는지
            if(next[0] in 1..N && next[1] in 1..N)
            {
                //자기 냄새 남아있는 곳
                if(map[next[0]][next[1]] == index)
                {
                    sharkList[i].x = next[0]
                    sharkList[i].y = next[1]
                    sharkList[i].dir = priority[index][dir][j]
                    queue_smell[i].offer(Smell(next[0],next[1]))
                    //map[next[0]][next[1]] = index
                    continue@first
                }
            }
        }
    }
}

fun getNext(x : Int, y : Int, dir : Int) : Array<Int>
{
    var next = arrayOf(x, y)

    when (dir)
    {
        //위
        1 -> next[0]--
        //아래
        2 -> next[0]++
        //왼쪽
        3 -> next[1]--
        //오른쪽
        4 -> next[1]++
    }

    return next
}

//냄새 남기기
fun putSmell()
{
    for(i in (1 until sharkList.size).filter { i -> !isDead[i] })
    {
        var smell = queue_smell[i].last()
        map[smell.x][smell.y] = i
    }
}

//냄새빠짐
fun pollSmell()
{
    for(i in (1 until queue_smell.size).filter { i -> !queue_smell[i].isEmpty()})
    {
        var smell = queue_smell[i].poll()

        //똑같은 smell을 더 안포함하고 있으면
        if(!queue_smell[i].contains(smell))
            map[smell.x][smell.y] = 0
    }
}

data class Shark(val index : Int, var dir : Int, var x : Int, var y : Int)

data class Smell(var x : Int, var y : Int)

fun main() {
    solution()
}