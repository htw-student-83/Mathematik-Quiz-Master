package com.example.programtointerfacetest.view.gui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.accounts.AccountCreationResult
import com.example.programtointerfacetest.control.ControlerSingletonImpl
import com.example.programtointerfacetest.control.GameStateManager
import com.example.programtointerfacetest.control.GameStates
import com.example.programtointerfacetest.ui.theme.ProgramToInterfaceTestTheme
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RegistrationActivity : ComponentActivity(){

    private val modell = ModellImpl()
    private val controler = ControlerSingletonImpl
    private var gamestate = GameStateManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lightBlue = Color(0xFF80D8FF)
        setContent {
            ProgramToInterfaceTestTheme {
                var pinTextForFirstname by remember { mutableStateOf("") }
                var pinTextForLastname by remember { mutableStateOf("") }
                var pinTextForPassword by remember { mutableStateOf("") }

                val context = LocalContext.current
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

                        modell.GetModelNameOfQuiz(
                            backgroundColor = Color(0xFF80D8FF)
                        )

                        Spacer(modifier = Modifier.padding(15.dp))

                        modell.GetModelTextProcess(
                            processTopic = "Registration",
                            backgroundColor = Color(0xFF80D8FF),
                            fontSize = 25.sp
                        )
                        Spacer(modifier = Modifier.height(85.dp))

                        modell.GetModelEditField(
                            image = Icons.Filled.Person,
                            onTextChange = {pinTextForFirstname = it} ,
                            placeholder = "firstname...",
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        modell.GetModelEditField(
                            image = Icons.Filled.Person,
                            onTextChange = {pinTextForLastname = it} ,
                            placeholder = "lastname...",
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        modell.GetModelEditFieldForLogin(
                            image = Icons.Filled.Lock,
                            onTextChange = { pinTextForPassword = it },
                            placeholder = "password...",
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            modifier = Modifier
                                .width(250.dp)
                                .align(Alignment.CenterHorizontally) // z. B. in Column
                                .padding(bottom = 3.dp, top = 3.dp)
                                .testTag("EditFieldTest")
                        )

                        Spacer(modifier = Modifier.height(25.dp))

                        modell.GetModelButton(
                            "Account erstellen",
                            backgroundColor = Color(0xFF8FE065),
                            fontcolor = Color.Black,
                            buttonWidth = 200.dp,
                            onClick = {
                                val today = Calendar.getInstance().apply {
                                    set(Calendar.HOUR_OF_DAY, 0)
                                    set(Calendar.MINUTE, 0)
                                    set(Calendar.SECOND, 0)
                                    set(Calendar.MILLISECOND, 0)
                                }.time
                                //TODO evaluate the whole input before the controler will get the new data
                                val controlInstance = controler.getInstance(applicationContext)
                                if (controlInstance != null) {
                                    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                                    val todayFormatted = dateFormat.format(today)
                                    //Here the instance of controler is called
                                    val resultForRegistration = controlInstance.checkUerDataForRegistration2(
                                        pinTextForFirstname, pinTextForLastname, pinTextForPassword,
                                        0, false, todayFormatted)
                                    when(resultForRegistration) {
                                        is AccountCreationResult.Success -> {
                                            gamestate.setState(GameStates.REGISTERED)
                                            val fullname = "$pinTextForFirstname $pinTextForLastname"
                                            Log.d("Registration", fullname)
                                            val sharedPrefUSERID =  context.getSharedPreferences("USERID", Context.MODE_PRIVATE)
                                            sharedPrefUSERID.edit().putLong("UID", resultForRegistration.userId).apply()
                                            val intent = Intent(context, RegistrationCompletedAcitvity::class.java)
                                            intent.putExtra("USERNAME", fullname)
                                            context.startActivity(intent)
                                        }

                                        is AccountCreationResult.Error -> {
                                            errorText.value = resultForRegistration.message
                                        }
                                    }
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        modell.GetModelButton(
                            "Abbruch",
                            backgroundColor = Color(0xFFBBBBB9),
                            fontcolor = Color.Black,
                            buttonWidth = 200.dp,
                            onClick = {
                                val intent = Intent(context, LoginActivity::class.java)
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
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFC9D0B21)
                                ),
                                modifier = Modifier
                                    .testTag("errorText")
                                    .offset(x = 80.dp, y = (25).dp)
                                    .semantics {}
                            )
                        }
                    }
                }
            }
        }
    }
}