
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import com.example.programtointerfacetest.view.gui.HauptmenuActivity
import com.example.programtointerfacetest.view.gui.LoginActivity
import com.example.programtointerfacetest.view.gui.LogoutCompletedActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LogoutTest {

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
     * after the user clicked on the button logout the current user will logout successfully
     * */
    @Test
    fun goodTest10() {
        composeTestRule.onNodeWithText("Logout").performClick()
        intended(hasComponent(LogoutCompletedActivity::class.java.name))
        Thread.sleep(2000)
        intended(hasComponent(LoginActivity::class.java.name))
    }

 */


}