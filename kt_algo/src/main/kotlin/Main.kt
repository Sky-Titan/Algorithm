import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.min

var br = BufferedReader(InputStreamReader(System.`in`))
var bw = BufferedWriter(OutputStreamWriter(System.out))


fun solution() {

    var strtok = StringTokenizer(br.readLine())

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



fun main() {
    solution()


    br.close()
    bw.close()
}