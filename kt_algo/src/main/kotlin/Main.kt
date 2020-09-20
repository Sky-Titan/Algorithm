
import kotlin.collections.HashSet
import kotlin.math.abs
import kotlin.math.min

fun solution(){

    var isBroken = BooleanArray(10)

    val N_str = readLine()!!
    val N = N_str.toInt()
    val M = readLine()!!.toInt()


    //부서진 버튼이 있다면
    if(M > 0)
    {
        var str = readLine()!!

        for(i in 0 until str.length step 2)
            isBroken[str[i] - '0'] = true
    }

    var range = (0..9).filter { i -> !isBroken[i] }

    var range_set = HashSet<Int>(range)

    var max_value = getMaxValue(N, range_set)

    var min = abs(N - 100)

    for(i in (0 .. max_value).filter { i ->
        for(c in i.toString())
        {
            if(!range_set.contains(c-'0'))
                return@filter false
        }
        true
    })
    {
        var str = i.toString()

        min = min(str.length + abs(N - i), min)

        if(i == N)
            break
    }

    print(min)

}

fun getMaxValue(N : Int, range_set : HashSet<Int>) : Int
{
    for(i in N .. 1000000)
    {
        var isFinish = true
        for(c in i.toString())
        {
            if(!range_set.contains(c-'0'))
            {
                isFinish = false
                break
            }
        }

        if(isFinish)
            return i
    }
    return 1000000
}


fun main() {
    solution()
}