package com.example.model.accounts

import com.example.model.database.DatabaseServiceFacade

internal class AccountFacadeImpl(
    //database: DatabaseServiceFacade,

    //TODO the class AccountFacadeImpl only knows the class AccountImpl

    private val database: DatabaseServiceFacade,

): AccountFacade {
    private val account: AccountService = AccountImpl(database)

    override fun createAccount(firstname: String, lastname: String, password: String,
        points: Int, logged: Boolean, activeSince: String
    ): String {
        return  this.account.createAccount(firstname, lastname, password, points, logged,
            activeSince)
    }

    override fun createAccount2(firstname: String, lastname: String, password: String,
                               points: Int, logged: Boolean, activeSince: String
    ): AccountCreationResult {
        return  this.account.createAccount2(firstname, lastname, password, points, logged,
            activeSince)
    }

    override fun reloadData(): User? {
        return this.account.reloadData()
    }

    override fun authenticate(password: String): String {
        return this.account.authenticate(password)
    }

    override fun logout(userID: Long): Boolean {
        return this.account.logout(userID)
    }

    override fun relaodLoggedUserName(): String {
        return this.account.relaodLoggedUserName()
    }

    override fun deleteAccount(userID: Long): Boolean {
        return this.account.deleteAccount(userID)
    }

    override fun loadDataOfBestFiveUser(): List<TopFiveUser?> {
        return this.account.loadDataOfBestFive()
    }

}

fun provideAccountFacade(database: DatabaseServiceFacade)
: AccountFacade = AccountFacadeImpl(database)
