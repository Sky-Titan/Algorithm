import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

import java.util.*
import java.util.function.BiFunction
import kotlin.collections.HashMap
import kotlin.math.max
import kotlin.math.min
import kotlin.text.StringBuilder

var br = BufferedReader(InputStreamReader(System.`in`))
var bw = BufferedWriter(OutputStreamWriter(System.out))


fun solution() {

    var N = br.readLine().toInt()

    var count_visited = HashMap<Int, Int>()
    for(n in 0 until N)
    {
        var strtok = StringTokenizer(br.readLine())
        var num = strtok.nextToken()
        var strikes = strtok.nextToken().toInt()
        var balls = strtok.nextToken().toInt()

        //백의 자리수
        for(i in 1..9)
        {
            //십의 자리수
            for(j in (1..9).filter { i != it })
            {
                //일의 자리수
                for(k in (1..9).filter { i != it && j != it })
                {
                    var s = 0//스트라이크
                    var b = 0//볼

                    //볼 스트라이크 수 세기
                    for(index in 0..2)
                    {
                        if(i == getInt(num[index]))
                        {
                            if(index == 0)
                                s++
                            else
                                b++
                        }

                        if(j == getInt(num[index]))
                        {
                            if(index == 1)
                                s++
                            else
                                b++
                        }

                        if(k == getInt(num[index]))
                        {
                            if(index == 2)
                                s++
                            else
                                b++
                        }
                    }

                    //동일하다면 추가
                    if(s == strikes && b == balls)
                    {
                        var key = i * 100 + j * 10 + k

                        count_visited.putIfAbsent(key, 0)
                        count_visited.computeIfPresent(key, BiFunction { t, u -> u + 1 })
                    }
                }
            }
        }
    }

    var count = 0

    var keySet = count_visited.keys
    var iterator = keySet.iterator()

    while(iterator.hasNext())
    {
        var key = iterator.next()
        if(count_visited[key] == N)
            count++
    }

    bw.write(count.toString())
}

fun getInt(char : Char) : Int
{
    return char - '0'
}


fun main() {
    solution()
    br.close()
    bw.close()
}