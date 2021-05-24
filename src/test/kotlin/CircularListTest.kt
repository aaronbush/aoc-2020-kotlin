import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CircularListTest {

    @Test
    fun get() {
        val circularList = CircularList(listOf(1, 2, 3, 4))
        assertEquals(4, circularList.size)
        assertEquals(1, circularList[0])
        assertEquals(2, circularList[1])
        assertEquals(3, circularList[2])
        assertEquals(4, circularList[3])
        assertEquals(1, circularList[4])
    }
}