package com.example.programtointerfacetest.view.gui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.programtointerfacetest.R
import com.example.programtointerfacetest.control.ControlerSingletonImpl
import com.example.programtointerfacetest.ui.theme.ProgramToInterfaceTestTheme

class GameRunMultiplayermodeActivity: ComponentActivity() {
    private val modell = ModellImpl()
    private val controler = ControlerSingletonImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lightBlue = Color(0xFFA8F3C4)
        setContent {
            ProgramToInterfaceTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var clicked by remember { mutableStateOf(false) }
                    val controlInstance = controler.getInstance(applicationContext)
                    val exercise = remember { mutableStateOf(controlInstance?.loadNewExercise()) }
                    var isRunning by remember { mutableStateOf(true) }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                            .background(lightBlue)
                    ) {
                        val context = LocalContext.current

                        Spacer(modifier = Modifier.height(35.dp))

                        modell.GetModelNameOfQuiz(
                            backgroundColor = Color(0xFFA8F3C4)
                        )

                        Spacer(modifier = Modifier.height(25.dp))

                        modell.GetModelTextProcess(
                            processTopic = "Multiplayermode",
                            backgroundColor = Color(0xFFA8F3C4),
                            fontSize = 25.sp
                        )

                        Spacer(modifier = Modifier.height(25.dp))

                        modell.StartTimer(
                            60,
                            true,
                            onToggle = { isRunning = !isRunning },
                            onTimeout = {

                            })

                        Spacer(modifier = Modifier.height(125.dp))

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
                                //TODO to implement the algorithm
                                clicked = true
                            }
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        modell.GetModelButton(
                            "Beenden",
                            backgroundColor = Color(0xFFBDBCBC),
                            fontcolor = Color.Black,
                            buttonWidth = 200.dp,
                            onClick = {
                                val intent = Intent(context, QuestionCancelCurrentRoundMultiplayermodeActivity::class.java)
                                context.startActivity(intent)
                            }
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        WirelessConnection(modifier = Modifier)

                        Spacer(modifier = Modifier.height(20.dp))

                        modell.GetModellDetailsOfRound(
                            topic = "Angemeldet:",
                            value = "Test User",
                            backgroundColor = Color(0xFFA8F3C4),
                            -10,
                            3
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        modell.GetModellDetailsOfRound(
                            topic = "Eigene Punkte:",
                            value = "0",
                            backgroundColor = Color(0xFFA8F3C4),
                            -1,
                            -19
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        modell.GetModellDetailsOfRound(
                            topic = "Punkte Gegner:",
                            value = "0",
                            backgroundColor = Color(0xFFA8F3C4),
                            1,
                            -20
                        )
                    }
                }
            }
        }
    }
/*
    @Composable
    fun GetModellLCountdown(
        totalTimeSeconds: Int
    ) {
        val context = LocalContext.current
        var timeLeft by remember { mutableIntStateOf(totalTimeSeconds) }

        LaunchedEffect(key1 = true) {
            while (timeLeft > 0) {
                delay(1000L)
                timeLeft = timeLeft - 1 // <- korrekt den State aktualisieren!
            }
            // Wenn Countdown bei 0 ankommt, Toast anzeigen (einmalig)
            Toast.makeText(context, "The time is over", Toast.LENGTH_SHORT).show()
        }

        val minutes = timeLeft / 60
        val seconds = timeLeft % 60
        val formattedTime = String.format("%02d:%02d", minutes, seconds)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = formattedTime,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

 */


    @Composable
    fun WirelessConnection(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.icon_wifi_direct), // dein Bild
                    contentDescription = "Background Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .padding(bottom = 10.dp)
                )
            }
        }
    }
}