package com.example.programtointerfacetest.view.gui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.programtointerfacetest.control.ControlerSingletonImpl
import com.example.programtointerfacetest.ui.theme.ProgramToInterfaceTestTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.programtointerfacetest.control.GameStateManager
import com.example.programtointerfacetest.control.GameStates
import kotlinx.coroutines.delay

class LoginActivity: ComponentActivity() {

    private val modell = ModellImpl()
    private val controler = ControlerSingletonImpl
    private var gamestate = GameStateManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lightBlue = Color(0xFF80D8FF)
        setContent {
            ProgramToInterfaceTestTheme {
                var pinText by remember { mutableStateOf("") }
                val maxInputLength = 20
                val errorText = remember { mutableStateOf("") }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                            .background(lightBlue)
                    ) {
                        Spacer(modifier = Modifier.padding(35.dp))

                        modell.GetModelNameOfQuiz(backgroundColor = Color(0xFF80D8FF))

                        Spacer(modifier = Modifier.padding(15.dp))

                        modell.GetModelTextProcess(
                            processTopic = "Login",
                            backgroundColor = Color(0xFF80D8FF),
                            fontSize = 25.sp
                        )
                        Spacer(modifier = Modifier.height(155.dp))

                        modell.GetModelEditFieldForLogin(
                            image = Icons.Filled.Lock,
                            onTextChange = { pinText = it },
                            placeholder = "password...",
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            modifier = Modifier
                                .width(250.dp)
                                .align(Alignment.CenterHorizontally) // z. B. in Column
                                .padding(bottom = 3.dp, top = 3.dp)
                                .testTag("EditFieldTest")
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        val context = LocalContext.current

                        modell.GetModelButton(
                            "login",
                            backgroundColor = Color(0xFF8FE065),
                            fontcolor = Color.Black,
                            buttonWidth = 200.dp,
                            onClick = {
                                val controlInstance = controler.getInstance(applicationContext)
                                if (controlInstance != null) {
                                    //Here the instance of controler is called
                                    val result = controlInstance.checkUserPassword(pinText)
                                    if(result == "success"){
                                        gamestate.setState(GameStates.LOGGED_IN)
                                        val intent = Intent(context, HauptmenuActivity::class.java)
                                        context.startActivity(intent)
                                    }else{
                                        errorText.value = result
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
                              //  color = Color.Red,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFC9D0B21)
                                ),
                                modifier = Modifier
                                    .testTag("errorText")
                                    .offset(x = 80.dp, y = (95).dp)
                                    .semantics {}
                                    .alpha(if (errorText.value.isNotEmpty()) 1f else 0f)
                            )
                        }
                        Spacer(modifier = Modifier.height(25.dp))

                        TextNewAccount(modifier = Modifier.height(25.dp))
                    }
                }
            }
        }
    }

    @Composable
    fun TextNewAccount(modifier: Modifier) {
        val context = LocalContext.current
        Box(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Noch keinen Account?",
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(start = 80.dp)
            )
            Text(
                text = "Registrieren",
                fontSize = 12.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 215.dp)
                    .clickable {
                        val intent = Intent(context, RegistrationActivity::class.java)
                        context.startActivity(intent)
                    }
                    .testTag("RegistrationTest")
            )
        }
    }
}
