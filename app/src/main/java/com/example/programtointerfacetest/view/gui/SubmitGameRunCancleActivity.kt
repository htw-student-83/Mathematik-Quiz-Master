package com.example.programtointerfacetest.view.gui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.programtointerfacetest.R
import com.example.programtointerfacetest.control.ControlerSingletonImpl
import com.example.programtointerfacetest.control.GameStateManager
import com.example.programtointerfacetest.control.GameStates
import com.example.programtointerfacetest.ui.theme.ProgramToInterfaceTestTheme

class SubmitGameRunCancleActivity: ComponentActivity() {

    private val modell = ModellImpl()
    private val controler = ControlerSingletonImpl
    private var gamestate = GameStateManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lightBlue = Color(0xFFA2EEBB)
        setContent {
            ProgramToInterfaceTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val controlInstance = controler.getInstance(applicationContext)
                    val loggedUser = remember { controlInstance?.let { mutableStateOf(it.reloadLoggedUserForARunningGame()) } }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                            .background(lightBlue)
                    ) {
                        if (loggedUser != null) {
                            if (controlInstance != null) {
                                modell.GetModellProcessSuccessful(
                                    loggedUser.value,
                                    "die aktuelle Runde wurde erfolgreich abgebrochen.",
                                    R.drawable.successfully_cancled,
                                )
                            }
                        }

                        gamestate.setState(GameStates.LOGGED_IN)

                        modell.ChangeActivityAutomatically(
                            targetActivity = HauptmenuActivity::class
                        )
                    }
                }
            }
        }
    }
}