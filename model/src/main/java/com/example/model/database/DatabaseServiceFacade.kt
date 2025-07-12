package com.example.model.database

import com.example.model.accounts.TopFiveUser
import com.example.model.accounts.User

interface DatabaseServiceFacade {

    /**
     * persistent an new user into the database
     *
     * @param firstname
     * @param lastname
     * @param password
     * @param points
     * @param logged
     * @param activeSince
     * @return
     */
    fun saveNewUser(
        firstname: String, lastname: String, password:String,
        points: Int = 0, logged: Boolean = false, activeSince: String,
    ): String

    fun saveNewUser2(
        firstname: String, lastname: String, password:String,
        points: Int = 0, logged: Boolean = false, activeSince: String,
    ): Long

    /**
     * store the current points after a game round
     *
     * @param points
     * @return true, if the points was stored successfully otherwise false
     */
    fun saveGamePoints(points: Int): Boolean

    /**
     * relaod the user details from the database
     *
     * @return user details or null, if the data couldn't reload
     */
    fun reloadUserData(): User?

    /**
     * delete an exists account from the database
     * @return true, if the process was successfully otherwise false
     */
    fun deleteAccount(userID: Long): Boolean

    /**
     * check is the pw stored in the database for a successful login
     *
     * @param password
     * @return true if the pw is knwon otherwise false
     */
    fun checkUserPW(password: String): String


    /**
     * change the state logged from true of false
     *
     * @param userID
     * @return true if the state was updated successfully otherwise false
     */
    fun logout(userID: Long): Boolean


    /**
     * load the first and lastname of the user, whom state logged is true
     *
     * @return first- and lastname
     */
    fun relaodLoggedUser(): String



    /**
     * load the first and lastname of the user, whom state logged is true
     *
     * @return first- and lastname
     */
    fun laodTopFiveUsers(): List<TopFiveUser?>



}