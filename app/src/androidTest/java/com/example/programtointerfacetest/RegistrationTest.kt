package com.example.programtointerfacetest

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.intent.Intents
import com.example.programtointerfacetest.view.gui.RegistrationActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RegistrationTest {

    private val firstname: String = "daniel"
    private val firstname2: String = "Daniel"
    private val lastname: String = "Granaß"
    private val password: String = "Daniel1234"

    @get:Rule
    val composeTestRule = createAndroidComposeRule<RegistrationActivity>()

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }


    /**
     * user starts registration with invalid firstname
     */
    @Test
    fun goodTest() {
        composeTestRule.onNodeWithText("firstname...").performTextInput(firstname)
        composeTestRule.onNodeWithText("lastname...").performTextInput(lastname)
        composeTestRule.onNodeWithText("password...").performTextInput(password)
        composeTestRule.onNodeWithText("Account erstellen").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("errorText").assertIsDisplayed()
            .assertTextContains( "Your firstname doesn't start with an UpperCase")
    }


    /**
     * User starts a registration without to enter a password
     * This test case is representative for all in this app like this
     */
    @Test
    fun badTest2() {
        composeTestRule.onNodeWithText("firstname...").performTextInput(firstname2)
        composeTestRule.onNodeWithText("lastname...").performTextInput(lastname)
        composeTestRule.onNodeWithText("password...").performTextInput("")
        composeTestRule.onNodeWithText("Account erstellen").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("errorText").assertIsDisplayed()
            .assertTextContains( "Your password is missing")
    }


    /**
     * User starts a registration with no data
     */
    @Test
    fun badTest3() {
        composeTestRule.onNodeWithText("firstname...").performTextInput("")
        composeTestRule.onNodeWithText("lastname...").performTextInput("")
        composeTestRule.onNodeWithText("password...").performTextInput("")
        composeTestRule.onNodeWithText("Account erstellen").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("errorText").assertIsDisplayed()
            .assertTextContains( "All fields must be filled.")
    }

}