package com.example.model.mathematics

import com.example.model.database.DatabaseServiceFacade

internal class MathematicsFacadeImpl(
    database: DatabaseServiceFacade,
    private val exercise: MathematikService = MathematicsImpl(database),
): MathematicsFacade {

    override fun generateNewExercise(): String {
        return this.exercise.generateNewExercise()
    }

    override fun startTimer() {
        TODO("Not yet implemented")
    }

    override fun stopTimer() {
        TODO("Not yet implemented")
    }

    override fun evaluateAnswer(answer: Int): Boolean {
        return this.exercise.evaluateAnswer(answer)
    }

    override fun savePoints(points: Int) {
        return this.exercise.savePoints(points)
    }

    override fun incrementPoints(savedPoints: Int) {
        return this.exercise.incrementPoints(savedPoints)
    }

    override fun setInitialPoints() {
        return this.exercise.setInitialPoints()
    }

    override fun getCurrentPoints(): Int {
        return this.exercise.getCurrentPoints()
    }

}

fun provideExerciseFacade(database: DatabaseServiceFacade): MathematicsFacade =
    MathematicsFacadeImpl(database = database)

