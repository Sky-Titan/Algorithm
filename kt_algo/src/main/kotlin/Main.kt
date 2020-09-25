import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max


fun solution() {

    var N = readLine()!!.toInt()

    var ropes = Array<Int> (N, { readLine()!!.toInt()})

    ropes.sortDescending()

    var count = 1
    var max = 0

    for(weight in ropes)
    {
        var total = weight * count
        max = max(total, max)
        count++
    }
    print(max)
}


fun main() {
    solution()
}