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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.programtointerfacetest.control.ControlerSingletonImpl
import com.example.programtointerfacetest.ui.theme.ProgramToInterfaceTestTheme
import kotlinx.coroutines.delay
import androidx.core.content.edit
import com.example.programtointerfacetest.control.GameStateManager
import com.example.programtointerfacetest.control.GameStates

class GameRunSinglemodeActivity: ComponentActivity() {
    private val modell = ModellImpl()
    private val controler = ControlerSingletonImpl
    private var gamestate = GameStateManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lightBlue = Color(0xFFA8F3C4)
        setContent {
            ProgramToInterfaceTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val context = LocalContext.current
                    val errorText = remember { mutableStateOf("") }
                    var score by remember { mutableIntStateOf(0) }
                    val controlInstance = controler.getInstance(applicationContext)
                    val exercise = remember { mutableStateOf(controlInstance?.loadNewExercise()) }
                    val loggedUser = remember { controlInstance?.let { mutableStateOf(it.reloadLoggedUserForARunningGame()) } }
                    val isRunning = remember { mutableStateOf(true) }

                    val sharedPrefTimer = context.getSharedPreferences("TIMER", Context.MODE_PRIVATE)
                    val isRunningState = remember { mutableStateOf(sharedPrefTimer.getBoolean("IS_RUNNING", true)) }

                    val sharedPrefUSERPOINTS = context.getSharedPreferences("USERPOINTS", Context.MODE_PRIVATE)

                    val userpoints = remember { mutableStateOf(0) }

                    val rundIsGoingOn = intent.getBooleanExtra("ISCOMING", true)

                    val maxValidSolution = 200

                    val minValidSolution = 11

                    LaunchedEffect(isRunningState.value) {
                        sharedPrefTimer.edit { putBoolean("IS_RUNNING", isRunningState.value) }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                            .background(lightBlue)
                    ) {

                        Spacer(modifier = Modifier.height(35.dp))

                        modell.GetModelNameOfQuiz(
                            backgroundColor = Color(0xFFA8F3C4)
                        )

                        Spacer(modifier = Modifier.height(25.dp))

                        modell.GetModelTextProcess(
                            processTopic = "Singlemode",
                            backgroundColor = Color(0xFFA8F3C4),
                            fontSize = 25.sp
                        )

                        Spacer(modifier = Modifier.height(25.dp))

                        modell.StartTimer(
                            20,
                            isRunning = isRunning.value,
                            onToggle = { isRunning.value = !isRunning.value },
                            onTimeout = {
                                isRunning.value = false
                                gamestate.setState(GameStates.GAME_ROUND_COMPLETED)
                                val intent = Intent(context, GratulationEndOfARoundSinglemodeActivity::class.java)
                                context.startActivity(intent)
                            }
                        )

                        Spacer(modifier = Modifier.height(145.dp))

                        var input by remember { mutableStateOf("") }

                        modell.GetModellCreateExercise(
                            exercise.value,
                            input = input,
                            onTextChange = { input = it }
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        modell.GetModelButton(
                            "Bestätigen",
                            backgroundColor = Color(0xFF00C853),
                            fontcolor = Color.Black,
                            buttonWidth = 200.dp,
                            onClick = {
                                if (controlInstance != null) {
                                    if(input.isNotEmpty()){
                                        val userInputConvertToInt = input.toInt()
                                        if(userInputConvertToInt in minValidSolution..maxValidSolution) {
                                            val result = controlInstance.checkUserAnswer(userInputConvertToInt)
                                            if (result) {
                                                score = controlInstance.loadCurrentPoints()
                                                sharedPrefUSERPOINTS.edit { putInt("UPOINTS", score)}
                                                userpoints.value = score
                                                exercise.value = controlInstance.loadNewExercise()
                                                input = ""
                                            } else {
                                                exercise.value = controlInstance.loadNewExercise()
                                                input = ""
                                            }
                                        }else{
                                            errorText.value = "Your input is invalid."
                                        }
                                    }else{
                                        errorText.value = "The field must be filled."
                                    }
                                }
                            }
                        )

                        // Zeige Fehlertext in Compose-UI an
                        if (errorText.value.isNotEmpty()) {
                            LaunchedEffect(errorText.value) {
                                delay(3000) // 3000 Millisekunden = 3 Sekunden
                                errorText.value = ""
                            }

                            Text(
                                text = errorText.value,
                                color = Color.Red,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.Bold,
                                ),
                                modifier = Modifier
                                    .testTag("errorText")
                                    .offset(x = 80.dp, y = 5.dp)
                                    .semantics {}
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        modell.GetModelButton(
                            "Beenden",
                            backgroundColor = Color(0xFFBDBCBC),
                            fontcolor = Color.Black,
                            buttonWidth = 200.dp,
                            onClick = {
                                isRunning.value = false
                                gamestate.setState(GameStates.GAME_ROUND_STOP)
                                val intent = Intent(context, QuestionCancelCurrentRoundSinglemodeActivity::class.java)
                                context.startActivity(intent)
                            }
                        )

                        Spacer(modifier = Modifier.height(100.dp))

                        if (loggedUser != null) {
                            modell.GetModellDetailsOfRound(
                                topic = "Angemeldet:",
                                value = loggedUser.value,
                                backgroundColor = Color(0xFFA8F3C4),
                                5,
                                -18
                            )
                        }

                        Spacer(modifier = Modifier.height(2.dp))

                        LaunchedEffect(Unit) {
                            if(!rundIsGoingOn){
                                sharedPrefUSERPOINTS.edit().putInt("UPOINTS", 0).apply()
                                userpoints.value = 0
                            }else{
                                userpoints.value = sharedPrefUSERPOINTS.getInt("UPOINTS", 0)
                            }

                        }

                        modell.GetModellDetailsOfRound(
                            topic = "Laufende Punkte:",
                            value = userpoints.value.toString(),
                            backgroundColor = Color(0xFFA8F3C4),
                            29,
                            -18
                        )

                    }
                }
            }
        }
    }
}