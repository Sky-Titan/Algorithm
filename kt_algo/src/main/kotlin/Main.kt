import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

import kotlin.collections.HashMap
import kotlin.math.max

import kotlin.math.min

var br = BufferedReader(InputStreamReader(System.`in`))
var bw = BufferedWriter(OutputStreamWriter(System.out))


fun solution() {

    var N = br.readLine().toInt()

    var list = IntArray(N, {br.readLine().toInt()})

    list.sortDescending()



}



fun main() {
    solution()


    br.close()
    bw.close()
}