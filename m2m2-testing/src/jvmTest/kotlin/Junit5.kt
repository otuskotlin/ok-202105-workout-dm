import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import kotlin.test.Test
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS) //Датё возможность запускать без статик.
class Junit5 {


    @BeforeAll
    fun tearUp(){
        println("before all  tests")
    }

    @Test
    fun trueTest(){
        assertEquals("1", 1.toString())
    }

    //Замена статик, в Java
    companion object{
        @BeforeAll
        @JvmStatic
        fun tearUp1(){
            println("Без статик")
        }
    }
}