package com.example.programtointerfacetest

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.intent.Intents
import com.example.programtointerfacetest.view.gui.LoginActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<LoginActivity>()

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }


    /**
     * user starts the login without to enter a password
     */
    @Test
    fun badTest() {
        composeTestRule.onNodeWithTag("EditFieldTest").performTextInput("")
        composeTestRule.onNodeWithText("login").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("errorText").assertIsDisplayed().assertTextContains("Your password is missing")
    }

    /**
     * user starts the login with a too short password
     */
    @Test
    fun badTest2() {
        composeTestRule.onNodeWithTag("EditFieldTest").performTextInput("Dann")
        composeTestRule.onNodeWithText("login").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("errorText").assertIsDisplayed().assertTextContains("Your password is too short")
    }
    /**
     * user starts the login with a too long password
     */
    @Test
    fun badTest3() {
        composeTestRule.onNodeWithTag("EditFieldTest").performTextInput("Danny123454fgdfgdfgfg")
        composeTestRule.onNodeWithText("login").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("errorText").assertIsDisplayed().assertTextContains("Your password is too long")
    }


/*
    @Test
    fun loginButton_click_triggersCallback() {
        var clicked = false

        composeTestRule.setContent {
            modell.GetModelButton(
                buttonText = "Login",
                backgroundColor = Color.Blue,
                fontcolor = Color.White,
                buttonWidth = 200.dp,
                onClick = { clicked = true } // <- überprüfbarer Callback
            )
        }

        // Button finden und klicken
        composeTestRule.onNodeWithTag("testButton").performClick()

        // Prüfen, ob Callback ausgelöst wurde
        assert(clicked)
    }

    @Test
    fun acount_erstellen_Button_click_triggersCallback() {
        var clicked = false

        composeTestRule.setContent {
            modell.GetModelButton(
                buttonText = "Account erstellen",
                backgroundColor = Color.Blue,
                fontcolor = Color.White,
                buttonWidth = 200.dp,
                onClick = { clicked = true } // <- überprüfbarer Callback
            )
        }

        // Button finden und klicken
        composeTestRule.onNodeWithTag("testButton").performClick()

        // Prüfen, ob Callback ausgelöst wurde
        assert(clicked)
    }

 */

}