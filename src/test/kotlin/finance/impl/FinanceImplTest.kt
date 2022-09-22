package finance.impl

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations.openMocks

class FinanceImplTest {

    @InjectMocks
    lateinit var financeImpl:FinanceImpl

    @BeforeEach
    fun init(){
        openMocks(this)
    }

    @ParameterizedTest
    @ValueSource(strings = ["0","1"])
    fun `test amortization with  or yearly basis`(months: String) {
        val result = financeImpl.amortization(20000.00, 7.5,5.0,months.toInt(),false )
        println("amortization result $result")
        assert(result > 0)
    }

    @Test
    fun `test throw on amortization` (){
        assertThrows<Exception> {
            financeImpl.amortization(20000.00, 7.5,5.0,3,false )
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1,4,6,12,52,365])
    fun `test compound interest calculation`(compounding: Int){
        val result = financeImpl.compoundInterest(9.4,compounding,100000.00,1)

        when(compounding){
            1->{
                println("Annual compounding result $result")
            }
            4 -> {
                println("Semiannual compounding result $result")
            }
            6 -> {
                println("Quarterly compounding result $result")
            }
            12 -> {
                println("Monthly compounding result $result")
            }
            52 -> {
                println("Weekly compounding result $result")
            }
            else -> {
                println("Daily compounding result $result")
            }
        }
        assert(result > 0)
    }
}