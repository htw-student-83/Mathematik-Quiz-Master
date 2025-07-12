package com.example.model.accounts

interface AccountFacade {

    /**
     * create a new account after the user had sent his data
     * @ return true, when the new profile is active otherwise false
     */
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


    /**
     * load the data from the database
     * @return data from an registered user
     */
    fun reloadData(): User?


    /**
     * evaluate the users password after a click on the button login
     * @param password
     * @return true, when the input is correct otherwise the user will see a toast
     */
    fun authenticate(password:String): String


    /**
     * user will logout after a button click on logout
     * @return true when the user is after the process inactive otherwise false
     */
    fun logout(userID: Long): Boolean

    /**
     * TODO
     *
     * @return
     */
    fun relaodLoggedUserName(): String


    /**
     * delete an active account
     * @return true, when the users account was deleted successfully otherwise false
     */
    fun deleteAccount(userID: Long): Boolean


    fun loadDataOfBestFiveUser(): List<TopFiveUser?>


}