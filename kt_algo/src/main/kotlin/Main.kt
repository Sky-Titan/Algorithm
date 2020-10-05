import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList

var br = BufferedReader(InputStreamReader(System.`in`))
var bw = BufferedWriter(OutputStreamWriter(System.out))

var N = 0
var K = 0
lateinit var map : Array<Array<Int>>

val UP = 0
val DOWN = 1
val LEFT = 2
val RIGHT = 3

var orders : Queue<Order> = LinkedList()
var snake = ArrayList<Position>()

fun solution() {

    N = br.readLine().toInt()
    K = br.readLine().toInt()

    map = Array(N + 1, { Array(N + 1, {0}) })

    for(i in 0 until K)
    {
        var strtok = StringTokenizer(br.readLine())
        var x = strtok.nextToken().toInt()
        var y = strtok.nextToken().toInt()

        map[x][y] = 1
    }

    //머리부분분
   map[1][1] = 2

    snake.add(Position(1, 1))

    var L = br.readLine().toInt()

    for(i in 0 until L)
    {
        var strtok = StringTokenizer(br.readLine())
        var time = strtok.nextToken().toInt()
        var dir = strtok.nextToken()[0]

        orders.offer(Order(time, dir))
    }

    bw.write(simulation().toString())
}

fun simulation() :Int
{
    var current_time = 0
    var dir = RIGHT

    while(true)
    {
        if(!orders.isEmpty())
        {
            var order = orders.peek()

            //방향 회전 시간
            if(order.time == current_time)
            {
                orders.poll()
                dir = turnDirection(dir, order.dir)
            }
        }

        current_time++

        //뱀움직임
        if(!moveSnake(dir))
            return current_time
    }
}

fun turnDirection(current_Dir : Int, changed_Dir : Char) : Int
{
    //왼쪽 90도
    if(changed_Dir == 'L')
    {
        when(current_Dir)
        {
            UP -> return LEFT
            DOWN -> return RIGHT
            LEFT -> return DOWN
            else -> return UP
        }

    }
    //오른쪽 90도
    else
    {
        when(current_Dir)
        {
            UP -> return RIGHT
            DOWN -> return LEFT
            LEFT -> return UP
            else -> return DOWN
        }
    }
}

fun moveSnake(dir : Int) : Boolean
{
    var head = snake.first()
    var next_x = head.x
    var next_y = head.y

    when(dir)
    {
        UP -> {
            next_x --
        }

        DOWN -> {
            next_x ++
        }

        LEFT -> {
            next_y --
        }

        RIGHT -> {
            next_y ++
        }
    }

    //벽 안에 정상적으로 이동
    if(1 <= next_x && next_x <= N && 1 <= next_y && next_y <= N)
    {
        //자기 몸과 부딪힘
        if(map[next_x][next_y] == 2)
            return false
        else
        {
            //사과 놓여져 있으면 꼬리 증가
            if(map[next_x][next_y] == 1)
                snake.add(Position(0 ,0))
            //빈칸이면 꼬리 수축
            else
            {
                var tail = snake.last()
                map[tail.x][tail.y] = 0
            }

            //몸 이동
            for(i in snake.size - 1 downTo 1)
            {
                snake[i].x = snake[i - 1].x
                snake[i].y = snake[i - 1].y
            }

            //머리 이동
            head.x = next_x
            head.y = next_y
            map[head.x][head.y] = 2

        }
    }
    //벽에 부딪힘
    else
        return false

    return true
}


data class Order(var time : Int, var dir : Char)
data class Position(var x : Int, var y : Int)


fun main() {
    solution()
    br.close()
    bw.close()
}