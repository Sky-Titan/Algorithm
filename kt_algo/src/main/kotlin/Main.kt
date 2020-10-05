import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.util.*

var br = BufferedReader(InputStreamReader(System.`in`))
var bw = BufferedWriter(OutputStreamWriter(System.out))


fun solution() {

    var T = br.readLine().toInt()

    for(t in 0 until T)
    {
        var strtok = StringTokenizer(br.readLine())
        var A = strtok.nextToken().toInt()
        var B = strtok.nextToken().toInt()

        var visited = BooleanArray(10000)

        var queue : Queue<Register> = LinkedList()
        queue.offer(Register(A, ""))
        visited[A] = true

        while(!queue.isEmpty())
        {
            var now = queue.poll()

            //목적지 도착
            if(now.register_value == B)
            {
                bw.write(now.orders)
                bw.newLine()
                break
            }

            //1. D
            var next = (now.register_value * 2) % 10000

            if(!visited[next])
            {
                visited[next] = true
                queue.offer(Register(next, now.orders + "D"))
            }

            //2. S
            next = now.register_value - 1
            if(next == -1)
                next = 9999

            if(!visited[next])
            {
                visited[next] = true
                queue.offer(Register(next, now.orders + "S"))
            }

            //3. L
            next = rotationNumbers(now.register_value, 'L')

            if(!visited[next])
            {
                visited[next] = true
                queue.offer(Register(next, now.orders + "L"))
            }

            //4. R
            next = rotationNumbers(now.register_value, 'R')

            if(!visited[next])
            {
                visited[next] = true
                queue.offer(Register(next, now.orders + "R"))
            }
        }

    }
}

fun rotationNumbers(origin_num : Int, dir : Char) : Int
{
    var origin_num = origin_num

    if(dir == 'L')
    {
        var d1 = origin_num / 1000
        origin_num -= d1 * 1000
        origin_num *= 10
        origin_num += d1
    }
    else
    {
        var d4 = origin_num % 10
        origin_num /= 10
        origin_num += d4 * 1000
    }
    return origin_num
}


data class Register(var register_value:Int, var orders: String)

fun main() {
    solution()
    br.close()
    bw.close()
}