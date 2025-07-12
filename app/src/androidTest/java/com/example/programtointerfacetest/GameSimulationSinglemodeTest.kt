package com.example.programtointerfacetest

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import com.example.programtointerfacetest.view.gui.GameRunSinglemodeActivity
import com.example.programtointerfacetest.view.gui.GratulationEndOfARoundSinglemodeActivity
import com.example.programtointerfacetest.view.gui.HauptmenuActivity
import com.example.programtointerfacetest.view.gui.QuestionCancelCurrentRoundSinglemodeActivity
import com.example.programtointerfacetest.view.gui.QuestionNewRoundSinglemodeActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameSimulationSinglemodeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<GameRunSinglemodeActivity>()

    @Before
    fun setup() {
        Intents.init()
    }

    /**
     * At first the start point is zero
     */
    @Test
    fun goodTest() {
        composeTestRule.onNodeWithText("0").assertIsDisplayed().assertTextContains("0")
    }

    /**
     * User solves an exercise not correctly and not point is awarded
     */
    @Test
    fun badTest() {
        composeTestRule.onNodeWithText("0").assertTextContains("0")
        composeTestRule.onNodeWithText("Lösung").performTextInput("14")
        composeTestRule.onNodeWithText("Bestätigen").performClick()
        composeTestRule.onNodeWithText("1").assertIsDisplayed().assertTextContains("1")
    }

    /**
     * User uses the button "Beenden", but cancels the process again
     * This scenario stands for single- and multiplayer mode
     */
    @Test
    fun goodTest3() {
        composeTestRule.onNodeWithText("Beenden").performClick()
        intended(hasComponent(QuestionCancelCurrentRoundSinglemodeActivity::class.java.name))
        composeTestRule.onNodeWithText("Nein").performClick()
        intended(hasComponent(GameRunSinglemodeActivity::class.java.name))
    }


    /**
     * User cancel the current game run goes back to main menu
     */
    @Test
    fun goodTest2() {
        composeTestRule.onNodeWithText("Beenden").performClick()
        intended(hasComponent(QuestionCancelCurrentRoundSinglemodeActivity::class.java.name))
        composeTestRule.onNodeWithText("Ja").performClick()
        intended(hasComponent(HauptmenuActivity::class.java.name))
    }


    /**
     * When the time is over the next activity is active
     */
    @Test
    fun goodTest6() {
        val testClock = composeTestRule.mainClock
        testClock.autoAdvance = false
        testClock.advanceTimeBy(5000)
        intended(hasComponent(GratulationEndOfARoundSinglemodeActivity::class.java.name))
    }


}