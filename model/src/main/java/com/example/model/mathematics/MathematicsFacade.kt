package com.example.model.mathematics

interface MathematicsFacade {
    /**
     * create two random numbers for an new exercise
     */
    fun generateNewExercise(): String


    /**
     * start the countdown for a new round
     */
    fun startTimer()


    /**
     * stop the countdown suddenly when the user plans to break the current round
     */
    fun stopTimer()


    /**
     * evaluate the users answer
     * @return true when the answer is correct otherwise false
     */
    fun evaluateAnswer(answer: Int): Boolean


    /**
     * persistent the points, which have been played in a round
     */
    fun savePoints(points: Int)


    /**
     * add always one point to the already saved point in a current round, if an exercise was solved
     * successfully
     * @return the new result of points for one user
     */
    fun incrementPoints(savedPoints: Int)


    /**
     * initial the points of 0
     *
     * @return initial value
     */
    fun setInitialPoints()

    /**
     * get the current points in a game run
     *
     * @return updated points
     */
    fun getCurrentPoints(): Int
}