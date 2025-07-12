package com.example.programtointerfacetest

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import com.example.programtointerfacetest.view.gui.AccountDeletingActivity
import com.example.programtointerfacetest.view.gui.HauptmenuActivity
import com.example.programtointerfacetest.view.gui.LoginActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AccountDeletingTest {
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
/*
    /**
     * after the user clicked on "Account löschen" the account will delete successfully
     * */
    @Test
    fun goodTest() {
        composeTestRule.onNodeWithText("Account löschen").performClick()
        intended(hasComponent(AccountDeletingActivity::class.java.name))
        Thread.sleep(2000)
        intended(hasComponent(LoginActivity::class.java.name))
    }

 */

}