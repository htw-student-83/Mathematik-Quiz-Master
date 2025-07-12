package com.example.programtointerfacetest.view.gui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.programtointerfacetest.R
import com.example.programtointerfacetest.control.ControlerSingletonImpl
import com.example.programtointerfacetest.ui.theme.ProgramToInterfaceTestTheme

class GratulationEndOfARoundSinglemodeActivity: ComponentActivity(){
    private val modell = ModellImpl()
    private val controler = ControlerSingletonImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lightBlue = Color(0xFFA2EEBB)
        setContent {
            ProgramToInterfaceTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val controlInstance = controler.getInstance(applicationContext)
                    val loggedUser = remember { controlInstance?.let { mutableStateOf(it.reloadLoggedUserForARunningGame()) } }
                    val score by remember { mutableIntStateOf(0) }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                            .background(lightBlue)
                    ) {
                        if (controlInstance != null) {

                        }
                        if (loggedUser != null) {
                            if (controlInstance != null) {
                                modell.GetModellProcessSuccessful(
                                    loggedUser.value,
                                    "du hast in der aktuellen Runde ${controlInstance.loadCurrentPoints()} Punkte erspielt.",
                                    R.drawable.successful_round_singlemode,
                                )
                            }
                        }

                        modell.ChangeActivityAutomatically(
                            targetActivity = QuestionNewRoundSinglemodeActivity::class
                        )
                    }
                }
            }
        }
    }
}