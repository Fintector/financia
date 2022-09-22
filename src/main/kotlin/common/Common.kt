package common

import kotlin.math.pow

fun getBuildNumerator(payAtBeginning:Boolean, period: Double, ratePerPeriod: Double):Double{
    var numInterestAccruals = period
    if(payAtBeginning){
        numInterestAccruals -= 1
    }
    return ratePerPeriod * (1 + ratePerPeriod).pow(numInterestAccruals)
}