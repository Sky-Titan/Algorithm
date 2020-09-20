import java.util.*
import kotlin.collections.ArrayList

fun solution(){

    var N = readLine()!!.toInt()
    var M = readLine()!!.toInt()

    var recommendation = IntArray(101)
    var time = IntArray(101)

    var queue = PriorityQueue<Int>(
        {o1, o2 ->
            if(recommendation[o1] == recommendation[o2])
                time[o1] - time[o2]
            else
                recommendation[o1] - recommendation[o2]
        }
    )

    var str = readLine()!!.split(" ")

    for(i in 0 until str.size)
    {
        var student = str[i].toInt()

        recommendation[student] ++

        if(!queue.contains(student))
        {
            //새로 게시됨 -> 게시 시간 갱신
            time[student] = i

            //사진틀 꽉참
            if(queue.size == N)
                recommendation[queue.poll()] = 0
            queue.offer(student)

        }
        //이미 게시되어있음
        else
        {
            //새로 삽입해서 다시 정렬되도록
            queue.remove(student)
            queue.offer(student)
        }
    }

    var result = ArrayList<Int>(queue)

    result.sort()

    for(a in result)
        print("$a ")
}


fun main() {
    solution()
}