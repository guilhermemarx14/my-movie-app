package com.guilhermemarx14.mymovieapp.calculator

import com.google.common.truth.Truth
import com.guilhermemarx14.mymovieapp.calculator.CalculatorFloatImpl
import org.junit.Before
import org.junit.Test

class CalculatorFloatImplTest {
    lateinit var calculator: CalculatorFloatImpl

    @Before
    fun setup() {
        calculator = CalculatorFloatImpl()
    }

    @Test
    fun sum_whenReceivingPositiveNumbers() {
        // Given
        val number1 = 2.0F
        val number2 = 3.0F

        // When
        val result = calculator.sum(number1, number2)

        // Then
        Truth.assertThat(result).isEqualTo(5.0)
    }

    @Test
    fun multiplication_whenReceivingPositiveNumbers() {
        // Given
        val number1 = 2.0F
        val number2 = 3.0F

        // When
        val result = calculator.multiplication(number1, number2)

        // Then
        Truth.assertThat(result).isEqualTo(6.0)
    }


}