package com.example.programtointerfacetest.view.gui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.programtointerfacetest.ui.theme.ProgramToInterfaceTestTheme

class QuestionStartNewRoundMultiplayermodeActivity:ComponentActivity() {
    private val modell = ModellImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lightBlue = Color(0xFF80D8FF)
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
                        val context = LocalContext.current

                        Spacer(modifier = Modifier.padding(35.dp))

                        modell.GetModelNameOfQuiz(backgroundColor = Color(0xFF80D8FF))

                        Spacer(modifier = Modifier.padding(15.dp))

                        modell.GetModelTextProcess(
                            processTopic = "Multiplayermode",
                            backgroundColor = Color(0xFF80D8FF),
                            fontSize = 25.sp
                        )
                        Spacer(modifier = Modifier.height(165.dp))

                        modell.GetModelQuestion(
                            "Soll eine neue Runde gestartet werden?",
                            backgroundColor = Color(0xFF80D8FF),
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        modell.GetModelButton(
                            "Ja",
                            backgroundColor = Color(0x7E888787),
                            fontcolor = Color.Black,
                            buttonWidth = 200.dp,
                            onClick = {
                                val intent = Intent(context, GameRunMultiplayermodeActivity::class.java)
                                context.startActivity(intent)
                            }
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        modell.GetModelButton(
                            "Nein",
                            backgroundColor = Color(0xFF728AF1),
                            fontcolor = Color.Black,
                            buttonWidth = 200.dp,
                            onClick = {
                                val intent = Intent(context, HauptmenuActivity::class.java)
                                context.startActivity(intent)
                            }
                        )
                    }
                }
            }
        }
    }
}