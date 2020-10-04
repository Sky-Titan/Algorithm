import org.junit.Test

import org.junit.Assert.*
import java.util.*


internal class MainKtTest{

    @Test
    fun test(){
        var isPrime = BooleanArray(10001)

        for(i in 2 .. 10000)
        {
            if(!isPrime[i])
            {
                for(j in 2 * i .. 10000 step i)
                    isPrime[j] = true
            }
        }
        isPrime.forEachIndexed { index, b -> isPrime[index] = !b}

        var isPrime2 = BooleanArray(10001, { true })

        for(i in 2 .. 10000)
        {
            for(j in 2 * i .. 10000 step i)
                isPrime2[j] = false
        }
    }
}


