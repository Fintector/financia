package finance.impl

import common.getBuildNumerator
import finance.Finance
import java.math.RoundingMode

class FinanceImpl : Finance {

    /**
     * Amortization function is used to periodically lower the book value of a
     * loan or an intangible asset over a set period of time. Concerning a loan,
     * amortization focuses on spreading out loan payments over time.
     * When applied to an asset, amortization is similar to depreciation
     *
     * @param p - amount of loan (Principal amount)
     * @param r - rate applied
     * @param t - time period of in yearly or monthly considering <yearOrMonth> selected with the selection done
     * @param yearOrMonth - if applied 0 refers to yearly payment if applied 1 refers to monthly payment
     * @param payFromBeginning - boolean for either to start paying immediately or not
     * @return
     */
    override fun amortization(
        p: Double,
        r: Double,
        t:Double,
        yearOrMonth: Int,
        payFromBeginning: Boolean
    ): Double {
        val numerator: Double
        val denominator: Double
        val ratePerPeriod = r / 12 / 100;
        when(yearOrMonth){
            0 -> {
                numerator = getBuildNumerator(payFromBeginning,t * 12,ratePerPeriod);
                denominator = Math.pow((1 + ratePerPeriod), t * 12) - 1
            }
            1->{
                numerator = getBuildNumerator(payFromBeginning,t,ratePerPeriod)
                denominator = Math.pow((1 + ratePerPeriod), t) - 1;
            }
            else -> {
                throw Exception("Please Define the mode 1 for months and 0 for years")
            }
        }
        val am: Double = p * (numerator / denominator)
        return ((am * 100)/100).toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
    }

    /**
     * Compound interest
     *
     * @param r annual interest rate
     * @param n frequency of receiving interest  i.e
     * n - 12 : Monthly,
     * n - 52 : Weekly,
     * n = 365 : Daily ,
     * n = 1 : Annually,
     * n = 6 : semiannually,
     * n = 4 : quarterly
     * @param p principal present value
     * @param t time period
     * @return
     */
    override fun compoundInterest(r:Double, n: Int, p: Double, t:Int): Double {
        val ci = p * Math.pow((1 + (r/100/ n)), (n * t).toDouble())
        return (ci * 100/ 100).toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
    }
}