package com.example.programtointerfacetest

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import com.example.programtointerfacetest.view.gui.GameRunMultiplayermodeActivity
import com.example.programtointerfacetest.view.gui.HauptmenuActivity
import com.example.programtointerfacetest.view.gui.QuestionCancelCurrentRoundMultiplayermodeActivity
import com.example.programtointerfacetest.view.gui.SubmitGameRunCancleActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameCancelMultiplayermodeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<GameRunMultiplayermodeActivity>()

    @Before
    fun setup() {
        Intents.init()
    }

    /**
     * User uses the button "Beenden" and enters it finally
     */
    @Test
    fun goodTest() {
        composeTestRule.onNodeWithText("Beenden").performClick()
        intended(hasComponent(QuestionCancelCurrentRoundMultiplayermodeActivity::class.java.name))
        composeTestRule.onNodeWithText("Ja").performClick()
        intended(hasComponent(SubmitGameRunCancleActivity::class.java.name))
        intended(hasComponent(HauptmenuActivity::class.java.name))

    }

}