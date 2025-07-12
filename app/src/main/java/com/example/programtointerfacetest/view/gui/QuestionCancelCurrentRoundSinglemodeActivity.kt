package com.example.programtointerfacetest.view.gui

import android.content.Context
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.programtointerfacetest.control.ControlerSingletonImpl
import com.example.programtointerfacetest.control.GameStateManager
import com.example.programtointerfacetest.control.GameStates
import com.example.programtointerfacetest.ui.theme.ProgramToInterfaceTestTheme

class QuestionCancelCurrentRoundSinglemodeActivity: ComponentActivity() {

    private val modell = ModellImpl()
    private val controler = ControlerSingletonImpl
    private var gamestate = GameStateManager()

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
                            processTopic = "Singlemode",
                            backgroundColor = Color(0xFF80D8FF),
                            fontSize = 25.sp
                        )
                        Spacer(modifier = Modifier.height(165.dp))

                        modell.GetModelQuestion(
                            "Soll das Training wirklich beendet werden?",
                            backgroundColor = Color(0xFF80D8FF),
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        modell.GetModelButton(
                            "Ja",
                            backgroundColor = Color(0x7E888787),
                            fontcolor = Color.Black,
                            buttonWidth = 200.dp,
                            onClick = {
                                gamestate.setState(GameStates.GAME_ROUND_CANCELED)
                                controler.getInstance(applicationContext)?.resetCurrentPoints()
                                val sharedPrefUSERPOINTS = context.getSharedPreferences("USERPOINTS", Context.MODE_PRIVATE)
                                sharedPrefUSERPOINTS.edit().remove("UPOINTS").apply()
                                val sharedPrefTimmer = getSharedPreferences("TIMER_PREFS", Context.MODE_PRIVATE)
                                sharedPrefTimmer.edit().putBoolean("IS_RUNNING", false).apply()
                                val intent = Intent(context, SubmitGameRunCancleActivity::class.java)
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
                                gamestate.setState(GameStates.GAME_ROUND_RUNNING)
                                val intent = Intent(context, GameRunSinglemodeActivity::class.java)
                                intent.putExtra("ISCOMING", true)
                                context.startActivity(intent)
                            }
                        )
                    }
                }
            }
        }
    }
}