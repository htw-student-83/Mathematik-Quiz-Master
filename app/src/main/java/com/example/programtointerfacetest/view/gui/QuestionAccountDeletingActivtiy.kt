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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.programtointerfacetest.control.ControlerSingletonImpl
import com.example.programtointerfacetest.ui.theme.ProgramToInterfaceTestTheme

class QuestionAccountDeletingActivtiy: ComponentActivity() {
    private val modell = ModellImpl()
    private val controler = ControlerSingletonImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lightBlue = Color(0xFF80D8FF)
        setContent {
            ProgramToInterfaceTestTheme {
                val controlInstance = controler.getInstance(applicationContext)
                val loggedUser = remember { controlInstance?.let { mutableStateOf(it.reloadLoggedUserForARunningGame()) } }
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
                            processTopic = "Account löschen",
                            backgroundColor = Color(0xFF80D8FF),
                            fontSize = 25.sp
                        )
                        Spacer(modifier = Modifier.height(165.dp))

                        modell.GetModelQuestion(
                            "Willst du deinen Account wirklich löschen?",
                            backgroundColor = Color(0xFF80D8FF),
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        modell.GetModelButton(
                            "Ja",
                            backgroundColor = Color(0x7E888787),
                            fontcolor = Color.Black,
                            buttonWidth = 200.dp,
                            onClick = {
                                val userID = intent.getLongExtra("UID",  -1L)
                                val controlInstance = controler.getInstance(applicationContext)
                                if (controlInstance != null) {
                                    if(controlInstance.userAccountDelete(userID)){
                                        val sharedPrefUSERPOINTS = context.getSharedPreferences("USERPOINTS", Context.MODE_PRIVATE)
                                        sharedPrefUSERPOINTS.edit().remove("UPOINTS").apply()
                                        val intent = Intent(context, AccountDeletingActivity::class.java)
                                        if (loggedUser != null) {
                                            intent.putExtra("UNAME", loggedUser.value)
                                        }
                                        context.startActivity(intent)
                                    }
                                }
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