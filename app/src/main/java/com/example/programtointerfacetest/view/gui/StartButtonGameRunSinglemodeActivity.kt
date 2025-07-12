package com.example.programtointerfacetest.view.gui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.programtointerfacetest.control.GameStateManager
import com.example.programtointerfacetest.control.GameStates
import com.example.programtointerfacetest.ui.theme.ProgramToInterfaceTestTheme

class StartButtonGameRunSinglemodeActivity: ComponentActivity() {

    private val modell = ModellImpl()
    private var gamestate = GameStateManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lightBlue = Color(0xFFA8F3C4)
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
                        Spacer(modifier = Modifier.padding(35.dp))

                        modell.GetModelNameOfQuiz(backgroundColor = Color(0xFFA8F3C4))

                        Spacer(modifier = Modifier.padding(15.dp))

                        modell.GetModelTextProcess(
                            processTopic = "Singlemode",
                            backgroundColor = Color(0xFFA8F3C4),
                            fontSize = 25.sp
                        )
                        Spacer(modifier = Modifier.height(165.dp))

                        GameStartForSinglemode()

                    }
                }
            }
        }
    }

    @Composable
    fun GameStartForSinglemode(modifier: Modifier = Modifier) {
        var showTimer by remember { mutableStateOf(false) }
        val lightGreen = Color(0xFF35F17D)
        val context = LocalContext.current
        Box(
            modifier = modifier
                .fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally)
                .background(color = lightGreen, shape = CircleShape)
                .size(100.dp)
                .clickable {
                    showTimer = true
                }
                .border(
                    width = 2.dp,
                    color = Color(0xFF35F17D),
                    shape = CircleShape
                ),
        ) {
            Text(
                text = "Start",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 22.dp, vertical = 22.dp)
                    .clickable {
                        gamestate.setState(GameStates.GAME_ROUND_RUNNING)
                        val intent = Intent(context, GameRunSinglemodeActivity::class.java)
                        context.startActivity(intent)
                    }
            )

        }
    }

}