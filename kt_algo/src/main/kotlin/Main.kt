import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

var br = BufferedReader(InputStreamReader(System.`in`))
var bw = BufferedWriter(OutputStreamWriter(System.out))

var N = 0
var M = 0
lateinit var map : Array<Array<Char>>
lateinit var visited : Array<Array<Array<Array<Boolean>>>>

fun solution() {

    br.readLine()!!.split(" ").forEachIndexed { index, s ->
        if(index == 0)
            N = s.toInt()
        else
            M = s.toInt()
    }
    map = Array(N, { Array(M, {'.'}) })
    visited = Array(N, { Array(M, { Array(N, {Array(M, {false})}) }) })

    var start = Balls(0, 0, 0, 0, 0)

    for(i in 0 until N)
    {
        br.readLine()!!.forEachIndexed{j, s ->
            map[i][j] = s

            if(s == 'R')
            {
                start.r_x = i
                start.r_y = j
                map[i][j] = '.'
            }
            else if(s == 'B')
            {
                start.b_x = i
                start.b_y = j
                map[i][j] = '.'
            }
        }
    }


    bw.write("${bfs(start)}")

}

fun bfs(start : Balls) : Int
{
    visited[start.r_x][start.r_y][start.b_x][start.b_y] = true
    var queue : Queue<Balls> = LinkedList()

    queue.offer(start)

    while(!queue.isEmpty())
    {
        var now = queue.poll()
        //println(now.toString())
        if(now.count > 10)
            return -1
        if(map[now.r_x][now.r_y] == 'O')
            return now.count

        for(i in 0..3)
        {
            var next = tilt(now, i)

            if(isInBoundary(next))
            {
                if(!visited[next[0]][next[1]][next[2]][next[3]] && map[next[2]][next[3]] != 'O')
                {
                    visited[next[0]][next[1]][next[2]][next[3]] = true
                    queue.offer(Balls(next[0], next[1], next[2], next[3], now.count + 1))
                }
            }
        }
    }

    return -1
}

fun tilt(now : Balls, dir : Int) : Array<Int>
{
    var next = arrayOf(now.r_x, now.r_y, now.b_x, now.b_y)

    when(dir)
    {
        0 -> {
            //빨간 공 먼저
            if(now.r_x < now.b_x)
            {
                for(i in (now.r_x downTo 0))
                {
                    if(map[i][next[1]] != '#')
                    {
                        next[0] = i
                        if(map[next[0]][next[1]] == 'O')
                            break
                    }
                    else
                        break
                }

                for(i in (now.b_x downTo 0))
                {
                    if( (map[i][next[3]] == '.' && (i != next[0] || next[3] != next[1]) )|| map[i][next[3]] == 'O')
                    {
                        next[2] = i
                        if(map[next[2]][next[3]] == 'O')
                            break
                    }
                    else
                        break
                }
            }
            else
            {
                for(i in (now.b_x downTo 0))
                {
                    if( map[i][next[3]] != '#')
                    {
                        next[2] = i
                        if(map[next[2]][next[3]] == 'O')
                            break
                    }
                    else
                        break
                }

                for(i in (now.r_x downTo 0))
                {
                    if( (map[i][next[1]] == '.' && (i != next[2] || next[1] != next[3]))|| map[i][next[1]] == 'O')
                    {
                        next[0] = i
                        if(map[next[0]][next[1]] == 'O')
                            break
                    }
                    else
                        break
                }
            }
        }

        1 -> {
            //빨간 공 먼저
            if(now.r_x > now.b_x)
            {
                for(i in (now.r_x until N))
                {
                    if( map[i][next[1]] != '#')
                    {
                        next[0] = i
                        if(map[next[0]][next[1]] == 'O')
                            break
                    }
                    else
                        break
                }

                for(i in (now.b_x until N))
                {
                    if( (map[i][next[3]] == '.' && (i != next[0] || next[3] != next[1]) )|| map[i][next[3]] == 'O')
                    {
                        next[2] = i
                        if(map[next[2]][next[3]] == 'O')
                            break
                    }
                    else
                        break
                }
            }
            else
            {
                for(i in (now.b_x until N))
                {
                    if( map[i][next[3]] != '#')
                    {
                        next[2] = i
                        if(map[next[2]][next[3]] == 'O')
                            break
                    }
                    else
                        break
                }

                for(i in (now.r_x until N))
                {
                    if( (map[i][next[1]] == '.' && (i != next[2] || next[1] != next[3]))|| map[i][next[1]] == 'O')
                    {
                        next[0] = i
                        if(map[next[0]][next[1]] == 'O')
                            break
                    }
                    else
                        break
                }
            }

        }

        2 -> {
            //빨간 공 먼저
            if(now.r_y < now.b_y)
            {
                for(j in (now.r_y downTo 0))
                {
                    if( map[next[0]][j] != '#')
                    {
                        next[1] = j
                        if(map[next[0]][next[1]] == 'O')
                            break
                    }
                    else
                        break
                }

                for(j in (now.b_y downTo 0))
                {
                    if( (map[next[2]][j] == '.' && (next[2] != next[0] || j != next[1]) )|| map[next[2]][j] == 'O')
                    {
                        next[3] = j
                        if(map[next[2]][next[3]] == 'O')
                            break
                    }
                    else
                        break
                }
            }
            else
            {
                for(j in (now.b_y downTo 0))
                {
                    if( map[next[2]][j] != '#')
                    {
                        next[3] = j
                        if(map[next[2]][next[3]] == 'O')
                            break
                    }
                    else
                        break
                }

                for(j in (now.r_y downTo 0))
                {
                    if( (map[next[0]][j] == '.' && (next[0] != next[2] || j != next[3]))|| map[next[0]][j] == 'O')
                    {
                        next[1] = j
                        if(map[next[0]][next[1]] == 'O')
                            break
                    }
                    else
                        break
                }
            }

        }

        3 -> {
            //빨간 공 먼저
            if(now.r_y > now.b_y)
            {
                for(j in (now.r_y until M))
                {
                    if( map[next[0]][j] != '#')
                    {
                        next[1] = j
                        if(map[next[0]][next[1]] == 'O')
                            break
                    }
                    else
                        break
                }

                for(j in (now.b_y until M))
                {
                    if( (map[next[2]][j] == '.' && (next[2] != next[0] || j != next[1]) )|| map[next[2]][j] == 'O')
                    {
                        next[3] = j
                        if(map[next[2]][next[3]] == 'O')
                            break
                    }
                    else
                        break
                }
            }
            else
            {
                for(j in (now.b_y until M))
                {
                    if( map[next[2]][j] != '#')
                    {
                        next[3] = j
                        if(map[next[2]][next[3]] == 'O')
                            break
                    }
                    else
                        break
                }

                for(j in (now.r_y until M))
                {
                    if( (map[next[0]][j] == '.' && (next[0] != next[2] || j != next[3]))|| map[next[0]][j] == 'O')
                    {
                        next[1] = j
                        if(map[next[0]][next[1]] == 'O')
                            break
                    }
                    else
                        break
                }
            }
        }
    }
    return next
}


fun isInBoundary(next : Array<Int>) : Boolean
{
    if( next[0] in 0 until N && next[1] in 0 until M && next[2] in 0 until N && next[3] in 0 until M)
        return true
    return false
}

data class Balls(var r_x : Int, var r_y : Int, var b_x : Int, var b_y : Int, var count : Int)


fun main() {
    solution()
    br.close()
    bw.close()
}