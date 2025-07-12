package com.example.programtointerfacetest.view.gui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.programtointerfacetest.R
import com.example.programtointerfacetest.ui.theme.ProgramToInterfaceTestTheme

class PopUpFirstPlaceInTopFiveActivity: ComponentActivity() {
    private val modell = ModellImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lightBlue = Color(0xFFA2EEBB)
        setContent {
            ProgramToInterfaceTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                            .background(lightBlue)
                    ) {
                        modell.GetModellProcessSuccessful(
                            "Username",
                            "Herzlichen Glückwunsch zum\n1. Platz im Hightscore",
                            R.drawable.new_hightscore_value
                        )

                        modell.ChangeActivityAutomatically(
                            targetActivity = QuestionStartNewRoundMultiplayermodeActivity::class
                        )
                    }
                }
            }
        }
    }
}