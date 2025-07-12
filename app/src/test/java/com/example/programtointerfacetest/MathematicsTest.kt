package com.example.programtointerfacetest

import android.content.Context
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.programtointerfacetest.control.Controler
import com.example.programtointerfacetest.control.ControlerSingletonImpl
import com.example.programtointerfacetest.view.gui.LoginActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import kotlin.test.assertTrue

class MathematicsTest {

    private lateinit var controler: Controler

    @Before
    fun setUp(){
        val context = mock(Context::class.java) // mit Mockito z.B.
        controler = ControlerSingletonImpl.getInstance(context)!!
    }

    /**************************
     *  creating exercise     *
     **************************/
    private val answer: Int? = null
    private val answer2: Int = -123
    private val answer3: Int = 0
    private val answer4: Int = 2147483647

    data class User(val firstname: String, val lastname: String, val points: Int)

    val userList = arrayOf(
        User("Sebastian", "Müller", 420),
        User("Anna", "Huber", 180),
        User("Daniel", "Granaß", 120),
        User("Sabine", "Seier", 12),
    )


    /**
     * an new exercise will create
     */
    @Test
    fun goodTest() {
       val newExercise = this.controler.loadNewExercise()
        Assert.assertTrue(newExercise != "")
    }


    /**
     * an userlist will list correctly
     */
    @Test
    fun goodTest2() {
        val sortedList = this.userList.sortedBy { it.points }
        Assert.assertEquals(userList, sortedList)
    }


    /**
     * User submits a too big number as an answer
     */
    @Test
    fun badTest() {
        val result = answer?.let { this.controler.checkUserAnswer(it) }
        Assert.assertEquals("Your input is invalid.", result)
    }



    /**
     * User submits an to small number as an answer
     */
    @Test
    fun badTest2() {
        val result = this.controler.checkUserAnswer(answer2)
        assertTrue(result)
    }


    /**
     * User submits zero as an answer
     */
    @Test
    fun badTest3() {
        val result = this.controler.checkUserAnswer(answer3)
        Assert.assertEquals("Your input is invalid.", result)
    }

}