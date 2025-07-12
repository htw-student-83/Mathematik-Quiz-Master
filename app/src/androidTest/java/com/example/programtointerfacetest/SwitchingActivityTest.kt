package com.example.programtointerfacetest

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import com.example.programtointerfacetest.view.gui.AccountActivity
import com.example.programtointerfacetest.view.gui.HauptmenuActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SwitchingActivityTest {

    @get:Rule
    val composeTestRule =  createAndroidComposeRule<HauptmenuActivity>()

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    /**
     * after the user clicked on button "Account" the details about the logged user will be displayed
     * This test scenario stands for all similar tests like this.
     * */
    @Test
    fun goodTest() {
        composeTestRule.onNodeWithText("Account").performClick()
        intended(hasComponent(AccountActivity::class.java.name))
    }

    /*

    /**
     * user starts the app
     */
    @Test
    fun goodTest2() {
        composeTestRule.onNodeWithTag("startButton").performClick()
        intended(hasComponent(LoginActivity::class.java.name))
    }

     */

}