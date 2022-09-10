package com.guilhermemarx14.mymovieapp.calculator

import com.google.common.truth.Truth.assertThat
import com.guilhermemarx14.mymovieapp.calculator.CalculatorIntImpl
import com.guilhermemarx14.mymovieapp.calculator.Operations
import org.junit.Before
import org.junit.Test

class CalculatorIntImplTest {
    lateinit var calculator: CalculatorIntImpl

    @Before
    fun setup() {
        calculator = CalculatorIntImpl()
    }

    @Test
    fun sum_whenReceivingPositiveNumbers() {
        // Given
        val number1 = 2
        val number2 = 3

        // When
        val result = calculator.sum(number1, number2)

        // Then
        assertThat(result).isEqualTo(5)
    }

    @Test
    fun multiplication_whenReceivingPositiveNumbers() {
        // Given
        val number1 = 2
        val number2 = 3

        // When
        val result = calculator.multiplication(number1, number2)

        // Then
        assertThat(result).isEqualTo(6)
    }


}