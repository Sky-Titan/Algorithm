import kotlin.math.max

class Solution {

    var max = 0

    fun solution(number: String, k: Int): String {
        var answer = ""

        backTracking(0, 0, StringBuilder(), number, number.length - k)

        return max.toString()
    }

    fun backTracking(index : Int, depth : Int, now : StringBuilder, origin : String, k : Int)
    {
        if(depth == k)
        {
            if(now.isNotEmpty())
            max = max(max, now.toString().toInt())
        }
        else
        {
            var str = now.toString()
            for(i in index until origin.length)
            {
                var next = StringBuilder(str)
                backTracking(i + 1, depth + 1, next, origin, k)

            }
        }
    }

}