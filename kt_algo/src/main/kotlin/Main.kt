

fun solution() {

    var N = readLine()!!.toInt()

    var result = 665
    var count = 0

    while(count < N)
    {
        result++
        if(isRight(result))
            count++
    }
    print(result)

}

//세상의 종말 수가 맞는지 확인
fun isRight(current : Int) : Boolean
{
    var str = current.toString()

    var count = 0

    for(i in 0 until str.length)
    {
        if(str[i] == '6')
            count++
        else
            count = 0

        if(count == 3)
            return true
    }
    return false
}


fun main() {
    solution()
}