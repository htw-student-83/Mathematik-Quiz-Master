package com.example.programtointerfacetest.model.database

import android.content.Context
import com.example.model.accounts.TopFiveUser
import com.example.model.accounts.User
import com.example.model.database.DatabaseServiceFacade

internal class DatabaseServiceFacadeImpl(
    context: Context,

    /*
        TODO the intern class DatabaseServiceFacadeImpl only knows
         the intern class DatabaseServiceImpl with an access to the methods
     */

    private val databaseIntern: DatabaseService = DatabaseServiceImpl(context)
)
    : DatabaseServiceFacade {

    companion object{
        private var instance: DatabaseServiceFacadeImpl? = null
        fun getInstance(context: Context): DatabaseServiceFacade?{
            if(instance == null){
                instance = DatabaseServiceFacadeImpl(context)
                return instance
            }
            return instance
        }
    }

    override fun saveNewUser(
        firstname: String, lastname: String, password: String,
        points: Int, logged: Boolean, activeSince: String
    ): String {
        return this.databaseIntern.saveNewUser(firstname, lastname, password, points, logged, activeSince)
    }

    override fun saveNewUser2(
        firstname: String,
        lastname: String,
        password: String,
        points: Int,
        logged: Boolean,
        activeSince: String
    ): Long {
        
        return this.databaseIntern.saveNewUser2(firstname, lastname, password, points, logged, activeSince)
    }

    override fun saveGamePoints(points: Int): Boolean {
        return this.databaseIntern.saveGamePoints(points)
    }

    override fun reloadUserData(): User? {
        return this.databaseIntern.reloadUserData()
    }

    override fun deleteAccount(userID: Long): Boolean {
        return this.databaseIntern.deleteAccount(userID)
    }

    override fun checkUserPW(password: String): String {
       return this.databaseIntern.checkUserPW(password)
    }

    override fun logout(userID: Long): Boolean {
        return this.databaseIntern.logout(userID)
    }

    override fun relaodLoggedUser(): String {
        return this.databaseIntern.reloadLoggedUser()
    }

    override fun laodTopFiveUsers(): List<TopFiveUser?> {
        return this.databaseIntern.loadUserDataForTopFive()
    }


}

/*
fun provideDatabaseFacade(context: Context)
: DatabaseServiceFacade = DatabaseServiceFacadeImpl(context)
 */