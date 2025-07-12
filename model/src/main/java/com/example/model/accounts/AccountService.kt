package com.example.model.accounts

internal interface AccountService {

    fun createAccount(
        firstname: String,
        lastname: String,
        password:String,
        points: Int,
        logged: Boolean,
        activeSince: String
    ): String

    fun createAccount2(
        firstname: String,
        lastname: String,
        password:String,
        points: Int,
        logged: Boolean,
        activeSince: String
    ): AccountCreationResult


    fun reloadData(): User?


    fun authenticate(password:String): String


    fun logout(userID: Long): Boolean


    fun relaodLoggedUserName(): String


    fun deleteAccount(userID: Long): Boolean


    fun loadDataOfBestFive(): List<TopFiveUser?>
}