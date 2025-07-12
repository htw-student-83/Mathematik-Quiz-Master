package com.example.programtointerfacetest

import android.content.Context
import com.example.model.accounts.AccountCreationResult
import com.example.programtointerfacetest.control.Controler
import com.example.programtointerfacetest.control.ControlerSingletonImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AccountTest {

    private lateinit var controler: Controler

    @Before
    fun setUp(){
        val context = mock(Context::class.java) // mit Mockito z.B.
        controler = ControlerSingletonImpl.getInstance(context)!!
    }

    /************************************
     *            Tests values          *
     ************************************/
    private val firstname = "Daniel"
    private val firstname2 = "Daniel"
    private val lastname = "Granaß"
    private val lastname2 = "Granaß"
    private val password2 = "Dann"
    private val password3 = "DannDann3456zhujigtrre"
    private val password4 = ""
    val date = "23.07.25"
    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    val activeDate: Date = dateFormat.parse(date) ?: Date()
    val activeSinceString: String = dateFormat.format(activeDate)


    /*************************************************
     * User Registration                             *
     *************************************************/


    /**
     * user registers with a too short password
     */
    @Test
    fun badTest2() {
       val message =
           this.controler.checkUerDataForRegistration2(firstname, lastname, password2, 0,
               false, activeSinceString           )
        Assert.assertTrue(message is AccountCreationResult.Error)
    }

    /**
     * user registers with a too long password
     */
    @Test
    fun badTest3() {
        val message =
            this.controler.checkUerDataForRegistration2(firstname, lastname, password3, 0,
                false, activeSinceString
            )
        Assert.assertTrue(message is AccountCreationResult.Error)
    }

    /**
     * user starts registration without to fill all fields
     */
    @Test
    fun badTest4() {
        val message =
            this.controler.checkUerDataForRegistration2(firstname, lastname, password4, 0, false,
                activeSinceString
            )
        Assert.assertTrue(message is AccountCreationResult.Error)
    }


    /**
     * user starts registration and keeps all fields empty
     */
    @Test
    fun badTest8() {
        val message =
            this.controler.checkUerDataForRegistration2(firstname2, lastname2, password4, 0, false,
                activeSinceString
            )
        Assert.assertTrue(message is AccountCreationResult.Error)
    }

    /*************************************************
     * User Login                                    *
     *************************************************/

    /**
     * user starts login with a too short password
     */
    @Test
    fun badTest5() {
        val result = this.controler.checkUserPassword(password2)
        Assert.assertEquals("Your password is too short", result)
    }


    /**
     * user starts login with a too long password
     */
    @Test
    fun badTest6() {
        val result = this.controler.checkUserPassword(password3)
        Assert.assertEquals("Your password is too long", result)
    }


    /**
     * user start login without to enter the password
     */
    @Test
    fun badTest7() {
        val result = this.controler.checkUserPassword(password4)
        Assert.assertEquals("Your password is missing", result)
    }


}