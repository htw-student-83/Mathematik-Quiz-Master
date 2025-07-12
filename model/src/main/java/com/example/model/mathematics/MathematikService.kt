package com.example.model.mathematics

internal interface MathematikService {

    fun generateNewExercise(): String


    fun startTimer()


    fun stopTimer()


    fun evaluateAnswer(answer: Int): Boolean


    fun savePoints(points: Int)


    fun incrementPoints(savedPoints: Int)


    fun setInitialPoints()


    fun getCurrentPoints(): Int

}