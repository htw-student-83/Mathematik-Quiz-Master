package com.example.programtointerfacetest.model

import com.example.model.accounts.AccountCreationResult
import com.example.model.accounts.AccountFacade
import com.example.model.accounts.TopFiveUser
import com.example.model.accounts.User
import com.example.model.mathematics.MathematicsFacade


class ModelServiceFacadeImpl(
    private val account: AccountFacade,
    private val mathematics: MathematicsFacade
): ModelServiceFacade {

    //TODO the class QuizServiceFacadetImpl only knows AccountFacade

    /*
       TODO Ziel soll es sein, dass zunächst das gesamte System zusammengestellt wird,
        also alle Objekte von den jeweiligen Klassen erzeugt werden, so dass am Ende
        die App genutzt werden kann
    */

    //TODO get only an instance for database for forwarding to sub packages
    //private val database: DatabaseServiceFacade = DatabaseServiceFacadeImpl.getInstance(context)

    //TODO den jeweiligen Referenzen die Instanz von database übergeben
    //private val account: AccountFacade = provideAccountFacade(database)
    // private val mathematics: MathematicsFacade = provideExerciseFacade(database)


    override fun createAccount(firstname: String, lastname: String,
        password: String, points: Int, logged: Boolean, activeSince: String
    ): String {
        return this.account.createAccount(firstname, lastname,
            password, points, logged, activeSince)
    }

    override fun createAccount2(firstname: String, lastname: String,
                               password: String, points: Int, logged: Boolean, activeSince: String
    ): AccountCreationResult {
        return this.account.createAccount2(firstname, lastname,
            password, points, logged, activeSince)
    }

    override fun reloadData(): User? {
        return this.account.reloadData()
    }

    override fun checkPassword(password: String): String {
        return this.account.authenticate(password)
    }

    override fun logout(userID: Long): Boolean {
        return this.account.logout(userID)
    }

    override fun deleteAccount(userID: Long): Boolean {
        return this.account.deleteAccount(userID)
    }

    override fun generateNewExercise(): String {
        return this.mathematics.generateNewExercise()
    }

    override fun startTimer() {
        return this.mathematics.startTimer()
    }

    override fun stopTimer() {
        return this.mathematics.stopTimer()
    }

    override fun evaluateAnswer(usersAnswer: Int): Boolean {
        return this.mathematics.evaluateAnswer(usersAnswer)
    }

    override fun savePoints(points: Int) {
        return this.mathematics.savePoints(points)
    }

    override fun incrementPoints(savedPoints: Int) {
        return this.mathematics.incrementPoints(savedPoints)
    }

    override fun setInitialPoints() {
        return this.mathematics.setInitialPoints()
    }

    override fun getCurrentPointsFromMathematics(): Int {
        return this.mathematics.getCurrentPoints()
    }

    override fun getLoggedUsername(): String {
        return this.account.relaodLoggedUserName()
    }

    override fun getDataOfBestFiveUser(): List<TopFiveUser?> {
        return this.account.loadDataOfBestFiveUser()
    }

}