package com.guilhermemarx14.mymovieapp.calculator

class CalculatorFloatImpl : CalculatorInterface<Float> {

    override fun sum(num1: Float, num2: Float) = num1 + num2
    override fun multiplication(num1: Float, num2: Float) = num1 * num2
}