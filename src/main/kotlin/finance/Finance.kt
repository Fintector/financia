package finance


interface Finance {
    fun amortization(p:Double, r:Double,t:Double, yearOrMonth:Int,payFromBeginning:Boolean):Double
    fun compoundInterest(r:Double, n: Int, p: Double, t:Int): Double
}