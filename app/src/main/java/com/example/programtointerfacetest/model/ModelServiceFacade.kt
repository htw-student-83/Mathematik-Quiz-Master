package com.example.programtointerfacetest.model

import com.example.model.accounts.AccountCreationResult
import com.example.model.accounts.TopFiveUser
import com.example.model.accounts.User

interface ModelServiceFacade {

   /**
    * create a new account after the user had sent his data
    *
    * @param firstname
    * @param lastname
    * @param password
    * @return true, when the new profile is active otherwise false
    */
   fun createAccount(firstname: String, lastname: String, password: String,
                         points: Int, logged: Boolean, activeSince: String
   ): String



   /**
    * create a new account after the user had sent his data
    *
    * @param firstname
    * @param lastname
    * @param password
    * @return true, when the new profile is active otherwise false
    */
   fun createAccount2(firstname: String, lastname: String, password: String,
                     points: Int, logged: Boolean, activeSince: String
   ): AccountCreationResult


   /**
    * load the data from the database
    * @return data from an registered user
    */
   fun reloadData(): User?


   /**
    * evaluate the users password after first check up from controler
    *
    * @param password
    * @return true, when the password is known otherwise false
    */
   fun checkPassword(password: String): String


   /**
    * user will logout after a button click on logout
    * @return true when the user is after the process inactive otherwise false
    */
   fun logout(userID: Long): Boolean


   /**
    * delete an active account
    * @return true, when the users account was deleted successfully otherwise false
    */
   fun deleteAccount(userID: Long): Boolean


   /**
    * create two random numbers for an new exercise
    *
    * @return exercise
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
    *
    * @param usersAnswert
    * @return true when the answer is correct otherwise false
    */
   fun evaluateAnswer(usersAnswert: Int): Boolean


   /**
    * persistent the points, which have been played in a round
    *
    * @param points
    */
   fun savePoints(points: Int)

   /**
    * add always one point to the already saved point in a current round, if an exercise was solved
    * successfully
    *
    * @param savedPoints
    */
   fun incrementPoints(savedPoints: Int)


   /**
    * initial the points of 0
    */
   fun setInitialPoints()


   /**
    * load the current points
    *
    * @return points
    */
   fun getCurrentPointsFromMathematics(): Int

   /**
    * load the username, who is currently logged in
    *
    * @return username
    */
   fun getLoggedUsername(): String


   /**
    * load a list about the best five user
    *
    * @return play list
    */
   fun getDataOfBestFiveUser():List<TopFiveUser?>

}