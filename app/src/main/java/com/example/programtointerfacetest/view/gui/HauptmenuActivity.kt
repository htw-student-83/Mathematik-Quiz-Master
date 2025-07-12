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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.programtointerfacetest.control.GameStateManager
import com.example.programtointerfacetest.control.GameStates
import com.example.programtointerfacetest.ui.theme.ProgramToInterfaceTestTheme
import kotlinx.coroutines.delay

class HauptmenuActivity: ComponentActivity() {

    private val modell = ModellImpl()
    private val controler = ControlerSingletonImpl
    private var gamestate = GameStateManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lightBlue = Color(0xFF80D8FF)
        setContent {
            ProgramToInterfaceTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val controlInstance = controler.getInstance(applicationContext)
                    val loggedUser = remember { controlInstance?.let { mutableStateOf(it.reloadLoggedUserForARunningGame()) } }
                    val errorText = remember { mutableStateOf("") }

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
                            processTopic = "Hauptmenu",
                            backgroundColor = Color(0xFF80D8FF),
                            fontSize = 25.sp
                        )
                        Spacer(modifier = Modifier.height(145.dp))

                        modell.GetModelButton(
                            "Singlemode",
                            backgroundColor = Color(0xFF8090EC),
                            fontcolor = Color.Black,
                            buttonWidth = 200.dp,
                            onClick = {
                                val intent = Intent(
                                    context,
                                    StartButtonGameRunSinglemodeActivity::class.java
                                )
                                context.startActivity(intent)
                            }
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        modell.GetModelButton(
                            "Multiplayermode",
                            backgroundColor = Color(0xFF0091EA),
                            fontcolor = Color.Black,
                            buttonWidth = 200.dp,
                            onClick = {
                                val intent =
                                    Intent(context, QuestionWirelessConnectionActivity::class.java)
                                context.startActivity(intent)
                            }
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        modell.GetModelButton(
                            "Account",
                            backgroundColor = Color(0xFF61C9C2),
                            fontcolor = Color.Black,
                            buttonWidth = 200.dp,
                            onClick = {
                                val intent = Intent(context, AccountActivity::class.java)
                                context.startActivity(intent)
                            }
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        modell.GetModelButton(
                            "Top Five",
                            backgroundColor = Color(0xFF64DD17),
                            fontcolor = Color.Black,
                            buttonWidth = 200.dp,
                            onClick = {
                                val intent = Intent(context, TopFiveActivity::class.java)
                                context.startActivity(intent)
                            }
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        modell.GetModelButton(
                            "Logout",
                            backgroundColor = Color(0xDAE85555),
                            fontcolor = Color.Black,
                            buttonWidth = 200.dp,
                            onClick = {
                                if (controlInstance != null) {
                                    val sharedPref = getSharedPreferences("USERID", Context.MODE_PRIVATE)
                                    val userID = sharedPref.getLong("UID", 1L)
                                    val result = controlInstance.userLogout(userID)
                                    if (result) {
                                        gamestate.setState(GameStates.LOGGED_OUT)
                                        val intent = Intent(context, LogoutCompletedActivity::class.java)
                                        if (loggedUser != null) {
                                            intent.putExtra("UNAME", loggedUser.value)
                                        }
                                        context.startActivity(intent)
                                    } else {
                                        errorText.value = result.toString()
                                    }
                                }
                            }
                        )

                            Spacer(modifier = Modifier.height(5.dp))

                            modell.GetModelButton(
                                "Account löschen",
                                backgroundColor = Color(0xF3986C4A),
                                fontcolor = Color.Black,
                                buttonWidth = 200.dp,
                                onClick = {
                                    val sharedPref = getSharedPreferences("USERID", Context.MODE_PRIVATE)
                                    val userID = sharedPref.getLong("UID", 1L)
                                    val intent = Intent(context, QuestionAccountDeletingActivtiy::class.java)
                                    intent.putExtra("UID", userID)
                                    context.startActivity(intent)
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
                    }
                }
            }
        }
    }
}