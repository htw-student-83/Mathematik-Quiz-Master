package com.example.model.accounts

import com.example.model.database.DatabaseServiceFacade

internal class AccountImpl(databaseServiceFacade: DatabaseServiceFacade):AccountService {

    private val databaseIntern = databaseServiceFacade

    //TODO the class AccountImpl only knows the facade of database
    //private val database: DatabaseServiceFacade=provideDatabaseFacade()

    override fun createAccount(
        firstname: String, lastname: String, password: String,
        points: Int, logged: Boolean, activeSince: String
    ): String {
       return this.databaseIntern.saveNewUser( firstname, lastname, password, points, logged,
           activeSince)
    }

    override fun createAccount2(
        firstname: String, lastname: String, password: String,
        points: Int, logged: Boolean, activeSince: String
    ): AccountCreationResult {
        val userID = this.databaseIntern.saveNewUser2(firstname, lastname, password, points, logged,
            activeSince)
        return if(userID !=1L){
            AccountCreationResult.Success(userID)
        }else{
            AccountCreationResult.Error("Du bist bereits registriert.")
        }
    }

    override fun reloadData(): User? {
        return this.databaseIntern.reloadUserData()
    }

    override fun authenticate(password: String): String {
        return this.databaseIntern.checkUserPW(password)
    }

    override fun logout(userID: Long): Boolean {
        return this.databaseIntern.logout(userID)
    }

    override fun relaodLoggedUserName(): String {
        return this.databaseIntern.relaodLoggedUser()
    }

    override fun deleteAccount(userID: Long): Boolean {
        return this.databaseIntern.deleteAccount(userID)
    }

    override fun loadDataOfBestFive(): List<TopFiveUser?> {
        return this.databaseIntern.laodTopFiveUsers()
    }

}