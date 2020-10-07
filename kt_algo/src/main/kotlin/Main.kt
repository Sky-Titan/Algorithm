import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

import java.util.*

var br = BufferedReader(InputStreamReader(System.`in`))
var bw = BufferedWriter(OutputStreamWriter(System.out))

var N = 0
var color_count = IntArray(2)
val BLUE = 1
val WHITE = 0

fun solution() {

    N = br.readLine().toInt()

    var map = Array(N, {Array(N, {0})})

    map.forEach {
        var strtok = StringTokenizer(br.readLine())
        it.forEachIndexed { index, i -> it[index] = strtok.nextToken().toInt() }
    }

    countSquares(0, 0, N, map)

    bw.write("${color_count[WHITE]}\n")
    bw.write("${color_count[BLUE]}\n")
}

fun countSquares(x : Int, y : Int, length : Int, map: Array<Array<Int>>)
{
    var color = map[x][y]
    var isAllSame = true

    for(i in x until x + length)
    {
        for(j in y until y + length)
        {
            if(color != map[i][j])
            {
                isAllSame = false
                break
            }
        }
    }

    if(isAllSame)
        color_count[color]++
    else
    {
        countSquares(x, y, length / 2, map)
        countSquares(x + length / 2, y, length / 2, map)
        countSquares(x, y + length / 2, length / 2, map)
        countSquares(x + length / 2, y + length / 2, length / 2, map)
    }

}


fun main() {
    solution()
    br.close()
    bw.close()
}