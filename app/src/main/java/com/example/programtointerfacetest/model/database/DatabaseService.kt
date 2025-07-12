package com.example.programtointerfacetest.model.database

import com.example.model.accounts.TopFiveUser
import com.example.model.accounts.User


internal interface DatabaseService {

    fun saveNewUser(firstname: String, lastname: String, password:String,
        points: Int = 0 , logged: Boolean = false, activeSince: String,
    ): String

    fun saveNewUser2(firstname: String, lastname: String, password:String,
                    points: Int = 0 , logged: Boolean = false, activeSince: String,
    ): Long

    fun checkUserPW(password: String): String

    fun saveGamePoints(points: Int): Boolean

    fun reloadUserData(): User?

    fun deleteAccount(userID: Long): Boolean

    fun logout(userID: Long): Boolean

    fun reloadLoggedUser(): String

    fun loadUserDataForTopFive(): List<TopFiveUser?>

}