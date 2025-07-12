package com.example.model.mathematics

import com.example.model.database.DatabaseServiceFacade
import kotlin.random.Random

internal class MathematicsImpl(databaseServiceFacade: DatabaseServiceFacade) : MathematikService {

    private val product: IProductOfMultiplication = ProductOfMultiplication(0)
    private val databaseIntern = databaseServiceFacade
    private var currentPoints: Int = 0

    override fun generateNewExercise(): String {
        val randomNumber1 = Random.nextInt(1,11)
        val randomNumber2 = Random.nextInt(11,21)
        val result =  randomNumber1 * randomNumber2
        this.product.setProduct(result)
        return " $randomNumber1 x $randomNumber2 = "
    }

    override fun startTimer() {
        TODO("Not yet implemented")
    }

    override fun stopTimer() {
        TODO("Not yet implemented")
    }

    override fun evaluateAnswer(answer: Int): Boolean {
        val solution = this.product.getProduct()
        if(answer == solution){
            incrementPoints(this.currentPoints)
            return true
        }
        return false
    }

    override fun savePoints(points: Int) {
        this.databaseIntern.saveGamePoints(points)
    }

    override fun incrementPoints(savedPoints: Int) {
        this.currentPoints = savedPoints + 1
    }

    override fun setInitialPoints() {
        this.currentPoints = 0
    }

    override fun getCurrentPoints(): Int {
        return this.currentPoints
    }


}