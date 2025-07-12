package com.example.programtointerfacetest.control

import com.example.model.accounts.AccountCreationResult
import com.example.model.accounts.TopFiveUser
import com.example.model.accounts.User
import com.example.programtointerfacetest.network.protocolengine.ConnectionType

interface Controler {

    /**
     * registered an new user
     *
     * @param firstname
     * @param lastname
     * @param password
     * @param points
     * @param logged
     * @param activeSince
     * @return AccountCreationResult -> success if the process was successfully or error when
     * something was wrong
     */
    fun checkUerDataForRegistration2(
        firstname: String, lastname: String, password: String,
        points: Int, logged: Boolean, activeSince: String
    ): AccountCreationResult

    /**
     * evaluate the user's password
     *
     * @param password
     * @return true if the password is valid otherwise false
     */
    fun checkUserPassword(password: String): String

    /**
     * starts the logout for the user
     *
     * @return true if the status of an user is inactive
     */
    fun userLogout(userID: Long): Boolean

    /**
     * starts the process to delete an account
     *
     * @return true, if an account is deleted successfully otherwise false
     */
    fun userAccountDelete(userID: Long): Boolean

    /**
     * starts the process to reload user data
     *
     * @return persistent data from the database or null, if something was wrong
     */
    fun userDataReload(): User?

    /**
     * start a new round for single or multiplayer mode
     */
    fun startNewGameRound()

    /**
     * to stop the countdown and switch to another activity
     */
    fun stopCurrentRound()

    /**
     * displayed an exercise
     *
     * @return exercise
     */
    fun loadNewExercise(): String

    /**
     * check is the edit field for user's answer empty or not
     *
     * @param answer users input
     * @return true, if an user has input a number otherwise false
     */
    fun checkUserAnswer(answer: Int): Boolean

    /**
     * reads the result for a round at multiplayermode and forwards it to another place
     *
     * @param points
     */
    fun saveResultForTopFive(points: Int)

    /**
     * add to the result one point more
     *
     * @param points
     */
    fun incrementPoints(points: Int)

    /**
     * give game points to the output stream to transport above bluetooth or wifidirecdt
     *
     * @param points
     * @param type Bluetooth, WifiDirect
     */
    fun sendGamePointsAboveOutputStream(points: Int, type: ConnectionType)

    /**
     * read game points from the inputstream
     *
     * @return game points
     */
    fun readGamePointsFromInputStream(): Int

    /**
     * start the process to connect with another player above bluetooth
     */
    fun startConnectionAboveBluetooth()

    /**
     * start the process to connect with another player above wifi direct
     */
    fun startConnectionAboveWifiDirect()

    /**
     * load the updated points of user
     *
     * @return points
     */
    fun loadCurrentPoints(): Int

    /**
     * load the name of logged user
     *
     * @return name
     */
    fun reloadLoggedUserForARunningGame(): String


    /**
     * reset the current user points to 0
     */
    fun resetCurrentPoints()


    /**
     * get a list about the top five of the quiz
     *
     * @return list of user
     */
    fun getTheTopFive(): List<TopFiveUser?>

}